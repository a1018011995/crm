package com.doocker.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doocker.crm.mapper.CourseMapper;
import com.doocker.crm.po.Course;
import com.doocker.crm.po.CourseExample;
import com.doocker.crm.po.CourseExample.Criteria;
import com.doocker.crm.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class CourseServiceImpl implements CourseService {
	
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Override
	public Integer updateCourse(Course course) {
		System.out.println("------------");
		if(null == course.getCourseid()){
			return 0;
		}
		return courseMapper.updateByPrimaryKey(course);
	}

	@Override
	public Integer deleteCourse(Integer id) {
		return courseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer insertCourse(Course course) {
		if( null != course.getCourseid()){
			return 0;
		}
		return courseMapper.insert(course);
	}

	@Override
	public Course getCourse(Integer id) {
		return courseMapper.selectByPrimaryKey(id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageInfo<Course> selectListByPage(String courseName, Integer page, Integer rows) {
		//example通过CourseExample来动态的增加修改查询条件
		CourseExample example = new CourseExample();
		if(null != courseName){
			Criteria createCriteria = example.createCriteria();
			createCriteria.andCoursenameLike("%"+courseName+"%");
		} 
		//分页插件的使用
		PageHelper.startPage(page, rows);
		
		List<Course> selectByExample = courseMapper.selectByExample(example);
		
		PageInfo<Course> info = new PageInfo(selectByExample);
		return info;
	}

	@Override
	public Integer deleteById(Integer id) {
		
		return courseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer add(Course course) {
		return courseMapper.insert(course); 
	}

}
