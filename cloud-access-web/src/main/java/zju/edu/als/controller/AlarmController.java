package zju.edu.als.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zju.edu.als.domain.alarm.AlarmSetting;
import zju.edu.als.domain.result.Result;
import zju.edu.als.monitor.MonitorConfig;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zzq on 2016/11/2.
 */
@Controller
@RequestMapping("/alarm")
public class AlarmController {
    private static final Logger logger = LoggerFactory.getLogger(AlarmController.class);

    @Resource("monitorConfig")
    private MonitorConfig monitorConfig;

    @ModelAttribute("alarmSetting")
    private AlarmSetting getAlarmSetting(HttpServletRequest request){
        String alarmSettingStr=request.getParameter("alarmSetting");
        if(alarmSettingStr==null){
            return null;
        }
        AlarmSetting alarmSetting;
        try {
            alarmSetting = JSONObject.parseObject(alarmSettingStr, AlarmSetting.class);
        }catch (Exception e){
            logger.error("Invoke getGuardianData JsonParseException ",e);
            return null;
        }
        return alarmSetting;
    }
    @RequestMapping("/updateAlarmSetting")
    @ResponseBody
    public Result updateAlarmSetting(@ModelAttribute("alarmSetting")AlarmSetting alarmSetting){
        return monitorConfig.updateAlarmSetting(alarmSetting);
    }
    
    @RequestMapping("/getAll")
    @ResponseBody
    public Result getAll(){
        return monitorConfig.getAllSettings();   
    }
}
