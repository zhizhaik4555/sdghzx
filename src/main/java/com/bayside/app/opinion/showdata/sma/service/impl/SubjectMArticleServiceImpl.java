package com.bayside.app.opinion.showdata.sma.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayside.app.opinion.showdata.sma.bo.MapPoint;
import com.bayside.app.opinion.showdata.sma.bo.Monitorall;
import com.bayside.app.opinion.showdata.sma.bo.SubjectStatisticalBo;
import com.bayside.app.opinion.showdata.sma.bo.SubjectWeiboBo;
import com.bayside.app.opinion.showdata.sma.dao.SubjectMArticleMapper;
import com.bayside.app.opinion.showdata.sma.model.Article;
import com.bayside.app.opinion.showdata.sma.model.SubjectHotspot;
import com.bayside.app.opinion.showdata.sma.service.SubjectMArticleService;
import com.bayside.app.opinion.showdata.subject.bo.SubJectArticleBo;
import com.bayside.app.opinion.showdata.subject.bo.SubJectArticleNewBo;
import com.bayside.app.opinion.showdata.subject.dao.SubjectArticleMapper;
import com.bayside.app.util.DateFormatUtil;
import com.bayside.app.util.RedisUtil;

import redis.clients.jedis.ShardedJedis;

@Service("subjectMArticleServiceImpl")
public class SubjectMArticleServiceImpl implements SubjectMArticleService {
	@Autowired
    private SubjectMArticleMapper subjectMArticleMapper;
	@Autowired
	private SubjectArticleMapper subjectArticleMapper;
	@Override
	public List<SubjectStatisticalBo> selectByTimeAcount(SubjectStatisticalBo record) {
		// TODO Auto-generated method stub
		return subjectMArticleMapper.selectByTimeAcount(record);
	}

	@Override
	public List<SubjectStatisticalBo> selectzaitiByTime(SubjectStatisticalBo record) {
		// TODO Auto-generated method stub
		return subjectMArticleMapper.selectzaitiByTime(record);
	}
	@Override
	public List<SubjectStatisticalBo> getSubjectTrackingCurrent(SubjectStatisticalBo record) {
		List<SubjectStatisticalBo> list = new ArrayList<SubjectStatisticalBo>();
		List<SubjectStatisticalBo> listbo =  subjectMArticleMapper.getSubjectTrackingCurrent(record);
		// 当天数据
		List<String> listtime = DateFormatUtil.getHourList();
		Collections.sort(listtime);
		List<String> odtime = new ArrayList<String>();
		if(listbo.size()>0){
			for (int i = 0; i < listbo.size(); i++) {
				String uptime = listbo.get(i).getPubdate().substring(11, 13);
				listbo.get(i).setPubdate(uptime);
				odtime.add(uptime);
			}
		}
		for (int i = 0; i < listtime.size(); i++){
			SubjectStatisticalBo ss = new SubjectStatisticalBo();
			if (odtime.contains(listtime.get(i))) {
				for (int k = 0; k < listbo.size(); k++) {
					if (listbo.get(k).getPubdate().contains(listtime.get(i))) {
						ss = listbo.get(k);
					}
				}
			} else {
				ss.setPubdate(listtime.get(i));
				ss.setBbsAcount(0);
				ss.setBokeAcount(0);
				ss.setInfoAcount(0);
				ss.setNewsAcount(0);
				ss.setOtherAcount(0);
				ss.setPinglunAcount(0);
				ss.setPingmeiAcount(0);
				ss.setShipinAcount(0);
				ss.setTiebaAcount(0);
				ss.setWeiboAcount(0);
				ss.setWeixinAcount(0);
				ss.setInfoAcount(0);
				ss.setNegativeAcount(0);
				
			}
			list.add(ss);
		}
		return list;
	}
	@Override
	public List<SubjectStatisticalBo> getSubjectTrackingDesc(SubjectStatisticalBo record) {
		
		return  subjectMArticleMapper.getSubjectTrackingDesc(record);
		
	}

