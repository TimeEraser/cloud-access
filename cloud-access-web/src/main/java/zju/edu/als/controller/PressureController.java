package zju.edu.als.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zju.edu.als.dao.PressureDao;
import zju.edu.als.domain.data.PressureData;
import zju.edu.als.domain.result.Result;
import zju.edu.als.monitor.PressureMonitor;
import zju.edu.als.util.DateFormatUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * Created by zzq on 2016/10/29.
 */
@Controller
@RequestMapping("/pressure")
public class PressureController {
    private static final Logger logger = LoggerFactory.getLogger(PressureController.class);

    @Resource(name = "pressureDao")
    PressureDao pressureDao;

    @Resource(name = "pressureMonitor")
    PressureMonitor pressureMonitor;

    @ModelAttribute(value = "pressureData")
    private PressureData getPressureData(HttpServletRequest request){
        String pressureDataStr=request.getParameter("pressureData");
        if(pressureDataStr==null){
            return null;
        }
        PressureData pressureData;
        try {
            pressureData = JSONObject.parseObject(pressureDataStr, PressureData.class);
        }catch (Exception e){
            logger.error("Invoke getPressureData JsonParseException ",e);
            return null;
        }
        return pressureData;
    }

    @ModelAttribute(value = "pressureDataList")
    private List<PressureData> getPressureDataList(HttpServletRequest request){
        String pressureDataListStr=request.getParameter("pressureDataList");
        if(pressureDataListStr==null){
            return null;
        }
        List<PressureData> pressureDataList;
        try {
            pressureDataList = JSONObject.parseObject(pressureDataListStr,new TypeReference<List<PressureData>>(){});
        }catch (Exception e){
            logger.error("Invoke getPressureDataList JsonParseException ",e);
            return null;
        }
        return pressureDataList;

    }
    @RequestMapping("/insert")
    @ResponseBody
    public Result insert(@ModelAttribute(value = "pressureData")PressureData pressureData){
        if(pressureData==null){
            return Result.fail("NullPointException");
        }
        try {
            pressureDao.insertPressureData(pressureData);
            pressureMonitor.handleData(pressureData);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        return Result.ok();
    }

    @RequestMapping("/batchInsert")
    @ResponseBody
    public Result batchInsert(@ModelAttribute(value = "pressureDataList")List<PressureData> pressureDataList){
        if(pressureDataList==null){
            return Result.fail("NullPointException");
        }
        try {
            pressureDao.batchInsertPressureData(pressureDataList);
            pressureMonitor.handleData(pressureDataList.toArray(new PressureData[pressureDataList.size()]));
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        return Result.ok();
    }
    @RequestMapping("/{surgeryNo}/getAll")
    @ResponseBody
    public Result getAllBySurgeryNo(@PathVariable("surgeryNo")String surgeryNo){
        List<PressureData> pressureDataList;
        try {
            pressureDataList = pressureDao.selectPressureDataBySurgeryNo(surgeryNo);
            return Result.ok(pressureDataList);
        }catch (Exception e){
            logger.error("Invoke getAllBySurgeryNo",e);
            return Result.fail(e);
        }
    }
    @RequestMapping("/{surgeryNo}/{timeRange}")
    @ResponseBody
    public Result getAllBySurgeryNoWithTimeRange(@PathVariable("surgeryNo")String surgeryNo,@PathVariable("timeRange")String timeRange){
        List<PressureData> pressureDataList;
        long beginTime;
        long endTime;
        try{
            String[] times=timeRange.split("~");
            beginTime= DateFormatUtil.parse(times[0]);
            endTime= DateFormatUtil.parse(times[1]);
        } catch (ParseException e) {
            logger.error(timeRange +"parse exception");
            return Result.fail(e);
        }
        try {
            pressureDataList = pressureDao.selectPressureDataBySurgeryNoWithTimeRange(surgeryNo,beginTime,endTime);
            return Result.ok(pressureDataList);
        }catch (Exception e){
            logger.error("Invoke getAllBySurgeryNoWithTimeRange",e);
            return Result.fail(e);
        }
    }
}
