package com.bayside.app.opinion.showdata.sma.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayside.app.opinion.showdata.sma.bo.MapPoint;
import com.bayside.app.opinion.showdata.sma.bo.SubjectHotspotBo;
import com.bayside.app.opinion.showdata.sma.bo.SubjectStatisticalBo;
import com.bayside.app.opinion.showdata.sma.bo.SubjectWeiboBo;
import com.bayside.app.opinion.showdata.sma.model.Article;
import com.bayside.app.opinion.showdata.sma.model.SubjectHotspot;
import com.bayside.app.opinion.showdata.sma.model.SubjectMArticle;


public interface SubjectMArticleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SubjectMArticle record);

    int insertSelective(SubjectMArticle record);

    SubjectMArticle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SubjectMArticle record);

    int updateByPrimaryKeyWithBLOBs(SubjectMArticle record);

    int updateByPrimaryKey(SubjectMArticle record);
    List<SubjectStatisticalBo> selectAllByDataSource(SubjectStatisticalBo record);
    List<SubjectStatisticalBo> selectByTimeAcount(SubjectStatisticalBo record);
    List<SubjectStatisticalBo> selectzaitiByTime(SubjectStatisticalBo record);
    List<SubjectStatisticalBo> getSubjectTrackingDesc(SubjectStatisticalBo record);
    List<SubjectStatisticalBo> getSubjectTrackingCurrent(SubjectStatisticalBo record);
    List<SubjectWeiboBo> getBloggerProvince(SubjectWeiboBo record);
    List<MapPoint> selectAllMapPoint();
    
    List<Article> getYuqingProvincer1(Article record);
    
    List<SubjectHotspot> selecthot(@Param("userid")String userid,@Param("time")String time);
    List<SubjectHotspot> indexselecthot(SubjectHotspotBo sHotspotBo);
}