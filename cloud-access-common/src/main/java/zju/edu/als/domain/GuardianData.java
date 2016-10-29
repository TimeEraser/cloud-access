package zju.edu.als.domain;

/**
 * Created by zzq on 2016/10/27.
 */
public class GuardianData {
    private long id;//'主键'
    private String surgeryNo ;//'手术号'
    private long timestamp;//'时间戳'
    private int heartRate;//'心率'
    private int systolicPressure;//'收缩压'
    private int diastolicPressure;//'舒张压'
    private int bloodOxygen;//'血氧'

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurgeryNo() {
        return surgeryNo;
    }

    public void setSurgeryNo(String surgeryNo) {
        this.surgeryNo = surgeryNo;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(int systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public int getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(int diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public int getBloodOxygen() {
        return bloodOxygen;
    }

    public void setBloodOxygen(int bloodOxygen) {
        this.bloodOxygen = bloodOxygen;
    }
}
