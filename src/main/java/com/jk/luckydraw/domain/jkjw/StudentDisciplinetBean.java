package com.jk.luckydraw.domain.jkjw;

import lombok.Data;

import java.util.Date;

@Data
public class StudentDisciplinetBean {

    private Integer id;

    private String studentName;

    private Integer classId;

    private String teacherName;

    private Integer disciplinetId;

    private Integer score;

    private Integer scoreType;

    private String img;

    private String detail;

    private Date createTime;
}
