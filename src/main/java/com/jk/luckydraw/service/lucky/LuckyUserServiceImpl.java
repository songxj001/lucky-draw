package com.jk.luckydraw.service.lucky;

import com.jk.luckydraw.domain.user.LuckyUserBean;
import com.jk.luckydraw.mapper.lucky.LuckyUserMapper;
import com.jk.luckydraw.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LuckyUserServiceImpl implements LuckyUserService {

    @Autowired
    private LuckyUserMapper luckyUserMapper;

    @Value("${img.location}")
    private String location;

    @Value("${img.serverpath}")
    private String serverpath;
    @Override
    public HashMap save(LuckyUserBean luckyUserBean, MultipartFile fileImg, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        luckyUserBean.setIp(IpUtil.getIpAddr(request));
        //获取用户的浏览器指纹
        String mac = request.getHeader("mac");
        luckyUserBean.setMac(mac);
        //检查用户是否参与过
        int count = luckyUserMapper.checkUser(luckyUserBean);
        if (count > 0){
            result.put("code",1);
            result.put("msg","您已经参加过了不能在参加了哦");
            result.put("icon",6);
            return result;
        }
        try {
            String originalFilename = fileImg.getOriginalFilename();
            //  String fileUpload = FileUtil.FileUpload(fileImg, request,location);
            //上传图片并压缩
            String resize = ImageUtils.resize(fileImg.getInputStream(), 400, 400,true, serverpath);
            luckyUserBean.setPhoto(resize);
            //luckyUserBean.setPhoto(serverpath+fileUpload);
            luckyUserMapper.saveLuckyUser(luckyUserBean);
            result.put("code",0);
            result.put("msg","参与成功,祝您中大奖");
            result.put("icon",6);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",2);
            result.put("msg","网络不给力,请稍后再试");
            result.put("icon",5);
        }
        return result;
    }
}