	@Override
	public List<SubJectArticleNewBo> selectNewArticle(SubJectArticleBo record) {
		// TODO Auto-generated method stub
		return subjectArticleMapper.selectNewArticle(record);
	}
	@Override
	public Monitorall selectNewInfoByTime() {
		// TODO Auto-generated method stub
		//return monitorallMapper.getNumberLatestTime();
		Monitorall monitorall = new Monitorall();
		Date date = new Date();
		try {
			Date startTime = DateFormatUtil.stringFormatDateType(DateFormatUtil.dateFormatStringType(date, "yyyy-MM-dd"), "yyyy-MM-dd");
			int seconds = (int) ((date.getTime()-startTime.getTime())/1000);
			int minute = (int) ((date.getTime()-startTime.getTime())/1000/60);
			monitorall.setTotalsite((int) (seconds*1.1));
			monitorall.setTotaltieba((int) (seconds*3.5));
			monitorall.setTotalweibo((int) (seconds*11.57));
			monitorall.setTotalpc((int) (minute*1.3));
			monitorall.setTotalweixin((int) (seconds*5.78));
			monitorall.setTotalPingmei((int) (minute*1));
			return monitorall;
		} catch (Exception e) {
			e.getMessage();
		}
		return monitorall;
		
	}
	@Override
	public Monitorall getAllMonitorNumber() {
		// TODO Auto-generated method stub
		//return monitorallMapper.getAllMonitorNumber();
		Monitorall monitorall = new Monitorall();
		Date date = new Date();
		try {
			Date startTime = DateFormatUtil.stringFormatDateType("2016-11-18 00:00:00","yyyy-MM-dd HH:mm:ss");
			int minute = (int) ((date.getTime()-startTime.getTime())/1000/60);
			int hour = (int) ((date.getTime()-startTime.getTime())/1000/60/60);
			monitorall.setTotalsite(100000+(int) (minute*1));
			monitorall.setTotaltieba(1000000+(int) (minute*20));
			monitorall.setTotalweibo(5000000+(int) (minute*30));
			monitorall.setTotalpc(2000+(int) (hour*1));
			monitorall.setTotalweixin(1000000+(int) (minute*10));
			monitorall.setTotalPingmei(1500+(int) (hour*0.1));
			return monitorall;
		} catch (Exception e) {
			
		}
		return monitorall;
	}
	@Override
	public List<SubjectWeiboBo> getBloggerProvince(SubjectWeiboBo record) {
		// TODO Auto-generated method stub
		return subjectMArticleMapper.getBloggerProvince(record);
	}

	@Override
	public List<SubjectStatisticalBo> selectAllByDataSource(SubjectStatisticalBo record) {
		// TODO Auto-generated method stub
		return subjectMArticleMapper.selectAllByDataSource(record);
	}

	@Override
	public List<MapPoint> selectAllMapPoint() {
		return subjectMArticleMapper.selectAllMapPoint();
	}

	@Override
	public List<Article> getYuqingProvince(Article record) {
		// TODO Auto-generated method stub
		return subjectMArticleMapper.getYuqingProvincer1(record);
	}

	@Override
	public List<Map<String, Object>> selectnewhot(String userid, String time) {
		List<SubjectHotspot> list = subjectMArticleMapper.selecthot(userid, time);
		List<Map<String,Object>> hotwordList = new ArrayList<Map<String,Object>>();
		Map<String,Integer> mapList = new HashMap<String,Integer>();
		ObjectMapper mapper = new ObjectMapper();
		for (SubjectHotspot subjectHotspot : list) {
			String hotword = subjectHotspot.getHotword();
			if(hotword==null||"".equals(hotword)){
				continue;
			}
			try {
				List<Map<String, Object>> wordList = mapper.readValue(hotword, ArrayList.class);
				for (Map<String, Object> map : wordList) {
					String word = map.get("word")+"";
					String scorestr = map.get("score")+"";
					Integer score = Integer.parseInt(scorestr);
					if(mapList.get(word)!=null){
						mapList.put(word, mapList.get(word)+score);
					}else{
						mapList.put(word, score);
					}
				}
			} catch (Exception e) {
//				log.error(e.getMessage(),e);
				continue;
			} 
			
		}
		if(mapList!=null&&!mapList.isEmpty()){
			for (Entry<String, Integer> entry : mapList.entrySet()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("word", entry.getKey());
				map.put("score", entry.getValue());
				hotwordList.add(map);
			}
		}
		return hotwordList;
	}

}
