package com.jk.luckydraw.service.jkjw;

import com.jk.luckydraw.common.CommonConf;
import com.jk.luckydraw.domain.jkjw.DisciplinetBean;
import com.jk.luckydraw.domain.jkjw.StudentBean;
import com.jk.luckydraw.domain.jkjw.StudentDisciplinetBean;
import com.jk.luckydraw.mapper.jkjw.JkjwMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
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
        Integer studentId = studentDisciplinetBean.getStudentId();
        try {
            List<StudentBean> studentBeans = jkjwMapper.queryUserInfoByNameAndClassId(studentDisciplinetBean);
            if (studentBeans == null || studentBeans.size() <= 0){
                result.put("code",1);
                result.put("msg","没有查到该学生信息，请核对");
                return result;
            }
            //判断是否有重名学生
            if (studentBeans.size() > 1){
                result.put("code",5);
                result.put("msg","发现重名学生，请选择对应学生的身份证号");
                result.put("data",studentBeans);
                return result;
            }
            StudentBean studentBean = studentBeans.get(0);
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
            studentDisciplinetBean.setStudentId(studentBean.getId());
            studentDisciplinetBean.setCreateTime(new Date());
            jkjwMapper.saveStudentDisciplinet(studentDisciplinetBean);
            result.put("code",0);
            result.put("msg","提交成功");
            //判断短信开关
            if (CommonConf.SMS_SWITCH){
                //发送短信通知
                //剩余分数
                int remainingScore = studentBean.getScore()-disciplinetScore;
                Thread thread = new Thread(new SendSms(studentBean.getName(),disciplinetBean.getTitle(),disciplinetScore,remainingScore,studentBean.getPhonenumber()));
                thread.start();
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",2);
            result.put("msg","查询学生信息异常");
            return result;
        }
    }

    @Override
    public List<Map> queryWjl() {
        return jkjwMapper.queryWjl();
    }

    @Override
    public List<Map> queryMouthsWjl() {
        return jkjwMapper.queryMouthsWjl();
    }

    @Override
    public Map queryStudentWjList(String phone, String password, HttpSession session) {
        HashMap<String, Object> result = new HashMap<>();
        Object attribute = session.getAttribute(phone);
        if (attribute == null || !attribute.toString().equals(password)){
            result.put("code",1);
            result.put("msg","验证码错误");
            return result;
        }
        //查询学生信息，
        List<StudentBean> studentBeans = jkjwMapper.queryStudentList(phone);
        StudentBean studentBean = studentBeans.get(0);
        //查询违纪信息
        List<Map> wjList = jkjwMapper.queryWjListByName(studentBean.getName());
        result.put("code",0);
        result.put("msg","成功");
        result.put("data",wjList);
        return result;
    }
}
