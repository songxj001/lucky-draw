package com.jk.luckydraw.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class LuckyUserBean {

    private Integer id;

    private String name;

    private Integer sex;

    private String photo;

    private String phoneNumber;

    private String mac;

    private String ip;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT-6")
    private Date createTime;

}
