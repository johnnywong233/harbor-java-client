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
@JsonPropertyOrder({ "name", "count", "creator" })
public class PopRepo {

	@JsonProperty("name")
	private String name;
	@JsonProperty("count")
	private Integer count;
	@JsonProperty("creator")
	private String creator;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	/**
	 *
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 *
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return The count
	 */
	@JsonProperty("count")
	public Integer getCount() {
		return count;
	}

	/**
	 *
	 * @param count
	 *            The count
	 */
	@JsonProperty("count")
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 *
	 * @return The creator
	 */
	@JsonProperty("creator")
	public String getCreator() {
		return creator;
	}

	/**
	 *
	 * @param creator
	 *            The creator
	 */
	@JsonProperty("creator")
	public void setCreator(String creator) {
		this.creator = creator;
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
		return "PopRepo{" + "name='" + name + '\'' + ", count=" + count + ", creator='" + creator + '\''
				+ ", additionalProperties=" + additionalProperties + '}';
	}
}
