package zju.edu.als.dao;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zju.edu.als.domain.data.PumpSpeedData;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzq on 2016/11/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PumpSpeedDaoTest {
    @Resource
    private PumpSpeedDao pumpSpeedDao;

    @Test
    public void insertTest(){
        PumpSpeedData pumpSpeedData = new PumpSpeedData();
        pumpSpeedData.setSurgeryNo("19920327");
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
        pumpSpeedDao.insertPumpSpeedData(pumpSpeedData);
    }

    @Test
    public void batchInsertTest(){
        PumpSpeedData  pumpSpeedData2 = new PumpSpeedData();
        pumpSpeedData2.setSurgeryNo("19920327");
        pumpSpeedData2.setTimestamp(System.currentTimeMillis());
        pumpSpeedData2.setBloodPump(1);
        pumpSpeedData2.setDialysisPump(1);
        pumpSpeedData2.setFiltrationPump(1);
        pumpSpeedData2.setHeparinPump(1);
        pumpSpeedData2.setSeparationPump(1);
        pumpSpeedData2.setTripePump(1);
        pumpSpeedData2.setWarmer(20);
        pumpSpeedData2.setCumulativeTime(1000);
        pumpSpeedData2.setBloodPumpT(1000);
        pumpSpeedData2.setDialysisPumpT(1000);
        pumpSpeedData2.setHeparinPumpT(1000);
        pumpSpeedData2.setSeparationPumpT(1000);
        pumpSpeedData2.setTripePumpT(1000);
        PumpSpeedData  pumpSpeedData3 = new PumpSpeedData();
        pumpSpeedData3.setSurgeryNo("19920327");
        pumpSpeedData3.setTimestamp(System.currentTimeMillis());
        pumpSpeedData2.setBloodPump(1);
        pumpSpeedData2.setDialysisPump(1);
        pumpSpeedData2.setFiltrationPump(1);
        pumpSpeedData2.setHeparinPump(1);
        pumpSpeedData2.setSeparationPump(1);
        pumpSpeedData2.setTripePump(1);
        pumpSpeedData2.setWarmer(20);
        pumpSpeedData2.setCumulativeTime(1000);
        pumpSpeedData2.setBloodPumpT(1000);
        pumpSpeedData2.setDialysisPumpT(1000);
        pumpSpeedData2.setHeparinPumpT(1000);
        pumpSpeedData2.setSeparationPumpT(1000);
        pumpSpeedData2.setTripePumpT(1000);
        List<PumpSpeedData> pumpSpeedDataList = new ArrayList<>();
        pumpSpeedDataList.add(pumpSpeedData2);
        pumpSpeedDataList.add(pumpSpeedData3);

        pumpSpeedDao.batchInsertPumpSpeedData(pumpSpeedDataList);
    }
    @Test
    public void selectBySurgeryNoTest(){
        List<PumpSpeedData> pumpSpeedDataList = pumpSpeedDao.selectPumpSpeedDataBySurgeryNo("19920327");
        System.out.println(JSONObject.toJSON(pumpSpeedDataList));
    }

    @Test
    public void selectBySurgeryNoWithTimeRangeTest(){
        List<PumpSpeedData> pumpSpeedDataList = pumpSpeedDao.selectPumpSpeedDataBySurgeryNoWithTimeRange("19920327",new Long("1478683289485"),new Long("1478683289487"));
        System.out.println(JSONObject.toJSON(pumpSpeedDataList));
    }
}
