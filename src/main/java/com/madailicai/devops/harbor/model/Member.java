package com.madailicai.devops.harbor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@JsonPropertyOrder({ "roles", "user_id", "username" })
public class Member {

	@JsonProperty("roles")
	private List<Role> roles = new ArrayList<Role>();
	@JsonProperty("user_id")
	private Integer userId;
	@JsonProperty("username")
	private String username;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * @return The roles
	 */
	@JsonProperty("roles")
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            The roles
	 */
	@JsonProperty("roles")
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return The userId
	 */
	@JsonProperty("user_id")
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            The user_id
	 */
	@JsonProperty("user_id")
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return The username
	 */
	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            The username
	 */
	@JsonProperty("username")
	public void setUsername(String username) {
		this.username = username;
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
		return "Member{" + "roles=" + roles + ", userId=" + userId + ", username='" + username + '\''
				+ ", additionalProperties=" + additionalProperties + '}';
	}
}
