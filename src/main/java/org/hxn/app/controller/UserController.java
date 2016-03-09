package org.hxn.app.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hxn.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {
	private static final Log LOG = LogFactory.getLog(UserController.class);

	@Autowired
	private UserService userService;// 注入业务接口

}
