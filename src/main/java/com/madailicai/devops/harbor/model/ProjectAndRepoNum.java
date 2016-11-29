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
@JsonPropertyOrder({ "my_project_count", "my_repo_count", "public_project_count", "public_repo_count" })
public class ProjectAndRepoNum {

	@JsonProperty("my_project_count")
	private Integer myProjectCount;
	@JsonProperty("my_repo_count")
	private Integer myRepoCount;
	@JsonProperty("public_project_count")
	private Integer publicProjectCount;
	@JsonProperty("public_repo_count")
	private Integer publicRepoCount;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return The myProjectCount
	 */
	@JsonProperty("my_project_count")
	public Integer getMyProjectCount() {
		return myProjectCount;
	}

	/**
	 *
	 * @param myProjectCount
	 *            The my_project_count
	 */
	@JsonProperty("my_project_count")
	public void setMyProjectCount(Integer myProjectCount) {
		this.myProjectCount = myProjectCount;
	}

	/**
	 *
	 * @return The myRepoCount
	 */
	@JsonProperty("my_repo_count")
	public Integer getMyRepoCount() {
		return myRepoCount;
	}

	/**
	 *
	 * @param myRepoCount
	 *            The my_repo_count
	 */
	@JsonProperty("my_repo_count")
	public void setMyRepoCount(Integer myRepoCount) {
		this.myRepoCount = myRepoCount;
	}

	/**
	 *
	 * @return The publicProjectCount
	 */
	@JsonProperty("public_project_count")
	public Integer getPublicProjectCount() {
		return publicProjectCount;
	}

	/**
	 *
	 * @param publicProjectCount
	 *            The public_project_count
	 */
	@JsonProperty("public_project_count")
	public void setPublicProjectCount(Integer publicProjectCount) {
		this.publicProjectCount = publicProjectCount;
	}

	/**
	 *
	 * @return The publicRepoCount
	 */
	@JsonProperty("public_repo_count")
	public Integer getPublicRepoCount() {
		return publicRepoCount;
	}

	/**
	 *
	 * @param publicRepoCount
	 *            The public_repo_count
	 */
	@JsonProperty("public_repo_count")
	public void setPublicRepoCount(Integer publicRepoCount) {
		this.publicRepoCount = publicRepoCount;
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
		return "ProjectAndRepoNum{" + "myProjectCount=" + myProjectCount + ", myRepoCount=" + myRepoCount
				+ ", publicProjectCount=" + publicProjectCount + ", publicRepoCount=" + publicRepoCount
				+ ", additionalProperties=" + additionalProperties + '}';
	}
}
