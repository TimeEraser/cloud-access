package zju.edu.als.domain.surgery;

/**
 * Created by zzq on 2016/11/4.
 */
public class Surgery {
    private int id;
    private String surgeryNo;
    private String patient;
    private String patientId;
    private String doctor;
    private String doctorId;
    private String description;
    private int state;
    private String alarmPerson;
    private long lastAlarm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurgeryNo() {
        return surgeryNo;
    }

    public void setSurgeryNo(String surgeryNo) {
        this.surgeryNo = surgeryNo;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAlarmPerson() {
        return alarmPerson;
    }

    public void setAlarmPerson(String alarmPerson) {
        this.alarmPerson = alarmPerson;
    }

    public long getLastAlarm() {
        return lastAlarm;
    }

    public void setLastAlarm(long lastAlarm) {
        this.lastAlarm = lastAlarm;
    }
}
