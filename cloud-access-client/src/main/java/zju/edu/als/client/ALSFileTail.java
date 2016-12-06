package zju.edu.als.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static zju.edu.als.client.constant.ClientConstant.SURGERY_DATA_OFFSET;
@Component
public class ALSFileTail implements Runnable ,SmartLifecycle{
    private static final Logger logger = LoggerFactory.getLogger(ALSFileTail.class);
    private volatile boolean isRunning = false;
    private static long IDLE_SLEEP_INTERVAL = 1000;
    private File ALSDataFile;
    private long filePointer;
    private ExecutorService executorService;
    private boolean isConfigInit=false;
    public ALSFileTail(){
        executorService= Executors.newSingleThreadExecutor();
    }
    public void setConfig(ALSFileTailConfig alsFileTailConfig){
        this.ALSDataFile =alsFileTailConfig.getALSDataFile();
        this.filePointer =alsFileTailConfig.getALSDataFilePoint();
        isConfigInit = true;
    }

   
    public void run() {
        while (!isConfigInit){
            try {
                Thread.currentThread().sleep(IDLE_SLEEP_INTERVAL);
            } catch (InterruptedException e) {
                break;
            }
        }
        try {    
            RandomAccessFile raf ;
            while (isConfigInit) {
                long fileLength = this.ALSDataFile.length();
                raf = new RandomAccessFile(ALSDataFile, "r");
                if(filePointer > fileLength){
                    filePointer=0;
                }
                if (fileLength > filePointer) {    
                    raf.seek(filePointer);
                    String lineISO=raf.readLine();
                    while (lineISO != null) {
                        String line = new String(lineISO.getBytes("ISO-8859-1"),"utf-8");
                        DataSenderManager.sendData(line);
                        lineISO = raf.readLine();
                    }
                    filePointer = raf.getFilePointer();
                }
                try {
                    Thread.currentThread().sleep(IDLE_SLEEP_INTERVAL);
                } catch (InterruptedException e) {
                    raf.close();
                    break;
                }
            }    

        } catch (IOException e) {
               
        }
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        callback.run();
        stop();
    }

    @Override
    public void start() {
        executorService.execute(this);
        isRunning=true;
    }

    @Override
    public void stop() {
        try {
            RandomAccessFile ALSDataOffsetFile = new RandomAccessFile(ALSDataFile.getParent()+"/"+SURGERY_DATA_OFFSET,"rw");
            ALSDataOffsetFile.write(String.valueOf(filePointer).getBytes());
            ALSDataOffsetFile.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        executorService.shutdownNow();
        DataSenderManager.closeSocketConnect();
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPhase() {
        return 0;
    }
}
