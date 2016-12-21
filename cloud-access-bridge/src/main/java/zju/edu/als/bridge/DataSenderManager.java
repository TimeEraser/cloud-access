package zju.edu.als.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Created by zzq on 2016/11/29.
 */
public class DataSenderManager {
    private static final Logger logger = LoggerFactory.getLogger(DataSenderManager.class);
    public static LinkedBlockingQueue<String> messages=new LinkedBlockingQueue<>();
    private static String host;
    private static Integer port;
    private static Socket socket;
    private static BufferedWriter out;
    public static void buildSocketConnect(String _host,Integer _port)  {
        host=_host;
        port=_port;
        while (socket==null) {
            try {
                socket = new Socket(host, port);
                socket.setKeepAlive(true);
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
            } catch (IOException e) {
                logger.error("build socket connect failed", e);
                try {
                    Thread.currentThread().sleep(5000);
                    logger.info("rebuild socket connect");
                    socket=null;
                } catch (InterruptedException e1) {

                }
            }
        }
    }
    public static void sendData(){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String message = messages.take();
                        sendMessage(message);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });
    }

    public static void sendMessage(String message) {
            try {
                System.out.println(message);
                out.write(message + "\n");
                out.flush();
            }  catch (IOException e) {
                logger.error("socket Connect lost send data failed", e);
                socket = null;
                buildSocketConnect(host, port);
                sendMessage(message);
            }
    }
    public static void closeSocketConnect(){
        try {
            out.close();
            socket.close();
            out=null;
            socket=null;
        } catch (IOException e) {

        }
    }
}
