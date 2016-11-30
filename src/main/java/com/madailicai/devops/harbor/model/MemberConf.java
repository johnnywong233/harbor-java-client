package com.madailicai.devops.harbor.model;

/**
 * Created by yangxueying on 2016/11/30.
 */

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "roles", "username" })
public class MemberConf {

	@JsonProperty("roles")
	private List<Integer> roles = new ArrayList<Integer>();
	@JsonProperty("username")
	private String username;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * @return The roles
	 */
	@JsonProperty("roles")
	public List<Integer> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            The roles
	 */
	@JsonProperty("roles")
	public void setRoles(List<Integer> roles) {
		this.roles = roles;
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

}
