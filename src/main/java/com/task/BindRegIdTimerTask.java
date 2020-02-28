/**   
 * 优芽教育   版权所有
 * Copyright YOYA  EDU
 * All right reserved.
 *====================================================
 * 文件名称: BindRegIdTimerTask.java 
 * 修订记录：
 * No    日期				    功能描述
 * 1.    2019年10月17日 上午11:15:20		TODO
 *====================================================
 */
package com.task;

import java.util.TimerTask;

/**
 * 类名: BindRegIdTimerTask
 * 类描述: TODO(简单描述)
 * @version：(当前版本)
 * @since (从哪个版本引入)
 */
public class BindRegIdTimerTask extends TimerTask {

	
	/**
	 * <p>名称: run</p>
	 * <p>描述: (简单描述)</p>
	 * @see TimerTask#run()
	 */
	@Override
	public void run() {
		try {
            String string = null;
            string.substring(0, 1);
		} catch (Exception e) {
            try {
                String string = null;
                string.substring(0, 1);
            } catch (Exception ex) {

            }

            e.printStackTrace();
        }
	}
}
