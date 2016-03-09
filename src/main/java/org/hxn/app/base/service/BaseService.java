package org.hxn.app.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hxn.app.util.Pager;

public interface BaseService<T> {
	/**
	 * 持久化实体
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 持久化实体列表
	 * 
	 * @param entity
	 */
	void save(List<T> entities);

	/**
	 * 根据主键查询实体
	 * 
	 * @param <T>
	 * @param clazz
	 *            实体类
	 * @param id
	 *            主键
	 * @return
	 */
	T get(Serializable id);

	/**
	 * 根据主键id列表查询实体列表
	 * 
	 * @param ids
	 * @return
	 */
	List<T> get(List<? extends Serializable> ids);

	/**
	 * 根据主键id Set集查询实体列表
	 * 
	 * @param ids
	 * @return
	 */
	List<T> get(Set<? extends Serializable> ids);

	/**
	 * 根据主键id数组查询实体列表
	 * 
	 * @param ids
	 * @return
	 */
	List<T> get(Serializable[] ids);

	/**
	 * 根据属性名-值对，查询满足条件的唯一实体
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	T get(String propertyName, Object propertyValue);

	/**
	 * 根据属性名-值对，查询满足条件的实体列表
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	List<T> getList(String propertyName, Object propertyValue);

	/**
	 * 获取满足某一属性值在指定数组中的实体列表
	 * 
	 * @param propertyName
	 * @param values
	 * @return
	 */
	List<T> getList(String propertyName, Object[] propertyValues);

	/**
	 * 获取满足某一属性值在指定列表中的实体列表
	 * 
	 * @param propertyName
	 * @param values
	 * @return
	 */
	List<T> getList(String propertyName, List<?> propertyValues);

	/**
	 * 获取满足某一属性值在指定Set集中的实体列表
	 * 
	 * @param propertyName
	 * @param values
	 * @return
	 */
	List<T> getList(String propertyName, Set<?> propertyValues);

	/**
	 * 模糊查询:
	 * 
	 * @param propertyName
	 * @param propertyValue
	 *            可传入值形如:"%小%"
	 * @return
	 */
	List<T> getListByLike(String propertyName, String propertyValue);

	/**
	 * 无筛选条件查询所有实体
	 * 
	 * @return
	 */
	List<T> getAll();

	/**
	 * 无筛选条件查询所有实体的总数
	 * 
	 * @return
	 */
	Long getTotalCount();

	/**
	 * 更新实体
	 */
	void update(T entity);

	/**
	 * 更新实体列表
	 */
	void update(List<T> entities);

	/**
	 * 保存或更新实体
	 */
	void merge(T entity);

	/**
	 * 保存或更新实体列表
	 */
	void merge(List<T> entities);

	/**
	 * 无筛选条件，仅分页
	 * 
	 * @return
	 */
	List<T> getByPage(Pager<T> pager);

	/**
	 * 根据属性名-值对，查询满足条件的实体分页列表: 单一条件分页
	 * 
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	List<T> getList(String propertyName, Object propertyValue, Pager<T> pager);

	/**
	 * 单筛选条件查询所有实体的总数
	 * 
	 * @return
	 */
	Long getTotalCount(String propertyName, Object propertyValue);

	/**
	 * getReference 查询:类似于hibernate中的load()方法
	 * 
	 * @param id
	 * @return
	 */
	T getReference(Serializable id);

	/**
	 * 根据主键删除实体
	 * 
	 * @param id
	 */
	void delete(Serializable id);

	/**
	 * 删除实体
	 * 
	 * @param entity
	 */
	void delete(T entity);

	/**
	 * 根据id数组批量删除实体
	 * 
	 * @param ids
	 */
	void delete(Serializable[] ids);

	/**
	 * 根据id列表批量删除实体
	 * 
	 * @param ids
	 */
	void delete(List<? extends Serializable> ids);

	/**
	 * 根据id Set集批量删除实体
	 * 
	 * @param ids
	 */
	void delete(Set<? extends Serializable> ids);

	/**
	 * 根据属性名-值对删除实体
	 * 
	 * @param propertyName
	 * @param propertyValue
	 */
	void delete(String propertyName, Object propertyValue);

	/**
	 * 删除属性名propertyName在propertyValues数组中的实体
	 * 
	 * @param propertyName
	 * @param propertyValues
	 */
	void delete(String propertyName, Object[] propertyValues);

	/**
	 * 删除属性名propertyName在propertyValues List列表中的实体
	 * 
	 * @param propertyName
	 * @param propertyValues
	 */
	void delete(String propertyName, List<?> propertyValues);

	/**
	 * 删除属性名propertyName在propertyValues Set集中的实体
	 * 
	 * @param propertyName
	 * @param propertyValues
	 */
	void delete(String propertyName, Set<?> propertyValues);

	/**
	 * 根据sql查询实体列表
	 * 
	 * @param sql
	 * @param c
	 * @return
	 */
	List<T> getAllBySql(String sql);

	/**
	 * 根据sql分页查询实体列表
	 * 
	 * @param sql
	 * @param pager
	 * @return
	 */
	List<T> getAllBySqlPage(String sql, Pager<T> pager);
}
