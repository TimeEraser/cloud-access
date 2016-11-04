package zju.edu.als.monitor;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import zju.edu.als.domain.data.DataBase;

import javax.annotation.Resource;


/**
 * Created by zzq on 2016/10/29.
 */
@Component
public abstract class BaseMonitor implements SmartLifecycle{

    @Resource(name = "monitorConfig")
    protected MonitorConfig monitorConfig;

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
    protected abstract void postHandle(DataBase dataBase);


    @Override
    public int getPhase() {
        return 1;
    }
}

