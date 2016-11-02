package zju.edu.als.monitor;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import zju.edu.als.domain.alarm.AlarmSetting;
import zju.edu.als.domain.data.DataBase;
import zju.edu.als.domain.data.PumpSpeedData;

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
        Boolean alarm = false;
        if (dataBase instanceof PumpSpeedData) {
            AlarmSetting bloodPumpAlarmSetting = monitorConfig.getAlarmSetting("bloodPump");
            AlarmSetting separationPumpAlarmSetting = monitorConfig.getAlarmSetting("separationPump");
            AlarmSetting dialysisPumpAlarmSetting = monitorConfig.getAlarmSetting("dialysisPump");
            AlarmSetting tripePumpAlarmSetting = monitorConfig.getAlarmSetting("tripePump");
            AlarmSetting filtrationPumpAlarmSetting = monitorConfig.getAlarmSetting("filtrationPump");
            AlarmSetting circulatingPumpAlarmSetting = monitorConfig.getAlarmSetting("circulatingPump");
            AlarmSetting heparinPumpAlarmSetting = monitorConfig.getAlarmSetting("heparinPump");
            AlarmSetting warmerAlarmSetting = monitorConfig.getAlarmSetting("warmer");
            if (bloodPumpAlarmSetting.getCeiling() < ((PumpSpeedData) dataBase).getBloodPump()
                    || bloodPumpAlarmSetting.getFloor() > ((PumpSpeedData) dataBase).getBloodPump()) {
                alarmMessage.append("血泵数据不在正常范围");
                alarm = true;
            }
            if (separationPumpAlarmSetting.getCeiling() < ((PumpSpeedData) dataBase).getSeparationPump()
                    || separationPumpAlarmSetting.getFloor() > ((PumpSpeedData) dataBase).getSeparationPump()) {
                alarmMessage.append("分离泵数据不在正常范围");
                alarm = true;
            }
            if (dialysisPumpAlarmSetting.getCeiling() < ((PumpSpeedData) dataBase).getDialysisPump()
                    || dialysisPumpAlarmSetting.getFloor() > ((PumpSpeedData) dataBase).getDialysisPump()
                    ) {
                alarmMessage.append("透析泵数据不在正常范围");
                alarm = true;
            }
            if (tripePumpAlarmSetting.getCeiling() < ((PumpSpeedData) dataBase).getTripePump()
                    || tripePumpAlarmSetting.getFloor() > ((PumpSpeedData) dataBase).getTripePump()
                    ) {
                alarmMessage.append("废液泵数据不在正常范围");
                alarm = true;
            }
            if (filtrationPumpAlarmSetting.getCeiling() < ((PumpSpeedData) dataBase).getFiltrationPump()
                    || filtrationPumpAlarmSetting.getFloor() > ((PumpSpeedData) dataBase).getFiltrationPump()) {
                alarmMessage.append("过滤泵数据不在正常范围");
                alarm = true;
            }
            if (circulatingPumpAlarmSetting.getCeiling() < ((PumpSpeedData) dataBase).getCirculatingPump()
                    || circulatingPumpAlarmSetting.getFloor() > ((PumpSpeedData) dataBase).getCirculatingPump()) {
                alarmMessage.append("累计泵数据不在正常范围");
                alarm = true;
            }

            if (heparinPumpAlarmSetting.getCeiling() < ((PumpSpeedData) dataBase).getHeparinPump()
                    || heparinPumpAlarmSetting.getFloor() > ((PumpSpeedData) dataBase).getHeparinPump()) {
                alarmMessage.append("肝素泵数据不在正常范围");
                alarm = true;
            }
            if (warmerAlarmSetting.getCeiling() < ((PumpSpeedData) dataBase).getWarmer()
                    || warmerAlarmSetting.getFloor() > ((PumpSpeedData) dataBase).getWarmer()) {
                alarmMessage.append("加热温度数据不在正常范围");
                alarm = true;
            }
            if (alarm) {

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
