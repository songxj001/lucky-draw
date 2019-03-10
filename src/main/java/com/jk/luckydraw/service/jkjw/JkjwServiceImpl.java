package com.jk.luckydraw.service.jkjw;

import com.jk.luckydraw.domain.jkjw.DisciplinetBean;
import com.jk.luckydraw.domain.jkjw.StudentBean;
import com.jk.luckydraw.domain.jkjw.StudentDisciplinetBean;
import com.jk.luckydraw.mapper.jkjw.JkjwMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class JkjwServiceImpl implements JkjwService {

    @Autowired
    private JkjwMapper jkjwMapper;

    @Override
    public List<Map> queryClassList() {
        return jkjwMapper.queryClassList();
    }

    @Override
    public List<Map> queryWjList() {
        return jkjwMapper.queryWjList();
    }

    @Override
    public Map saveStudentDisciplinet(StudentDisciplinetBean studentDisciplinetBean) {
        HashMap<String, Object> result = new HashMap<>();
        //通过学生姓名和班级查询学生唯一信息
        String studentName = studentDisciplinetBean.getStudentName();
        Integer classId = studentDisciplinetBean.getClassId();
        try {
            StudentBean studentBean = jkjwMapper.queryUserInfoByNameAndClassId(studentName,classId);
            if (studentBean == null){
                result.put("code",1);
                result.put("msg","没有查到该学生信息，请核对");
                return result;
            }
            //通过违纪id查询违纪的详细信息
            Integer disciplinetId = studentDisciplinetBean.getDisciplinetId();
            DisciplinetBean disciplinetBean = jkjwMapper.queryWjInfoById(disciplinetId);
            if(disciplinetBean == null){
                result.put("code",3);
                result.put("msg","没有找到该违纪信息");
                return result;
            }
            //扣分
            Integer disciplinetScore = disciplinetBean.getScore();
            int count = jkjwMapper.updateStudentScore(studentBean.getId(),disciplinetScore);
            if (count != 1){
                result.put("code",4);
                result.put("msg","扣分失败");
                return result;
            }
            //保存违纪处理记录
            studentDisciplinetBean.setScore(disciplinetBean.getScore());
            studentDisciplinetBean.setScoreType(1);
            studentDisciplinetBean.setCreateTime(new Date());
            jkjwMapper.saveStudentDisciplinet(studentDisciplinetBean);
            result.put("code",0);
            result.put("msg","提交成功");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",2);
            result.put("msg","查询学生信息异常，可能班级有重名");
            return result;
        }
    }

    @Override
    public List<Map> queryWjl() {
        return jkjwMapper.queryWjl();
    }
}
