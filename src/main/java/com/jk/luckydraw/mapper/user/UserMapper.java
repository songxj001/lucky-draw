package com.jk.luckydraw.mapper.user;

import com.jk.luckydraw.domain.lucky.LuckyPersonBean;
import com.jk.luckydraw.domain.user.UserBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from t_user where account = #{account}")
    UserBean getUserInfo(UserBean userBean);

    @Select("select count(1) from t_lucky_user")
    int findLuckyUserCount();

    @Select("select id,name,photo as image,photo as thumb_image from t_lucky_user")
    List<LuckyPersonBean> findLuckyUserList();
}
