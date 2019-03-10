package com.jk.luckydraw.service.jkjw;

import com.jk.luckydraw.domain.jkjw.StudentDisciplinetBean;

import java.util.List;
import java.util.Map;

public interface JkjwService {

    List<Map> queryClassList();

    List<Map> queryWjList();

    Map saveStudentDisciplinet(StudentDisciplinetBean studentDisciplinetBean);

    List<Map> queryWjl();
}
