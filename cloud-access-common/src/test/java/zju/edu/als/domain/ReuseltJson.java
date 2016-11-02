package zju.edu.als.domain;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import zju.edu.als.domain.result.Result;


/**
 * Created by zzq on 2016/11/1.
 */
public class ReuseltJson {
    @Test
    public void resultJsonTest(){
        System.out.print(JSONObject.toJSON(Result.fail("Null Point")));
    }
}
