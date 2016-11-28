package zju.edu.als.domain;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import zju.edu.als.domain.data.ALSData;

/**
 * Created by zzq on 2016/11/27.
 */
public class ALSDataJson {
    @Test
    public void test(){
        ALSData alsData = new ALSData();
        alsData.setCumulativeTime(6000);
        alsData.setTimestamp(System.currentTimeMillis());
        alsData.setdBPT(60);
        alsData.setdDPT(60);
        alsData.setdFP2T(60);
        alsData.setdFPT(60);
        alsData.setdP1st(60);
        alsData.setdP2nd(60);
        alsData.setdP3rd(60);
        alsData.setdPacc(60);
        alsData.setdPart(60);
        alsData.setdPven(60);
        alsData.setdRPT(60);
        alsData.setdSPT(60);
        alsData.setdTMP(60);
        alsData.setiBPSpeed(60);
        alsData.setiCPSpeed(60);
        alsData.setiDPSpeed(60);
        alsData.setiFP2SPeed(60);
        alsData.setiRPSpeed(60);
        alsData.setiSPSpeed(60);
        alsData.setWarmer(60);
        System.out.print(JSONObject.toJSONString(alsData));

    }
}
