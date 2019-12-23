package com.jk.luckydraw.mapper.jkjw;

import com.jk.luckydraw.domain.jkjw.StudentDisciplinetBean;
import org.apache.ibatis.jdbc.SQL;

public class JkjwProvider {

    public String selectWhitStudentSql(StudentDisciplinetBean studentDisciplinetBean){
        return new SQL() {
            {
                SELECT("*");
                FROM("t_student");
                if (studentDisciplinetBean.getStudentName()!=null) {
                    WHERE("name=#{studentName}");
                }
                if(studentDisciplinetBean.getStudentId()!=null) {
                    WHERE("id=#{studentId}");
                }
            }
        }.toString();
    }
}
