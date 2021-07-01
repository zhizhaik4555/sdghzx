package com.bayside.app.opinion.showdata.subject.bo;

import java.util.List;

import com.bayside.app.opinion.showdata.subject.model.SubjectArticle;

public class SubJectArticleBo extends SubjectArticle{

   private String subjectid; 
	
    private String articleid;

    private String userid;
    
	private String keywordRule; //关键词规则

    private Boolean reportinfo; //是否上报信息     默认为0，为否，1为真

    private Boolean attention;  //是否关注      默认为0，为否，1为真

    private Boolean warning;  //是否加入预警   默认为0，为否，1为真

    private Boolean readsign;  //是否已读     默认为0，为否，1为真

    private Boolean briefing;  //加入简报    默认为0，为否，1为真
    private List<String> medialist;
    private List<String> emotionlist;
    private List<String> subjectlist;
    private String startTime;
    private String endTime;
    private String mid;
    private Integer start;
    private Integer size;
    private String tittle;
    private Integer setTrade;
    
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getKeywordRule() {
		return keywordRule;
	}
	public void setKeywordRule(String keywordRule) {
		this.keywordRule = keywordRule;
	}
	public Boolean getReportinfo() {
		return reportinfo;
	}
	public void setReportinfo(Boolean reportinfo) {
		this.reportinfo = reportinfo;
	}
	public Boolean getAttention() {
		return attention;
	}
	public void setAttention(Boolean attention) {
		this.attention = attention;
	}
	public Boolean getWarning() {
		return warning;
	}
	public void setWarning(Boolean warning) {
		this.warning = warning;
	}
	public Boolean getReadsign() {
		return readsign;
	}
	public void setReadsign(Boolean readsign) {
		this.readsign = readsign;
	}
	public Boolean getBriefing() {
		return briefing;
	}
	public void setBriefing(Boolean briefing) {
		this.briefing = briefing;
	}
	public List<String> getMedialist() {
		return medialist;
	}
	public void setMedialist(List<String> medialist) {
		this.medialist = medialist;
	}
	public List<String> getEmotionlist() {
		return emotionlist;
	}
	public void setEmotionlist(List<String> emotionlist) {
		this.emotionlist = emotionlist;
	}
	public List<String> getSubjectlist() {
		return subjectlist;
	}
	public void setSubjectlist(List<String> subjectlist) {
		this.subjectlist = subjectlist;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getSetTrade() {
		return setTrade;
	}
	public void setSetTrade(Integer setTrade) {
		this.setTrade = setTrade;
	}

}
