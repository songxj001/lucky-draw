package com.jk.luckydraw.service.user;

import com.jk.luckydraw.domain.lucky.LuckyPersonBean;
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
}
