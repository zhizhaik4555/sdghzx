package com.bayside.app.opinion.showdata.user.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.bayside.app.opinion.showdata.sma.bo.SubjectStatisticalBo;
import com.bayside.app.opinion.showdata.sma.model.SubjectStatistical;
import com.bayside.app.opinion.showdata.sma.service.SubjectMArticleService;
import com.bayside.app.opinion.showdata.subject.model.Subject;
import com.bayside.app.opinion.showdata.user.bo.SetIndexModalBo;
import com.bayside.app.opinion.showdata.user.bo.UserBo;
import com.bayside.app.opinion.showdata.user.dao.UserMapper;
import com.bayside.app.opinion.showdata.user.model.SetIndexModal;
import com.bayside.app.opinion.showdata.user.model.User;
import com.bayside.app.opinion.showdata.user.service.UserService;

import com.bayside.app.util.AppConstant;
import com.bayside.app.util.Response;
import com.bayside.app.util.ResponseStatus;
import com.bayside.app.util.UuidUtil;
import com.bayside.util.SimpleDate;
@RestController
@EnableAutoConfiguration
public class UserController{
	 @Autowired
	 private UserService userServiceImpl;
	@Autowired
	 private SubjectMArticleService subjectMArticleServiceImpl;
	 @RequestMapping(value = "/selectLoginUser", method = RequestMethod.GET)
	 public Response selectLoginUser(User record,HttpServletRequest request,HttpSession session){
		 User user = userServiceImpl.selectByPassword(record);
		 if(null!=user){
			 UserBo ubo = new UserBo();
				BeanUtils.copyProperties(user, ubo);
			 ubo.setId(user.getId());
			  UserBo ub = userServiceImpl.selectPowerByUserId(ubo);
			  session.setAttribute("userid", user.getId());
			  session.setAttribute("user", user);
			  
			  if(null!=ub){
				  user.setStatus(ub.getSetTrade());
	 				if(1==ub.getSetTrade()){
	 					session.setAttribute("setTrade", ub.getSetTrade());
	 				}else{
	 					session.setAttribute("setTrade", 0);
	 				}
	 		  }else{
	 				session.setAttribute("setTrade", 0);
	 				 
	 		 }
			  return new Response(ResponseStatus.Success,ubo,true);
		 }else{
			 return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		 }
		 
	 }
	 /**
	  * <p>方法名称：selectUserCity</p>
	  * <p>方法描述：获取用户所在地</p>
	  * @param record
	  * @param request
	  * @param session
	  * @return
	  * @author peigd
	  * @since  2017年8月23日
	  * <p> history 2017年8月23日 administrator  创建   <p>
	  */
	 @RequestMapping(value = "/selectUserCity", method = RequestMethod.GET)
	 public Response selectUserCity(String id){
		 User user = userServiceImpl.selectUserCity(id);
		 if(null!=user){
			  return new Response(ResponseStatus.Success,user,true);
		 }else{
			 return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		 }
		 
	 }
	
	 /**
	  * <p>方法名称：outlogin</p>
	  * <p>方法描述：退出</p>
	  * @param request
	  * @return
	  * @author 常瑞
	  * @since  2017年8月23日
	  * <p> history 2017年8月23日 administrator  创建   <p>
	  */
	 @RequestMapping(value = "/outlogin", method = RequestMethod.GET)
	 public Response outlogin(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if (session == null) {
			return new Response(ResponseStatus.Success,"未登录",true);
		}
		session.removeAttribute("user");
		session.removeAttribute("userid");
		return new Response(ResponseStatus.Success,"注销成功",true);
	 }
	 
