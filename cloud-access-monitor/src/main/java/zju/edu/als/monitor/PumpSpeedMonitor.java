package zju.edu.als.monitor;

import org.springframework.stereotype.Component;
import zju.edu.als.domain.alarm.AlarmSetting;
import zju.edu.als.domain.data.DataBase;

/**
 * Created by zzq on 2016/10/29.
 */
@Component
public class PumpSpeedMonitor extends BaseMonitor {
    @Override
    protected void preHandle(DataBase dataBase) {

    }

    @Override
    protected void monitorVerify(DataBase dataBase) {
        StringBuilder alarmMessage = new StringBuilder();
        if (dataBase instanceof PumpSpeedData) {
            AlarmSetting bloodPumpAlarmSetting = monitorConfig.getAlarmSetting("bloodPump");
            AlarmSetting separationPumpAlarmSetting = monitorConfig.getAlarmSetting("separationPump");
            AlarmSetting dialysisPumpAlarmSetting = monitorConfig.getAlarmSetting("dialysisPump");
            AlarmSetting tripePumpAlarmSetting = monitorConfig.getAlarmSetting("tripePump");
            AlarmSetting filtrationPumpAlarmSetting = monitorConfig.getAlarmSetting("filtrationPump");
            AlarmSetting circulatingPumpAlarmSetting = monitorConfig.getAlarmSetting("circulatingPump");
            AlarmSetting heparinPumpAlarmSetting = monitorConfig.getAlarmSetting("heparinPump");
            AlarmSetting warmerAlarmSetting = monitorConfig.getAlarmSetting("warmer");
            checkThreshold(bloodPumpAlarmSetting,((PumpSpeedData) dataBase).getBloodPump(),alarmMessage);
            checkThreshold(separationPumpAlarmSetting,((PumpSpeedData) dataBase).getSeparationPump(),alarmMessage);
            checkThreshold(dialysisPumpAlarmSetting,((PumpSpeedData) dataBase).getDialysisPump(),alarmMessage);
            checkThreshold(tripePumpAlarmSetting,((PumpSpeedData) dataBase).getTripePump(),alarmMessage);
            checkThreshold(filtrationPumpAlarmSetting,((PumpSpeedData) dataBase).getFiltrationPump(),alarmMessage);
            checkThreshold(circulatingPumpAlarmSetting,((PumpSpeedData) dataBase).getCirculatingPump(),alarmMessage);
            checkThreshold(heparinPumpAlarmSetting,((PumpSpeedData) dataBase).getHeparinPump(),alarmMessage);
            checkThreshold(warmerAlarmSetting,((PumpSpeedData) dataBase).getWarmer(),alarmMessage);
            if(alarmMessage.length()>0){
                alarmCenter.sendAlarm(dataBase.getSurgeryNo(),alarmMessage.toString());
            }
        }
    }

    @Override
    protected void postHandle(DataBase dataBase) {

    }

}
