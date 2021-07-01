package com.bayside.app.opinion.showdata.subject.model;

import java.util.Date;

public class Subject {
    private String id;

    private String subjectname;

    private String warningType;

    private Boolean warning;

    private String tinterval;

    private Date starttime;

    private Date endtime;

    private String userid;

    private Date createTime;

    private String classifyid;

    private Boolean isdelete;

    private String warnStart;

    private String warnEnd;

    private Integer type;

    private String userparentid;

    private Byte isfilter;
    private String regionWord;

    private String subjectWord;

    private String eventWord;

    private String ambiguityWord;

    private String combinedWord;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname == null ? null : subjectname.trim();
    }

    public String getWarningType() {
        return warningType;
    }

    public void setWarningType(String warningType) {
        this.warningType = warningType == null ? null : warningType.trim();
    }

    public Boolean getWarning() {
        return warning;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }

    public String getTinterval() {
        return tinterval;
    }

    public void setTinterval(String tinterval) {
        this.tinterval = tinterval == null ? null : tinterval.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getClassifyid() {
        return classifyid;
    }

    public void setClassifyid(String classifyid) {
        this.classifyid = classifyid == null ? null : classifyid.trim();
    }

    public Boolean getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }

    public String getWarnStart() {
        return warnStart;
    }

    public void setWarnStart(String warnStart) {
        this.warnStart = warnStart == null ? null : warnStart.trim();
    }

    public String getWarnEnd() {
        return warnEnd;
    }

    public void setWarnEnd(String warnEnd) {
        this.warnEnd = warnEnd == null ? null : warnEnd.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserparentid() {
        return userparentid;
    }

    public void setUserparentid(String userparentid) {
        this.userparentid = userparentid == null ? null : userparentid.trim();
    }

    public Byte getIsfilter() {
        return isfilter;
    }

    public void setIsfilter(Byte isfilter) {
        this.isfilter = isfilter;
    }

	public String getRegionWord() {
		return regionWord;
	}

	public void setRegionWord(String regionWord) {
		this.regionWord = regionWord;
	}

	public String getSubjectWord() {
		return subjectWord;
	}

	public void setSubjectWord(String subjectWord) {
		this.subjectWord = subjectWord;
	}

	public String getEventWord() {
		return eventWord;
	}

	public void setEventWord(String eventWord) {
		this.eventWord = eventWord;
	}

	public String getAmbiguityWord() {
		return ambiguityWord;
	}

	public void setAmbiguityWord(String ambiguityWord) {
		this.ambiguityWord = ambiguityWord;
	}

	public String getCombinedWord() {
		return combinedWord;
	}

	public void setCombinedWord(String combinedWord) {
		this.combinedWord = combinedWord;
	}
}