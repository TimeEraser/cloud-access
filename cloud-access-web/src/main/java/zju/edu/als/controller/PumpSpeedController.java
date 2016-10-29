package zju.edu.als.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zju.edu.als.dao.PumpSpeedDao;
import zju.edu.als.domain.PumpSpeedData;
import zju.edu.als.domain.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zzq on 2016/10/29.
 */
public class PumpSpeedController {
    private static final Logger logger = LoggerFactory.getLogger(PressureController.class);

    @Resource("pumpSpeedDao")
    PumpSpeedDao pumpSpeedDao;

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
    @RequestMapping("/inset")
    @ResponseBody
    public Result insert(@ModelAttribute(value = "pumpSpeedData")PumpSpeedData pumpSpeedData){
        if(pumpSpeedData==null){
            return Result.fail("NullPointException");
        }
        try {
            pumpSpeedDao.insertPumpSpeedData(pumpSpeedData);
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
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        return Result.ok();
    }
}
