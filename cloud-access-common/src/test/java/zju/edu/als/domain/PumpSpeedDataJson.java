package zju.edu.als.domain;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import zju.edu.als.domain.data.PressureData;
import zju.edu.als.domain.data.PumpSpeedData;

/**
 * Created by zzq on 2016/11/1.
 */
public class PumpSpeedDataJson {
    @Test
    public void GuardianDataJsonTest(){
        PumpSpeedData pumpSpeedData = new PumpSpeedData();
        pumpSpeedData.setSurgeryNo("exampleSurgeryNo");
        pumpSpeedData.setTimestamp(System.currentTimeMillis());
        pumpSpeedData.setBloodPump(1);
        pumpSpeedData.setDialysisPump(1);
        pumpSpeedData.setFiltrationPump(1);
        pumpSpeedData.setHeparinPump(1);
        pumpSpeedData.setSeparationPump(1);
        pumpSpeedData.setTripePump(1);
        pumpSpeedData.setWarmer(20);
        pumpSpeedData.setCumulativeTime(1000);
        pumpSpeedData.setBloodPumpT(1000);
        pumpSpeedData.setDialysisPumpT(1000);
        pumpSpeedData.setHeparinPumpT(1000);
        pumpSpeedData.setSeparationPumpT(1000);
        pumpSpeedData.setTripePumpT(1000);
        System.out.println(JSONObject.toJSON(pumpSpeedData));
    }
}
