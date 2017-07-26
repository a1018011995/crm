package com.doocker.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doocker.crm.controller.commont.EasyuiResult;
import com.doocker.crm.po.Clazz;
import com.doocker.crm.po.Dept;
import com.doocker.crm.service.ClazzService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("clazz")
public class ClazzController {
	
	@Autowired
	private ClazzService clazzService;
	
	@RequestMapping("get")
	//作用是反悔的clazz对象转化为json
	@ResponseBody
	public Clazz getClazz(Integer id){
		return clazzService.getClazz(id);
	}
	
	
	@RequestMapping("list")
	@ResponseBody
	public EasyuiResult listClazz(
			//@RequestParam(value="clazzname" 指的是表单中的name 
			//             ,required=false  这个参数是否必须,
			//				defaultValue="1" 此参数的默认值)
			@RequestParam(value="clazzname",required=false)String clazzName,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="3")Integer rows
			){
		PageInfo<Clazz> list = new PageInfo<Clazz>();
		//架空行
		try{
			list = clazzService.selectListByPage(clazzName,page,rows);
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
			ids =  clazzService.deleteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new EasyuiResult(0L,null,false,"error");
		}
		return new EasyuiResult(0L,ids,true,"success");
	}
	@RequestMapping("add")
	@ResponseBody
	public 	EasyuiResult add(Clazz clazz){
		clazz.setClazzid(null);
		
		Integer ids = 0;
		try {
			ids =  clazzService.add(clazz);
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
			@RequestParam(value="clazzid",required=true) Integer clazzid,
			@RequestParam(value="clazzname",required=true)String clazzname,
			@RequestParam(value="courseid",required=true)Integer courseid){
		//clazzid:row.clazzid,clazzname:iclazzname,courseid:icourseid
		Integer ids = 0;
		try {
			Clazz clazz = new Clazz();
			clazz.setClazzid(clazzid);
			clazz.setClazzname(clazzname);
			clazz.setCourseid(courseid);;
			ids =  clazzService.updateClazz(clazz);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new EasyuiResult(0L,null,false,"error");
		}
		return new EasyuiResult(0L,ids,true,"success");
	}
}
