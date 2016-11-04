package zju.edu.als.alarm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import zju.edu.als.constant.SurgeryState;
import zju.edu.als.dao.SurgeryDao;
import zju.edu.als.domain.surgery.Surgery;

import javax.annotation.Resource;

/**
 * Created by zzq on 2016/11/4.
 */
@Component
public class AlarmCenter {
    private static final Logger logger = LoggerFactory.getLogger(AlarmCenter.class);
    @Resource(name = "surgeryDao")
    private SurgeryDao surgeryDao;
    public  void sendAlarm(String surgeryNo,String message){
        try {
            Surgery surgery=surgeryDao.selectSurgery(surgeryNo);
            String[] persons = surgery.getAlarmPerson().split(",");
            for (String person:
                 persons) {
                SMSClient.sendMessage(person,surgery.getPatient()+" "+message);
            }
        }catch (Exception e){
            logger.error("Invoke sendAlarm selectSurgery Failed",e);
        }
    }
}
