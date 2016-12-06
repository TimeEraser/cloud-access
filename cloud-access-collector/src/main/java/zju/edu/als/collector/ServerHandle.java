package zju.edu.als.collector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zju.edu.als.alarm.AlarmCenter;
import zju.edu.als.dao.ALSDao;
import zju.edu.als.dao.GuardianDao;
import zju.edu.als.dao.SurgeryDao;
import zju.edu.als.monitor.ALSMonitor;
import zju.edu.als.monitor.GuardianMonitor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzq on 2016/11/28.
 */
public class ServerHandle implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(ServerHandle.class);
    private ExecutorService executorService;
    private ALSDao alsDao;
    private SurgeryDao surgeryDao;
    private GuardianDao guardianDao;
    private ALSMonitor alsMonitor;
    private GuardianMonitor guardianMonitor;
    private AlarmCenter alarmCenter;
    private Integer port;
    public ServerHandle(ALSDao alsDao, SurgeryDao surgeryDao, GuardianDao guardianDao, ALSMonitor alsMonitor, GuardianMonitor guardianMonitor, AlarmCenter alarmCenter, Integer port) {
        this.alsDao=alsDao;
        this.surgeryDao = surgeryDao;
        this.guardianDao = guardianDao;
        this.alsMonitor = alsMonitor;
        this.guardianMonitor = guardianMonitor;
        this.alarmCenter=alarmCenter;
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
                executorService.execute(new ClientHandle(alsDao, surgeryDao, guardianDao, alsMonitor, guardianMonitor, alarmCenter, client));
            } catch (IOException e) {

            }
        }
    }
}
