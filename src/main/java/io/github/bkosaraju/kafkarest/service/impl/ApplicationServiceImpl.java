package io.github.bkosaraju.kafkarest.service.impl;

import io.github.bkosaraju.kafkarest.service.ConsumeRestSource;
import io.github.bkosaraju.kafkarest.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApplicationServiceImpl {

    ConsumeRestSource restSource;
    KafkaProducerService kafkaProducer;

    @Autowired
    public ApplicationServiceImpl(ConsumeRestSource restSource, KafkaProducerService kafkaProducer) {
        this.restSource = restSource;
        this.kafkaProducer = kafkaProducer;
    }

    @Scheduled(fixedRate = 60000)
    @Async
    public void ConsumeMessages() {
        try {
            kafkaProducer.publishMessages(restSource.ConsumeMessages());
        } catch (Exception e) {
            //Swallow the exception in case if needs to stop
            log.error("Error Occured while running the application");
        }
    }
}
