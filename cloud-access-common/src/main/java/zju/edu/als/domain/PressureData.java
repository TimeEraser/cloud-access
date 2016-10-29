package zju.edu.als.domain;

/**
 * Created by zzq on 2016/10/27.
 */
public class PressureData {
    private long id;//'主键'
    private String surgeryNo ;//'手术号'
    private long time_stamp;//'时间戳'
    private int inBloodPressure;//'采血压'
    private int plasmaInletPressure;//'血浆入口压P1st'
    private int arterialPressure;//'动脉压'
    private int venousPressure;//'静脉压'
    private int plasmaPressure;//'血浆压'
    private int transmembranePressure;//'跨膜压'

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

    public long getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(long time_stamp) {
        this.time_stamp = time_stamp;
    }

    public int getInBloodPressure() {
        return inBloodPressure;
    }

    public void setInBloodPressure(int inBloodPressure) {
        this.inBloodPressure = inBloodPressure;
    }

    public int getPlasmaInletPressure() {
        return plasmaInletPressure;
    }

    public void setPlasmaInletPressure(int plasmaInletPressure) {
        this.plasmaInletPressure = plasmaInletPressure;
    }

    public int getArterialPressure() {
        return arterialPressure;
    }

    public void setArterialPressure(int arterialPressure) {
        this.arterialPressure = arterialPressure;
    }

    public int getVenousPressure() {
        return venousPressure;
    }

    public void setVenousPressure(int venousPressure) {
        this.venousPressure = venousPressure;
    }

    public int getPlasmaPressure() {
        return plasmaPressure;
    }

    public void setPlasmaPressure(int plasmaPressure) {
        this.plasmaPressure = plasmaPressure;
    }

    public int getTransmembranePressure() {
        return transmembranePressure;
    }

    public void setTransmembranePressure(int transmembranePressure) {
        this.transmembranePressure = transmembranePressure;
    }
}
