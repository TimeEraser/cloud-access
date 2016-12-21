package zju.edu.als.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zzq on 2016/11/28.
 */
public class ClientHandle implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(ClientHandle.class);
    private BlockingQueue<String> messages;
    private Socket socket;
    public ClientHandle(BlockingQueue<String> messages,Socket socket){
        this.messages=messages;
        this.socket=socket;
    }

    @Override
    public void run() {
        BufferedReader in= null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
            while (true) {
                try {
                    String dataStr = in.readLine();
                    System.out.println(dataStr);
                    if(dataStr==null){
                        break;
                    }
                    messages.add(dataStr);
                }catch (Exception e){
                    logger.error("Data Handle Exception",e);
                }
            }
        } catch (IOException e) {
            logger.error("Client Lost",e);
        }finally {
            try {
                in.close();
                socket.close();
            } catch (IOException e) {

            }
        }

    }
}
