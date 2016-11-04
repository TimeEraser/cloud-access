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
        StringBuilder alarmMessage = new StringBuilder();
        Boolean alarm = false;
        if (dataBase instanceof PressureData) {
            AlarmSetting inBloodPressureAlarmSetting = monitorConfig.getAlarmSetting("inBloodPressure");
            AlarmSetting plasmaInletPressureAlarmSetting = monitorConfig.getAlarmSetting("plasmaInletPressure");
            AlarmSetting arterialPressureAlarmSetting = monitorConfig.getAlarmSetting("arterialPressure");
            AlarmSetting venousPressureAlarmSetting = monitorConfig.getAlarmSetting("venousPressure");
            AlarmSetting plasmaPressureAlarmSetting = monitorConfig.getAlarmSetting("plasmaPressure");
            AlarmSetting transmembranePressureAlarmSetting = monitorConfig.getAlarmSetting("transmembranePressure");
            if (inBloodPressureAlarmSetting.getCeiling() < ((PressureData) dataBase).getInBloodPressure()
                    || plasmaInletPressureAlarmSetting.getFloor() > ((PressureData) dataBase).getInBloodPressure()) {
                alarmMessage.append("采血压数据不在正常范围");
                alarm = true;
            }
            if (plasmaInletPressureAlarmSetting.getCeiling() < ((PressureData) dataBase).getPlasmaInletPressure()
                    || plasmaInletPressureAlarmSetting.getFloor() > ((PressureData) dataBase).getPlasmaInletPressure()) {
                alarmMessage.append("血浆入口压数据不在正常范围");
                alarm = true;
            }
            if (arterialPressureAlarmSetting.getCeiling() < ((PressureData) dataBase).getArterialPressure()
                    || arterialPressureAlarmSetting.getFloor() > ((PressureData) dataBase).getArterialPressure()
                    ) {
                alarmMessage.append("动脉压数据不在正常范围");
                alarm = true;
            }
            if (venousPressureAlarmSetting.getCeiling() < ((PressureData) dataBase).getVenousPressure()
                    || venousPressureAlarmSetting.getFloor() > ((PressureData) dataBase).getVenousPressure()
                    ) {
                alarmMessage.append("静脉压数据不在正常范围");
                alarm = true;
            }
            if (plasmaPressureAlarmSetting.getCeiling() < ((PressureData) dataBase).getPlasmaPressure()
                    || plasmaPressureAlarmSetting.getFloor() > ((PressureData) dataBase).getPlasmaPressure()) {
                alarmMessage.append("血浆压数据不在正常范围");
                alarm = true;
            }
            if (transmembranePressureAlarmSetting.getCeiling() < ((PressureData) dataBase).getTransmembranePressure()
                    || transmembranePressureAlarmSetting.getFloor() > ((PressureData) dataBase).getTransmembranePressure()) {
                alarmMessage.append("跨膜压数据不在正常范围");
                alarm = true;
            }
        }
    }


    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        callback.run();
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
