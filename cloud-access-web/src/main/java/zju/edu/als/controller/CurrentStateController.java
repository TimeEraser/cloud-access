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
import zju.edu.als.dao.ALSDao;
import zju.edu.als.dao.GuardianDao;
import zju.edu.als.dao.SurgeryDao;
import zju.edu.als.domain.data.ALSData;
import zju.edu.als.domain.data.GeneralData;
import zju.edu.als.domain.data.GuardianData;
import zju.edu.als.domain.result.Result;
import zju.edu.als.domain.surgery.Surgery;
import zju.edu.als.util.DateFormatUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zzq on 2016/12/1.
 */
@RequestMapping("/{state}")
@Controller
public class CurrentStateController {
    private static final Logger logger = LoggerFactory.getLogger(CurrentStateController.class);
    @Resource
    private SurgeryDao surgeryDao;
    @Resource
    private ALSDao alsDao;
    @Resource
    private GuardianDao guardianDao;
    @ModelAttribute("surgeryNoList")
    public List<String> getSurgeryNoList(HttpServletRequest request){
        String surgeryNoListStr = request.getParameter("surgeryNoList");
        if(surgeryNoListStr==null){
            return null;
        }
        try {
            List<String> surgeryNoList = JSONObject.parseObject(surgeryNoListStr,new TypeReference<List<String>>(){});
            logger.info("Invoke getSurgery"+surgeryNoList);
            return surgeryNoList;
        }catch (Exception e){
            logger.error("Invoke getSurgery JsonParseException ",e);
            return null;
        }

    }
    @ModelAttribute("timeRange")
    public String getTimeRange(HttpServletRequest request){
        String timeRange = request.getParameter("timeRange");
        return timeRange;
    }
    @RequestMapping("/surgery")
    @ResponseBody
    public Result surgery(@PathVariable("state")Integer state){
        try {
            List<Surgery> surgeryList = surgeryDao.selectSurgeryByState(state);
            return Result.ok(surgeryList);
        }catch (Exception e){
            return Result.fail(e);
        }
    }

    @RequestMapping("/data")
    @ResponseBody
    public Result data(@ModelAttribute("surgeryNoList")List<String> surgeryNoList,@ModelAttribute("timeRange")String timeRange){
        
        if(surgeryNoList==null||surgeryNoList.size()==0){
            return Result.fail("No SurgeryNo");
        }
        Map<String,GeneralData> generalDataMap = new HashMap<>();
        for(String surgeryNo:surgeryNoList){
            GeneralData generalData = new GeneralData();
            generalData.setSurgeryNo(surgeryNo);
            generalData.setAlsDataList(new ArrayList<ALSData>());
            generalData.setGuardianDataList(new ArrayList<GuardianData>());
            generalDataMap.put(surgeryNo,generalData);
        }
        if(timeRange==null){
            return Result.fail("No TimeRange");
        }
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
        List<ALSData> alsDataList=alsDao.selectALSDataBySurgeryNoListWithTimeRange(surgeryNoList,beginTime,endTime);
        List<GuardianData> guardianDataList=guardianDao.selectGuardianDataBySurgeryNoListWithTimeRange(surgeryNoList,beginTime,endTime);
        for(ALSData alsData:alsDataList){
            String surgeryNo = alsData.getSurgeryNo();
            if(generalDataMap.containsKey(surgeryNo)){
                generalDataMap.get(surgeryNo).getAlsDataList().add(alsData);
            }
        }
        for(GuardianData guardianData:guardianDataList){
            String surgeryNo = guardianData.getSurgeryNo();
            if(generalDataMap.containsKey(surgeryNo)){
                generalDataMap.get(surgeryNo).getGuardianDataList().add(guardianData);
            }
        }
        return Result.ok(generalDataMap);
    }
}
