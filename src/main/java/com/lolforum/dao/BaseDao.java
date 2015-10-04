package com.lolforum.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	/**
	 * 保存一个对象
	 * 
	 * @param o
	 * @return
	 */
	public Serializable save(T o);
	
	/**
	 * 删除一个对象
	 * 
	 * @param o
	 */
	public void delete(T o);
	
	/**
	 * 更新一个对象
	 * 
	 * @param o
	 */
	public void update(T o);
	
	/**
	 * 保存或更新对象
	 * 
	 * @param o
	 */
	public void saveOrUpdate(T o);
	
	/**
	 * 查询
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);
	
	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param);
	
	/**
	 * 查询集合(带分页)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 *            查询第几页
	 * @param rows
	 *            每页显示几条记录
	 * @return
	 */
	public List<T> find(String hql, Object[] param, Integer page, Integer rows);
	
	/**
	 * 获得一个对象
	 * 
	 * @param c
	 *            对象类型
	 * @param id
	 * @return Object
	 */
	public T get(Serializable id);
	
	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T get(String hql, Object[] param);
	
	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);
	
	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @return 响应数目
	 */
	public Integer executeHql(String hql);
	
	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param param
	 * @return 响应数目
	 */
	public Integer executeHql(String hql, Object[] param);
	
	/**
	 * 根据ID得到实体
	 * @param id
	 * @return
	 */
	public T getById(Integer id);
	
	/**
	 * 根据ID得到实体集合
	 * @param ids
	 * @return
	 */
	public List<T> getByIds(Integer[] ids);
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 根据参数查找 select count(*) from 类
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql, Object[] param);
	
}
