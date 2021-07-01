package com.bayside.app.opinion.showdata.user.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bayside.app.opinion.showdata.subject.model.Subject;
import com.bayside.app.opinion.showdata.user.bo.SetIndexModalBo;
import com.bayside.app.opinion.showdata.user.bo.UserBo;
import com.bayside.app.opinion.showdata.user.model.SetIndexModal;
import com.bayside.app.opinion.showdata.user.model.User;




public interface UserService {
	User selectByPassword(User record);
	UserBo selectPowerByUserId(UserBo record);

    User selectUserCity(String id);
    
    User selectUserFast(User record);
    
    int insertIndexModel(SetIndexModalBo record);
    
    Boolean insertAllModalBo(List<SetIndexModalBo> list, String userid);
    
    int deleteByUserId(String userid);
    
    List<Subject> selectBySelective(Subject record);
    
    int updateSetIndexModal(SetIndexModalBo record);
    
    int deleteIndexModal(String id);
    
    List<SetIndexModal> selectIndexModel(String userid);
    List<SetIndexModal> initindexmodal(HttpServletRequest request);
    List<Map<String, Object>> indexselectnewhot(SetIndexModalBo record);
    Boolean updateshowinfo(List<SetIndexModalBo> list, String userid);
    List<SetIndexModal> selectshowmodal(String userid);
    int updatestatus(SetIndexModal record);
    List<SetIndexModal> selectModalByshowId(String showid);
    int updateshowname(SetIndexModal record);
    int deleteshowByshowid(String showid);
    Boolean updateshowAllModalBo(List<SetIndexModalBo> list, String userid);
    List<SetIndexModal> selectshowmodalByStatus(String userid);
   // SetIndexModal selectshowmodalByshowid(String showid);
    List<SetIndexModal> selectischeck(String userid,String showid);
}
