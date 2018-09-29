package com.haiwen.school.zx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.haiwen.school.zx.beans.*;
import com.haiwen.school.zx.mapper.AuditMapper;
import com.haiwen.school.zx.mapper.AuditinfoMapper;
import com.haiwen.school.zx.mapper.CourseMapper;
import com.haiwen.school.zx.mapper.CourseinfoMapper;
import com.haiwen.school.zx.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private AuditMapper auditMapper;
    @Autowired
    private AuditinfoMapper auditinfoMapper;
    @Autowired
    private CourseinfoMapper courseinfoMapper;
    public Map<String, Object> getAll(int page, int limit, Course course, Courseinfo courseinfo) {
        Map<String, Object> infoMap = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(course.getStartdate())) {
            String start = course.getStartdate().split(" - ")[0];
            String end = course.getStartdate().split(" - ")[1];
            infoMap.put("start", start);
            infoMap.put("end", end);
        }
        if(StringUtil.isNotEmpty(course.getTeachername())){
            infoMap.put("teachername", course.getTeachername());
        }

        if(StringUtil.isNotEmpty(course.getCoursename())){
            infoMap.put("coursename", course.getCoursename());
        }

        if(course.getCoursetype()!=null){
            infoMap.put("coursetype", course.getCoursetype());
        }

        if(course.getStatusid()!=null){
            infoMap.put("statusid", course.getStatusid());
        }
        if(course.getTeacherid()!=null){
            infoMap.put("teacherid", course.getTeacherid());
        }
        List<Course> userList = new ArrayList<Course>();
        if(courseinfo.getStuid()!=null){
            infoMap.put("stuid", courseinfo.getStuid());
            userList = courseMapper.getCourseByStu(infoMap);
        }else {
            userList = courseMapper.getAll(infoMap);
        }



        PageHelper.startPage(page, limit);
        PageInfo<Course> pageInfo=new PageInfo<Course>(userList);
        Map<String,Object> map=new HashMap<String, Object>();

        map.put("code",0);
        map.put("msg","查询不到数据");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    public Course getById(Integer id) {
        return courseMapper.getById(id);
    }

    public int addCourse(Course course , Teacher teacher) {
        if(course.getId()==null){
            course.setTeacherid(teacher.getId());
            course.setCoursetype(teacher.getProfession());
            course.setStatusid(3);
            Audit audit=new Audit();
            audit.setRemake(teacher.getTeachername()+"发起开课申请");
            audit.setSendername(teacher.getUsername());
            audit.setAudittype(1);
            audit.setStatus(1);
            auditMapper.insertSelective(audit);
            courseMapper.insertSelective(course);
            Auditinfo auditinfo=new Auditinfo();
            auditinfo.setAuditid(audit.getId());
            auditinfo.setCourseid(course.getId());
            return  auditinfoMapper.insertSelective(auditinfo);
        }
        return 0;
    }

    public int getSum(Course course) {
        return courseMapper.getSum(course);
    }

    public int getEnterSum(Courseinfo course) {
        return courseinfoMapper.getEnterSum(course) ;
    }

    public int addCourseInfo(Course course,Courseinfo courseinfo) {
        course.setRealsum(course.getRealsum()+1);
        courseMapper.updateByPrimaryKeySelective(course);
        return courseinfoMapper.insertSelective(courseinfo);
    }

    public int openCourse() {
        SimpleDateFormat aDate=new SimpleDateFormat("yyyy-MM-dd");
        String statrt=aDate.format(new Date());
        Course course = new Course();
        course.setStatusid(5);
        System.out.println(statrt);
        course.setStartdate(statrt);

        return courseMapper.openCourse(course);
    }

    public int doClose(Course course) {
        SimpleDateFormat aDate=new SimpleDateFormat("yyyy-MM-dd");
        String statrt=aDate.format(new Date());
        course.setEnddate(statrt);
        course.setStatusid(2);
        return courseMapper.updateByPrimaryKeySelective(course);
    }

    public List<Course> getByName(int id) {
        return courseMapper.getByName(id);
    }
    public Map<String, Object> getMyStudent(int page, int limit, Course course) {
        PageHelper.startPage(page, limit);
        List<Courseinfo> userList=courseinfoMapper.getMyStudent(course);
        PageInfo<Courseinfo> pageInfo=new PageInfo<Courseinfo>(userList);
        Map<String,Object> map=new HashMap<String, Object>();

        map.put("code",0);
        map.put("msg","查询不到数据");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    public int docredit(Courseinfo courseinfo) {
        return courseinfoMapper.updateByPrimaryKeySelective(courseinfo);
    }
}
