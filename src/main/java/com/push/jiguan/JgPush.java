package com.push.jiguan;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.WinphoneNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wujc
 * @ClassName JgPush
 * @Description: TODO
 * @create 2018-11-08 11:46
 */
public class JgPush {
    private static final Logger logger = LoggerFactory.getLogger(JgPush.class);
    private JPushClient jPushClient = null;
    protected final Object createJpushClientLock = new Object();

    public void handlerJpush(JpushDto jpushDto) {
        PushPayload payload = getPushPayload(jpushDto);
        try {
            JPushClient jPushClient = this.getJpushClient();
            PushResult result = jPushClient.sendPush(payload);
            logger.info("Got jpush result:" + result);
        } catch (APIConnectionException e) {
            logger.error("Connection error,should retry latter." + e.getMessage());
        } catch (APIRequestException e) {
            logger.error("Should received the error,and fix the request," + e.getMessage());
            logger.info("Http status:" + e.getStatus());
            logger.info("Error code:" + e.getErrorCode());
            logger.info("Error Message:" + e.getErrorMessage());
        }
    }

    /**
     * 获取jpushClient对象
     * @return
     */
    private JPushClient getJpushClient() {
        if (jPushClient == null) {
            synchronized (createJpushClientLock) {
                if (jPushClient == null) {
                    String jpushMasterSecre = "8dfa228eb8b8be332df25668";
                    String jpushAppKey = "e8c9e1589c4c02c3dd811b77";
                    jPushClient = new JPushClient(jpushMasterSecre, jpushAppKey);
                }
            }
        }
        return jPushClient;
    }

    private PushPayload getPushPayload(JpushDto jDto) {
        switch (jDto.getJpushAudienceType()) {
            case ALL:
                return PushPayload.alertAll(jDto.getAlert());
            case ALIAS:
                return PushPayload
                        .newBuilder()
                        .setPlatform(Platform.all())
                        .setAudience(Audience.alias(jDto.getAlias()))
                        .setNotification(
                                Notification
                                        .newBuilder()
                                        .addPlatformNotification(
                                                AndroidNotification.newBuilder().setAlert(jDto.getAlert())
                                                        .setTitle(jDto.getTitle()).addExtras(jDto.getExtras()).build())
                                        .addPlatformNotification(
                                                IosNotification.newBuilder().setAlert(jDto.getAlert())
                                                        .addExtras(jDto.getExtras()).build())
                                        .addPlatformNotification(
                                                WinphoneNotification.newBuilder().setAlert(jDto.getAlert())
                                                        .addExtras(jDto.getExtras()).build()).build())
                        .setMessage(Message.newBuilder().setMsgContent(jDto.getAlert()).addExtras(jDto.getExtras()).build())
                        .setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
            default:
                return null;
        }

    }

}
