package com.jk.luckydraw.service.jkjw;

import com.jk.luckydraw.domain.jkjw.StudentDisciplinetBean;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface JkjwService {

    List<Map> queryClassList();

    List<Map> queryWjList();

    Map saveStudentDisciplinet(StudentDisciplinetBean studentDisciplinetBean);

    List<Map> queryWjl();

    List<Map> queryMouthsWjl();

    Map queryStudentWjList(String phone, String password, HttpSession session);

}
