package com.bayside.app.opinion.showdata.subject.dao;

import java.util.List;


import com.bayside.app.opinion.showdata.subject.model.Subject;

import com.bayside.app.opinion.showdata.subject.model.SubjectWithBLOBs;


public interface SubjectMapper {
    int deleteByPrimaryKey(String id);

    int insert(SubjectWithBLOBs record);

    int insertSelective(SubjectWithBLOBs record);

    SubjectWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SubjectWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SubjectWithBLOBs record);

    int updateByPrimaryKey(Subject record);
    
    List<Subject> selectBySelective(Subject record);
    
   
}