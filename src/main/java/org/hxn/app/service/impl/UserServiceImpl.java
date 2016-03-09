package org.hxn.app.service.impl;

import org.hxn.app.base.service.impl.BaseServiceImpl;
import org.hxn.app.dao.UserDao;
import org.hxn.app.entity.User;
import org.hxn.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	protected void setBaseDao(UserDao userDao) {
		super.setBaseDao(userDao);
	}
	public UserDao getUserDao() {
		return userDao;
	}
	

//	@Transactional(propagation = Propagation.REQUIRED)
//	@TriggersRemove(cacheName = "userCache", removeAll = true) // 清除缓存
	/*@Override
	public void save(UserInfo userInfo) {
		userDao.save(userInfo);
	}*/

//	@Cacheable(cacheName = "userCache") // 缓存数据
	/*@Override
	public UserInfo getById(Serializable id) {
		return userDao.getById( id);
	}*/

}
