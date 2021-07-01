package com.bayside.app.opinion.showdata.user.dao;

import com.bayside.app.opinion.showdata.user.bo.UserBo;
import com.bayside.app.opinion.showdata.user.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User selectByPassword(User record);
    UserBo selectPowerByUserId(UserBo record);
    
    User selectUserCity(String id);
    
    User selectUserFast(User record);
}