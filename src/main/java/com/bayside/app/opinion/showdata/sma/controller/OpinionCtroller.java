package com.bayside.app.opinion.showdata.sma.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * <p>Title: OpinionCtroller</P>
 * <p>Description: </p>
 * <p>Copyright: 山东贝赛信息科技有限公司 Copyright (c) 2016</p>
 * @author liuyy
 * @version 1.0
 * @since 2017年7月14日
 */

import com.bayside.app.opinion.showdata.sma.bo.MapPoint;
import com.bayside.app.opinion.showdata.sma.bo.Monitorall;
import com.bayside.app.opinion.showdata.sma.bo.SubjectHotspotBo;
import com.bayside.app.opinion.showdata.sma.bo.SubjectStatisticalBo;
import com.bayside.app.opinion.showdata.sma.bo.SubjectWeiboBo;
import com.bayside.app.opinion.showdata.sma.model.Article;
import com.bayside.app.opinion.showdata.sma.service.SubjectMArticleService;
import com.bayside.app.opinion.showdata.subject.bo.SubJectArticleBo;
import com.bayside.app.opinion.showdata.subject.bo.SubJectArticleNewBo;
import com.bayside.app.opinion.showdata.user.model.User;
import com.bayside.app.opinion.showdata.user.service.UserService;
import com.bayside.app.util.AppConstant;
import com.bayside.app.util.Response;
import com.bayside.app.util.ResponseStatus;
import com.bayside.util.CommonUtil;
import com.bayside.util.SimpleDate;
import com.sun.mail.handlers.message_rfc822;
@RestController
@EnableAutoConfiguration
public class OpinionCtroller {
	@Autowired
     private SubjectMArticleService subjectMArticleServiceImpl;
	@Autowired
	 private UserService userServiceImpl;
	/**
	 * 
	 * <p>方法名称：selectByTimeAcount</p>
	 * <p>方法描述： 载体分布 舆情监测</p>
	 * @param record
	 * @return
	 * @author liuyy
	 * @throws ParseException 
	 * @since  2017年7月14日
	 * <p> history 2017年7月14日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/selectByTimeAcount", method = RequestMethod.GET)
	public Response selectByTimeAcount(HttpServletRequest request, SubjectStatisticalBo record) throws ParseException {
		record.setUserid((String)request.getSession().getAttribute("userid"));
		//时间处理
	if (record.getStartTime() != null) {
			if (record.getStartTime().equals(AppConstant.timetype.CURRENT)) {
				record.setStartTime(SimpleDate.formatDate(new Date()));
			} else if (record.getStartTime().equals(AppConstant.timetype.SUN)) {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, -7);
				String str = SimpleDate.formatDate(c.getTime());
				record.setStartTime(str);
			} else if (record.getStartTime().equals(AppConstant.timetype.MONTH)) {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, -30);
				String  str = SimpleDate.formatDate(c.getTime());
				record.setStartTime(str);
			} else if (record.getStartTime().equals(AppConstant.timetype.ZIDINGYI)) {
				record.setStartTime(record.getStartTime()+" 00:00:00");
				record.setEndTime(record.getEndTime()+" 23:59:59");
			}
		}else{
			record.setStartTime(SimpleDate.formatDate(new Date()));
		}
		Calendar c = Calendar.getInstance();
//		c.add(Calendar.DATE, -7);
		String str = SimpleDate.formatDate(c.getTime());
		record.setStartTime(str);
		List<SubjectStatisticalBo> list = subjectMArticleServiceImpl.selectByTimeAcount(record);
		if(list.size()>0){
			return new Response(ResponseStatus.Success,list,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}
		
	}
	/**
	 * 
	 * <p>方法名称：getSubjectTrackingDesc</p>
	 * <p>方法描述：载体趋势分析</p>
	 * @param record
	 * @return
	 * @author liuyy
	 * @throws ParseException 
	 * @since  2017年7月14日
	 * <p> history 2017年7月14日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/getSubjectTrackingDesc", method = RequestMethod.GET)
	public Response getSubjectTrackingDesc(HttpServletRequest request, SubjectStatisticalBo record) throws ParseException{
		record.setUserid((String)request.getSession().getAttribute("userid"));
		//时间处理
		if (record.getStartTime() != null) {
				if (record.getStartTime().equals(AppConstant.timetype.CURRENT)) {
					record.setStartTime(SimpleDate.formatDate(new Date()));
				} else if (record.getStartTime().equals(AppConstant.timetype.SUN)) {
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DATE, -7);
					String str = SimpleDate.formatDate(c.getTime());
					record.setStartTime(str);
				} else if (record.getStartTime().equals(AppConstant.timetype.MONTH)) {
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DATE, -30);
					String  str = SimpleDate.formatDate(c.getTime());
					record.setStartTime(str);
				} else if (record.getStartTime().equals(AppConstant.timetype.ZIDINGYI)) {
					record.setStartTime(record.getStartTime()+" 00:00:00");
					record.setEndTime(record.getEndTime()+" 23:59:59");
				}
				List<SubjectStatisticalBo> list = subjectMArticleServiceImpl.getSubjectTrackingDesc(record);
				if(list.size()>0){
					return new Response(ResponseStatus.Success,list,true);
				}else{
					return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
				}
			}else{
				record.setStartTime(SimpleDate.formatDate(new Date()));
				Integer trade  = (Integer)request.getSession().getAttribute("setTrade");
				if(null!=trade){
					if(trade!=1){
						record.setSetTrade(0);
					}
				}
				List<SubjectStatisticalBo> list = subjectMArticleServiceImpl.getSubjectTrackingCurrent(record);
				if(list.size()>0){
					return new Response(ResponseStatus.Success,list,true);
				}else{
					return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
				}
			}
	}
	/**
	 * 
	 * <p>方法名称：getInfoDiffusionTop</p>
	 * <p>方法描述：信息发布TOP</p>
	 * @param record
	 * @return
	 * @throws ParseException
	 * @author administrator
	 * @since  2017年7月19日
	 * <p> history 2017年7月19日 administrator  创建   <p>
	 */
	@RequestMapping(value = "/getInfoDiffusionTop", method = RequestMethod.GET)
	public Response getInfoDiffusionTop(HttpServletRequest request, SubjectStatisticalBo record) throws ParseException{
		record.setUserid((String)request.getSession().getAttribute("userid"));
		//时间处理
				if (record.getStartTime() != null) {
						if (record.getStartTime().equals(AppConstant.timetype.CURRENT)) {
							record.setStartTime(SimpleDate.formatDate(new Date()));
						} else if (record.getStartTime().equals(AppConstant.timetype.SUN)) {
							Calendar c = Calendar.getInstance();
							c.add(Calendar.DATE, -7);
							String str = SimpleDate.formatDate(c.getTime());
							record.setStartTime(str);
						} else if (record.getStartTime().equals(AppConstant.timetype.MONTH)) {
							Calendar c = Calendar.getInstance();
							c.add(Calendar.DATE, -30);
							String  str = SimpleDate.formatDate(c.getTime());
							record.setStartTime(str);
						} else if (record.getStartTime().equals(AppConstant.timetype.ZIDINGYI)) {
							record.setStartTime(record.getStartTime()+" 00:00:00");
							record.setEndTime(record.getEndTime()+" 23:59:59");
						}
					}else{
						record.setStartTime(SimpleDate.formatDate(new Date()));
					}
				Integer trade  = (Integer)request.getSession().getAttribute("setTrade");
				if(null!=trade){
					if(trade!=1){
						record.setSetTrade(0);
					}
				}
				List<SubjectStatisticalBo> list = subjectMArticleServiceImpl.selectAllByDataSource(record);
				if(list.size()>0){
					return new Response(ResponseStatus.Success,list,true);
				}else{
					return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
				}
	}
	
