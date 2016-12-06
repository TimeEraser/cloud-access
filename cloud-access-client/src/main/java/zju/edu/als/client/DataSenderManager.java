package zju.edu.als.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by zzq on 2016/11/29.
 */
public class DataSenderManager {
    private static final Logger logger = LoggerFactory.getLogger(DataSenderManager.class);
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
    public static void sendData(String data){
        try {
            System.out.println(data);
            out.write(data+"\n");
            out.flush();
        } catch (IOException e) {
            logger.error("socket Connect lost send data failed",e);
            socket=null;
            buildSocketConnect(host,port);
            sendData(data);
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
