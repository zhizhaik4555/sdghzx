package com.bayside.app.opinion.showdata.sma.model;

import java.util.Date;

public class SubjectMArticle {
    private String id;

    private String subjectid;

    private String articleid;

    private String userid;

    private String keywordRule;

    private Boolean reportinfo;

    private Boolean attention;

    private Boolean warning;

    private Boolean readsign;

    private Boolean briefing;

    private String emotion;

    private Date updatetime;

    private Date pubdate;

    private String dataSource;

    private String formats;

    private Date attentiontime;

    private Boolean send;

    private String warningWord;

    private Byte collection;

    private Double dependency;

    private String weidu;

    private Date storageTime;

    private String viewpoint;

    private String warningid;

    private Boolean noquery;

    private Integer simnum;

    private String simids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid == null ? null : subjectid.trim();
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid == null ? null : articleid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getKeywordRule() {
        return keywordRule;
    }

    public void setKeywordRule(String keywordRule) {
        this.keywordRule = keywordRule == null ? null : keywordRule.trim();
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

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion == null ? null : emotion.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
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
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    public String getFormats() {
        return formats;
    }

    public void setFormats(String formats) {
        this.formats = formats == null ? null : formats.trim();
    }

    public Date getAttentiontime() {
        return attentiontime;
    }

    public void setAttentiontime(Date attentiontime) {
        this.attentiontime = attentiontime;
    }

    public Boolean getSend() {
        return send;
    }

    public void setSend(Boolean send) {
        this.send = send;
    }

    public String getWarningWord() {
        return warningWord;
    }

    public void setWarningWord(String warningWord) {
        this.warningWord = warningWord == null ? null : warningWord.trim();
    }

    public Byte getCollection() {
        return collection;
    }

    public void setCollection(Byte collection) {
        this.collection = collection;
    }

    public Double getDependency() {
        return dependency;
    }

    public void setDependency(Double dependency) {
        this.dependency = dependency;
    }

    public String getWeidu() {
        return weidu;
    }

    public void setWeidu(String weidu) {
        this.weidu = weidu == null ? null : weidu.trim();
    }

    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }

    public String getViewpoint() {
        return viewpoint;
    }

    public void setViewpoint(String viewpoint) {
        this.viewpoint = viewpoint == null ? null : viewpoint.trim();
    }

    public String getWarningid() {
        return warningid;
    }

    public void setWarningid(String warningid) {
        this.warningid = warningid == null ? null : warningid.trim();
    }

    public Boolean getNoquery() {
        return noquery;
    }

    public void setNoquery(Boolean noquery) {
        this.noquery = noquery;
    }

    public Integer getSimnum() {
        return simnum;
    }

    public void setSimnum(Integer simnum) {
        this.simnum = simnum;
    }

    public String getSimids() {
        return simids;
    }

    public void setSimids(String simids) {
        this.simids = simids == null ? null : simids.trim();
    }
}