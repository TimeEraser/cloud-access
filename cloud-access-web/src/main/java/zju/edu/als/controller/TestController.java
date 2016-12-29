package zju.edu.als.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zzq on 2016/12/29.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/helloWord")
    @ResponseBody
    public String helloWord(){
        return "helloWord";
    }
}
