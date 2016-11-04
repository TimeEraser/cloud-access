package zju.edu.als.monitor;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import zju.edu.als.alarm.AlarmCenter;
import zju.edu.als.domain.data.DataBase;

import javax.annotation.Resource;


/**
 * Created by zzq on 2016/10/29.
 */
@Component
public abstract class BaseMonitor implements SmartLifecycle{
    @Resource
    protected AlarmCenter alarmCenter;
    @Resource
    protected MonitorConfig monitorConfig;

    protected  Boolean alarm = false;
    protected StringBuilder alarmMessage;
    public void handleData(DataBase... dataBases){
        for (DataBase dataBase:
             dataBases) {
            preHandle(dataBase);
            monitorVerify(dataBase);
            postHandle(dataBase);
        }
    }
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
}

