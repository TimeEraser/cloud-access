package zju.edu.als.collector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import zju.edu.als.alarm.AlarmCenter;
import zju.edu.als.dao.ALSDao;
import zju.edu.als.dao.GuardianDao;
import zju.edu.als.dao.SurgeryDao;
import zju.edu.als.monitor.ALSMonitor;
import zju.edu.als.monitor.GuardianMonitor;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzq on 2016/11/28.
 */
@Component
public class Collector implements SmartLifecycle{
    private static final Logger logger = LoggerFactory.getLogger(Collector.class);
    private ExecutorService executorService;
    @Resource
    private ALSDao alsDao;
    @Resource
    private SurgeryDao surgeryDao;
    @Resource
    private GuardianDao guardianDao;
    @Resource
    private AlarmCenter alarmCenter;
    @Resource
    private ALSMonitor alsMonitor;
    @Resource
    private GuardianMonitor guardianMonitor;
    private volatile boolean isRunning=false;

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        callback.run();
        stop();
    }

    @Override
    public void start() {
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new ServerHandle(alsDao, surgeryDao, guardianDao, alsMonitor, guardianMonitor, alarmCenter, 9203));
        isRunning=true;
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPhase() {
        return 1;
    }
}
