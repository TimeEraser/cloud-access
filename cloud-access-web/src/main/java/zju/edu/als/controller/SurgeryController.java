package zju.edu.als.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zju.edu.als.dao.SurgeryDao;
import zju.edu.als.domain.result.Result;
import zju.edu.als.domain.surgery.Surgery;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @RequestMapping("/beginSurgery")
    @ResponseBody
    public Result beginSurgery(@ModelAttribute("surgery") Surgery surgery){
        if(surgery==null||surgery.getSurgeryNo()==null){
            return Result.fail("Null Point");
        }
        try{
            surgeryDao.insertSurgery(surgery);
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
        try{
            surgeryDao.endSurgery(surgery.getSurgeryNo());
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

    @RequestMapping("/getAll")
    @ResponseBody
    public Result getAll(){
        try {
            List<Surgery> surgeryList=surgeryDao.selectAllSurgery();
            return Result.ok(surgeryList);
        }catch (Exception e){
            logger.error("Invoke selectAllSurgery Failed",e);
            return Result.fail(e);
        }
    }
}
