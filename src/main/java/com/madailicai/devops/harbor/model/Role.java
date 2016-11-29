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
@JsonPropertyOrder({ "role_id", "role_code", "role_name", "role_mask" })
public class Role {

	@JsonProperty("role_id")
	private Integer roleId;
	@JsonProperty("role_code")
	private String roleCode;
	@JsonProperty("role_name")
	private String roleName;
	@JsonProperty("role_mask")
	private Integer roleMask;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
	 * @return The roleCode
	 */
	@JsonProperty("role_code")
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 *
	 * @param roleCode
	 *            The role_code
	 */
	@JsonProperty("role_code")
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
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
	 * @return The roleMask
	 */
	@JsonProperty("role_mask")
	public Integer getRoleMask() {
		return roleMask;
	}

	/**
	 *
	 * @param roleMask
	 *            The role_mask
	 */
	@JsonProperty("role_mask")
	public void setRoleMask(Integer roleMask) {
		this.roleMask = roleMask;
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
