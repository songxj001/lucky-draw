package com.jk.luckydraw.service.user;

import com.jk.luckydraw.domain.user.UserBean;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface UserService {

    HashMap<String,String> getUserInfo(UserBean userBean, HttpServletRequest request);

    HashMap findLuckUser();
}
