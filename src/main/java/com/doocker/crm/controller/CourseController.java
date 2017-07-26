package com.doocker.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doocker.crm.controller.commont.EasyuiResult;
import com.doocker.crm.po.Course;
import com.doocker.crm.service.CourseService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping("get")
	//作用是反悔的course对象转化为json
	@ResponseBody
	public Course getCourse(@RequestParam(value="id",required=true)Integer id){
		return courseService.getCourse(id);
	}
	
	
	@RequestMapping("list")
	@ResponseBody
	public EasyuiResult listCourse(
			//@RequestParam(value="coursename" 指的是表单中的name 
			//             ,required=false  这个参数是否必须,
			//				defaultValue="1" 此参数的默认值)
			@RequestParam(value="coursename",required=false)String courseName,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="3")Integer rows
			){
		PageInfo<Course> list = new PageInfo<Course>();
		//架空行
		try{
			list = courseService.selectListByPage(courseName,page,rows);
		}catch(Exception e){
			e.printStackTrace();
			return new EasyuiResult(0L,null,false,"error");
		}
		return new EasyuiResult(list.getTotal(),list.getList(),true,"success");
		
	}
	@RequestMapping("del")
	@ResponseBody
	public 	EasyuiResult delete(@RequestParam(value="id",required=true) Integer id){
		Integer ids = 0;
		try {
			ids =  courseService.deleteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new EasyuiResult(0L,null,false,"error");
		}
		return new EasyuiResult(0L,ids,true,"success");
	}
	@RequestMapping("add")
	@ResponseBody
	public 	EasyuiResult add(Course course){
		course.setCourseid(null);
		Integer ids = 0;
		try {
			ids =  courseService.add(course);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new EasyuiResult(0L,null,false,"error");
		}
		return new EasyuiResult(0L,ids,true,"success");
	}
	
	@RequestMapping("update")
	@ResponseBody
	public 	EasyuiResult update(
			@RequestParam(value="courseid",required=true) Integer courseid,
			@RequestParam(value="coursename",required=true) String coursename,
			@RequestParam(value="description",required=true)String description,
			@RequestParam(value="learntime",required=true)Integer learntime,		
			@RequestParam(value="money",required=true)Integer money){
		//{coursename:icoursename,description:idescription,
		//learntime:ilearntime,money:imoney}
		//data:{coursename:icoursename,description:idescription,learntime:ilearntime,money:imoney}
		System.out.println("------------"+coursename);
		Integer ids = 0;
		try {
			Course course = new Course();
			course.setCourseid(courseid);
			course.setCoursename(coursename);
			course.setDescription(description);
			course.setLearntime(learntime);
			course.setMoney(money);
			ids =  courseService.updateCourse(course);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new EasyuiResult(0L,null,false,"error");
		}
		return new EasyuiResult(0L,ids,true,"success");
	}
}
