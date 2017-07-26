package com.doocker.crm.service;

import com.doocker.crm.po.Course;
import com.github.pagehelper.PageInfo;

public interface CourseService {
	Integer updateCourse(Course course);
	Integer deleteCourse(Integer id);
	Integer insertCourse(Course course);
	Course getCourse(Integer id);
	PageInfo<Course> selectListByPage(String courseName, Integer page, Integer rows);
	Integer deleteById(Integer id);
	Integer add(Course course);
}
