package zju.edu.als.monitor;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import zju.edu.als.domain.alarm.AlarmSetting;
import zju.edu.als.domain.data.DataBase;
import zju.edu.als.domain.data.PressureData;

/**
 * Created by zzq on 2016/10/29.
 */
@Component
public class PressureMonitor extends BaseMonitor {
    @Override
    protected void preHandle(DataBase dataBase) {

    }

    @Override
    protected void monitorVerify(DataBase dataBase) {
        StringBuilder alarmMessage=new StringBuilder();
        if (dataBase instanceof PressureData) {
            AlarmSetting inBloodPressureAlarmSetting = monitorConfig.getAlarmSetting("inBloodPressure");
            AlarmSetting plasmaInletPressureAlarmSetting = monitorConfig.getAlarmSetting("plasmaInletPressure");
            AlarmSetting arterialPressureAlarmSetting = monitorConfig.getAlarmSetting("arterialPressure");
            AlarmSetting venousPressureAlarmSetting = monitorConfig.getAlarmSetting("venousPressure");
            AlarmSetting plasmaPressureAlarmSetting = monitorConfig.getAlarmSetting("plasmaPressure");
            AlarmSetting transmembranePressureAlarmSetting = monitorConfig.getAlarmSetting("transmembranePressure");
            checkThreshold(inBloodPressureAlarmSetting,((PressureData) dataBase).getInBloodPressure(),alarmMessage);
            checkThreshold(plasmaInletPressureAlarmSetting,((PressureData) dataBase).getPlasmaInletPressure(),alarmMessage);
            checkThreshold(arterialPressureAlarmSetting,((PressureData) dataBase).getArterialPressure(),alarmMessage);
            checkThreshold(venousPressureAlarmSetting,((PressureData) dataBase).getVenousPressure(),alarmMessage);
            checkThreshold(plasmaPressureAlarmSetting,((PressureData) dataBase).getPlasmaPressure(),alarmMessage);
            checkThreshold(transmembranePressureAlarmSetting,((PressureData) dataBase).getTransmembranePressure(),alarmMessage);
            if(alarmMessage.length()>0){
                alarmCenter.sendAlarm(dataBase.getSurgeryNo(),alarmMessage.toString());
            }
        }
    }

    @Override
    protected void postHandle(DataBase dataBase) {

    }


}
