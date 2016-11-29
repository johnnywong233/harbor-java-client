package com.madailicai.devops.harbor;

import java.io.IOException;
import java.net.CookieManager;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.madailicai.devops.harbor.model.Log;
import com.madailicai.devops.harbor.model.Manifest;
import com.madailicai.devops.harbor.model.Member;
import com.madailicai.devops.harbor.model.PopRepo;
import com.madailicai.devops.harbor.model.Project;
import com.madailicai.devops.harbor.model.ProjectAndRepoNum;
import com.madailicai.devops.harbor.model.ProjectMember;
import com.madailicai.devops.harbor.model.Search;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * @author Grissom & Misha
 */
public class HarborClient {

	private static final String REQUEST_RESPONSE_INFO = "Request: [%s], Response: [%s]";

	private static final Logger logger = LoggerFactory.getLogger(HarborClient.class);

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	private OkHttpClient okhttpClient;

	private ObjectMapper mapper = new ObjectMapper();

	private String baseUrl;

	private boolean verifySSL = false;

	/**
	 * @param baseUrl
	 */
	public HarborClient(String baseUrl) {
		this(baseUrl, false);
	}

	/**
	 * @param baseUrl
	 * @param verifySSL
	 */
	public HarborClient(String baseUrl, boolean verifySSL) {
		this.baseUrl = baseUrl;
		this.verifySSL = verifySSL;
		getOkHttpClient();
	}

	/**
	 * append '/' to baseUrl if it doesn't end with '/'
	 * @return
	 */
	private String getBaseUrl() {
		if (!this.baseUrl.endsWith("/")) {
			this.baseUrl += "/";
		}
		return this.baseUrl;
	}

