package com.pd;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.log.ClientLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YCWB0382
 * @date 2023-06-29 14:45
 */
@Slf4j
@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        System.setProperty(ClientLogger.CLIENT_LOG_USESLF4J, "true");
        SpringApplication.run(ProducerApplication.class, args);
    }
}
