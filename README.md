# Harbor
[harbor](https://github.com/vmware/harbor) is the enterprise-class registry server for docker distribution.

# harbor-java-client
Harbor Java Client uses Spring boot and okhttpclient to expose Harbor REST API via HarborJavaClient.

# Usage
~~~
/** Constructor
 * public HarborClient(String baseUrl) // default verifySSL is false 
 * public HarborClient(String baseUrl, boolean verifySSL) 
 **/
 
//1) Creat harbor client
HarborClient harborClient = new HarborClient(baseUrl);

//2) login
harborClient.login(username, password);

//3) Call other API

//n) log out
harborClient.logout();

~~~

# Implemented Harbor API 

## Basic
* Login:  post /login
* Logout:  get  /log_out
* Search:  get /search
* Get Logs:  get /logs
* Get Statistics:  get /statistics

## Project
* Get Projects:  get /projects
* Check Projects:  head /projects
* Create Projects:  post /projects
* Set publicity status:  put /projects/{project_id}/publicity
* Search Logs:  post /projects/{project_id}/logs/filter
* Search role members:  get /projects/{project_id}/members/
* Add members:  post /projects/{project_id}/members/
* Remove members:  delete /projects/{project_id}/members/{user_id}
* Get role members:  get /projects/{project_id}/members/{user_id}
* Update members:  put /projects/{project_id}/members/{user_id}

## Users
* Create user:  post /users
* Remove user:  delete /users/{user_id}      (admin only)

## Repositories API
* Search repositories :  get /repositories
* Get repository tags:  get /repositories/tags
* Get manifests :  get /repositories/manifests
* Get top repositories:  get /repositories/top
* Remove repositories :  delete /repositories

# Not implemented Harbor API

考虑到安全性及方便性，以下API暂时没有实现

* Get Users: get /users
* Update User: put /users/{user_id}
* Update Password: put /users/{user_id}/password
* Upgrade to sysadmin: put /users/{user_id}/sysadmin

# HarborClient详细说明
创建harborClient对象之后，就可以直接调用需要的函数和harbor交互了。

### 1.Login
  
	int code = harborClient.login(name, password);
 
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
name | Y  | String | 用户名 |
password | Y  | String | 密码 |

返回值| 类型| 说明|
------|-----|----|
code | Integer | 状态码|

### 2.Logout

	int code = harborClient.logout();

返回值| 类型| 说明|
------|-----|----|
code | Integer | 状态码|

### 3. Basic Search 

	Search search = harborClient.search(q);
	
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
q | Y  | String | 目标任意关键字如项目ID、项目名、仓库名等 |

返回值| 类型| 说明|
------|-----|----|
search | Search | 列表展示搜索到的项目或仓库|

### 4. Get Logs

	List<Log> logs = harborClient.getLogs(lines, start_time, end_time);
	
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
lines | N  | Integer | 显示的log行数，默认十行 |
start_time | N  | Long | log开始时间（unix timestap） |
end_time | N  | Long | log结束时间（unix timestap） |

非必填项可传入null；

返回值| 类型| 说明|
------|-----|----|
logs | List<Log> | log列表|

### 5. Get Statistics

	ProjectAndRepoNum projectAndRepoNum = harborClient.getStatistics(); 
	
返回值| 类型| 说明|
------|-----|----|
projectAndRepoNum | ProjectAndRepoNum | 用户相关的项目和仓库数目|

### 6. Get Projects

	List<Project> projects = harborClient.getProjects(projectName, isPublic);

参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
projectName | N  | String | 项目名 |
isPublic | N  | Integer | 是否公开 |

返回值| 类型| 说明|
------|-----|----|
projects | List<Project> | 项目列表|


### 7. Check Projects

	String res = harborClient.checkProject(projectName);
	
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
projectName | N  | String | 项目名 |

返回值| 类型| 说明|
------|-----|----|
res | String | 请求结果状态|

请求结果状态如下：

HTTP Status Code| Reason | 
------|-----|
200|Project name exists|
401|User need to log in first|
404|Project name does not exist|
500|Unexpected internal errors|
default|Unknown|

### 8. Create Projects
		
	Project pro=new Project();
	pro.setName("proname");
	pro.setPublic(false);
	String res = harborClient.createProject(pro);	
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
pro | Y  | Project | 需要创建的项目内容 |

返回值| 类型| 说明|
------|-----|----|
res | String | 请求结果状态|

请求结果状态如下：

HTTP Status Code| Reason | 
------|-----|
201|Project created successfully|
400|Unsatisfied with constraints of the project creation|
401|User need to log in first|
409|Project name already exists|
500|Unexpected internal errors|
default|Unknown|

### 9. Set Publicity
	int id = 17;
	Project project=new Project();
	project.setPublic(true);
	String res = harborClient.setPublicity(id, project);	
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
id | Y  | integer | 项目ID |
project | Y  | Project | 设置项目是否公开 |

返回值| 类型| 说明|
------|-----|----|
res | String | 请求结果状态|

请求结果状态如下：

HTTP Status Code| Reason | 
------|-----|
200|Updated project publicity status successfully|
400|Illegal format of provided ID value|
401|User need to log in first|
403|User does not have permission to the project|
404|Project ID does not exist|
500|Unexpected internal errors|
default|Unknown|

### 10. Search Logs With Filter

	int id = 17;
	Log accessLog =new Log();
	accessLog.setKeywords("create/pull/push/delete/jessia");
	accessLog.setProjectId(17);
	Object res = harborClient.filterLog(id, accessLog);
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
id | Y  | integer | 项目ID |
accessLog | Y  | Log | 查询内容 |

返回值| 类型| 说明|
------|-----|----|
res | Object | 请求结果状态|

请求结果状态如下：

HTTP Status Code| Reason | 
------|-----|
200|以列表形式返回查找结果|
400|Illegal format of provided ID value|
401|User need to log in first|
500|Unexpected internal errors|
default|Unknown|

### 11.Add Member

	int id = 22;
	MemberConf memberConf =new MemberConf();
	List<Integer> role=new ArrayList<>();
	role.add(3);
	memberConf.setRoles(role);
	memberConf.setUsername("chufuyuan");
	Object res = harborClient.addMember(id, memberConf);
	
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
id | Y  | integer | 项目ID |
memberConf | Y  | MemberConf | 需要添加的成员信息 |

返回值| 类型| 说明|
------|-----|----|
res | Object | 请求结果状态|

### 12.Get Role Members With ProjectID
	List<ProjectMember> projectMembers = harborClient.getProjectMembers(projectId);
	
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
projectId | Y  | integer | 项目ID |

返回值| 类型| 说明|
------|-----|----|
projectMembers | List<ProjectMember> | 项目相关成员信息|
	
### 13.Delete Member
	int pid = 22;
	int uid = 3;
	String res = harborClient.deleteMember(pid, uid);
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
pid | Y  | integer | 项目ID |
uid | Y  | integer | 用户ID |

返回值| 类型| 说明|
------|-----|----|
res | String | 请求结果状态|
	
请求结果状态如下：

HTTP Status Code| Reason | 
------|-----|
200|Project role members deleted successfully|
400|Illegal format of provided ID value|
401|User need to log in first|
403|User in session does not have permission to the project|
404|Project ID does not exist|
500|Unexpected internal errors|
default|Unknown|
	
### 14.Get Role Members With Project & User

	Member member = harborClient.getRole(project_id, user_id);
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
project_id | Y  | integer | 项目ID |
user_id | Y  | integer | 用户ID |

返回值| 类型| 说明|
------|-----|----|
member | Member | 查询的用户角色信息|

### 15.Update Member

	int pid = 17;
	int uid = 3;
	MemberConf memberConf =new MemberConf();
	List<Integer> role=new ArrayList<>();
	role.add(2);
	memberConf.setRoles(role);
	memberConf.setUsername("misha");
	String res = harborClient.updateMember(pid, uid, memberConf);	
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
pid | Y  | integer | 项目ID |
uid | Y  | integer | 用户ID |
memberConf | Y  | MemberConf | 要修改的角色信息 |

返回值| 类型| 说明|
------|-----|----|
res | String | 请求结果状态|
	
请求结果状态如下：

HTTP Status Code| Reason | 
------|-----|
200|Project role members updated successfully|
400|Illegal format of provided ID value|
401|User need to log in first|
403|User in session does not have permission to the project|
404|Project ID does not exist|
500|Unexpected internal errors|
default|Unknown|

### 16.Create User
	ProjectMember projectMember =new ProjectMember();
	projectMember.setUsername("misha");	
	projectMember.setEmail("YourEmail@xxx");
	projectMember.setPassword("PassWord");
	projectMember.setRealname("YourName");
	String res = harborClient.createUser(projectMember);
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
projectMember | Y  | ProjectMember | 创建的用户信息 |


返回值| 类型| 说明|
------|-----|----|
res | String | 请求结果状态|
	
请求结果状态如下：

HTTP Status Code| Reason | 
------|-----|
200|User created successfully|
400|Unsatisfied with constraints of the user creation|
403|User registration can only be used by admin role user when self-registration is off|
409|username has already been used|
500|Unexpected internal errors|
default|Unknown|

### 17.Delete User

	int userId = 1;
	String res = harborClient.deleteUser(userId);

参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
userId | Y  | String | 要删除的用户ID |


返回值| 类型| 说明|
------|-----|----|
res | String | 请求结果状态|
	
请求结果状态如下：

HTTP Status Code| Reason | 
------|-----|
200|Marked user as be removed successfully|
400|Invalid user ID|
401|User need to log in first|
403|User does not have permission of admin role|
404|User ID does not exist|
500|Unexpected internal errors|
default|Unknown|

### 18.Get Repositories
	List<String> res = harborClient.getRepositories(projectId, q);

参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
projectId | Y  | Integer | 项目ID |
q | N  | String | 可用于过滤的仓库名 |

返回值| 类型| 说明|
------|-----|----|
res | List | 查询的仓库列表|

### 19.Get Repositorie Tags

	List<String> res = harborClient.getRepositorieTags(repoName);
	
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
repoName | Y  | String | 仓库名称 |

返回值| 类型| 说明|
------|-----|----|
res | List | 查询的仓库标签列表|

### 20.Get Manifest

	Manifest manifest = harborClient.getManifest(repoName, tag);
	
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
repoName | Y  | String | 仓库名称 |
tag | Y  | String | 仓库标签 |

返回值| 类型| 说明|
------|-----|----|
manifest | Manifest | 清单列表|

### 21.Get Top Repositories

	List<PopRepo> popRepos = harborClient.getTopRepositories(count);
	
参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
count | N  | Integer | 查询的仓库数目 默认为10 |

返回值| 类型| 说明|
------|-----|----|
popRepos | List | 热门仓库列表|

### 22.Delete Repositories
	String repoName = "misha/debian1";
	String tag =null;
	String res =  harborClient.deleteRepositories(repoName, tag);

参数名 | 是否必填 | 类型 | 说明 |
------------ | ------------- | ------------ | ------------
repoName | Y  | String | 仓库名称 |
tag | N  | String | 仓库标签 |

返回值| 类型| 说明|
------|-----|----|
res | String | 请求结果状态|
	
请求结果状态如下：

HTTP Status Code| Reason | 
------|-----|
200|Delete repository or tag successfully|
400|Invalid repo_name|
401|Unauthorized|
403|Forbidden|
404|Repository or tag not found|
default|Unknown|

# Author
Please contcat Grissom(@grissomsh) and Misha(misha913loki) if you have any issue or comment.