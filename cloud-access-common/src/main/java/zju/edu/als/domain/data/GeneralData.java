package zju.edu.als.domain.data;

import java.util.List;

/**
 * Created by zzq on 2016/12/1.
 */
public class GeneralData {
    String surgeryNo;
    List<ALSData> alsDataList;
    List<GuardianData> guardianDataList;

    public String getSurgeryNo() {
        return surgeryNo;
    }

    public void setSurgeryNo(String surgeryNo) {
        this.surgeryNo = surgeryNo;
    }

    public List<ALSData> getAlsDataList() {
        return alsDataList;
    }

    public void setAlsDataList(List<ALSData> alsDataList) {
        this.alsDataList = alsDataList;
    }

    public List<GuardianData> getGuardianDataList() {
        return guardianDataList;
    }

    public void setGuardianDataList(List<GuardianData> guardianDataList) {
        this.guardianDataList = guardianDataList;
    }
}
