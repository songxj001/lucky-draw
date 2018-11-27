package com.jk.luckydraw.service.user;

import com.jk.luckydraw.domain.lucky.LuckyHistoryBean;
import com.jk.luckydraw.domain.lucky.LuckyPersonBean;
import com.jk.luckydraw.domain.user.LuckyUserBean;
import com.jk.luckydraw.domain.user.UserBean;
import com.jk.luckydraw.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;



    @Override
    public HashMap<String, String> getUserInfo(UserBean userBean, HttpServletRequest request) {
        HashMap<String, String> result = new HashMap<>();
        HttpSession session = request.getSession(true);
        UserBean userInfo = userMapper.getUserInfo(userBean);
        if(userInfo == null){
            //账号不存在
            result.put("code","1");
            return result;
        }
        if(!userInfo.getPassword().equals(userBean.getPassword())){
            //密码错误
            result.put("code","2");
            return result;
        }
        session.setAttribute(session.getId(),userInfo);
        //登录成功
        result.put("code","0");
        return result;
    }

    @Override
    public HashMap findLuckUser() {
        HashMap<Object, Object> result = new HashMap<>();
        int count = userMapper.findLuckyUserCount();
        result.put("total",count);
        List<LuckyPersonBean> users = userMapper.findLuckyUserList();
        result.put("users",users);
        return result;
    }

    @Override
    public int findPeopleCount() {
        return userMapper.findLuckyUserCount();
    }

    @Override
    public List<LuckyUserBean> findPeopleList() {
        return userMapper.findPeopleList();
    }

    @Override
    public void delPeople(Integer[] ids) {
        userMapper.delPeople(ids);
    }

    @Override
    public void saveLuckyUser(Integer userId, Integer prizeId) {
        LuckyHistoryBean luckyHistoryBean = new LuckyHistoryBean();
        luckyHistoryBean.setPrizeId(prizeId);
        luckyHistoryBean.setLuckyUserId(userId);
        userMapper.saveLuckyUser(luckyHistoryBean);
    }

    @Override
    public int findLuckyHistoryCount() {
        return userMapper.findLuckyHistoryCount();
    }

    @Override
    public List<LuckyHistoryBean> findLuckyHistoryList() {
        return userMapper.findLuckyHistoryList();
    }

    @Override
    public void delLuckyHistory(Integer[] ids) {
        userMapper.delLuckyHistory(ids);
    }


}
