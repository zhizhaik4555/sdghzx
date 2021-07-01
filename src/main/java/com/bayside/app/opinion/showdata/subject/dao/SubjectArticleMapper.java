package com.bayside.app.opinion.showdata.subject.dao;

import java.util.List;

import com.bayside.app.opinion.showdata.subject.bo.SubJectArticleBo;
import com.bayside.app.opinion.showdata.subject.bo.SubJectArticleNewBo;
import com.bayside.app.opinion.showdata.subject.model.SubjectArticle;
import com.bayside.app.opinion.showdata.subject.model.SubjectArticleWithBLOBs;

public interface SubjectArticleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SubjectArticle record);

    int insertSelective(SubjectArticle record);

    SubjectArticleWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SubjectArticle record);

    int updateByPrimaryKeyWithBLOBs(SubjectArticle record);

    int updateByPrimaryKey(SubjectArticle record);
    List<SubJectArticleNewBo> selectNewArticle(SubJectArticleBo record);
}