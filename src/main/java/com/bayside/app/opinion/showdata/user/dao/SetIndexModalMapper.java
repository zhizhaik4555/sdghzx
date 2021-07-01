package com.bayside.app.opinion.showdata.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayside.app.opinion.showdata.user.bo.SetIndexModalBo;
import com.bayside.app.opinion.showdata.user.model.SetIndexModal;



public interface SetIndexModalMapper {
    int deleteByPrimaryKey(String id);

    int insert(SetIndexModal record);

    int insertSelective(SetIndexModalBo record);

    SetIndexModal selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SetIndexModalBo record);

    int updateByPrimaryKey(SetIndexModal record);
     
    List<SetIndexModal> selectByUserid(String userid);
    List<SetIndexModal> selectIndexModel(String userid);
    SetIndexModal selectModalById(String id);
    int deleteByUserId(String userid);
    int deleteshowByshowid(String showid);
    List<SetIndexModal> selectshowmodal(String userid);
    int updatestatus(SetIndexModal record);
    List<SetIndexModal> selectModalByshowId(String showid);
    int updateshowname(SetIndexModal record);
    int updatestatusByUserid(SetIndexModal record);
    List<SetIndexModal> selectshowmodalByStatus(String userid);
    SetIndexModal selectshowmodalByshowid(String showid);
    List<SetIndexModal> selectischeck(@Param("userid")String userid,@Param("showid")String showid);
}