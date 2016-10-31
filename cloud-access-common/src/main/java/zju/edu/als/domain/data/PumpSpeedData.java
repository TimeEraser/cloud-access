package zju.edu.als.domain.data;

/**
 * Created by zzq on 2016/10/27.
 */
public class PumpSpeedData extends DataBase{
    private long cumulativeTime;//'累计时间'
    private int bloodPump;//'血泵'
    private int separationPump;//'分离泵'
    private int dialysisPump;//'透析泵'
    private int tripePump;//'废液泵'
    private int filtrationPump;//'过滤泵'
    private int circulatingPump;//'累计泵'
    private int heparinPump;//'肝素泵'
    private int warmer;//'加热温度'
    private int bloodPumpT;//'血液泵累计'
    private int separationPumpT;//'FP累计'
    private int dialysisPumpT;//'DP累计'
    private int tripePumpT;//'RP累计'
    private int filtrationPumpT;//'FP2累计'
    private int circulatingPumpT;//'CP累计'
    private int heparinPumpT;//'SP累计'

   public long getCumulativeTime() {
        return cumulativeTime;
    }

    public void setCumulativeTime(long cumulativeTime) {
        this.cumulativeTime = cumulativeTime;
    }

    public int getBloodPump() {
        return bloodPump;
    }

    public void setBloodPump(int bloodPump) {
        this.bloodPump = bloodPump;
    }

    public int getSeparationPump() {
        return separationPump;
    }

    public void setSeparationPump(int separationPump) {
        this.separationPump = separationPump;
    }

    public int getDialysisPump() {
        return dialysisPump;
    }

    public void setDialysisPump(int dialysisPump) {
        this.dialysisPump = dialysisPump;
    }

    public int getTripePump() {
        return tripePump;
    }

    public void setTripePump(int tripePump) {
        this.tripePump = tripePump;
    }

    public int getFiltrationPump() {
        return filtrationPump;
    }

    public void setFiltrationPump(int filtrationPump) {
        this.filtrationPump = filtrationPump;
    }

    public int getCirculatingPump() {
        return circulatingPump;
    }

    public void setCirculatingPump(int circulatingPump) {
        this.circulatingPump = circulatingPump;
    }

    public int getHeparinPump() {
        return heparinPump;
    }

    public void setHeparinPump(int heparinPump) {
        this.heparinPump = heparinPump;
    }

    public int getWarmer() {
        return warmer;
    }

    public void setWarmer(int warmer) {
        this.warmer = warmer;
    }

    public int getBloodPumpT() {
        return bloodPumpT;
    }

    public void setBloodPumpT(int bloodPumpT) {
        this.bloodPumpT = bloodPumpT;
    }

    public int getSeparationPumpT() {
        return separationPumpT;
    }

    public void setSeparationPumpT(int separationPumpT) {
        this.separationPumpT = separationPumpT;
    }

    public int getDialysisPumpT() {
        return dialysisPumpT;
    }

    public void setDialysisPumpT(int dialysisPumpT) {
        this.dialysisPumpT = dialysisPumpT;
    }

    public int getTripePumpT() {
        return tripePumpT;
    }

    public void setTripePumpT(int tripePumpT) {
        this.tripePumpT = tripePumpT;
    }

    public int getFiltrationPumpT() {
        return filtrationPumpT;
    }

    public void setFiltrationPumpT(int filtrationPumpT) {
        this.filtrationPumpT = filtrationPumpT;
    }

    public int getCirculatingPumpT() {
        return circulatingPumpT;
    }

    public void setCirculatingPumpT(int circulatingPumpT) {
        this.circulatingPumpT = circulatingPumpT;
    }

    public int getHeparinPumpT() {
        return heparinPumpT;
    }

    public void setHeparinPumpT(int heparinPumpT) {
        this.heparinPumpT = heparinPumpT;
    }
}
