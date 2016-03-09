package org.hxn.app.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.hxn.app.entity.User;
import org.hxn.app.enums.Gender;
import org.hxn.app.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext.xml")
public class TestDDL {

	@Autowired
	private UserService userService;

	@Test
	public void addUsers() throws Exception {
		List<User> users = new ArrayList<User>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = sdf.parse("1899-11-01");

		Date today = new Date();
		today = sdf.parse(sdf.format(today));

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse);
		long time1 = calendar.getTimeInMillis();
		calendar.setTime(today);
		long time2 = calendar.getTimeInMillis();
		long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

		GregorianCalendar cal = new GregorianCalendar();
		for (int i = 0; i < betweenDays; i++) {
			cal.setTime(parse);
			cal.add(Calendar.DATE, i);
			Date dateNow = cal.getTime();

			User user = new User();
			user.setBirthDay(dateNow);
			user.setCreateTime(dateNow);
			user.setEateTime(dateNow);
			user.setGender(Gender.FEMALE);
			user.setId(UUID.randomUUID().toString().replace("-", ""));
			user.setPassword("123");
			user.setUsername("hxn" + (i + 1));
			user.setAge(22);
			users.add(user);
			// userService.save(user);
		}
		userService.save(users);
	}

	@Test
	public void testQuery() throws ParseException {
		// String createTime = "2011-05-18 00:00:00";
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date data = sdf.parse(createTime);
		List<User> users = userService.getList("age", 22);
		for (User user : users) {
			System.out.println(user);
		}
	}

}
