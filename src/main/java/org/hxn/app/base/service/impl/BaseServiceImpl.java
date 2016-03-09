package org.hxn.app.base.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hxn.app.base.dao.BaseDao;
import org.hxn.app.base.service.BaseService;
import org.hxn.app.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private BaseDao<T> baseDao;

	protected void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void save(T entity) {
		baseDao.save(entity);

	}

	@Override
	public void save(List<T> entities) {
		baseDao.save(entities);
	}

	@Override
	public T get(Serializable id) {
		T entity = baseDao.get(id);
		return entity;
	}

	@Override
	public List<T> get(List<? extends Serializable> ids) {
		List<T> entities = baseDao.get(ids);
		return entities;
	}

	@Override
	public List<T> get(Set<? extends Serializable> ids) {
		List<T> entities = baseDao.get(ids);
		return entities;
	}

	@Override
	public List<T> get(Serializable[] ids) {
		List<T> entities = baseDao.get(ids);
		return entities;
	}

	@Override
	public T get(String propertyName, Object propertyValue) {
		T entity = baseDao.get(propertyName, propertyValue);
		return entity;
	}

	@Override
	public List<T> getList(String propertyName, Object propertyValue) {
		List<T> entities = baseDao.getList(propertyName, propertyValue);
		return entities;
	}

	@Override
	public List<T> getList(String propertyName, Object[] propertyValues) {
		List<T> entities = baseDao.getList(propertyName, propertyValues);
		return entities;
	}

	@Override
	public List<T> getList(String propertyName, List<?> propertyValues) {
		List<T> entities = baseDao.getList(propertyName, propertyValues);
		return entities;
	}

	@Override
	public List<T> getList(String propertyName, Set<?> propertyValues) {
		List<T> entities = baseDao.getList(propertyName, propertyValues);
		return entities;
	}

	@Override
	public List<T> getListByLike(String propertyName, String propertyValue) {
		List<T> entities = baseDao.getListByLike(propertyName, propertyValue);
		return entities;
	}

	@Override
	public List<T> getAll() {
		List<T> entities = baseDao.getAll();
		return entities;
	}

	@Override
	public Long getTotalCount() {
		return baseDao.getTotalCount();
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public void update(List<T> entities) {
		baseDao.update(entities);
	}

	@Override
	public void merge(T entity) {
		baseDao.merge(entity);
	}

	@Override
	public void merge(List<T> entities) {
		baseDao.merge(entities);
	}

	@Override
	public List<T> getByPage(Pager<T> pager) {
		return baseDao.getByPage(pager);
	}

	@Override
	public List<T> getList(String propertyName, Object propertyValue, Pager<T> pager) {
		return baseDao.getList(propertyName, propertyValue, pager);
	}

	@Override
	public Long getTotalCount(String propertyName, Object propertyValue) {
		return baseDao.getTotalCount(propertyName, propertyValue);
	}

	@Override
	public T getReference(Serializable id) {
		return baseDao.getReference(id);
	}

	@Override
	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	@Override
	public void delete(T entity) {
		baseDao.delete(entity);
	}

	@Override
	public void delete(Serializable[] ids) {
		baseDao.delete(ids);
	}

	@Override
	public void delete(List<? extends Serializable> ids) {
		baseDao.delete(ids);
	}

	@Override
	public void delete(Set<? extends Serializable> ids) {
		baseDao.delete(ids);
	}

	@Override
	public void delete(String propertyName, Object propertyValue) {
		baseDao.delete(propertyName, propertyValue);
	}

	@Override
	public void delete(String propertyName, Object[] propertyValues) {
		baseDao.delete(propertyName, propertyValues);
	}

	@Override
	public void delete(String propertyName, List<?> propertyValues) {
		baseDao.delete(propertyName, propertyValues);
	}

	@Override
	public void delete(String propertyName, Set<?> propertyValues) {
		baseDao.delete(propertyName, propertyValues);
	}

	@Override
	public List<T> getAllBySql(String sql) {
		List<T> entities = baseDao.getAllBySql(sql);
		return entities;
	}

	@Override
	public List<T> getAllBySqlPage(String sql, Pager<T> pager) {
		List<T> entities = baseDao.getAllBySqlPage(sql, pager);
		return entities;
	}

}
