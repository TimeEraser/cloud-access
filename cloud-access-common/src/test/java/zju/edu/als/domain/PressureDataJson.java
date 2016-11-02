package zju.edu.als.domain;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import zju.edu.als.dao.PressureDao;
import zju.edu.als.domain.data.GuardianData;
import zju.edu.als.domain.data.PressureData;

/**
 * Created by zzq on 2016/11/1.
 */
public class PressureDataJson {
    @Test
    public void GuardianDataJsonTest(){
        PressureData pressureData = new PressureData();
        pressureData.setSurgeryNo("exampleSurgeryNo");
        pressureData.setTimestamp(System.currentTimeMillis());
        pressureData.setArterialPressure(1);
        pressureData.setInBloodPressure(1);
        pressureData.setPlasmaInletPressure(1);
        pressureData.setPlasmaPressure(1);
        pressureData.setTransmembranePressure(1);
        pressureData.setVenousPressure(1);
        System.out.println(JSONObject.toJSON(pressureData));
    }
}
