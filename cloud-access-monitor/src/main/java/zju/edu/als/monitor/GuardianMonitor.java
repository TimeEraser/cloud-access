package zju.edu.als.monitor;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import zju.edu.als.domain.alarm.AlarmSetting;
import zju.edu.als.domain.data.DataBase;
import zju.edu.als.domain.data.GuardianData;

/**
 * Created by zzq on 2016/10/29.
 */
@Component
public class GuardianMonitor extends BaseMonitor{

    @Override
    protected void preHandle(DataBase dataBase) {

    }

    @Override
    protected void monitorVerify(DataBase dataBase) {
        StringBuilder alarmMessage=new StringBuilder();
        Boolean alarm = false;
        if(dataBase instanceof GuardianData){
           AlarmSetting heartRateAlarmSetting=monitorConfig.getAlarmSetting("heartRate");
           AlarmSetting systolicPressureAlarmSetting=monitorConfig.getAlarmSetting("systolicPressure");
           AlarmSetting diastolicPressureAlarmSetting=monitorConfig.getAlarmSetting("diastolicPressure");
           AlarmSetting bloodOxygenAlarmSetting=monitorConfig.getAlarmSetting("bloodOxygen");
           if(heartRateAlarmSetting.getCeiling()<((GuardianData) dataBase).getHeartRate()
                   ||heartRateAlarmSetting.getFloor()>((GuardianData) dataBase).getHeartRate()){
               alarmMessage.append("心率数据不在正常范围");
               alarm=true;
           }
           if(systolicPressureAlarmSetting.getCeiling()<((GuardianData) dataBase).getSystolicPressure()
                   ||systolicPressureAlarmSetting.getFloor()>((GuardianData) dataBase).getSystolicPressure()){
               alarmMessage.append("收缩压数据不在正常范围");
               alarm=true;
           }
           if(diastolicPressureAlarmSetting.getCeiling()<((GuardianData) dataBase).getDiastolicPressure()
                   ||diastolicPressureAlarmSetting.getFloor()>((GuardianData) dataBase).getDiastolicPressure()
                   ){
               alarmMessage.append("舒张压数据不在正常范围");
               alarm=true;
           }
           if(bloodOxygenAlarmSetting.getCeiling()<((GuardianData) dataBase).getBloodOxygen()
               ||bloodOxygenAlarmSetting.getFloor()>((GuardianData) dataBase).getBloodOxygen()){
               alarmMessage.append("血氧数据不在正常范围");
               alarm=true;
           }
           if(alarm){

           }
       }
    }

    @Override
    protected void postHandle(DataBase dataBase) {

    }

    @Override
    public boolean isAutoStartup() {
        return false;
    }

    @Override
    public void stop(Runnable callback) {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

}
