package org.hxn.app.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.hxn.app.base.dao.BaseDao;
import org.hxn.app.util.Pager;

public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class<T> clazz;

	@PersistenceContext
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.clazz = null;
		Class<?> c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			this.clazz = (Class<T>) parameterizedType[0];
		}
	}

	@Override
	public void save(T entity) {
		em.persist(entity);
	}

	@Override
	public void save(List<T> entities) {
		for (int i = 0; i < entities.size(); i++) {
			em.persist(entities.get(i));
			if (i % 30 == 0) {
				em.flush();
				em.clear();
			}
		}
	}

	@Override
	public T get(Serializable id) {
		return em.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> get(List<? extends Serializable> ids) {
		String jpql = "select t from " + clazz.getName() + " as t where t.id in :ids";
		return em.createQuery(jpql).setParameter("ids", ids).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> get(Set<? extends Serializable> ids) {
		String jpql = "select t from " + clazz.getName() + " as t where t.id in :ids";
		return em.createQuery(jpql).setParameter("ids", ids).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> get(Serializable[] ids) {
		String jpql = "select t from " + clazz.getName() + " as t where t.id in :ids";
		return em.createQuery(jpql).setParameter("ids", Arrays.asList(ids)).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(String propertyName, Object propertyValue) {
		String jpql = "select t from " + clazz.getName() + " as t where t." + propertyName + " = :propertyValue";
		return (T) em.createQuery(jpql).setParameter("propertyValue", propertyValue).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(String propertyName, Object propertyValue) {
		String jpql = "select t from " + clazz.getName() + " as t where t." + propertyName + " = :propertyValue";
		Query query = em.createQuery(jpql);
		query.setParameter("propertyValue", propertyValue);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(String propertyName, Object[] propertyValues) {
		String jpql = "select t from " + clazz.getName() + " as t where t." + propertyName + " in :propertyValues";
		return em.createQuery(jpql).setParameter("propertyValues", Arrays.asList(propertyValues)).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(String propertyName, List<?> propertyValues) {
		String jpql = "select t from " + clazz.getName() + " as t where t." + propertyName + " in :propertyValues";
		return em.createQuery(jpql).setParameter("propertyValues", propertyValues).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(String propertyName, Set<?> propertyValues) {
		String jpql = "select t from " + clazz.getName() + " as t where t." + propertyName + " in :propertyValues";
		return em.createQuery(jpql).setParameter("propertyValues", propertyValues).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getListByLike(String propertyName, String propertyValue) {
		String jpql = "select t from " + clazz.getName() + " as t where t." + propertyName + " like :propertyValue";
		return em.createQuery(jpql).setParameter("propertyValue", propertyValue).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		String jpql = "select t from " + clazz.getName() + " as t";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public Long getTotalCount() {
		String jpql = "select count(*) from " + clazz.getName() + " as t";
		return (Long) em.createQuery(jpql).getSingleResult();
	}

	@Override
	public void update(T entity) {
		Field idField = null;
		T updateEntity = null;
		Field[] fields = null;
		try {
			idField = clazz.getDeclaredField("id");
			idField.setAccessible(true);
			updateEntity = em.find(clazz, idField.get(entity));
			fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				String propertyName = "";
				Object propertyValue = null;

				propertyName = fields[i].getName();
				if (propertyName.equals("id") || "serialVersionUID".equals(propertyName)) {
					continue;
				}
				propertyValue = fields[i].get(entity);
				if (propertyValue != null) {
					fields[i].set(updateEntity, propertyValue);
				}
			}
		} catch (NoSuchFieldException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(List<T> entities) {
		for (T entity : entities) {
			Field idField = null;
			T updateEntity = null;
			Field[] fields = null;
			try {
				idField = clazz.getDeclaredField("id");
				idField.setAccessible(true);
				updateEntity = em.find(clazz, idField.get(entity));
				fields = clazz.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					fields[i].setAccessible(true);
					String propertyName = "";
					Object propertyValue = null;

					propertyName = fields[i].getName();
					if (propertyName.equals("id") || "serialVersionUID".equals(propertyName)) {
						continue;
					}
					propertyValue = fields[i].get(entity);
					if (propertyValue != null) {
						fields[i].set(updateEntity, propertyValue);
					}
				}
			} catch (NoSuchFieldException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void merge(T entity) {
		em.merge(entity);
	}

	@Override
	public void merge(List<T> entities) {
		for (int i = 0; i < entities.size(); i++) {
			em.merge(entities.get(i));
			if (i % 100 == 0) {
				em.flush();
				em.clear();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByPage(Pager<T> pager) {
		String jpql = "select t from " + clazz.getName() + " as t";
		return em.createQuery(jpql).setFirstResult(pager.getFirstResult()).setMaxResults(pager.getMaxResults())
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(String propertyName, Object propertyValue, Pager<T> pager) {
		String jpql = "select t from " + clazz.getName() + " as t where t." + propertyName + " = :propertyValue";
		return em.createQuery(jpql).setParameter("propertyValue", propertyValue).setFirstResult(pager.getFirstResult())
				.setMaxResults(pager.getMaxResults()).getResultList();
	}

	@Override
	public Long getTotalCount(String propertyName, Object propertyValue) {
		String jpql = "select count(*) from " + clazz.getName() + " as t where t." + propertyName + " = :propertyValue";
		return (Long) em.createQuery(jpql).setParameter("propertyValue", propertyValue).getSingleResult();
	}

	@Override
	public T getReference(Serializable id) {
		T entity = em.getReference(clazz, id);
		return entity;
	}

	@Override
	public void delete(Serializable id) {
		T entity = em.getReference(clazz, id);
		em.remove(entity);
	}

	@Override
	public void delete(T entity) {
		em.remove(em.contains(entity) ? entity : em.merge(entity));
	}

	@Override
	public void delete(Serializable[] ids) {
		String jpql = "delete from " + clazz.getName() + " as t where t.id in :ids";
		em.createQuery(jpql).setParameter("ids", Arrays.asList(ids)).executeUpdate();
	}

	@Override
	public void delete(List<? extends Serializable> ids) {
		String jpql = "delete from " + clazz.getName() + " as t where t.id in :ids";
		em.createQuery(jpql).setParameter("ids", ids).executeUpdate();
	}

	@Override
	public void delete(Set<? extends Serializable> ids) {
		String jpql = "delete from " + clazz.getName() + " as t where t.id in :ids";
		em.createQuery(jpql).setParameter("ids", ids).executeUpdate();
	}

	@Override
	public void delete(String propertyName, Object propertyValue) {
		String jpql = "delete from " + clazz.getName() + " as t where t." + propertyName + " = :propertyValue";
		em.createQuery(jpql).setParameter("propertyValue", propertyValue).executeUpdate();
	}

	@Override
	public void delete(String propertyName, Object[] propertyValues) {
		String jpql = "delete from " + clazz.getName() + " as t where t." + propertyName + " in :propertyValues";
		em.createQuery(jpql).setParameter("propertyValues", Arrays.asList(propertyValues)).executeUpdate();
	}

	@Override
	public void delete(String propertyName, List<?> propertyValues) {
		String jpql = "delete from " + clazz.getName() + " as t where t." + propertyName + " in :propertyValues";
		em.createQuery(jpql).setParameter("propertyValues", propertyValues).executeUpdate();
	}

	@Override
	public void delete(String propertyName, Set<?> propertyValues) {
		String jpql = "delete from " + clazz.getName() + " as t where t." + propertyName + " in :propertyValues";
		em.createQuery(jpql).setParameter("propertyValues", propertyValues).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllBySql(String sql) {
		List<T> entities = em.createNativeQuery(sql, clazz).getResultList();
		return entities;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllBySqlPage(String sql, Pager<T> pager) {
		List<T> entities = em.createNativeQuery(sql, clazz).setFirstResult(pager.getFirstResult())
				.setMaxResults(pager.getMaxResults()).getResultList();
		return entities;
	}

}
