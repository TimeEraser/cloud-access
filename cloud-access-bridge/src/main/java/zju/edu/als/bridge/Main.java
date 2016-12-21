package zju.edu.als.bridge;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zzq on 2016/12/21.
 */
public class Main {
    private static LinkedBlockingQueue<String> messages=new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        DataSenderManager.buildSocketConnect("als-cloud.zju.edu.cn",9024);
        DataSenderManager.sendData(messages);
        Executors.newSingleThreadExecutor().execute(new ServerHandle(messages,9023));
    }
}
