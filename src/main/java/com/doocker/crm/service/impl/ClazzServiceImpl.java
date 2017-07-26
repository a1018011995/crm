package com.doocker.crm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doocker.crm.mapper.ClazzMapper;
import com.doocker.crm.po.Clazz;
import com.doocker.crm.po.ClazzExample;
import com.doocker.crm.po.ClazzExample.Criteria;
import com.doocker.crm.service.ClazzService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class ClazzServiceImpl implements ClazzService {
	
	
	@Autowired
	private ClazzMapper clazzMapper;
	
	@Override
	public Integer updateClazz(Clazz clazz) {
		if(null == clazz.getClazzid()){
			return 0;
		}
		return clazzMapper.updateByPrimaryKey(clazz);
	}

	@Override
	public Integer deleteClazz(Integer id) {
		return clazzMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer insertClazz(Clazz clazz) {
		if( null != clazz.getClazzid()){
			return 0;
		}
		return clazzMapper.insert(clazz);
	}

	@Override
	public Clazz getClazz(Integer id) {
		return clazzMapper.selectByPrimaryKey(id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageInfo<Clazz> selectListByPage(String clazzName, Integer page, Integer rows) {
		List<HashMap> selectByPage = null;
		//分页插件的使用
		PageHelper.startPage(page, rows);
		if(null!=clazzName){
			selectByPage = clazzMapper.selectByPage("%"+clazzName+"%");
		}else{
			selectByPage = clazzMapper.selectByPage(null);
		}
		
		
		PageInfo<Clazz> info = new PageInfo(selectByPage);
		return info;
	}

	@Override
	public Integer deleteById(Integer id) {
		
		return clazzMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer add(Clazz clazz) {
		return clazzMapper.insert(clazz); 
	}

}
