package zju.edu.als.collector;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import zju.edu.als.alarm.AlarmCenter;
import zju.edu.als.constant.SurgeryState;
import zju.edu.als.dao.ALSDao;
import zju.edu.als.dao.GuardianDao;
import zju.edu.als.dao.SurgeryDao;
import zju.edu.als.domain.data.ALSData;
import zju.edu.als.domain.surgery.Surgery;
import zju.edu.als.monitor.ALSMonitor;
import zju.edu.als.monitor.GuardianMonitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by zzq on 2016/11/28.
 */
public class ClientHandle implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(ClientHandle.class);
    private ALSDao alsDao;
    private SurgeryDao surgeryDao;
    private GuardianDao guardianDao;
    private ALSMonitor alsMonitor;
    private GuardianMonitor guardianMonitor;
    private AlarmCenter alarmCenter;
    private Socket client;
    private String clientSurgeryNo = null;
    public ClientHandle(ALSDao alsDao, SurgeryDao surgeryDao, GuardianDao guardianDao,Socket socket){

    }

    public ClientHandle(ALSDao alsDao, SurgeryDao surgeryDao, GuardianDao guardianDao, ALSMonitor alsMonitor, GuardianMonitor guardianMonitor, AlarmCenter alarmCenter, Socket client) {
        this.alsDao=alsDao;
        this.surgeryDao = surgeryDao;
        this.guardianDao = guardianDao;
        this.alsMonitor = alsMonitor;
        this.guardianMonitor = guardianMonitor;
        this.alarmCenter=alarmCenter;
        this.client = client;
    }

    @Override
    public void run() {
        BufferedReader in= null;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (true) {
                String dataStr = in.readLine();
                System.out.println(dataStr);
                if(dataStr==null){
                    if (clientSurgeryNo!=null) {
                        alarmCenter.sendAlarm(clientSurgeryNo, "服务器与人工肝的断连,请检查网络连接情况");
                    }
                    break;
                }
                try {
                    if (dataStr.startsWith("START")) {
                        Surgery surgery = JSONObject.parseObject(dataStr.substring(6), Surgery.class);
                        surgeryDao.startSurgery(surgery);
                        this.clientSurgeryNo = surgery.getSurgeryNo();
                    }
                    if (dataStr.startsWith("DATA")) {
                        ALSData alsData = JSONObject.parseObject(dataStr.substring(5), ALSData.class);
                        this.clientSurgeryNo = alsData.getSurgeryNo();
                        alsDao.insertALSData(alsData);
                    }
                    if (dataStr.startsWith("END")) {
                        Surgery surgery = JSONObject.parseObject(dataStr.substring(4), Surgery.class);
                        surgeryDao.endSurgery(surgery);
                        this.clientSurgeryNo = surgery.getSurgeryNo();
                        break;
                    }
                }catch (Exception e){
                    logger.error("Data Handle Exception",e);
                }
            }
        } catch (IOException e) {
            logger.error("Client Lost",e);
        }finally {
            try {
                in.close();
                client.close();
            } catch (IOException e) {

            }
        }

    }
}
