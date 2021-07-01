package com.bayside.app.opinion.showdata.user.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayside.app.opinion.showdata.sma.bo.SubjectHotspotBo;
import com.bayside.app.opinion.showdata.sma.dao.SubjectMArticleMapper;
import com.bayside.app.opinion.showdata.sma.model.SubjectHotspot;
import com.bayside.app.opinion.showdata.subject.dao.SubjectMapper;
import com.bayside.app.opinion.showdata.subject.model.Subject;
import com.bayside.app.opinion.showdata.user.bo.SetIndexModalBo;
import com.bayside.app.opinion.showdata.user.bo.UserBo;
import com.bayside.app.opinion.showdata.user.dao.SetIndexModalMapper;
import com.bayside.app.opinion.showdata.user.dao.UserMapper;
import com.bayside.app.opinion.showdata.user.model.SetIndexModal;
import com.bayside.app.opinion.showdata.user.model.User;
import com.bayside.app.opinion.showdata.user.service.UserService;

import com.bayside.app.util.AppConstant;
import com.bayside.app.util.UuidUtil;
import com.bayside.util.SimpleDate;
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
   private UserMapper userMapper;
	@Autowired
    private SetIndexModalMapper setIndexModalMapper;
	@Autowired
	private SubjectMapper subjectMapper;
	@Autowired
	private SubjectMArticleMapper subjectMArticleMapper;
	@Override
	public User selectByPassword(User record) {
		// TODO Auto-generated method stub
		return userMapper.selectByPassword(record);
	}
	@Override
	public UserBo selectPowerByUserId(UserBo record) {
		// TODO Auto-generated method stub
		return userMapper.selectPowerByUserId(record);
	}
	@Override
	public User selectUserCity(String id) {
		// TODO Auto-generated method stub
		return userMapper.selectUserCity(id);
	}
	@Override
	public User selectUserFast(User record) {
		// TODO Auto-generated method stub
		return userMapper.selectUserFast(record);
	}
	@Override
	public int insertIndexModel(SetIndexModalBo record) {
		// TODO Auto-generated method stub
		return setIndexModalMapper.insertSelective(record);
	}
	@Override
	public Boolean insertAllModalBo(List<SetIndexModalBo> list, String userid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Boolean flag = false;
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<SetIndexModalBo> smlist = new ArrayList<SetIndexModalBo>();
		String showname="";
		String showid="show"+UuidUtil.getUUID();
	    String status="";
	    String isDefault="";
	    
		  if(list!=null){
			  for(int j=0;j<list.size();j++){
				  if(null!=list.get(j).getShowname()){
					  showname = list.get(j).getShowname();
					  status = list.get(j).getStatus();
					  isDefault = list.get(j).getIsDefault();
 
				  }
//				  if(j==list.size()-1){
//					  
//				  }
			  }
			  if(!"".equals(status)){
				  if("1".equals(status)){
					//修改其他自定义为 不发布
						SetIndexModal sim = new SetIndexModal();
						sim.setStatus("0");
						sim.setUserid(userid);
						setIndexModalMapper.updatestatusByUserid(sim);
				  }
			  }
			  for(int i=0;i<list.size()-1;i++){
				  SetIndexModalBo record = list.get(i);
				  record.setId(UuidUtil.getUUID());
				  record.setUserid(userid);
				 record.setShowname(showname);
				 record.setShowid(showid);
				 record.setStatus(status);
				 record.setIsDefault(isDefault);
				 try {
					record.setStartTime(SimpleDate.formatDate(new Date()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
				   System.out.println(e1.getMessage());
				}
				  
				  smlist.add(record);
				
			  }
		  }
			
		  int sdd=0;
		  if(smlist.size()>0){
		  for (SetIndexModalBo sio : smlist) {
			sdd = setIndexModalMapper.insertSelective(sio);
					
		  }
		  if(sdd>0){
			 flag = true; 
		  }
		  }
		return flag;
	}
	@Override
	public int deleteByUserId(String userid) {
		// TODO Auto-generated method stub
		return setIndexModalMapper.deleteByUserId(userid);
	}
	@Override
	public List<Subject> selectBySelective(Subject record) {
		// TODO Auto-generated method stub
		return subjectMapper.selectBySelective(record);
	}
	@Override
	public int updateSetIndexModal(SetIndexModalBo record) {
		// TODO Auto-generated method stub
		return setIndexModalMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public int deleteIndexModal(String id) {
		// TODO Auto-generated method stub
		return setIndexModalMapper.deleteByPrimaryKey(id);
	}
	@Override
	public List<SetIndexModal> selectIndexModel(String userid) {
		// TODO Auto-generated method stub
		return setIndexModalMapper.selectIndexModel(userid);
	}
	@Override
	public List<SetIndexModal> initindexmodal(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String userid = (String) request.getSession().getAttribute("userid");
		   List<SetIndexModal> list = this.selectIndexModel(userid);
			return list;
	}
	@Override
	public List<Map<String, Object>> indexselectnewhot(SetIndexModalBo record) {
		// TODO Auto-generated method stub
		SubjectHotspotBo shb = new SubjectHotspotBo();
		shb.setUserid(record.getUserid());
		List<String> subjectList = new ArrayList<String>();
		
		  String[] sList = record.getSubject().split(",");
		 
		//  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		  for(int i=0;i<sList.length;i++){
			  subjectList.add(sList[i]);
			  shb.setSubjectlist(subjectList);
		  }
		  try {
			shb.setStartTime(SimpleDate.formatDate(new Date()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		  
		List<SubjectHotspot> list = subjectMArticleMapper.indexselecthot(shb);
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
				// TODO Auto-generated catch block
		
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
	@Override
	public Boolean updateshowinfo(List<SetIndexModalBo> list, String userid) {
		// TODO Auto-generated method stub
		Boolean flag = false;
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<SetIndexModalBo> smlist = new ArrayList<SetIndexModalBo>();
		  if(list!=null){
			  for(int i=0;i<list.size();i++){
				  SetIndexModalBo record = list.get(i);
				  record.setId(UuidUtil.getUUID());
				  record.setUserid(userid);
				  if(0 == i){
					  if(null!=record.getShowid()){
						  setIndexModalMapper.deleteshowByshowid(record.getShowid());
					  }
					 
				  }
				  if(null!=record.getShowname()){
					  record.setShowid("show"+UuidUtil.getUUID());
				  }
				  try {
					record.setStartTime(SimpleDate.formatDate(new Date()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
				   System.out.println(e1.getMessage());
				}
				  
				  smlist.add(record);
				
			  }
		  }
			
		  int sdd=0;
		  if(smlist.size()>0){
		  for (SetIndexModalBo sio : smlist) {
			sdd = setIndexModalMapper.insertSelective(sio);
					
		  }
		  if(sdd>0){
			 flag = true; 
		  }
		  }
		return flag;
	}
	@Override
	public List<SetIndexModal> selectshowmodal(String userid) {
		// TODO Auto-generated method stub
		return setIndexModalMapper.selectshowmodal(userid);
	}
	@Override
	public int updatestatus(SetIndexModal record) {
		// TODO Auto-generated method stub
		String status = record.getStatus();
		 if(!"".equals(status)){
			  if("1".equals(status)){
				//修改其他自定义为 不发布
					SetIndexModal sim = new SetIndexModal();
					sim.setStatus("0");
					sim.setUserid(record.getUserid());
					setIndexModalMapper.updatestatusByUserid(sim);
			  }
		  }
		return setIndexModalMapper.updatestatus(record);
	}
	@Override
	public List<SetIndexModal> selectModalByshowId(String showid) {
		// TODO Auto-generated method stub
		return setIndexModalMapper.selectModalByshowId(showid);
	}
	@Override
	public int updateshowname(SetIndexModal record) {
		// TODO Auto-generated method stub
		return setIndexModalMapper.updateshowname(record);
	}
	@Override
	public int deleteshowByshowid(String showid) {
		// TODO Auto-generated method stub
		return setIndexModalMapper.deleteshowByshowid(showid);
	}
	@Override
	public Boolean updateshowAllModalBo(List<SetIndexModalBo> list, String userid) {
		// TODO Auto-generated method stub
		
				Boolean flag = false;
				//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				List<SetIndexModalBo> smlist = new ArrayList<SetIndexModalBo>();
				String showname="";
				String showid="show"+UuidUtil.getUUID();
			    String status="";
			    String oldid="";
			    String isDefault ="";
				  if(list!=null){
					  for(int j=0;j<list.size();j++){
						  if(null!=list.get(j).getShowname()){
							  showname = list.get(j).getShowname();
			
							  oldid =  list.get(j).getShowid();
							  //删除原来的
							  isDefault = list.get(j).getIsDefault();
							  //查询原来的状态
							  SetIndexModal sm = setIndexModalMapper.selectshowmodalByshowid(oldid);
							  
							  status = sm.getStatus();
						  }
//						  if(j==list.size()-1){
//							  
//						  }
					  }
					  if(!"".equals(status)){
						  if("1".equals(status)){
							//修改其他自定义为 不发布
								SetIndexModal sim = new SetIndexModal();
								sim.setStatus("0");
								sim.setUserid(userid);
								setIndexModalMapper.updatestatusByUserid(sim);
						  }
					  }
					  for(int i=0;i<list.size()-1;i++){
						  SetIndexModalBo record = list.get(i);
						  record.setId(UuidUtil.getUUID());
						  record.setUserid(userid);
						 record.setShowname(showname);
						 record.setShowid(showid);
						 record.setStatus(status);
						 record.setIsDefault(isDefault);
						 try {
							record.setStartTime(SimpleDate.formatDate(new Date()));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
						   System.out.println(e1.getMessage());
						}
						  
						  smlist.add(record);
						
					  }
				  }
					
				  int sdd=0;
				  if(smlist.size()>0){
				  for (SetIndexModalBo sio : smlist) {
					sdd = setIndexModalMapper.insertSelective(sio);
							
				  }
				  if(sdd>0){
					 flag = true; 
				  }
				  }
				return flag;
	}
	@Override
	public List<SetIndexModal> selectshowmodalByStatus(String userid) {
		// TODO Auto-generated method stub
		return setIndexModalMapper.selectshowmodalByStatus(userid);
	}
	@Override
	public List<SetIndexModal> selectischeck(String userid, String showid) {
		// TODO Auto-generated method stub
		return setIndexModalMapper.selectischeck(userid, showid);
	}

}
