package com.task;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务处理
 * @author xiezq
 * @date 2017年9月19日 上午10:21:21
 */
public class Task {

	public static final Task me = new Task();
	
	private ScheduledExecutorService bindRegIdService;

	private Task() {}
	
	/**
	 * 启动任务线程池
	 */
	public void init() {
		String poolSize = "";
		String delay = "";
		String period = "";
		String unit = "";
		int task_poolSize = 2;
		if (StringUtils.isNotBlank(poolSize)) {
			try {
				task_poolSize = Integer.parseInt(poolSize);
			} catch (Exception e) {
			}
		}
		int task_delay = 5000;
		if (StringUtils.isNotBlank(delay)) {
			try {
				task_delay = Integer.parseInt(delay);
			} catch (Exception e) {
			}
		}
		long task_period = 1000;
		if (StringUtils.isNotBlank(period)) {
			try {
				task_period = Integer.parseInt(period);
			} catch (Exception e) {
			}
		}
		TimeUnit task_unit = TimeUnit.MILLISECONDS;
		if (StringUtils.isNotBlank(unit)) {
			try {
				task_unit = TimeUnit.valueOf(unit);
			} catch (Exception e) {
			}
		}

		// 初始化线程池
		if (null == bindRegIdService) {
			synchronized (Task.class) {
				if (null == bindRegIdService) {
					bindRegIdService = Executors.newScheduledThreadPool(task_poolSize);
					bindRegIdService.scheduleAtFixedRate(new BindRegIdTimerTask(), task_delay,
							task_period*2, task_unit);
					System.out.println("异步任务处理线程启动成功...");
				}
			}
		}

	}
}
