package sk.stuba.fei.team.local.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("syncService")
public class SyncService {
    private Logger logger = LoggerFactory.getLogger(SyncService.class);

    //    @Scheduled(fixedDelay = 1000 * 60 * 5, initialDelay = 1000)
    @Scheduled(fixedDelay = 1000 * 5)
    public void sync() {
        logger.info("sync called");
    }

}
