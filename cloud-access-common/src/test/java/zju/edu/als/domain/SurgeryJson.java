package zju.edu.als.domain;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import zju.edu.als.domain.surgery.Surgery;

/**
 * Created by zzq on 2016/11/28.
 */
public class SurgeryJson {
    @Test
    public void test(){
        Surgery surgery = new Surgery();
        surgery.setSurgeryNo("19920327");
        surgery.setDoctor("kk");
        surgery.setPatient("zz");
        surgery.setDoctorId("jj");
        surgery.setPatientId("mm");
        surgery.setState(0);
        surgery.setDescription("肝癌");
        surgery.setAlarmPerson("13073863816");
        System.out.println(JSONObject.toJSONString(surgery));
    }
}
