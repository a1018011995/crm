package com.doocker.crm;

import com.doocker.crm.controller.ClazzController;
import com.doocker.crm.po.Clazz;

public class text {
	public static void main(String[] args) {
		ClazzController c =  new ClazzController();

		c.update(10, "33", 2);
	}
}
