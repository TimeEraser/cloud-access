package zju.edu.als.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import zju.edu.als.alarm.AlarmCenter;
import zju.edu.als.domain.alarm.AlarmSetting;
import zju.edu.als.domain.data.DataBase;
import javax.annotation.Resource;
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
    @Resource
    protected HeartBeat heartBeat;
    protected volatile boolean isRunning = false;
    protected  Boolean needThresholdAlarm = false;
    public void handleData(DataBase... dataBases){
        for (DataBase dataBase:
             dataBases) {
            heartBeat.check(dataBase);
            preHandle(dataBase);
            monitorVerify(dataBase);
            postHandle(dataBase);
        }
    }


    protected abstract void preHandle(DataBase dataBase);
    protected abstract void monitorVerify(DataBase dataBase);
    protected abstract void postHandle(DataBase dataBase);
    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {
        runnable.run();
        stop();
    }

    @Override
    public void start() {
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
        return 2;
    }
    protected void checkThreshold(AlarmSetting alarmSetting,double value,StringBuilder messageBuilder){
        if(alarmSetting.getCeiling()<value){
            messageBuilder.append(alarmSetting.getAlarmItem()).append("超出正常范围\n");
        }else if(alarmSetting.getFloor()>value){
            messageBuilder.append(alarmSetting.getAlarmItem()).append("低于正常范围\n");
        }
    }
}


