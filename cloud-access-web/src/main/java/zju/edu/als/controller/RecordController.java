package zju.edu.als.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by zzq on 2016/12/19.
 */
@Controller
@RequestMapping("/record")
public class RecordController {
    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        int k=0;
       // FileCopyUtils.copy(file.getBytes(),new File("data/"+file.getOriginalFilename()));
        File file1=new File("/Users/macbook/Documents/f");
        file.transferTo(file1);
        return "success";
    }
}
