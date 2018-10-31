package com.unicom.ip.zyh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.unicom.ip.zyh.beans.Logininfo;
import com.unicom.ip.zyh.beans.Student;
import com.unicom.ip.zyh.mapper.LogininfoMapper;
import com.unicom.ip.zyh.mapper.StudentMapper;
import com.unicom.ip.zyh.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private LogininfoMapper logininfoMapper;
    public Map<String, Object> getAll(int page, int limit, Student student) {
        Map<String, Object> infoMap = new HashMap<String, Object>();
        if(StringUtil.isNotEmpty(student.getStuname())){
            infoMap.put("stuname", student.getStuname());
        }

        if(student.getSex()!=null){
            infoMap.put("sex", student.getSex());
        }


        PageHelper.startPage(page, limit);
        List<Student> userList=studentMapper.getAll(infoMap);
        PageInfo<Student> pageInfo=new PageInfo<Student>(userList);
        Map<String,Object> map=new HashMap<String, Object>();

        map.put("code",0);
        map.put("msg","查询不到数据");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    public Student toUpd(String username) {
        return studentMapper.getByUsername(username);
    }

    public int doUpdate(Student student) {
        if(student.getId()!=null){
           return studentMapper.updateByPrimaryKeySelective(student);
        }
        Logininfo logininfo = new Logininfo();
        logininfo.setUsername(student.getUsername());
        logininfo.setPowerid(3);
        logininfoMapper.updByName(logininfo);
        return studentMapper.insertSelective(student);
    }
}

