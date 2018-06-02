package com.can.aop;

import com.can.util.Page;
import com.can.util.PageParam;
import com.can.util.json.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @description: 分页aop,通过aop为分页设置当前的页数和总的页数
 * @author: LCN
 * @date: 2018-05-30 20:32
 */

@Aspect
@Component
public class PagePluginAop {

	private Logger logger = LoggerFactory.getLogger(PagePluginAop.class);

	@Pointcut("execution(* com.can.service..*Page(..))")
	public void pointCut() {
	}

	@Around("pointCut()")
	public Object setPageArg(ProceedingJoinPoint jp) throws Throwable{
		logger.info("对分页进行参数进行设置");

		// 获取目标方法原始的调用参数
		Object[] args = jp.getArgs();

		// 获取返回值
		Object rvt = jp.proceed(args);

		logger.info("分页page的返回值为------------->{}", JsonUtil.toJsonString(rvt));


		if(rvt != null && rvt instanceof Page<?>) {

			PageParam pageParam = (PageParam)args[0];
			Page<?> page = (Page<?>)rvt;
			page.setTotalPage(pageParam.getTotalPage());
			page.setCurrentPage(pageParam.getCurrentPage());
			logger.info("经过aop处理后的分页的结果为------------->{}", JsonUtil.toJsonString(page));

			return page;

		}

		return rvt;
	}


}
