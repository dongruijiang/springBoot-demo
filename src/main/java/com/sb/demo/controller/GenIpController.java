package com.sb.demo.controller;

import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sb.demo.domain.result;
import com.sb.demo.framework.redis.RedisCache;
import com.sb.demo.framework.web.controller.BaseController;

/**
 * 自动批量生产IP
 * @date 2020年3月30日 下午3:30:38
 * @author jdr
 */
@RestController
@RequestMapping("/genIp")
public class GenIpController extends BaseController{
	
	@Autowired
	private RedisCache redisCache;
	
	/**
	 * 生产 IP
	 * @param 
	 * @return String
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAll(){
		
		int count = 1000001;
		for (int i = 1; i < count; i++) {
			String randomIp = getRandomIp();
			//log.error(i  + " " +  randomIp);
			log.error(randomIp);
		}
		return "ok";
	}
	
	/**
	 * 测试Redis--添加进Redis数据
	 * 生产 IP -- 去重复IP
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/redis/set", method = RequestMethod.GET)
	public result set() {
		
		int count = 4000001;
		for (int i = 1; i < count; i++) {
			String randomIp = getRandomIp();
			//log.error(i  + " " +  randomIp);
			redisCache.setCacheObject(randomIp, randomIp);
		}
		return new result("200", "OK");
	}
	 
	/**
	 * 测试Redis--运行keys(*)命令
	 * 
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/redis/keys", method = RequestMethod.GET)
	public result keys() {
		
		Set<String> setStr = (Set<String>) redisCache.keys("*");
		for(String s:setStr) {
			log.error(s);
		}
		return new result("200", "OK");
	}
	
	public static String getRandomIp() {
		// ip范围
		int[][] range = { { 607649792, 608174079 }, // 36.56.0.0-36.63.255.255
				{ 1038614528, 1039007743 }, // 61.232.0.0-61.237.255.255
				{ 1783627776, 1784676351 }, // 106.80.0.0-106.95.255.255
				{ 2035023872, 2035154943 }, // 121.76.0.0-121.77.255.255
				{ 2078801920, 2079064063 }, // 123.232.0.0-123.235.255.255
				{ -1950089216, -1948778497 }, // 139.196.0.0-139.215.255.255
				{ -1425539072, -1425014785 }, // 171.8.0.0-171.15.255.255
				{ -1236271104, -1235419137 }, // 182.80.0.0-182.92.255.255
				{ -770113536, -768606209 }, // 210.25.0.0-210.47.255.255
				{ -569376768, -564133889 }, // 222.16.0.0-222.95.255.255
		};

		Random rdint = new Random();
		int index = rdint.nextInt(10);
		String ip = num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
		return ip;
	}

	/*
	 * 将十进制转换成IP地址
	 */
	public static String num2ip(int ip) {
		int[] b = new int[4];
		String x = "";
		b[0] = (int) ((ip >> 24) & 0xff);
		b[1] = (int) ((ip >> 16) & 0xff);
		b[2] = (int) ((ip >> 8) & 0xff);
		b[3] = (int) (ip & 0xff);
		x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "."
				+ Integer.toString(b[3]);

		return x;
	}
}
