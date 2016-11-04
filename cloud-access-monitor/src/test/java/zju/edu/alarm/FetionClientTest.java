package zju.edu.alarm;

import org.junit.Test;
import zju.edu.als.alarm.FetionClient;

/**
 * Created by zzq on 2016/11/3.
 */
public class FetionClientTest {
    @Test
    public void sentMessageTest(){
        FetionClient.sendMessage("18768113730","ZZQ@2016by$","18768113730","你好");
    }
}
