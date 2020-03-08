package com.jk.luckydraw.mapper.lucky;

import com.jk.luckydraw.domain.user.BaoMingUserBean;
import com.jk.luckydraw.domain.user.LuckyUserBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface LuckyUserMapper {

    @Select("select count(1) from t_lucky_user where name = #{name} or phoneNumber = #{phoneNumber} or mac = #{mac}")
    int checkUser(LuckyUserBean luckyUserBean);

    @Insert("insert into t_lucky_user(name,sex,photo,phoneNumber,createTime,mac,ip) values(#{name},#{sex},#{photo},#{phoneNumber},now(),#{mac},#{ip})")
    void saveLuckyUser(LuckyUserBean luckyUserBean);

    @Insert("insert into t_baoming_user(name,age,phoneNumber,address,mac,createTime) values(#{name},#{age},#{phoneNumber},#{address},#{mac},now())")
    void saveBaoMingUser(BaoMingUserBean baoMingUserBean);
}
