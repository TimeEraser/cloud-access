package zju.edu.als.dao;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zju.edu.als.domain.data.PressureData;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzq on 2016/11/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PressureDaoTest {
    @Resource
    private PressureDao pressureDao;

    @Test
    public void insertTest(){
        PressureData  pressureData = new PressureData();
        pressureData.setSurgeryNo("19920327");
        pressureData.setTimestamp(System.currentTimeMillis());
        pressureData.setVenousPressure(1);
        pressureData.setTransmembranePressure(1);
        pressureData.setPlasmaPressure(1);
        pressureData.setPlasmaInletPressure(1);
        pressureData.setArterialPressure(1);
        pressureData.setInBloodPressure(1);
        pressureDao.insertPressureData(pressureData);
    }

    @Test
    public void batchInsertTest(){
        PressureData  pressureData2 = new PressureData();
        pressureData2.setSurgeryNo("19920327");
        pressureData2.setTimestamp(System.currentTimeMillis());
        pressureData2.setVenousPressure(2);
        pressureData2.setTransmembranePressure(2);
        pressureData2.setPlasmaPressure(2);
        pressureData2.setPlasmaInletPressure(2);
        pressureData2.setArterialPressure(2);
        pressureData2.setInBloodPressure(2);
        PressureData  pressureData3 = new PressureData();
        pressureData3.setSurgeryNo("19920327");
        pressureData3.setTimestamp(System.currentTimeMillis());
        pressureData3.setVenousPressure(3);
        pressureData3.setTransmembranePressure(3);
        pressureData3.setPlasmaPressure(3);
        pressureData3.setPlasmaInletPressure(3);
        pressureData3.setArterialPressure(3);
        pressureData3.setInBloodPressure(3);
        List<PressureData> pressureDataList = new ArrayList<>();
        pressureDataList.add(pressureData2);
        pressureDataList.add(pressureData3);

        pressureDao.batchInsertPressureData(pressureDataList);
    }
    @Test
    public void selectBySurgeryNoTest(){
        List<PressureData> pressureDataList = pressureDao.selectPressureDataBySurgeryNo("19920327");
        System.out.println(JSONObject.toJSON(pressureDataList));
    }

    @Test
    public void selectBySurgeryNoWithTimeRangeTest(){
        List<PressureData> pressureDataList = pressureDao.selectPressureDataBySurgeryNoWithTimeRange("19920327",new Long("1478682344378"),new Long("1478682344381"));
        System.out.println(JSONObject.toJSON(pressureDataList));
    }
}
