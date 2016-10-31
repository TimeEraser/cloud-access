package zju.edu.als.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zju.edu.als.dao.GuardianDao;
import zju.edu.als.domain.data.GuardianData;
import zju.edu.als.domain.result.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zzq on 2016/10/29.
 */
@Controller
@RequestMapping("/guardian")
public class GuardianController {
    private static final Logger logger = LoggerFactory.getLogger(GuardianController.class);

    @Resource("guardianDao")
    GuardianDao guardianDao;

    @ModelAttribute(value = "guardianData")
    private GuardianData getGuardianData(HttpServletRequest request){
        String guardianDataStr=request.getParameter("guardianData");
        if(guardianDataStr==null){
            return null;
        }
        GuardianData guardianData;
        try {
            guardianData = JSONObject.parseObject(guardianDataStr, GuardianData.class);
        }catch (Exception e){
            logger.error("Invoke getGuardianData JsonParseException ",e);
            return null;
        }
        return guardianData;
    }

    @ModelAttribute(value = "guardianDataList")
    private List<GuardianData> getGuardianDataList(HttpServletRequest request){
        String guardianDataListStr=request.getParameter("guardianDataList");
        if(guardianDataListStr==null){
            return null;
        }
        List<GuardianData> guardianDataList;
        try {
            guardianDataList = JSONObject.parseObject(guardianDataListStr,new TypeReference<List<GuardianData>>(){});
        }catch (Exception e){
            logger.error("Invoke getGuardianDataList JsonParseException ",e);
            return null;
        }
        return guardianDataList;

    }

    @RequestMapping("/inset")
    @ResponseBody
    public Result insert(@ModelAttribute(value = "guardianData")GuardianData guardianData){
        if(guardianData==null){
            return Result.fail("NullPointException");
        }
        try {
            guardianDao.insertGuardianData(guardianData);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        return Result.ok();
    }

    @RequestMapping("/batchInsert")
    @ResponseBody
    public Result batchInsert(@ModelAttribute(value = "guardianDataList")List<GuardianData> guardianDataList){
        if(guardianDataList==null){
            return Result.fail("NullPointException");
        }
        try {
            guardianDao.batchInsertGuardianData(guardianDataList);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        return Result.ok();
    }
}
