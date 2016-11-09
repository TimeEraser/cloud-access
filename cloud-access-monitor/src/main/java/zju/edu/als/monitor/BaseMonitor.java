package zju.edu.als.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import zju.edu.als.alarm.AlarmCenter;
import zju.edu.als.domain.data.DataBase;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static zju.edu.als.alarm.AlarmConfig.HEARTBEAT_SENSITIVITY;


/**
 * Created by zzq on 2016/10/29.
 */
@Component
public abstract class BaseMonitor implements SmartLifecycle{
    private static final Logger logger = LoggerFactory.getLogger(BaseMonitor.class);
    @Resource
    protected AlarmCenter alarmCenter;

    @Resource
    protected MonitorConfig monitorConfig;

    protected TreeSet<DataBase> surgeryHeartbeatSet;

    private ReentrantLock lock;

    private Condition available;

    private ExecutorService executorService;

    protected ConcurrentHashMap<String,DataBase> surgeryHeartbeatMap;
    protected  Boolean alarm = false;
    protected StringBuilder alarmMessage;
    public void handleData(DataBase... dataBases){
        for (DataBase dataBase:
             dataBases) {
            heatBeatCheck(dataBase);
            preHandle(dataBase);
            monitorVerify(dataBase);
            postHandle(dataBase);
        }
    }
    private void heatBeatCheck(DataBase dataBase){
        try {
            if (surgeryHeartbeatMap.containsKey(dataBase.getSurgeryNo())) {
                DataBase last = surgeryHeartbeatMap.get(dataBase.getSurgeryNo());
                if(last.getTimestamp()<dataBase.getTimestamp()) {
                    surgeryHeartbeatSet.remove(last);
                    surgeryHeartbeatSet.add(dataBase);
                    surgeryHeartbeatMap.put(dataBase.getSurgeryNo(), dataBase);
                    signalDetector(dataBase);
                }
            }else {
                surgeryHeartbeatSet.add(dataBase);
                surgeryHeartbeatMap.put(dataBase.getSurgeryNo(), dataBase);
                signalDetector(dataBase);
            }
        }catch (Exception e) {
            logger.error("Invoke heatBeatCheck Failed", e);
        }
    }

    protected  void signalDetector(DataBase dataBase){
        if(dataBase!=null&&dataBase.equals(surgeryHeartbeatSet.first())) {
            lock.lock();
            try {
                available.signal();
            } catch (IllegalMonitorStateException e) {
                logger.error("Signal Failed", e);
            } finally {
                lock.unlock();
            }
        }
    };

    protected abstract void preHandle(DataBase dataBase);
    protected abstract void monitorVerify(DataBase dataBase);
    protected  void postHandle(DataBase dataBase){
        if(alarm){
            alarmCenter.sendAlarm(dataBase.getSurgeryNo(),alarmMessage.toString());
        }
    }


    @Override
    public int getPhase() {
        return 1;
    }

    @Override
    public void start() {
        lock= new ReentrantLock();

        available = lock.newCondition();

        executorService = Executors.newFixedThreadPool(3);

        surgeryHeartbeatSet = new TreeSet<>(new Comparator<DataBase>() {
            @Override
            public int compare(DataBase db1, DataBase db2) {
                if(db1.getTimestamp()>db2.getTimestamp()){
                    return 1;
                }else if(db1.getTimestamp()==db2.getTimestamp()){
                    return db1.getSurgeryNo().compareTo(db2.getSurgeryNo());
                }else {
                    return -1;
                }
            }
        });
        surgeryHeartbeatMap = new ConcurrentHashMap<>();

        executorService.execute(new DetectController());

    }
    class DetectController implements Runnable {
        public void run() {
            lock.lock();
            try {
                while (true) {
                    try {
                        if (surgeryHeartbeatSet==null||surgeryHeartbeatSet.size() == 0) {
                            available.await();
                        } else {
                            long currentTime = System.currentTimeMillis();
                            Map<String, DataBase> detectMap = new HashMap<>();
                            long delay=0;
                            while (surgeryHeartbeatSet.size() > 0) {
                                DataBase dataBase = surgeryHeartbeatSet.first();
                                delay = dataBase.getTimestamp() - currentTime + HEARTBEAT_SENSITIVITY;
                                if (delay <= 0) {
                                    DataBase dataBasePoll = surgeryHeartbeatSet.pollFirst();
                                    detectMap.put(dataBasePoll.getSurgeryNo(), dataBasePoll);
                                }else {
                                    break;
                                }
                            }
                            finishDetect(detectMap);
                            if(delay > 0) {
                                available.await(delay, TimeUnit.MILLISECONDS);
                            }
                        }
                    } catch (InterruptedException e) {
                        logger.info("Class :DetectorController Interrupted", e);
                        break;
                    }
                }
            } catch (Exception e) {
                logger.info("Class :DetectorController Exception", e);
            } finally {
                lock.unlock();
            }
        }
    }
    class DetectWorker implements Runnable{
        Map<String,DataBase> detectMap;
        public DetectWorker(Map<String,DataBase> detectMap){
            this.detectMap=detectMap;
        }
        @Override
        public void run() {
            if(CollectionUtils.isEmpty(detectMap)){
                return;
            }
            List<String> surgeryNoList = new ArrayList<>();
            for(DataBase dataBase:detectMap.values()){
                surgeryNoList.add(dataBase.getSurgeryNo());
            }
            alarmCenter.sendAlarms(surgeryNoList,"错过发送时间");
        }
    }

    private void finishDetect(Map<String,DataBase> detectMap) {
        DetectWorker detectWorker = new DetectWorker(detectMap);
        executorService.execute(detectWorker);
    }
}


