package com.doocker.crm.service;

import com.doocker.crm.po.Clazz;
import com.github.pagehelper.PageInfo;

public interface ClazzService {
	Integer updateClazz(Clazz clazz);
	Integer deleteClazz(Integer id);
	Integer insertClazz(Clazz clazz);
	Clazz getClazz(Integer id);
	PageInfo<Clazz> selectListByPage(String clazzName, Integer page, Integer rows);
	Integer deleteById(Integer id);
	Integer add(Clazz clazz);
}
