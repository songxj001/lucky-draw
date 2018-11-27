package com.jk.luckydraw.service.user;

import com.jk.luckydraw.domain.lucky.LuckyHistoryBean;
import com.jk.luckydraw.domain.user.LuckyUserBean;
import com.jk.luckydraw.domain.user.UserBean;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface UserService {

    HashMap<String,String> getUserInfo(UserBean userBean, HttpServletRequest request);

    HashMap findLuckUser();

    int findPeopleCount();

    List<LuckyUserBean> findPeopleList();

    void delPeople(Integer[] ids);

    void saveLuckyUser(Integer userId, Integer prizeId);

    int findLuckyHistoryCount();

    List<LuckyHistoryBean> findLuckyHistoryList();

    void delLuckyHistory(Integer[] ids);
}
