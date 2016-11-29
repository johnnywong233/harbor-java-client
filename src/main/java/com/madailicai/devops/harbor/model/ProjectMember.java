package com.madailicai.devops.harbor.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

/**
 * Created by yangxueying on 2016/11/14.
 */
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "user_id", "username", "email", "password", "realname", "comment", "deleted", "role_name",
		"role_id", "has_admin_role", "reset_uuid", "Salt", "creation_time", "update_time" })
public class ProjectMember {

	@JsonProperty("user_id")
	private Integer userId;
	@JsonProperty("username")
	private String username;
	@JsonProperty("email")
	private String email;
	@JsonProperty("password")
	private String password;
	@JsonProperty("realname")
	private String realname;
	@JsonProperty("comment")
	private String comment;
	@JsonProperty("deleted")
	private Integer deleted;
	@JsonProperty("role_name")
	private String roleName;
	@JsonProperty("role_id")
	private Integer roleId;
	@JsonProperty("has_admin_role")
	private Integer hasAdminRole;
	@JsonProperty("reset_uuid")
	private String resetUuid;
	@JsonProperty("Salt")
	private String salt;
	@JsonProperty("creation_time")
	private String creationTime;
	@JsonProperty("update_time")
	private String updateTime;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return The userId
	 */
	@JsonProperty("user_id")
	public Integer getUserId() {
		return userId;
	}

	/**
	 *
	 * @param userId
	 *            The user_id
	 */
	@JsonProperty("user_id")
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 *
	 * @return The username
	 */
	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	/**
	 *
	 * @param username
	 *            The username
	 */
	@JsonProperty("username")
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 *
	 * @return The email
	 */
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	/**
	 *
	 * @param email
	 *            The email
	 */
	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 *
	 * @return The password
	 */
	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	/**
	 *
	 * @param password
	 *            The password
	 */
	@JsonProperty("password")
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 *
	 * @return The realname
	 */
	@JsonProperty("realname")
	public String getRealname() {
		return realname;
	}

	/**
	 *
	 * @param realname
	 *            The realname
	 */
	@JsonProperty("realname")
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 *
	 * @return The comment
	 */
	@JsonProperty("comment")
	public String getComment() {
		return comment;
	}

	/**
	 *
	 * @param comment
	 *            The comment
	 */
	@JsonProperty("comment")
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 *
	 * @return The deleted
	 */
	@JsonProperty("deleted")
	public Integer getDeleted() {
		return deleted;
	}

	/**
	 *
	 * @param deleted
	 *            The deleted
	 */
	@JsonProperty("deleted")
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	/**
	 *
	 * @return The roleName
	 */
	@JsonProperty("role_name")
	public String getRoleName() {
		return roleName;
	}

	/**
	 *
	 * @param roleName
	 *            The role_name
	 */
	@JsonProperty("role_name")
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 *
	 * @return The roleId
	 */
	@JsonProperty("role_id")
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 *
	 * @param roleId
	 *            The role_id
	 */
	@JsonProperty("role_id")
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 *
	 * @return The hasAdminRole
	 */
	@JsonProperty("has_admin_role")
	public Integer getHasAdminRole() {
		return hasAdminRole;
	}

	/**
	 *
	 * @param hasAdminRole
	 *            The has_admin_role
	 */
	@JsonProperty("has_admin_role")
	public void setHasAdminRole(Integer hasAdminRole) {
		this.hasAdminRole = hasAdminRole;
	}

	/**
	 *
	 * @return The resetUuid
	 */
	@JsonProperty("reset_uuid")
	public String getResetUuid() {
		return resetUuid;
	}

	/**
	 *
	 * @param resetUuid
	 *            The reset_uuid
	 */
	@JsonProperty("reset_uuid")
	public void setResetUuid(String resetUuid) {
		this.resetUuid = resetUuid;
	}

	/**
	 *
	 * @return The salt
	 */
	@JsonProperty("Salt")
	public String getSalt() {
		return salt;
	}

	/**
	 *
	 * @param salt
	 *            The Salt
	 */
	@JsonProperty("Salt")
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 *
	 * @return The creationTime
	 */
	@JsonProperty("creation_time")
	public String getCreationTime() {
		return creationTime;
	}

	/**
	 *
	 * @param creationTime
	 *            The creation_time
	 */
	@JsonProperty("creation_time")
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 *
	 * @return The updateTime
	 */
	@JsonProperty("update_time")
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 *
	 * @param updateTime
	 *            The update_time
	 */
	@JsonProperty("update_time")
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "ProjectMember{" + "userId=" + userId + ", username='" + username + '\'' + ", email='" + email + '\''
				+ ", password='" + password + '\'' + ", realname='" + realname + '\'' + ", comment='" + comment + '\''
				+ ", deleted=" + deleted + ", roleName='" + roleName + '\'' + ", roleId=" + roleId + ", hasAdminRole="
				+ hasAdminRole + ", resetUuid='" + resetUuid + '\'' + ", salt='" + salt + '\'' + ", creationTime='"
				+ creationTime + '\'' + ", updateTime='" + updateTime + '\'' + ", additionalProperties="
				+ additionalProperties + '}';
	}
}