	 /**
	  * <p>方法名称：fastLogin</p>
	  * <p>方法描述：退出</p>
	  * @param request
	  * @return
	  * @author 常瑞
	  * @since  2017年9月8日
	  * <p> history 2017年9月8日 administrator  创建   <p>
	  */
	 @RequestMapping(value = "/fastLogin", method = RequestMethod.GET)
	 public Response fastLogin(User record,HttpServletRequest request,HttpSession session){
		 User user = userServiceImpl.selectUserFast(record);
		 if(null!=user){
			 UserBo ubo = new UserBo();
				BeanUtils.copyProperties(user, ubo);
			 ubo.setId(user.getId());
			  UserBo ub = userServiceImpl.selectPowerByUserId(ubo);
			  session.setAttribute("userid", user.getId());
			  session.setAttribute("user", user);
			  
			  if(null!=ub){
				  user.setStatus(ub.getSetTrade());
	 				if(1==ub.getSetTrade()){
	 					session.setAttribute("setTrade", ub.getSetTrade());
	 				}else{
	 					session.setAttribute("setTrade", 0);
	 				}
	 		  }else{
	 				session.setAttribute("setTrade", 0);
	 				 
	 		 }
			  return new Response(ResponseStatus.Success,ubo,true);
		 }else{
			 return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		 }
	 }
	 /**
	  * 
	  * <p>方法名称：insertIndexModel</p>
	  * <p>方法描述：添加模板</p>
	  * @param record
	  * @param request
	  * @return
	  * @author liuyy
	  * @since  2017年9月15日
	  * <p> history 2017年9月15日 Administrator  创建   <p>
	  */
	 @RequestMapping(value = "/insertIndexModel", method = RequestMethod.GET)
	  public Response insertIndexModel(SetIndexModalBo record,HttpServletRequest request){
		  String userid = (String) request.getSession().getAttribute("userid");
		 record.setId(UuidUtil.getUUID());
		  record.setUserid(userid);
		 // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		  if(record.getTimescope()!=null){
			  if(record.getTimescope().equals(AppConstant.timetype.CURRENT)){
				  try {
					String time = SimpleDate.formatDate(new Date());
					  record.setStartTime(time);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
				
				}
				
			  }else if(record.getTimescope().equals(AppConstant.timetype.WEEK)){
				  Calendar c = Calendar.getInstance();
				  c.add(Calendar.DATE, -7);
					try {
						String str = SimpleDate.formatDate(c.getTime());
						record.setStartTime(str);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						
					}
			  }
		  }
		 
