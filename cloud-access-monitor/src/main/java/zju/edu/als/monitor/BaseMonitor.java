package zju.edu.als.monitor;

import zju.edu.als.domain.data.DataBase;

/**
 * Created by zzq on 2016/10/29.
 */
public abstract class BaseMonitor {

    public void handleData(DataBase... dataBases){
        for (DataBase dataBase:
             dataBases) {
            preHandle(dataBase);
            monitorVerify(dataBase);
            postHandle(dataBase);
        }

    }
    public abstract void preHandle(DataBase dataBase);
    public abstract void monitorVerify(DataBase dataBase);
    public abstract void postHandle(DataBase dataBase);
}

