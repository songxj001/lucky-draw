package com.jk.luckydraw.mapper.jkjw;

import com.jk.luckydraw.domain.jkjw.DisciplinetBean;
import com.jk.luckydraw.domain.jkjw.StudentBean;
import com.jk.luckydraw.domain.jkjw.StudentDisciplinetBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface JkjwMapper {
    @Select("select tc.id,tc.className,tt.`name`,tt.id as teacherId from t_class tc left join t_teacher tt on tc.teacherId = tt.id order by tc.createtTime desc")
    List<Map> queryClassList();

    @Select("select * from t_disciplinet order by score asc")
    List<Map> queryWjList();

    @Select("select * from t_student where name = #{studentName} and classId = #{classId}")
    StudentBean queryUserInfoByNameAndClassId(@Param("studentName") String studentName, @Param("classId") Integer classId);

    @Select("select * from t_disciplinet where id = #{value}")
    DisciplinetBean queryWjInfoById(Integer disciplinetId);

    @Update("update t_student set score = score - #{disciplinetScore} where id = #{id}")
    int updateStudentScore(@Param("id") Integer id, @Param("disciplinetScore") Integer disciplinetScore);

    @Insert("insert into t_student_disciplinet(studentName,classId,teacherName,disciplinetId,score,scoreType,img,detail,createTime) values(#{studentName},#{classId},#{teacherName},#{disciplinetId},#{score},#{scoreType},#{img},#{detail},#{createTime})")
    void saveStudentDisciplinet(StudentDisciplinetBean studentDisciplinetBean);

    @Select("select CONCAT(a.months,'月') as months,a.count from (" +
            "   select MONTH(t.createTime) as months ,count(1) as count" +
            "   from t_student_disciplinet t " +
            "       where DATE_FORMAT(t.createTime,'%Y') = DATE_FORMAT(now(),'%Y')" +
            "       GROUP BY MONTH(t.createTime) " +
            "       order by MONTH(t.createTime) ASC" +
            ") as a")
    List<Map> queryWjl();
}
