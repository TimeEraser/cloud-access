package zju.edu.als.bridge;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zzq on 2016/12/21.
 */
public class BridgeMain {

    public static void main(String[] args) {
        DataSenderManager.buildSocketConnect("als-cloud.zju.edu.cn",9204);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.registerShutdownHook();
        DataSenderManager.sendData();

    }
}