	/**
	 * 获取OkHttpClient
	 *
	 * @return
	 */
	private void getOkHttpClient() {
		try {
			// Create a trust manager that does not validate certificate chains
			final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
				}

				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new java.security.cert.X509Certificate[0];
				}
			} };
			// Install the all-trusting trust manager
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			// Create an ssl socket factory with our all-trusting manager
			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			okhttpClient = new OkHttpClient();
			okhttpClient.setSslSocketFactory(sslSocketFactory);
			okhttpClient.setHostnameVerifier((String hostname, SSLSession session) -> !HarborClient.this.verifySSL);
			okhttpClient.setCookieHandler(new CookieManager());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * login</br>
	 * <b>URL</b>: /login</br>
	 * <b>Method</b>: POST
	 *
	 * @param name
	 *            [required]
	 * @param password
	 *            [required]
	 * @return HTTP Response Code
	 * @throws IOException
	 */
	public int login(String name, String password) throws IOException {
		String url = getBaseUrl().replace("/api", "") + "login?principal=" + name + "&password=" + password;
		RequestBody reqbody = RequestBody.create(null, new byte[0]);
		Request request = new Request.Builder().post(reqbody).url(url).build();
		Response response = okhttpClient.newCall(request).execute();
		int code = response.code();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		return code;
	}

	/**
	 * logout</br>
	 * <b>URL</b>: /log_out</br>
	 * <b>Method</b>: GET
	 *
	 * @return HTTP Response Code
	 * @throws IOException
	 */
	public int logout() throws IOException {
		String url = getBaseUrl().replace("/api", "") + "log_out";
		Request request = new Request.Builder().get().url(url).build();
		Response response = okhttpClient.newCall(request).execute();
		int code = response.code();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		return code;
	}

	/**
	 * The Search endpoint returns information about the projects and
	 * repositories offered at public status or related to the current logged in
	 * user. </br>
	 * <b>URL</b>: /search</br>
	 * <b><b>Method</b>: GET</b>
	 *
	 * @param q
	 *            [required] (Search parameter for project and repository name)
	 * @return The response includes the project and repository list in a proper
	 *         display order.
	 * @throws IOException
	 */
	public Search search(String q) throws IOException {
		String url = getBaseUrl() + "search?q=" + q;
		Request request = new Request.Builder().url(url).get().build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		return mapper.readValue(response.body().string(), Search.class);
	}

	/**
	 * This endpoint returns all projects created by Harbor, and can be filtered
	 * by project name.</br>
	 * <b>URL</b>: /projects</br>
	 * <b>Method</b>: GET
	 *
	 * @param projectName
	 *            (Project name for filtering results)
	 * @param isPublic
	 *            (Public sign for filtering projects)
	 * @return
	 * @throws IOException
	 */
	public List<Project> getProjects(String projectName, String isPublic) throws IOException {
		List<NameValuePair> qparams = new ArrayList<>();
		qparams.add(new BasicNameValuePair("project_name", projectName));
		qparams.add(new BasicNameValuePair("is_public", isPublic));
		qparams.removeIf(o -> Objects.isNull(o.getValue()));
		String url = getBaseUrl() + "projects?" + URLEncodedUtils.format(qparams, "UTF-8");
		Request request = new Request.Builder().url(url).get().build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		return mapper.readValue(response.body().string(), new TypeReference<List<Project>>() {
		});
	}

	/**
	 * For user to search a specified project's relevant role members.</br>
	 * <b>URL</b>:/projects/{project_id}/members/</br>
	 * <b>Method</b>: GET
	 *
	 * @param projectId
	 *            [required] (Relevant project ID)
	 * @return relevant role members
	 * @throws IOException
	 */
	public List<ProjectMember> getProjectMembers(int projectId) throws IOException {
		String url = getBaseUrl() + "projects/" + projectId + "/members/";
		Request request = new Request.Builder().url(url).get().build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		return mapper.readValue(response.body().string(), new TypeReference<List<ProjectMember>>() {
		});
	}

	/**
	 * For user to get role members accompany with relevant project and
	 * user.</br>
	 * <b>URL</b>: /projects/{project_id}/members/{user_id}</br>
	 * <b>Method</b>: GET
	 *
	 * @param projectId
	 *            [required] (Relevant project ID)
	 * @param userId
	 *            [required] (Relevant user ID)
	 * @return role members
	 * @throws IOException
	 */
	public Member getRole(int projectId, int userId) throws IOException {
		String url = getBaseUrl() + "projects/" + projectId + "/members/" + userId;
		Request request = new Request.Builder().url(url).get().build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		return mapper.readValue(response.body().string(), Member.class);
	}

	/**
	 * Statistic all of the projects number and repositories number relevant to
	 * the logined user, also the public projects number and repositories
	 * number. </br>
	 * <b>URL</b>: /statistics</br>
	 * <b>Method</b>: GET
	 *
	 * @return The projects number and repositories number relevant to the user.
	 * @throws IOException
	 */
	public ProjectAndRepoNum getStatistics() throws IOException {
		String url = getBaseUrl() + "statistics";
		Request request = new Request.Builder().url(url).get().build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		return mapper.readValue(response.body().string(), ProjectAndRepoNum.class);
	}

	/**
	 * Let user search repositories accompanying with relevant project ID and
	 * repo name.</br>
	 * <b>URL</b>: /repositories</br>
	 * <b>Method</b>: GET
	 *
	 * @param projectId
	 *            [required] (Relevant project ID)
	 * @param q
	 *            (Repo name for filtering results)
	 * @return respositories
	 * @throws IOException
	 */
	public List<String> getRepositories(String projectId, String q) throws IOException {
		String url = getBaseUrl() + "repositories?project_id=" + projectId;
		if (q != null) {
			url = url + "&q=" + q;
		}
		Request request = new Request.Builder().url(url).get().build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		return mapper.readValue(response.body().string(), new TypeReference<List<String>>() {
		});
	}

	/**
	 * Retrieve tags from a relevant repository.</br>
	 * <b>URL</b>: /repositories/tags</br>
	 * <b>Method</b>: GET
	 *
	 * @param repoName
	 *            [required] (Relevant repository name)
	 * @return RepositorieTags
	 * @throws IOException
	 */
	public List<String> getRepositorieTags(String repoName) throws IOException {
		String url = getBaseUrl() + "repositories/tags?repo_name=" + repoName;
		Request request = new Request.Builder().url(url).get().build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		return mapper.readValue(response.body().string(), new TypeReference<List<String>>() {
		});
	}

	/**
	 * Retreive manifests from a relevant repository.</br>
	 * <b>URL</b>: /repositories/manifests</br>
	 * <b>Method</b>: GET
	 *
	 * @param repoName
	 *            [required] (Repository name)
	 * @param tag
	 *            [required] (Tag name)
	 * @return
	 * @throws IOException
	 */
	public Manifest getManifest(String repoName, String tag) throws IOException {
		String url = getBaseUrl() + "repositories/manifests?repo_name=" + repoName + "&tag=" + tag;
		Request request = new Request.Builder().url(url).get().build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		return mapper.readValue(response.body().string(), Manifest.class);
	}

	/**
	 * Let users see the most popular public repositories.</br>
	 * <b>URL</b>: /repositories/top</br>
	 * <b>Method</b>: GET
	 *
	 * @param count
	 *            (The number of the requested public repositories, default is
	 *            10 if not provided)
	 * @return Retrieved top repositories.
	 * @throws IOException
	 */
	public List<PopRepo> getTopRepositories(String count) throws IOException {
		String url = getBaseUrl() + "repositories/top";
		if (count != null) {
			url = url + "?count=" + count;
		}
		Request request = new Request.Builder().url(url).get().build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		return mapper.readValue(response.body().string(), new TypeReference<List<PopRepo>>() {
		});
	}

	/**
	 * Let user see the recent operation logs of the projects which he is member
	 * of.</br>
	 * <b>URL</b>: /logs</br>
	 * <b>Method</b>: GET
	 *
	 * @param lines
	 *            (The number of logs to be shown, default is 10 if lines,
	 *            start_time, end_time are not provided)
	 * @param startTime
	 *            (The start time of logs to be shown in unix timestap)
	 * @param endTime
	 *            (The end time of logs to be shown in unix timestap)
	 * @return required logs.
	 * @throws IOException
	 */
	public List<Log> getLogs(String lines, String startTime, String endTime) throws IOException {
		logger.debug("get logs lines %s, start time %s, end time %s", lines, startTime, endTime);
		List<NameValuePair> qparams = new ArrayList<>();
		qparams.add(new BasicNameValuePair("lines", lines));
		qparams.add(new BasicNameValuePair("start_time", startTime));
		qparams.add(new BasicNameValuePair("end_time", endTime));
		qparams.removeIf(o -> Objects.isNull(o.getValue()));
		String url = getBaseUrl() + "/logs?" + URLEncodedUtils.format(qparams, "UTF-8");
		Request request = new Request.Builder().url(url).get().build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		return mapper.readValue(response.body().string(), new TypeReference<List<Log>>() {
		});
	}

	/**
	 * Used to check if the project name user provided already exist.</br>
	 * <b>URL</b>: /projects</br>
	 * <b>Method</b>: HEAD
	 *
	 * @param projectName
	 *            (Project name for checking exists)
	 * @return
	 * @throws IOException
	 */
	public String checkProject(String projectName) throws IOException {
		String res;
		String url = baseUrl + "projects?project_name=" + projectName;
		Request request = new Request.Builder().url(url).head().build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		switch (response.code()) {
		case 200:
			res = "Project name exists.";
			break;
		case 401:
			res = "User need to log in first.";
			break;
		case 404:
			res = "Project name does not exist.";
			break;
		case 500:
			res = "Unexpected internal errors.";
			break;
		default:
			res = "Unknown.";
			break;
		}
		return res;
	}

	/**
	 * Let user delete repositories and tags with repo name and tag.</br>
	 * <b>URL</b>: /repositories</br>
	 * <b>Method</b>: DELETE
	 *
	 * @param repoName
	 *            [required] (The name of repository which will be deleted)
	 * @param tag
	 *            (Tag of a repository)
	 * @return
	 * @throws IOException
	 */
	public String deleteRepositories(String repoName, String tag) throws IOException {
		String res;
		String url = baseUrl + "repositories?repo_name=" + repoName;
		if (tag != null) {
			url = url + "&tag=" + tag;
		}
		Request request = new Request.Builder().url(url).delete().build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		switch (response.code()) {
		case 200:
			res = "Delete repository or tag successfully.";
			break;
		case 400:
			res = "Invalid repo_name.";
			break;
		case 401:
			res = "Unauthorized.";
			break;
		case 403:
			res = "Forbidden.";
			break;
		case 404:
			res = "Repository or tag not found.";
			break;
		default:
			res = "Unknown.";
			break;
		}
		return res;
	}

	/**
	 * For user to create a new project.</br>
	 * <b>URL</b>: /projects</br>
	 * <b>Method</b>: POST
	 *
	 * @param project
	 *            [required] (New created project)
	 * @return
	 * @throws IOException
	 */
	public String createProject(String project) throws IOException {
		String res;
		String url = baseUrl + "projects";
		RequestBody requestBody = RequestBody.create(JSON, project);
		Request request = new Request.Builder().url(url).post(requestBody).build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		switch (response.code()) {
		case 201:
			res = "Project created successfully.";
			break;
		case 400:
			res = "Unsatisfied with constraints of the project creation.";
			break;
		case 401:
			res = "User need to log in first.";
			break;
		case 409:
			res = "Project name already exists.";
			break;
		case 500:
			res = "Unexpected internal errors.";
			break;
		default:
			res = "Unknown.";
			break;
		}
		return res;
	}

	/**
	 * Aimed to toggle a project publicity status.</br>
	 * <b>URL</b>: /projects/{project_id}/publicity</br>
	 * <b>Method</b>: PUT
	 *
	 * @param projectId
	 *            [required] (Selected project ID)
	 * @param project
	 *            [required] (Updates of project)
	 * @return
	 * @throws IOException
	 */
	public String setPublicity(int projectId, String project) throws IOException {
		String res;
		String url = baseUrl + "projects/" + projectId + "/publicity";
		RequestBody requestBody = RequestBody.create(JSON, project);
		Request request = new Request.Builder().url(url).put(requestBody).build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		switch (response.code()) {
		case 200:
			res = "Updated project publicity status successfully.";
			break;
		case 400:
			res = "Illegal format of provided ID value.";
			break;
		case 401:
			res = "User need to log in first.";
			break;
		case 403:
			res = "User does not have permission to the project.";
			break;
		case 404:
			res = "Project ID does not exist.";
			break;
		case 500:
			res = "Unexpected internal errors.";
			break;
		default:
			res = "Unknown.";
			break;
		}
		return res;
	}

	/**
	 * Let user search access logs filtered by operations and date time
	 * ranges.</br>
	 * <b>URL</b>: /projects/{project_id}/logs/filter</br>
	 * <b>Method</b>: POST
	 *
	 * @param projectId
	 *            [required] (Selected project ID)
	 * @param accessLog
	 *            (Search results of access logs)
	 * @return
	 * @throws IOException
	 */
	public List<Object> filterLog(int projectId, String accessLog) throws IOException {
		List<Object> res = new ArrayList<>();
		String url = baseUrl + "projects/" + projectId + "/logs/filter";
		RequestBody requestBody = RequestBody.create(JSON, accessLog);
		Request request = new Request.Builder().url(url).post(requestBody).build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		switch (response.code()) {
		case 200:
			res = mapper.readValue(response.body().string(), new TypeReference<List<Log>>() {
			});
			break;
		case 400:
			res.add("Illegal format of provided ID value.");
			break;
		case 401:
			res.add("User need to log in first.");
			break;
		case 500:
			res.add("Unexpected internal errors.");
			break;
		default:
			res.add("Unknown.");
			break;
		}
		return res;
	}

	/**
	 * For user to add project role member accompany with relevant project and
	 * user.</br>
	 * <b>URL</b>: /projects/{project_id}/members/</br>
	 * <b>Method</b>: POST
	 *
	 * @param projectId
	 *            [required] (Relevant project ID)
	 * @param roles
	 *            (Role members for adding to relevant project)
	 * @return
	 * @throws IOException
	 */
	public String addMember(int projectId, String roles) throws IOException {
		String res;
		String url = baseUrl + "projects/" + projectId + "/members";
		RequestBody requestBody = RequestBody.create(JSON, roles);
		Request request = new Request.Builder().url(url).post(requestBody).build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		switch (response.code()) {
		case 200:
			res = "Role members added to relevant project successfully.";
			break;
		case 400:
			res = "Illegal format of provided ID value.";
			break;
		case 401:
			res = "User need to log in first.";
			break;
		case 403:
			res = "User in session does not have permission to the project.";
			break;
		case 404:
			res = "Project ID does not exist.";
			break;
		case 409:
			res = "User has already added as a project role member.";
			break;
		case 500:
			res = "Unexpected internal errors.";
			break;
		default:
			res = "Unknown.";
			break;
		}
		return res;
	}

	/**
	 * Aimed to remove project role members already added to the relevant
	 * project and user.</br>
	 * <b>URL</b>: /projects/{project_id}/members/{user_id}</br>
	 * <b>Method</b>: DELETE
	 *
	 * @param projectId
	 *            [required] (Relevant project ID)
	 * @param userId
	 *            [required] (Relevant user ID)
	 * @return
	 * @throws IOException
	 */
	public String deleteMember(int projectId, int userId) throws IOException {
		String res;
		String url = baseUrl + "projects/" + projectId + "/members/" + userId;
		Request request = new Request.Builder().url(url).delete().build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		switch (response.code()) {
		case 200:
			res = "Project role members deleted successfully.";
			break;
		case 400:
			res = "Illegal format of provided ID value.";
			break;
		case 401:
			res = "User need to log in first.";
			break;
		case 403:
			res = "User in session does not have permission to the project.";
			break;
		case 404:
			res = "Project ID does not exist.";
			break;
		case 500:
			res = "Unexpected internal errors.";
			break;
		default:
			res = "Unknown.";
			break;
		}
		return res;
	}

	/**
	 * For user to update current project role members accompany with relevant
	 * project and user.</br>
	 * <b>URL</b>: /projects/{project_id}/members/{user_id}</br>
	 * <b>Method</b>: PUT
	 *
	 * @param projectId
	 *            [required] (Relevant project ID)
	 * @param userId
	 *            [required] (Relevant user ID)
	 * @param roles
	 *            (Updates for roles and username)
	 * @return
	 * @throws IOException
	 */
	public String updateMember(int projectId, int userId, String roles) throws IOException {
		String res;
		String url = baseUrl + "projects/" + projectId + "/members/" + userId;
		RequestBody requestBody = RequestBody.create(JSON, roles);
		Request request = new Request.Builder().url(url).put(requestBody).build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		switch (response.code()) {
		case 200:
			res = "Project role members updated successfully.";
			break;
		case 400:
			res = "Illegal format of provided ID value.";
			break;
		case 401:
			res = "User need to log in first.";
			break;
		case 403:
			res = "User in session does not have permission to the project.";
			break;
		case 404:
			res = "Project ID does not exist.";
			break;
		case 500:
			res = "Unexpected internal errors.";
			break;
		default:
			res = "Unknown.";
			break;
		}
		return res;
	}

	/**
	 * Create a user if the user does not already exist.</br>
	 * <b>URL</b>: /users</br>
	 * <b>Method</b>: POST
	 *
	 * @param user
	 *            [required] (New created user)
	 * @return
	 * @throws IOException
	 */
	public String createUser(String user) throws IOException {
		String res;
		String url = baseUrl + "users";
		RequestBody requestBody = RequestBody.create(JSON, user);
		Request request = new Request.Builder().url(url).post(requestBody).build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		switch (response.code()) {
		case 201:
			res = "User created successfully.";
			break;
		case 400:
			res = "Unsatisfied with constraints of the user creation.";
			break;
		case 403:
			res = "User registration can only be used by admin role user when self-registration is off.";
			break;
		case 409:
			res = "username has already been used.";
			break;
		case 500:
			res = "Unexpected internal errors.";
			break;
		default:
			res = "Unknown.";
			break;
		}
		return res;
	}

	/**
	 * admin only Let administrator of Harbor mark a registered user as be
	 * removed.It actually won't be deleted from DB.</br>
	 * <b>URL</b>: /users/{user_id}</br>
	 * <b>Method</b>: DELETE
	 *
	 * @param userId
	 *            [required] (User ID for marking as to be removed)
	 * @return
	 * @throws IOException
	 */
	public String deleteUser(int userId) throws IOException {
		String res;
		String url = baseUrl + "users/" + userId;
		Request request = new Request.Builder().url(url).delete().build();
		Response response = okhttpClient.newCall(request).execute();
		logger.debug(String.format(REQUEST_RESPONSE_INFO, request, response));
		switch (response.code()) {
		case 200:
			res = "Marked user as be removed successfully.";
			break;
		case 400:
			res = "Invalid user ID.";
			break;
		case 401:
			res = "User need to log in first.";
			break;
		case 403:
			res = "User does not have permission of admin role.";
			break;
		case 404:
			res = "User ID does not exist.";
			break;
		case 500:
			res = "Unexpected internal errors.";
			break;
		default:
			res = "Unknown.";
			break;
		}
		return res;
	}

}
