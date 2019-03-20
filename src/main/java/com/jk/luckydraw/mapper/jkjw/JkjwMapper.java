package com.jk.luckydraw.mapper.jkjw;

import com.jk.luckydraw.domain.jkjw.DisciplinetBean;
import com.jk.luckydraw.domain.jkjw.StudentBean;
import com.jk.luckydraw.domain.jkjw.StudentDisciplinetBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SelectBuilder.FROM;
import static org.apache.ibatis.jdbc.SelectBuilder.SELECT;
import static org.apache.ibatis.jdbc.SelectBuilder.WHERE;

public interface JkjwMapper {
    @Select("select tc.id,tc.className,tt.`name`,tt.id as teacherId from t_class tc left join t_teacher tt on tc.teacherId = tt.id order by tc.createtTime desc")
    List<Map> queryClassList();

    @Select("select * from t_disciplinet order by score asc")
    List<Map> queryWjList();

    @Select("select * from t_disciplinet where id = #{value}")
    DisciplinetBean queryWjInfoById(Integer disciplinetId);

    @Update("update t_student set score = score - #{disciplinetScore} where id = #{id}")
    int updateStudentScore(@Param("id") Integer id, @Param("disciplinetScore") Integer disciplinetScore);

    @Insert("insert into t_student_disciplinet(studentName,classId,teacherName,disciplinetId,score,scoreType,img,detail,createTime,studentId) values(#{studentName},#{classId},#{teacherName},#{disciplinetId},#{score},#{scoreType},#{img},#{detail},#{createTime},#{studentId})")
    void saveStudentDisciplinet(StudentDisciplinetBean studentDisciplinetBean);

    @Select("select CONCAT(a.months,'月') as months,a.count from (" +
            "   select MONTH(t.createTime) as months ,count(1) as count" +
            "   from t_student_disciplinet t " +
            "       where DATE_FORMAT(t.createTime,'%Y') = DATE_FORMAT(now(),'%Y')" +
            "       GROUP BY MONTH(t.createTime) " +
            "       order by MONTH(t.createTime) ASC" +
            ") as a")
    List<Map> queryWjl();

    @Select("SELECT CONCAT(a.days, '日') AS days,a.count FROM(" +
            "   SELECT DAY (t.createTime) AS days, count(1) AS count" +
            "       FROM t_student_disciplinet t" +
            "       WHERE" +
            "           DATE_FORMAT(t.createTime, '%Y-%M') = DATE_FORMAT(now(), '%Y-%M')" +
            "           GROUP BY DAY (t.createTime)" +
            "           ORDER BY DAY (t.createTime) ASC" +
            ") a")
    List<Map> queryMouthsWjl();

    @Select("select name from t_student where phonenumber = #{value}")
    List<StudentBean> queryStudentList(String phone);

    @Select("select * from t_student_disciplinet where studentName = #{value}")
    List<Map> queryWjListByName(String name);

    @SelectProvider(type= JkjwProvider.class,method="selectWhitStudentSql")
    List<StudentBean> queryUserInfoByNameAndClassId(StudentDisciplinetBean studentDisciplinetBean);


}
