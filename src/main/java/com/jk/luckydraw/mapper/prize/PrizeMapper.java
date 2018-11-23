package com.jk.luckydraw.mapper.prize;

import com.jk.luckydraw.domain.prize.PrizeBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface PrizeMapper {
    
    @Select("select t.id,t.name,t.sort,t.img,t.createTime,t.updateTime,tu.account as userName from t_prize t left join t_user tu on t.userId = tu.id order by t.sort asc")
    List<PrizeBean> findPrizeList();

    @Select("select count(1) from t_prize t left join t_user tu on t.userId = tu.id ")
    int findPrizeCount();

    @Insert("insert into t_prize(name,img,sort,createTime,userId) values(#{name},#{img},#{sort},now(),#{userId})")
    void savePrize(PrizeBean prizeBean);


    @DeleteProvider(type = Provider.class, method = "batchDelete")
    void delPrize(Integer[] ids);

    class Provider{
        /* 批量删除 */
        public String batchDelete(Map map) {
            Integer[] ids = (Integer[]) map.get("array");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM t_prize WHERE id IN (");
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
