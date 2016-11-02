package zju.edu.als.domain.alarm;

/**
 * Created by zzq on 2016/11/2.
 */
public class AlarmSetting {
    private int id;
    private String alarmItem;
    private double ceiling;
    private double floor;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlarmItem() {
        return alarmItem;
    }

    public void setAlarmItem(String alarmItem) {
        this.alarmItem = alarmItem;
    }

    public double getCeiling() {
        return ceiling;
    }

    public void setCeiling(double ceiling) {
        this.ceiling = ceiling;
    }

    public double getFloor() {
        return floor;
    }

    public void setFloor(double floor) {
        this.floor = floor;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
