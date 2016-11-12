package zju.edu.als.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zju.edu.als.constant.SurgeryState;
import zju.edu.als.dao.SurgeryDao;
import zju.edu.als.domain.result.Result;
import zju.edu.als.domain.surgery.Surgery;
import zju.edu.als.util.DateFormatUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static zju.edu.als.util.DateFormatUtil.DATE_TIME;

/**
 * Created by zzq on 2016/10/29.
 */
@Controller
@RequestMapping("/surgery")
public class SurgeryController {
    private static final Logger logger = LoggerFactory.getLogger(SurgeryController.class);
    @Resource
    private SurgeryDao surgeryDao;
    @ModelAttribute("surgery")
    public Surgery getSurgery(HttpServletRequest request){
        String surgeryStr = request.getParameter("surgery");
        if(surgeryStr==null){
            return null;
        }
        try {
            Surgery surgery = JSONObject.parseObject(surgeryStr, Surgery.class);
            logger.info("Invoke getSurgery"+JSONObject.toJSONString(surgery));
            return surgery;
        }catch (Exception e){
            logger.error("Invoke getSurgery JsonParseException ",e);
            return null;
        }

    }
    @RequestMapping("/newSurgery")
    @ResponseBody
    public Result newSurgery(@ModelAttribute("surgery") Surgery surgery){
        if(surgery==null||surgery.getSurgeryNo()==null){
            return Result.fail("Null Point");
        }
        surgery.setState(SurgeryState.INIT.ordinal());
        try{
            surgeryDao.insertSurgery(surgery);
            return Result.ok();
        }catch (Exception e){
            return Result.fail(e);
        }
    }

    @RequestMapping("/beginSurgery")
    @ResponseBody
    public Result beginSurgery(@ModelAttribute("surgery") Surgery surgery){
        if(surgery==null||surgery.getSurgeryNo()==null){
            return Result.fail("Null Point");
        }
        surgery.setState(SurgeryState.EXECUTING.ordinal());
        surgery.setStartTime(System.currentTimeMillis());
        try{
            surgeryDao.beginSurgery(surgery);
            return Result.ok();
        }catch (Exception e){
            return Result.fail(e);
        }
    }

    @RequestMapping("/endSurgery")
    @ResponseBody
    public Result endSurgery(@ModelAttribute("surgery") Surgery surgery){
        if(surgery==null||surgery.getSurgeryNo()==null){
            return Result.fail("Null Point");
        }
        surgery.setState(SurgeryState.COMPLETE.ordinal());
        surgery.setEndTime(System.currentTimeMillis());
        try{
            surgeryDao.endSurgery(surgery);
            return Result.ok();
        }catch (Exception e){
            return Result.fail(e);
        }
    }
    @RequestMapping("/updateSurgery")
    @ResponseBody
    public Result updateSurgery(@ModelAttribute("surgery") Surgery surgery){
        if(surgery==null||surgery.getSurgeryNo()==null){
            return Result.fail("Null Point");
        }
        try{
            surgeryDao.updateSurgery(surgery);
            return Result.ok();
        }catch (Exception e){
            return Result.fail(e);
        }
    }

    @RequestMapping("/persons")
    @ResponseBody
    public Result persons(){
        try {
            List<String> doctors = surgeryDao.selectDistinctDoctor();
            List<String> patients = surgeryDao.selectDistinctPatient();
            Map<String,List<String>> persons = new HashMap<>();
            persons.put("doctors",doctors);
            persons.put("patients",patients);
            return Result.ok(persons);
        }catch (Exception e){
            return Result.fail(e);
        }
    }
    @RequestMapping("/search")
    @ResponseBody
    public Result search(HttpServletRequest request){
        String doctor = request.getParameter("doctor");
        String patient= request.getParameter("patient");
        Long startTime= System.currentTimeMillis()-DATE_TIME*3;
        Long endTime = System.currentTimeMillis();
        try {
            if (request.getParameter("timeRange") == null) {
                endTime = System.currentTimeMillis();
            } else {
                String[] timeRangeStr= request.getParameter("timeRange").split("~");
                startTime=  DateFormatUtil.parse(timeRangeStr[0]);
                endTime = DateFormatUtil.parse(timeRangeStr[1]);
            }
        } catch (Exception e) {
            logger.error("解析前端timeRange失败,使用默认配置",e);
        }
        try {
            List<Surgery> surgeryList = surgeryDao.selectSurgeryDynamic(startTime,endTime,doctor,patient);
            return Result.ok(surgeryList);
        }catch (Exception e){
            return Result.fail(e);
        }
    }
}
