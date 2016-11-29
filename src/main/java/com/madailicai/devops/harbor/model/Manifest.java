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
@JsonPropertyOrder({ "Id", "Parent", "Created", "Duration Days", "Author", "Architecture", "Docker Version", "OS" })
public class Manifest {

	@JsonProperty("Id")
	private String id;
	@JsonProperty("Parent")
	private String parent;
	@JsonProperty("Created")
	private String created;
	@JsonProperty("Duration Days")
	private String durationDays;
	@JsonProperty("Author")
	private String author;
	@JsonProperty("Architecture")
	private String architecture;
	@JsonProperty("Docker Version")
	private String dockerVersion;
	@JsonProperty("OS")
	private String oS;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return The id
	 */
	@JsonProperty("Id")
	public String getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 *            The Id
	 */
	@JsonProperty("Id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * @return The parent
	 */
	@JsonProperty("Parent")
	public String getParent() {
		return parent;
	}

	/**
	 *
	 * @param parent
	 *            The Parent
	 */
	@JsonProperty("Parent")
	public void setParent(String parent) {
		this.parent = parent;
	}

	/**
	 *
	 * @return The created
	 */
	@JsonProperty("Created")
	public String getCreated() {
		return created;
	}

	/**
	 *
	 * @param created
	 *            The Created
	 */
	@JsonProperty("Created")
	public void setCreated(String created) {
		this.created = created;
	}

	/**
	 *
	 * @return The durationDays
	 */
	@JsonProperty("Duration Days")
	public String getDurationDays() {
		return durationDays;
	}

	/**
	 *
	 * @param durationDays
	 *            The Duration Days
	 */
	@JsonProperty("Duration Days")
	public void setDurationDays(String durationDays) {
		this.durationDays = durationDays;
	}

	/**
	 *
	 * @return The author
	 */
	@JsonProperty("Author")
	public String getAuthor() {
		return author;
	}

	/**
	 *
	 * @param author
	 *            The Author
	 */
	@JsonProperty("Author")
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 *
	 * @return The architecture
	 */
	@JsonProperty("Architecture")
	public String getArchitecture() {
		return architecture;
	}

	/**
	 *
	 * @param architecture
	 *            The Architecture
	 */
	@JsonProperty("Architecture")
	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}

	/**
	 *
	 * @return The dockerVersion
	 */
	@JsonProperty("Docker Version")
	public String getDockerVersion() {
		return dockerVersion;
	}

	/**
	 *
	 * @param dockerVersion
	 *            The Docker Version
	 */
	@JsonProperty("Docker Version")
	public void setDockerVersion(String dockerVersion) {
		this.dockerVersion = dockerVersion;
	}

	/**
	 *
	 * @return The oS
	 */
	@JsonProperty("OS")
	public String getOS() {
		return oS;
	}

	/**
	 *
	 * @param oS
	 *            The OS
	 */
	@JsonProperty("OS")
	public void setOS(String oS) {
		this.oS = oS;
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
		return "Manifest{" + "id='" + id + '\'' + ", parent='" + parent + '\'' + ", created='" + created + '\''
				+ ", durationDays='" + durationDays + '\'' + ", author='" + author + '\'' + ", architecture='"
				+ architecture + '\'' + ", dockerVersion='" + dockerVersion + '\'' + ", oS='" + oS + '\''
				+ ", additionalProperties=" + additionalProperties + '}';
	}
}
