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
@JsonPropertyOrder({ "project_id", "project_name", "project_public", "repository_name" })
public class Repository {

	@JsonProperty("project_id")
	private Integer projectId;
	@JsonProperty("project_name")
	private String projectName;
	@JsonProperty("project_public")
	private Integer projectPublic;
	@JsonProperty("repository_name")
	private String repositoryName;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return The projectId
	 */
	@JsonProperty("project_id")
	public Integer getProjectId() {
		return projectId;
	}

	/**
	 *
	 * @param projectId
	 *            The project_id
	 */
	@JsonProperty("project_id")
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	/**
	 *
	 * @return The projectName
	 */
	@JsonProperty("project_name")
	public String getProjectName() {
		return projectName;
	}

	/**
	 *
	 * @param projectName
	 *            The project_name
	 */
	@JsonProperty("project_name")
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 *
	 * @return The projectPublic
	 */
	@JsonProperty("project_public")
	public Integer getProjectPublic() {
		return projectPublic;
	}

	/**
	 *
	 * @param projectPublic
	 *            The project_public
	 */
	@JsonProperty("project_public")
	public void setProjectPublic(Integer projectPublic) {
		this.projectPublic = projectPublic;
	}

	/**
	 *
	 * @return The repositoryName
	 */
	@JsonProperty("repository_name")
	public String getRepositoryName() {
		return repositoryName;
	}

	/**
	 *
	 * @param repositoryName
	 *            The repository_name
	 */
	@JsonProperty("repository_name")
	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
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
		return "Repository{" + "projectId=" + projectId + ", projectName='" + projectName + '\'' + ", projectPublic="
				+ projectPublic + ", repositoryName='" + repositoryName + '\'' + ", additionalProperties="
				+ additionalProperties + '}';
	}
}
