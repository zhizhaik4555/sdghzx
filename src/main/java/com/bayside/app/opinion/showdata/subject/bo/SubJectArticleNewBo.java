package com.bayside.app.opinion.showdata.subject.bo;

import java.util.Date;

public class SubJectArticleNewBo {
	private String id;
	private String tittle;
	private Date pubdate;
	private String dataSource;
	private String emotion;
	private String url;
	private String subjectid;
	private String articleid;
	private Boolean attention;  //是否关注      默认为0，为否，1为真
	private String mid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getEmotion() {
		return emotion;
	}
	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	public String getArticleid() {
		return articleid;
	}
	public void setArticleid(String articleid) {
		this.articleid = articleid;
	}
	public Boolean getAttention() {
		return attention;
	}
	public void setAttention(Boolean attention) {
		this.attention = attention;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
}
