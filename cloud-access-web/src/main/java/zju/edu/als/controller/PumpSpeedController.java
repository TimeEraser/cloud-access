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
import zju.edu.als.dao.PumpSpeedDao;
import zju.edu.als.domain.data.PumpSpeedData;
import zju.edu.als.domain.result.Result;
import zju.edu.als.monitor.PumpSpeedMonitor;
import zju.edu.als.util.DateFormatUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * Created by zzq on 2016/10/29.
 */
@Controller
@RequestMapping("/pumpSpeed")
public class PumpSpeedController {
    private static final Logger logger = LoggerFactory.getLogger(PressureController.class);

    @Resource("pumpSpeedDao")
    PumpSpeedDao pumpSpeedDao;

    @Resource("pumpSpeedMonitor")
    PumpSpeedMonitor pumpSpeedMonitor;

    @ModelAttribute(value = "pumpSpeedData")
    private PumpSpeedData getPumpSpeedData(HttpServletRequest request){
        String pumpSpeedDataStr=request.getParameter("pumpSpeedData");
        if(pumpSpeedDataStr==null){
            return null;
        }
        PumpSpeedData pumpSpeedData;
        try {
            pumpSpeedData = JSONObject.parseObject(pumpSpeedDataStr, PumpSpeedData.class);
        }catch (Exception e){
            logger.error("Invoke getPumpSpeedData JsonParseException ",e);
            return null;
        }
        return pumpSpeedData;
    }

    @ModelAttribute(value = "pumpSpeedDataList")
    private List<PumpSpeedData> getPumpSpeedDataList(HttpServletRequest request){
        String pumpSpeedDataListStr=request.getParameter("pumpSpeedDataList");
        if(pumpSpeedDataListStr==null){
            return null;
        }
        List<PumpSpeedData> pumpSpeedDataList;
        try {
            pumpSpeedDataList = JSONObject.parseObject(pumpSpeedDataListStr,new TypeReference<List<PumpSpeedData>>(){});
        }catch (Exception e){
            logger.error("Invoke getPumpSpeedDataList JsonParseException ",e);
            return null;
        }
        return pumpSpeedDataList;

    }
    @RequestMapping("/insert")
    @ResponseBody
    public Result insert(@ModelAttribute(value = "pumpSpeedData")PumpSpeedData pumpSpeedData){
        if(pumpSpeedData==null){
            return Result.fail("NullPointException");
        }
        try {
            pumpSpeedDao.insertPumpSpeedData(pumpSpeedData);
            pumpSpeedMonitor.handleData(pumpSpeedData);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        return Result.ok();
    }
    @RequestMapping("/batchInsert")
    @ResponseBody
    public Result insert(@ModelAttribute(value = "pumpSpeedDataList")List<PumpSpeedData> pumpSpeedDataList){
        if(pumpSpeedDataList==null){
            return Result.fail("NullPointException");
        }
        try {
            pumpSpeedDao.batchInsertPumpSpeedData(pumpSpeedDataList);
            pumpSpeedMonitor.handleData(pumpSpeedDataList.toArray(new PumpSpeedData[pumpSpeedDataList.size()]));
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        return Result.ok();
    }
    @RequestMapping("/{surgeryNo}/getAll")
    @ResponseBody
    public Result getAllBySurgeryNo(@PathVariable("surgeryNo")String surgeryNo){
        List<PumpSpeedData> pumpSpeedDataList;
        try {
            pumpSpeedDataList = pumpSpeedDao.selectPumpSpeedDataBySurgeryNo(surgeryNo);
            return Result.ok(pumpSpeedDataList);
        }catch (Exception e){
            logger.error("Invoke getAllBySurgeryNo",e);
            return Result.fail(e);
        }
    }
    @RequestMapping("/{surgeryNo}/{timeRange}")
    @ResponseBody
    public Result getAllBySurgeryNoWithTimeRange(@PathVariable("surgeryNo")String surgeryNo,@PathVariable("timeRange")String timeRange){
        List<PumpSpeedData> pumpSpeedDataList;
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
            pumpSpeedDataList = pumpSpeedDao.selectPumpSpeedDataBySurgeryNoWithTimeRange(surgeryNo,beginTime,endTime);
            return Result.ok(pumpSpeedDataList);
        }catch (Exception e){
            logger.error("Invoke getAllBySurgeryNoWithTimeRange",e);
            return Result.fail(e);
        }
    }
}
