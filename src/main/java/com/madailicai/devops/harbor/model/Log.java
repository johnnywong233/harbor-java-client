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
@JsonPropertyOrder({ "log_id", "user_id", "project_id", "repo_name", "repo_tag", "guid", "operation", "op_time",
		"username", "keywords", "BeginTime", "begin_timestamp", "EndTime", "end_timestamp" })
public class Log {

	@JsonProperty("log_id")
	private Integer logId;
	@JsonProperty("user_id")
	private Integer userId;
	@JsonProperty("project_id")
	private Integer projectId;
	@JsonProperty("repo_name")
	private String repoName;
	@JsonProperty("repo_tag")
	private String repoTag;
	@JsonProperty("guid")
	private String guid;
	@JsonProperty("operation")
	private String operation;
	@JsonProperty("op_time")
	private String opTime;
	@JsonProperty("username")
	private String username;
	@JsonProperty("keywords")
	private String keywords;
	@JsonProperty("BeginTime")
	private String beginTime;
	@JsonProperty("begin_timestamp")
	private Integer beginTimestamp;
	@JsonProperty("EndTime")
	private String endTime;
	@JsonProperty("end_timestamp")
	private Integer endTimestamp;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return The logId
	 */
	@JsonProperty("log_id")
	public Integer getLogId() {
		return logId;
	}

	/**
	 *
	 * @param logId
	 *            The log_id
	 */
	@JsonProperty("log_id")
	public void setLogId(Integer logId) {
		this.logId = logId;
	}

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
	 * @return The repoName
	 */
	@JsonProperty("repo_name")
	public String getRepoName() {
		return repoName;
	}

	/**
	 *
	 * @param repoName
	 *            The repo_name
	 */
	@JsonProperty("repo_name")
	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	/**
	 *
	 * @return The repoTag
	 */
	@JsonProperty("repo_tag")
	public String getRepoTag() {
		return repoTag;
	}

	/**
	 *
	 * @param repoTag
	 *            The repo_tag
	 */
	@JsonProperty("repo_tag")
	public void setRepoTag(String repoTag) {
		this.repoTag = repoTag;
	}

	/**
	 *
	 * @return The guid
	 */
	@JsonProperty("guid")
	public String getGuid() {
		return guid;
	}

	/**
	 *
	 * @param guid
	 *            The guid
	 */
	@JsonProperty("guid")
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 *
	 * @return The operation
	 */
	@JsonProperty("operation")
	public String getOperation() {
		return operation;
	}

	/**
	 *
	 * @param operation
	 *            The operation
	 */
	@JsonProperty("operation")
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 *
	 * @return The opTime
	 */
	@JsonProperty("op_time")
	public String getOpTime() {
		return opTime;
	}

	/**
	 *
	 * @param opTime
	 *            The op_time
	 */
	@JsonProperty("op_time")
	public void setOpTime(String opTime) {
		this.opTime = opTime;
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
	 * @return The keywords
	 */
	@JsonProperty("keywords")
	public String getKeywords() {
		return keywords;
	}

	/**
	 *
	 * @param keywords
	 *            The keywords
	 */
	@JsonProperty("keywords")
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 *
	 * @return The beginTime
	 */
	@JsonProperty("BeginTime")
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 *
	 * @param beginTime
	 *            The BeginTime
	 */
	@JsonProperty("BeginTime")
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 *
	 * @return The beginTimestamp
	 */
	@JsonProperty("begin_timestamp")
	public Integer getBeginTimestamp() {
		return beginTimestamp;
	}

	/**
	 *
	 * @param beginTimestamp
	 *            The begin_timestamp
	 */
	@JsonProperty("begin_timestamp")
	public void setBeginTimestamp(Integer beginTimestamp) {
		this.beginTimestamp = beginTimestamp;
	}

	/**
	 *
	 * @return The endTime
	 */
	@JsonProperty("EndTime")
	public String getEndTime() {
		return endTime;
	}

	/**
	 *
	 * @param endTime
	 *            The EndTime
	 */
	@JsonProperty("EndTime")
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 *
	 * @return The endTimestamp
	 */
	@JsonProperty("end_timestamp")
	public Integer getEndTimestamp() {
		return endTimestamp;
	}

	/**
	 *
	 * @param endTimestamp
	 *            The end_timestamp
	 */
	@JsonProperty("end_timestamp")
	public void setEndTimestamp(Integer endTimestamp) {
		this.endTimestamp = endTimestamp;
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
		return "Log{" + "logId=" + logId + ", userId=" + userId + ", projectId=" + projectId + ", repoName='" + repoName
				+ '\'' + ", repoTag='" + repoTag + '\'' + ", guid='" + guid + '\'' + ", operation='" + operation + '\''
				+ ", opTime='" + opTime + '\'' + ", username='" + username + '\'' + ", keywords='" + keywords + '\''
				+ ", beginTime='" + beginTime + '\'' + ", beginTimestamp=" + beginTimestamp + ", endTime='" + endTime
				+ '\'' + ", endTimestamp=" + endTimestamp + ", additionalProperties=" + additionalProperties + '}';
	}
}
