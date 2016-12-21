package zju.edu.als.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzq on 2016/11/28.
 */
@Component
public class Collector implements SmartLifecycle{
    private static final Logger logger = LoggerFactory.getLogger(Collector.class);
    private ExecutorService executorService;
    private volatile boolean isRunning=false;

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
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new ServerHandle(DataSenderManager.messages,9203));
        isRunning=true;
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPhase() {
        return 1;
    }
}
