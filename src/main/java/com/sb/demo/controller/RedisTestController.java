package com.sb.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sb.demo.domain.Card;
import com.sb.demo.domain.result;
import com.sb.demo.framework.utils.RedisCache;

/**
 * Redis工具类测试方法--- Controller类
 * @date 2020年3月25日 下午8:38:17
 * @author jdr
 */
@RestController
@RequestMapping("/redis")
public class RedisTestController {

	@Autowired
	private RedisCache redisCache;

	/**
	 * 测试Redis--查询数据-单条
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public result get(@PathVariable("id") String id) {
		Card c = redisCache.getCacheObject("card:id:" + id);
		String str = c != null ? c.toString() : "500 ,fail!";
		return new result("200", "card:id:" + id + "   " + str );
	}

	/**
	 * 测试Redis--添加数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/set", method = RequestMethod.GET)
	public result set() {
		Card c1 = new Card();
		c1.setId(1L);
		c1.setName("aaa江_1");
		c1.setCardNumber("990");

		redisCache.setCacheObject("card:id:1", c1);

		Card c2 = new Card();
		c2.setId(2L);
		c2.setName("aaa江_2");
		c2.setCardNumber("990");

		redisCache.setCacheObject("card:id:2", c2);

		Card c3 = new Card();
		c3.setId(3L);
		c3.setName("aaa江_3");
		c3.setCardNumber("990");

		redisCache.setCacheObject("card:id:3", c3);

		Card c4 = new Card();
		c4.setId(4L);
		c4.setName("aaa江_4");
		c4.setCardNumber("990");

		redisCache.setCacheObject("card:id:4", c4);
		return new result("200", "card:id:1");
	}

	/**
	 * 测试Redis--添加数据
	 * 循环添加 对象10W条
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/seta", method = RequestMethod.GET)
	public result seta() {

		// func2
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		// 2016/05/05-01:01:34:364
		System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date));

		for (Integer i = 0; i < 100000; i++) {
			Card c = new Card();
			c.setId(Long.valueOf(i));
			c.setName("aaa江_" + i);
			c.setCardNumber("990");
			// System.out.println(c.toString());
			redisCache.setCacheObject("card:id:" + c.getId(), c);
		}
		Calendar cal1 = Calendar.getInstance();
		Date date1 = cal1.getTime();

		System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date1));

		long l = date1.getTime() - date.getTime() > 0 ? date1.getTime() - date.getTime()
				: date1.getTime() - date.getTime();

		System.out.println(l / 1000 + "秒");

		return new result("200", "card:id:1");
	}

	/**
	 * 测试Redis--查询数据
	 * 循环查询 对象1W条
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/geta", method = RequestMethod.GET)
	public result geta() {

		// func2
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		// 2016/05/05-01:01:34:364
		System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date));

		 StringBuilder sb = new StringBuilder();
		for (Integer i = 0; i < 10000; i++) {
			redisCache.getCacheObject("card:id:" + i);
			 sb.append(redisCache.getCacheObject("card:id:"+i).toString()+" ");
		}
		Calendar cal1 = Calendar.getInstance();
		Date date1 = cal1.getTime();

		System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date1));

		long l = date1.getTime() - date.getTime() > 0 ? date1.getTime() - date.getTime()
				: date1.getTime() - date.getTime();

		System.out.println(l / 1000 + "秒");
		//System.out.println(sb);
		//System.out.println(l / 1000 + "秒");

		return new result("200", sb.toString());
	}
}
