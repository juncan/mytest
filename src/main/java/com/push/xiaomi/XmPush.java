package com.push.xiaomi;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * @author wujc
 * @ClassName XmPush
 * @Description: 小米推送
 * @create 2018-11-01 10:20
 */
public class XmPush {
    private static final Logger logger = LoggerFactory.getLogger(XmPush.class);

    public static PushResult sendAndroidMessage(int app_type, String title, String description, String url, List<String> list) throws IOException, ParseException {
        //正式启动方式
        Constants.useOfficial();
        String app_secret_key = Constant.androidSeretKey().get(app_type);
        if (!StringUtils.isNoneBlank(app_secret_key)) {
            logger.info("secret_key空值");
            return new PushResult("500", "secret_key空值");
        }
        String package_name = Constant.packageName().get(app_type);
        if (!StringUtils.isNoneBlank(package_name)) {
            logger.info("package_name空值");
            return new PushResult("500", "package_name空值");
        }
        if (list.size() > 1000 || list.size() <= 0) {
            logger.info("list的size必须大于0，小于1000");
            return new PushResult("500", "list的size必须大于0，小于1000");
        }
        if (title.length() > 50) {
            logger.info("title必须大于0，小于50");
            return new PushResult("500", "title必须大于0，小于50");
        }
        if (description.length() > 128) {
            logger.info("description必须大于0，小于128");
            return new PushResult("500", "description必须大于0，小于1128");
        }
        Random random = new Random();
        int notifyId = random.nextInt(1000) + 1;
        Sender sender = new Sender(app_secret_key);
        String messagePayLoad = "This is message";
        Message message = new Message.Builder()
                .title(title)
                .description(description).payload(messagePayLoad)
                .restrictedPackageName(package_name)
                .notifyType(1) //使用默认提示音提示
                .notifyId(notifyId)
                .extra("url", url)
                .build();
        Result result = sender.send(message, list, 3);
        logger.info("Server response:", "MessageId:" + result.getMessageId()
                + " ErrorCode:" + result.getErrorCode().toString()
                + " Reason:" + result.getReason());
        return new PushResult(result);
    }

    public static PushResult sendIosMessage(int app_type, String title, String description, String url, List<String> list) throws Exception {
        Constants.useOfficial();
        String app_secret_key = Constant.iosSecretKey().get(app_type);
        if (StringUtils.isNoneBlank(app_secret_key)) {
            logger.info("secret_key空值");
            return new PushResult("500", "secret_key空值");
        }
        if (list.size() > 1000 || list.size() <= 0) {
            logger.info("list的size必须大于0，小于1000");
            return new PushResult("500","list的size必须大于0，小于1000");
        }
        if (title.length() > 50) {
            logger.info("title必须大于0，小于50");
            return new PushResult("500", "title必须大于0，小于50");
        }
        if (description.length() > 128) {
            logger.info("description必须大于0，小于128");
            return new PushResult("500", "description必须大于0，小于128");
        }
        Sender sender = new Sender(app_secret_key);
        Message message = new Message.IOSBuilder()
                .title(title)
                .description(description)
                .soundURL("default")      //消息铃声
                .badge(1)                 //默认角标
                .category("action")
                .body(description)
                .extra("url", url)
                .build();
        Result result = sender.send(message, list, 3);
        logger.info("Server response: ", "MessageId: " + result.getMessageId()
                + " ErrorCode: " + result.getErrorCode().toString()
                + " Reason: " + result.getReason());
        return new PushResult(result);
    }

}
