package com.bayside.app.opinion.showdata.sma.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bayside.app.opinion.showdata.sma.bo.MapPoint;
import com.bayside.app.opinion.showdata.sma.bo.Monitorall;
import com.bayside.app.opinion.showdata.sma.bo.SubjectStatisticalBo;
import com.bayside.app.opinion.showdata.sma.bo.SubjectWeiboBo;
import com.bayside.app.opinion.showdata.sma.model.Article;
import com.bayside.app.opinion.showdata.subject.bo.SubJectArticleBo;
import com.bayside.app.opinion.showdata.subject.bo.SubJectArticleNewBo;

public interface SubjectMArticleService {
	 List<SubjectStatisticalBo> selectByTimeAcount(SubjectStatisticalBo record);
	 List<SubjectStatisticalBo> selectzaitiByTime(SubjectStatisticalBo record);
	 List<SubjectStatisticalBo> getSubjectTrackingCurrent(SubjectStatisticalBo record);
	 List<SubjectStatisticalBo> getSubjectTrackingDesc(SubjectStatisticalBo record);
	 List<SubJectArticleNewBo> selectNewArticle(SubJectArticleBo record);
	 List<SubjectWeiboBo> getBloggerProvince(SubjectWeiboBo record);
	 List<SubjectStatisticalBo> selectAllByDataSource(SubjectStatisticalBo record);
	 Monitorall getAllMonitorNumber();
	 Monitorall selectNewInfoByTime();
	 List<MapPoint> selectAllMapPoint();
	 
	 List<Article> getYuqingProvince(Article record);
	 
	 // 热词云图
	 List<Map<String,Object>> selectnewhot(String userid, String time);
}
