package com.jk.luckydraw.domain.lucky;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class LuckyHistoryBean {

    private Integer id;

    private Integer luckyUserId;

    private String name;

    private String phoneNumber;

    private String photo;

    private Integer prizeId;

    private String prizeName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT-6")
    private Date createTime;


}
