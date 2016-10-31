package zju.edu.als.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zju.edu.als.domain.result.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zzq on 2016/10/29.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/beginSurgery")
    @ResponseBody
    public Result beginSurgery(HttpServletRequest request){
        String surgeryNo = request.getParameter("surgeryNo");
        return Result.ok();
    }
    @RequestMapping("/endSurgery")
    @ResponseBody
    public Result endSurgery(HttpServletRequest request){
        String surgeryNo = request.getParameter("surgeryNo");
        return Result.ok();
    }
}
