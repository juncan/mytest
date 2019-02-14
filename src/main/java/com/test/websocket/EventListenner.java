package com.test.websocket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wujc
 * @ClassName EventListenner
 * @Description: web消息通知
 * @create 2019-01-25 9:59
 */
public class EventListenner {
    private static List<SocketIOClient> clients = new ArrayList<SocketIOClient>(); //用于保存所有客户端

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setHostname("127.0.0.1");
        configuration.setPort(8082);
        //根据配置创建服务器对象
        SocketIOServer server = new SocketIOServer(configuration);

        server.addConnectListener(new ConnectListener() { //添加客户端连接监听器
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("connected:Session=" +client.getSessionId());
                clients.add(client);//保存客户端
            }
        });

        server.start();

        System.out.println("server started");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (SocketIOClient client : clients) {
                    Date currentTime = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = format.format(currentTime);
                    System.out.println(dateString);
                    client.sendEvent("pushpoint", dateString);//每隔一秒推送一次
                }
            }
        },1000,1000);
    }
}
