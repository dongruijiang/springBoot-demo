package com.sb.demo.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.sb.demo.common.utils.spring.SpringUtils;

/**
 * 工具类 ---通用类处理 获取i18n资源文件
 * 
 * @date 2020年3月26日 下午11:17:12
 * @author jdr
 */
public class MessageUtils {
	/**
	 * 根据消息键和参数 获取消息 委托给spring messageSource
	 *
	 * @param code 消息键
	 * @param args 参数
	 * @return 获取国际化翻译值
	 */
	public static String message(String code, Object... args) {
		MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
	}
}