	/**
	 * 
	 * <p>方法名称：selectNewArticle</p>
	 * <p>方法描述：舆情信息</p>
	 * @param record
	 * @return
	 * @author liuyy
	 * @throws ParseException 
	 * @since  2017年7月14日
	 * <p> history 2017年7月14日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/selectNewArticle", method = RequestMethod.GET)
	public Response selectNewArticle(HttpServletRequest request, SubJectArticleBo record) throws ParseException{
		record.setUserid((String)request.getSession().getAttribute("userid"));
		//时间处理
		if (record.getStartTime() != null) {
				if (record.getStartTime().equals(AppConstant.timetype.CURRENT)) {
					record.setStartTime(SimpleDate.formatDate(new Date()));
				} else if (record.getStartTime().equals(AppConstant.timetype.SUN)) {
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DATE, -7);
					String str = SimpleDate.formatDate(c.getTime());
					record.setStartTime(str);
				} else if (record.getStartTime().equals(AppConstant.timetype.MONTH)) {
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DATE, -30);
					String  str = SimpleDate.formatDate(c.getTime());
					record.setStartTime(str);
				} else if (record.getStartTime().equals(AppConstant.timetype.ZIDINGYI)) {
					record.setStartTime(record.getStartTime()+" 00:00:00");
					record.setEndTime(record.getEndTime()+" 23:59:59");
				}
			}else{
				record.setStartTime(SimpleDate.formatDate(new Date()));
			}
		Integer trade  = (Integer)request.getSession().getAttribute("setTrade");
		if(null!=trade){
			if(trade!=1){
				record.setSetTrade(0);
			}
		}
		List<SubJectArticleNewBo> list = subjectMArticleServiceImpl.selectNewArticle(record);
		if(list.size()>0){
			return new Response(ResponseStatus.Success,list,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}
	}
	/**
	 * <p>
	 * 方法名称：getNewmonitor
	 * </p>
	 * <p>
	 * 方法描述：//查询今天的监控数量
	 * </p>
	 * 
	 * @return
	 * @author peigd
	 * @since 2017年7月25日
	 *        <p>
	 *        history 2017年7月25日  创建
	 *        <p>
	 */
	@RequestMapping(value = "/getNewmonitor", method = RequestMethod.GET)
	public Response getNewmonitor() {
		Monitorall ma = subjectMArticleServiceImpl.selectNewInfoByTime();
		if (ma != null) {
			return new Response(ResponseStatus.Success, ma, true);
		} else {
			return new Response(ResponseStatus.Error, AppConstant.responseInfo.SELECTEERRO, false);
		}
	}
	/**
	 * <p>
	 * 方法名称：selectAllMonitorNumber
	 * </p>
	 * <p>
	 * 方法描述：查询总的监控数量
	 * </p>
	 * 
	 * @return
	 * @author liuyy
	 * @since 2016年10月12日
	 *        <p>
	 *        history 2016年10月12日 Administrator 创建
	 *        <p>
	 */
	@RequestMapping(value = "/selectAllMonitorNumber", method = RequestMethod.GET)
	public Response selectAllMonitorNumber() {
		Monitorall ma = subjectMArticleServiceImpl.getAllMonitorNumber();
		if (ma != null) {
			return new Response(ResponseStatus.Success, ma, true);
		} else {
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO, false);
		}
	}
	/**
	 * 
	 * <p>方法名称：selectWeiBoProvince</p>
	 * <p>方法描述：获取微博地域分布</p>
	 * @param record
	 * @return
	 * @author liuyy
	 * @throws ParseException 
	 * @since  2017年7月15日
	 * <p> history 2017年7月15日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/selectWeiBoProvince", method = RequestMethod.GET)
	public Response selectWeiBoProvince(HttpServletRequest request, SubjectWeiboBo record) throws ParseException{
		record.setUserid((String)request.getSession().getAttribute("userid"));
		//时间处理
		if (record.getStartTime() != null) {
				if (record.getStartTime().equals(AppConstant.timetype.CURRENT)) {
					record.setStartTime(SimpleDate.formatDate(new Date()));
				} else if (record.getStartTime().equals(AppConstant.timetype.SUN)) {
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DATE, -7);
					String str = SimpleDate.formatDate(c.getTime());
					record.setStartTime(str);
				} else if (record.getStartTime().equals(AppConstant.timetype.MONTH)) {
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DATE, -30);
					String  str = SimpleDate.formatDate(c.getTime());
					record.setStartTime(str);
				} else if (record.getStartTime().equals(AppConstant.timetype.ZIDINGYI)) {
					record.setStartTime(record.getStartTime()+" 00:00:00");
					record.setEndTime(record.getEndTime()+" 23:59:59");
				}
			}else{
				record.setStartTime(SimpleDate.formatDate(new Date()));
			}
		Calendar c = Calendar.getInstance();
//		c.add(Calendar.DATE, -7);
		String str = SimpleDate.formatDate(c.getTime());
		record.setStartTime(str);
		
		User user = userServiceImpl.selectUserCity(record.getUserid());
		SubjectWeiboBo swb = new SubjectWeiboBo();
		if(user.getCity() == "" || user.getCity() == null){
			swb.setCity("北京");
		}else{
			swb.setCity(user.getCity());
		}
		
		List<SubjectWeiboBo> list = subjectMArticleServiceImpl.getBloggerProvince(record);
		List<MapPoint> list1 = subjectMArticleServiceImpl.selectAllMapPoint();
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("list1", list);
		rtn.put("list2", list1);
		return new Response(ResponseStatus.Success,rtn,true);
		/*if(list.size()>0){
=======
		list.add(swb);
		if(list.size()>0){
>>>>>>> .r9729
			return new Response(ResponseStatus.Success,list,true);
		}else{
			return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		}*/
		
	}
	
	
	/**
	 * 
	 * <p>方法名称：selectWeiBoProvince</p>
	 * <p>方法描述：获取微博地域分布</p>
	 * @param record
	 * @return
	 * @author 常瑞
	 * @throws ParseException 
	 * @since  2017年7月15日
	 * <p> history 2017年7月15日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/selectYuqingProvince", method = RequestMethod.GET)
	public Response selectYuqingProvince(HttpServletRequest request, Article record) throws ParseException{
		record.setUserid((String)request.getSession().getAttribute("userid"));
		Calendar c = Calendar.getInstance();
		String str = SimpleDate.formatDate(c.getTime());
		record.setStartTime(str);
		
		User user = userServiceImpl.selectUserCity(record.getUserid());
		if(user.getCity() == "" || user.getCity() == null){
			user.setCity("北京");
		}
		
		List<Article> list = subjectMArticleServiceImpl.getYuqingProvince(record);
//		List<MapPoint> list1 = subjectMArticleServiceImpl.selectAllMapPoint();
		for(int i=0; i<list.size(); i++){
			list.get(i).setLocation(user.getCity());
		}
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("list1", list);
//		rtn.put("list2", list1);
		int totalbloggerMax = 0;
		for(int i = 0; i<list.size(); i++){
			if(list.get(i).getTotalblogger()>totalbloggerMax){
				totalbloggerMax = list.get(i).getTotalblogger();
			}
		}
		rtn.put("totalbloggerMax", totalbloggerMax);
		return new Response(ResponseStatus.Success,rtn,true);
		
	}
	
	/**
	 * 
	 * <p>方法名称：selectAllMapPoint</p>
	 * <p>方法描述：获取地图全部城市经纬度</p>
	 * @param request
	 * @return
	 * @author 常瑞
	 * @throws ParseException 
	 * @since  2017年7月15日
	 * <p> history 2017年7月15日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/selectAllMapPoint", method = RequestMethod.GET)
	public Response selectAllMapPoint(HttpServletRequest request) throws ParseException{
		List<MapPoint> list1 = subjectMArticleServiceImpl.selectAllMapPoint();
		return new Response(ResponseStatus.Success,list1,true);
	}
	
	/**
	 * 
	 * <p>方法名称：selectAllMapPoint</p>
	 * <p>方法描述：获取地图全部城市经纬度</p>
	 * @param request
	 * @return
	 * @author 常瑞
	 * @throws ParseException 
	 * @since  2017年7月15日
	 * <p> history 2017年7月15日 Administrator  创建   <p>
	 */
	@RequestMapping(value = "/selectHotspot", method = RequestMethod.GET)
	public Response selectHotspot(SubjectHotspotBo record, HttpServletRequest request) throws ParseException{
//		String time = record.getUpdatetime();
		String userid = (String) request.getSession().getAttribute("userid");
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		List<Map<String, Object>> lis = new ArrayList<Map<String, Object>>();
		try {
			String current = SimpleDate.formatDate(new Date());
			lis = subjectMArticleServiceImpl.selectnewhot(userid, current);
		} catch (Exception e) {
			// TODO: handle exception
//             Log.error(e.getMessage());
		}
		return new Response(ResponseStatus.Success, lis, true);
	}
}
