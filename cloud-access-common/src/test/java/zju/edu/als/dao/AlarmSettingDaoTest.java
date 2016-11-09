package zju.edu.als.dao;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zju.edu.als.domain.result.Result;

import javax.annotation.Resource;

/**
 * Created by zzq on 2016/11/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class AlarmSettingDaoTest {

    @Resource(name = "alarmSettingDao")
    private AlarmSettingDao alarmSettingDao;

    @Test
    public void  selectAllSettingTest(){
        System.out.print(JSONObject.toJSONString(Result.ok(alarmSettingDao.selectAllAlarmSettings())));
    }
}
