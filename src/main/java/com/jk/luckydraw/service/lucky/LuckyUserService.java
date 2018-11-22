package com.jk.luckydraw.service.lucky;

import com.jk.luckydraw.domain.user.LuckyUserBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface LuckyUserService {
    HashMap save(LuckyUserBean luckyUserBean, MultipartFile fileImg, HttpServletRequest request);
}
