package zju.edu.als.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzq on 2016/11/28.
 */
public class ServerHandle implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(ServerHandle.class);
    private ExecutorService executorService;
    private BlockingQueue<String> messages;
    private Integer port;
    public ServerHandle(BlockingQueue<String> messages,Integer port) {
        this.messages=messages;
        this.port=port;
    }
    @Override
    public void run() {
        executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            logger.error("Server Socket Bind Exception", e);
        }
        while (true) {
            try {
                Socket client = serverSocket.accept();
                client.setKeepAlive(true);
                executorService.execute(new ClientHandle(messages,client));

            } catch (SocketException e) {

            } catch (IOException e) {

            }
        }
    }
}
