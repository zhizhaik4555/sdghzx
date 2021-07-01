package com.bayside.app.opinion.showdata.subject.model;

public class SubjectWithBLOBs extends Subject {
    private String regionWord;

    private String subjectWord;

    private String eventWord;

    private String ambiguityWord;

    private String combinedWord;

    public String getRegionWord() {
        return regionWord;
    }

    public void setRegionWord(String regionWord) {
        this.regionWord = regionWord == null ? null : regionWord.trim();
    }

    public String getSubjectWord() {
        return subjectWord;
    }

    public void setSubjectWord(String subjectWord) {
        this.subjectWord = subjectWord == null ? null : subjectWord.trim();
    }

    public String getEventWord() {
        return eventWord;
    }

    public void setEventWord(String eventWord) {
        this.eventWord = eventWord == null ? null : eventWord.trim();
    }

    public String getAmbiguityWord() {
        return ambiguityWord;
    }

    public void setAmbiguityWord(String ambiguityWord) {
        this.ambiguityWord = ambiguityWord == null ? null : ambiguityWord.trim();
    }

    public String getCombinedWord() {
        return combinedWord;
    }

    public void setCombinedWord(String combinedWord) {
        this.combinedWord = combinedWord == null ? null : combinedWord.trim();
    }
}