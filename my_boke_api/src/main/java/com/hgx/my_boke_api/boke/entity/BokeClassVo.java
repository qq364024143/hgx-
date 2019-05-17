package com.hgx.my_boke_api.boke.entity;

import java.io.Serializable;

/**
 * 博客分类实体
 * @author hgx
 *
 */
public class BokeClassVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String classId;
	private String className;
	public BokeClassVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BokeClassVo(String classId, String className) {
		super();
		this.classId = classId;
		this.className = className;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String toString() {
		return "BokeClassVo [classId=" + classId + ", className=" + className
				+ "]";
	}
	
	
}
