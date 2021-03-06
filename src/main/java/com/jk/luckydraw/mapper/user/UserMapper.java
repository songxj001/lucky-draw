package com.jk.luckydraw.mapper.user;

import com.jk.luckydraw.domain.lucky.LuckyHistoryBean;
import com.jk.luckydraw.domain.lucky.LuckyPersonBean;
import com.jk.luckydraw.domain.user.LuckyUserBean;
import com.jk.luckydraw.domain.user.UserBean;
import com.jk.luckydraw.mapper.prize.PrizeMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    @Select("select * from t_user where account = #{account}")
    UserBean getUserInfo(UserBean userBean);

    @Select("select count(1) from t_lucky_user")
    int findLuckyUserCount();

    @Select("select id,name,photo as image,photo as thumb_image from t_lucky_user order by createTime desc")
    List<LuckyPersonBean> findLuckyUserList();

    @Select("select * from t_lucky_user order by createTime desc")
    List<LuckyUserBean> findPeopleList();

    @DeleteProvider(type = UserMapper.Provider.class, method = "batchDelete")
    void delPeople(Integer[] ids);

    @Insert("insert into t_lucky_history(luckyUserId,prizeId,createTime) values(#{luckyUserId},#{prizeId},now())")
    void saveLuckyUser(LuckyHistoryBean luckyHistoryBean);

    @Select("select count(1) from t_lucky_history")
    int findLuckyHistoryCount();

    @Select("select tlh.id,tlu.name,tlu.phoneNumber,tlu.photo,tp.name as prizeName,tlh.createTime from t_lucky_history tlh left join t_prize tp on tlh.prizeId = tp.id left join t_lucky_user tlu on tlh.luckyUserId = tlu.id order by tlh.createTime desc")
    List<LuckyHistoryBean> findLuckyHistoryList();

    @DeleteProvider(type = UserMapper.Provider.class, method = "batchDeleteLuckyHistory")
    void delLuckyHistory(Integer[] ids);


    class Provider{
        /* 批量删除 */
        public String batchDelete(Map map) {
            Integer[] ids = (Integer[]) map.get("array");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM t_lucky_user WHERE id IN (");
            for (int i = 0; i < ids.length; i++) {
                sb.append("'").append(ids[i]).append("'");
                if (i < ids.length - 1)
                    sb.append(",");
            }
            sb.append(")");
            return sb.toString();
        }

        public String batchDeleteLuckyHistory(Map map){
            Integer[] ids = (Integer[]) map.get("array");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM t_lucky_history WHERE id IN (");
            for (int i = 0; i < ids.length; i++) {
                sb.append("'").append(ids[i]).append("'");
                if (i < ids.length - 1)
                    sb.append(",");
            }
            sb.append(")");
            return sb.toString();
        }
    }
}
