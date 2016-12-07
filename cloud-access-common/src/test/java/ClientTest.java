import com.alibaba.fastjson.JSONObject;
import zju.edu.als.domain.data.ALSData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Append(new File("/Users/macbook/Documents/surgeryData.txt")));
    }
    static class Append implements Runnable{
        File file;
        public Append(File file){
            this.file=file;
        }
        @Override
        public void run() {
            RandomAccessFile randomAccessFile=null;
            try {
                 randomAccessFile = new RandomAccessFile(file,"rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                long fileLength=randomAccessFile.length();
                randomAccessFile.seek(fileLength);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true){
                ALSData alsData = new ALSData();
                alsData.setCumulativeTime((int)(Math.random()*100));
                alsData.setSurgeryNo("161202162548");
                alsData.setTimestamp(System.currentTimeMillis());
                alsData.setdBPT((int)(Math.random()*100));
                alsData.setdDPT((int)(Math.random()*100));
                alsData.setdFP2T((int)(Math.random()*100));
                alsData.setdFPT((int)(Math.random()*100));
                alsData.setdP1st((int)(Math.random()*100));
                alsData.setdP2nd((int)(Math.random()*100));
                alsData.setdP3rd((int)(Math.random()*100));
                alsData.setdPacc((int)(Math.random()*100));
                alsData.setdPart((int)(Math.random()*100));
                alsData.setdPven((int)(Math.random()*100));
                alsData.setdRPT((int)(Math.random()*100));
                alsData.setdSPT((int)(Math.random()*100));
                alsData.setdTMP((int)(Math.random()*100));
                alsData.setiBPSpeed((int)(Math.random()*100));
                alsData.setiCPSpeed((int)(Math.random()*100));
                alsData.setiDPSpeed((int)(Math.random()*100));
                alsData.setiFP2SPeed((int)(Math.random()*100));
                alsData.setiRPSpeed((int)(Math.random()*100));
                alsData.setiSPSpeed((int)(Math.random()*100));
                alsData.setWarmer((int)(Math.random()*100));
                String line = "DATA:"+ JSONObject.toJSONString(alsData)+"\n";
                try {
                    randomAccessFile.writeBytes(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}