		  int num = userServiceImpl.insertIndexModel(record);
		  if(num > 0){
			  return new Response(ResponseStatus.Success,num,true);
		  }else{
			  return new Response(ResponseStatus.Error,AppConstant.responseInfo.SAVEERRO,false);
		  }
		
	  }
	 /**
	  * 
	  * <p>方法名称：insertAllIndex</p>
	  * <p>方法描述：保存首页信息</p>
	  * @param info
	  * @param request
	  * @return
	  * @author liuyy
	  * @since  2017年9月15日
	  * <p> history 2017年9月15日 Administrator  创建   <p>
	  */
	 @RequestMapping(value = "/insertAllIndex", method = RequestMethod.GET)
	  public Response insertAllIndex(String info,HttpServletRequest request){
		  String userid = (String) request.getSession().getAttribute("userid");
		  List<SetIndexModalBo> list = new ArrayList<SetIndexModalBo>(); 
		  list = JSONArray.parseArray(info, SetIndexModalBo.class);//这里的t是Class<T> 
		  System.out.println(info);
	     
	      Boolean flag = userServiceImpl.insertAllModalBo(list, userid);
		  if(flag){
			 
			  return new Response(ResponseStatus.Success,AppConstant.responseInfo.SAVESUCCESS,true);
		  }else{
			  return new Response(ResponseStatus.Error,AppConstant.responseInfo.SAVEERRO,false);
		  }
		  
	  }
	 @RequestMapping(value = "/updateshowAllIndex", method = RequestMethod.GET)
	  public Response updateshowAllIndex(String info,HttpServletRequest request){
		  String userid = (String) request.getSession().getAttribute("userid");

		  
		  List<SetIndexModalBo> list = new ArrayList<SetIndexModalBo>(); 
		  list = JSONArray.parseArray(info, SetIndexModalBo.class);//这里的t是Class<T> 
		  System.out.println(info);
		  String oid="";
		  for(int j=0;j<list.size();j++){
			  if(null!=list.get(j).getShowid()){
				 
				   oid =  list.get(j).getShowid();
				 
				  
			  }
//			  if(j==list.size()-1){
//				  
//			  }
		  }
	      Boolean flag = userServiceImpl.updateshowAllModalBo(list, userid);
		  if(flag){
			  //删除原来的
			  userServiceImpl.deleteshowByshowid(oid);
			  return new Response(ResponseStatus.Success,AppConstant.responseInfo.SAVESUCCESS,true);
		  }else{
			  return new Response(ResponseStatus.Error,AppConstant.responseInfo.SAVEERRO,false);
		  }
		  
	  }
	 /**
	  * 
	  * <p>方法名称：selectIndexSubject</p>
	  * <p>方法描述：查询专题</p>
	  * @param request
	  * @return
	  * @author liuyy
	  * @since  2017年9月15日
	  * <p> history 2017年9月15日 Administrator  创建   <p>
	  */
	 @RequestMapping(value = "/selectIndexSubject", method = RequestMethod.GET)
	  public Response selectIndexSubject(HttpServletRequest request){
		  String userid = (String) request.getSession().getAttribute("userid");
		  Subject record = new Subject();
		  record.setUserid(userid);
		  record.setIsdelete(false);
		  List<Subject> list = userServiceImpl.selectBySelective(record);
		  if(list!=null){
			  return new Response(ResponseStatus.Success,list,true);
		  }else{
			  return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);  
		  }
	  }
	 /**
	  * 
	  * <p>方法名称：updateIndexModel</p>
	  * <p>方法描述：修改自定义模板</p>
	  * @param record
	  * @return
	  * @author liuyy
	  * @since  2017年9月15日
	  * <p> history 2017年9月15日 Administrator  创建   <p>
	  */
	 @RequestMapping(value = "/updateIndexModel", method = RequestMethod.GET)
	  public Response updateIndexModel(SetIndexModalBo record){
		 int num = userServiceImpl.updateSetIndexModal(record);
		 if(num > 0){
			 return new Response(ResponseStatus.Success,num,true);
		 }else{
			 return new Response(ResponseStatus.Error,AppConstant.responseInfo.UPDATEEERRO,false);
		 }
	  }
	 /**
	   * 
	   * <p>方法名称：deleteIndexModal</p>
	   * <p>方法描述：删除自定义模块</p>
	   * @param id
	   * @return
	   * @author liuyy
	   * @since  2017年2月25日
	   * <p> history 2017年2月25日 Administrator  创建   <p>
	   */
	  @RequestMapping(value = "/deleteIndexModal", method = RequestMethod.GET)
	  public Response deleteIndexModal(String id){
		  int num = userServiceImpl.deleteIndexModal(id);
		  if(num > 0){
			  return new Response(ResponseStatus.Success,num,true);
		  }else{
			  return new Response(ResponseStatus.Error,AppConstant.responseInfo.DELETEERRO,false);
		  }
	  }
	//初始化首页
	  @RequestMapping(value = "/initindex", method = RequestMethod.GET)
	  public Response initindex(HttpServletRequest request){
		  List<SetIndexModal> totallist = userServiceImpl.initindexmodal(request);
		 
		  if(totallist!=null){
			  return new Response(ResponseStatus.Success,totallist,true);
		  }else{
			  return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		  }
		  
	  }
	  //首页自定义热词云图
	  @RequestMapping(value = "/indexselectHot", method = RequestMethod.GET)
	  public Response indexselectHot(SetIndexModalBo record,HttpServletRequest request){
		  String userid = (String) request.getSession().getAttribute("userid");
		  record.setUserid(userid);
		  List<Map<String, Object>> list = userServiceImpl.indexselectnewhot(record);
		  if(list!=null){
			  return new Response(ResponseStatus.Success,list,true);
		  }else{
			  return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
		  }
	  }
	  /**
	   * 
	   * <p>方法名称：selectZaitiFenbu</p>
	   * <p>方法描述：自定义载体分布</p>
	   * @param record
	   * @param request
	   * @return
	   * @author liuyy
	   * @since  2017年9月18日
	   * <p> history 2017年9月18日 Administrator  创建   <p>
	   */
	  @RequestMapping(value = "/selectZaitiFenbu", method = RequestMethod.GET)
	  public Response selectZaitiFenbu(SetIndexModalBo record,HttpServletRequest request){
		  String userid = (String) request.getSession().getAttribute("userid");
		///  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		  SubjectStatisticalBo sb = new SubjectStatisticalBo();
		  List<SubjectStatisticalBo> list = new ArrayList<SubjectStatisticalBo>();
		  List<String> subjectList = new ArrayList<String>();
		  List<String> mediaList = new ArrayList<String>();
		  List<String> emotionList = new ArrayList<String>();
		  String[] sList = record.getSubject().split(",");
		 // String[] mList = record.getMediatype().split(",");
		 // String[] eList = record.getEmotion().split(",");
		  for(int i=0;i<sList.length;i++){
			  subjectList.add(sList[i]);
			  sb.setSubjectlist(subjectList);
		  }
//		  for(int i=0;i<mList.length;i++){
//			  mediaList.add(mList[i]);
//			  sb.setMedialist(mediaList);
//		  }
//		  for(int i=0;i<eList.length;i++){
//			  emotionList.add(eList[i]);
//			  sb.setEmotionlist(emotionList);
//		  }
		  sb.setUserid(userid);
		  try {
			sb.setStartTime(SimpleDate.formatDate(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
		   list = subjectMArticleServiceImpl.selectByTimeAcount(sb);
		 
		  if(list!=null){
			  return new Response(ResponseStatus.Success,list,true);
		  }else{
			  return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);  
		  }
	  }
	  /**
	   * 
	   * <p>方法名称：definedInfoDiffusionTop</p>
	   * <p>方法描述：自定义媒体top</p>
	   * @param request
	   * @param record
	   * @return
	   * @author liuyy
	   * @since  2017年9月18日
	   * <p> history 2017年9月18日 Administrator  创建   <p>
	   */
	  @RequestMapping(value = "/definedInfoDiffusionTop", method = RequestMethod.GET)
	  public Response definedInfoDiffusionTop(HttpServletRequest request, SubjectStatisticalBo record){
			record.setUserid((String)request.getSession().getAttribute("userid"));
			//时间处理
			  List<String> subjectList = new ArrayList<String>();
			  List<String> mediaList = new ArrayList<String>();
					
			  try {
			     record.setStartTime(SimpleDate.formatDate(new Date()));
				} catch (ParseException e) {
								// TODO Auto-generated catch block
					e.printStackTrace();
			    }
						
					Integer trade  = (Integer)request.getSession().getAttribute("setTrade");
					if(null!=trade){
						if(trade!=1){
							record.setSetTrade(0);
						}
					}
					 String[] sList = record.getSubject().split(",");
					 String[] mList = record.getMediatype().split(",");
					 // String[] eList = record.getEmotion().split(",");
					  for(int i=0;i<sList.length;i++){
						  subjectList.add(sList[i]);
						  record.setSubjectlist(subjectList);
					  }
					  for(int i=0;i<mList.length;i++){
						  mediaList.add(mList[i]);
						  record.setMedialist(mediaList);
					  }
					
					List<SubjectStatisticalBo> list = subjectMArticleServiceImpl.selectAllByDataSource(record);
					if(list.size()>0){
						return new Response(ResponseStatus.Success,list,true);
					}else{
						return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
					}
            }
	    @RequestMapping(value = "/definedSubjectTrackingDesc", method = RequestMethod.GET)
		public Response definedSubjectTrackingDesc(HttpServletRequest request, SubjectStatisticalBo record) throws ParseException{
			record.setUserid((String)request.getSession().getAttribute("userid"));
			List<String> subjectList = new ArrayList<String>();
			  List<String> mediaList = new ArrayList<String>();
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
					 String[] sList = record.getSubject().split(",");
					 String[] mList = record.getMediatype().split(",");
					 // String[] eList = record.getEmotion().split(",");
					  for(int i=0;i<sList.length;i++){
						  subjectList.add(sList[i]);
						  record.setSubjectlist(subjectList);
					  }
					  for(int i=0;i<mList.length;i++){
						  mediaList.add(mList[i]);
						  record.setMedialist(mediaList);
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
		  * <p>方法名称：resetIndex</p>
		  * <p>方法描述：恢复默认首页信息</p>
		  * @param request
		  * @return
		  * @author 常瑞
		  * @since  2017年9月19日
		  * <p> history 2017年9月19日 Administrator  创建   <p>
		  */
		 @RequestMapping(value = "/resetIndex", method = RequestMethod.GET)
		  public Response resetIndex(HttpServletRequest request){
			 String userid = (String) request.getSession().getAttribute("userid");
			 int num = userServiceImpl.deleteByUserId(userid);
			 if(num>0){
				  return new Response(ResponseStatus.Success,AppConstant.responseInfo.SAVESUCCESS,true);
			 }else{
				 return new Response(ResponseStatus.Success,"当前已处于默认状态",true);
			 }
		 }
		 /**
		  * 
		  * <p>方法名称：selectShowmodal</p>
		  * <p>方法描述：查询自定义大屏列表</p>
		  * @param request
		  * @return
		  * @author liuyy
		  * @since  2017年9月26日
		  * <p> history 2017年9月26日 Administrator  创建   <p>
		  */
		 @RequestMapping(value = "/selectShowmodal", method = RequestMethod.GET)
		 public Response selectShowmodal(HttpServletRequest request){
			 String userid = (String) request.getSession().getAttribute("userid");
			 List<SetIndexModal> list = userServiceImpl.selectshowmodal(userid);
			 if(list.size()>0){
				 return new Response(ResponseStatus.Success,list,true);
			 }else{
				 return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
			 }
			 
		 }
		/* *//**
		  * 
		  * <p>方法名称：updateshowmodal</p>
		  * <p>方法描述：修改自定义大屏模板</p>
		  * @param info
		  * @param request
		  * @return
		  * @author liuyy
		  * @since  2017年9月26日
		  * <p> history 2017年9月26日 Administrator  创建   <p>
		  *//*
		 @RequestMapping(value = "/updateshowmodal", method = RequestMethod.GET)
		  public Response updateshowmodal(String info,HttpServletRequest request){
			  String userid = (String) request.getSession().getAttribute("userid");
			  int num = userServiceImpl.deleteByUserId(userid);
			  List<SetIndexModalBo> list = new ArrayList<SetIndexModalBo>(); 
			  list = JSONArray.parseArray(info, SetIndexModalBo.class);//这里的t是Class<T> 
			  System.out.println(info);
		     
		      Boolean flag = userServiceImpl.updateshowinfo(list, userid);
			  if(flag){
				  return new Response(ResponseStatus.Success,AppConstant.responseInfo.SAVESUCCESS,true);
			  }else{
				  return new Response(ResponseStatus.Error,AppConstant.responseInfo.SAVEERRO,false);
			  }
			  
		  }*/
		 @RequestMapping(value = "/updateshowstatus", method = RequestMethod.GET)
		 public Response updateshowstatus(SetIndexModal record,HttpServletRequest request){
			 String userid = (String) request.getSession().getAttribute("userid");
			 record.setUserid(userid);
			 int num = userServiceImpl.updatestatus(record);
			 if(num > 0){
				 return new Response(ResponseStatus.Success,num,true);
			 }else{
				 return new Response(ResponseStatus.Error,AppConstant.responseInfo.UPDATEEERRO,false);
			 }
		 }
		 @RequestMapping(value = "/selectShowAllModel", method = RequestMethod.GET)
		 public Response selectShowAllModel(String showid){
			 
			 List<SetIndexModal> list = userServiceImpl.selectModalByshowId(showid);
			 if(list.size()>0){
				 return new Response(ResponseStatus.Success,list,true);
			 }else{
				 return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
			 }
			 
		 }
		 @RequestMapping(value = "/updateshowname", method = RequestMethod.GET)
		 public Response updateshowname(SetIndexModal record){
			 int num = userServiceImpl.updateshowname(record);
			 if(num > 0){
				 return new Response(ResponseStatus.Success,num,true);
			 }else{
				 return new Response(ResponseStatus.Error,AppConstant.responseInfo.UPDATEEERRO,false);
			 }
		 }
		 @RequestMapping(value = "/deleteShowById", method = RequestMethod.GET)
		public Response deleteShowById(String showid){
			int num = userServiceImpl.deleteshowByshowid(showid);
			if(num >0){
				return new Response(ResponseStatus.Success,num,true);
			}else{
			  return new Response(ResponseStatus.Error,AppConstant.responseInfo.DELETEERRO,false);
			
			}
		}
		 @RequestMapping(value = "/selectModelByStatus", method = RequestMethod.GET)
		 public Response selectModelByStatus(HttpServletRequest request){
			 String userid=(String)request.getSession().getAttribute("userid");
			 List<SetIndexModal> list = userServiceImpl.selectshowmodalByStatus(userid);
			 if(list.size()>0){
				 return new Response(ResponseStatus.Success,list,true);
			 }else{
				 return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
			 }
		 }
		 /**
		  * 
		  * <p>方法名称：selectischeck</p>
		  * <p>方法描述：是否出了本身外 还有正在发布的大屏</p>
		  * @param showid
		  * @param request
		  * @return
		  * @author liuyy
		  * @since  2017年9月28日
		  * <p> history 2017年9月28日 Administrator  创建   <p>
		  */
		 @RequestMapping(value = "/selectischeck", method = RequestMethod.GET)
		 public Response selectischeck(String showid,HttpServletRequest request){
			 String userid = (String)request.getSession().getAttribute("userid");
			 List<SetIndexModal> list = userServiceImpl.selectischeck(userid, showid);
			 if(list.size()>0){
				 return new Response(ResponseStatus.Success,list,true);
			 }else{
				 return new Response(ResponseStatus.Error,AppConstant.responseInfo.SELECTEERRO,false);
			 }
		 }
		
}
