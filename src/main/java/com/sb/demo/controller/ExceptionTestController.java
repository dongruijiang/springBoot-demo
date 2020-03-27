package com.sb.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sb.demo.common.exception.CustomException;
import com.sb.demo.framework.web.controller.BaseController;

/**
 * 异常统一处理测试----- Controller类
 * 
 * @date 2020年3月26日 下午9:50:20
 * @author jdr
 */
@RestController
@RequestMapping("/exception")
public class ExceptionTestController extends BaseController{
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showInfo(){
		log.info("dddddddddddddd");
        String info ="你好";
        //int a = 1/0;
        //throw new RuntimeException();
        //throw new BaseException("202","BaseException"); 
        throw new CustomException("资源没有了",404);
		//return info;
    }

}
