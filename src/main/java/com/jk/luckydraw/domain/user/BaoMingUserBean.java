package com.jk.luckydraw.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BaoMingUserBean {

    private Integer id;

    private String name;

    private String phoneNumber;

    private String address;

    private Integer age;

    private String mac;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT-6")
    private Date createTime;
}
