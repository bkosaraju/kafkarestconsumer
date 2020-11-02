package io.github.bkosaraju.kafkarest.service.impl;

import io.github.bkosaraju.kafkarest.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Service
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {

    KafkaTemplate<Integer,String> kafkaProducerTemplate;

    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<Integer, String> kafkaProducerTemplate) {
        this.kafkaProducerTemplate = kafkaProducerTemplate;
    }



    @Value("${spring.kafka.producer.topic}")
    private String topic;

    @Value("${spring.kafka.producer.errortopic:}")
    private String errortopic;

    public void publishMessages(List<String> msgs) {
        for (String msg : msgs) {
            ListenableFuture<SendResult<Integer, String>> future = kafkaProducerTemplate.send(topic, msg);
            future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

                @Override
                public void onSuccess(SendResult<Integer, String> result) {
                    log.debug("Successfully written message : {}",msg);
                }

                @Override
                public void onFailure(Throwable ex) {
                    if ( errortopic != null ) {
                        log.warn("Unable to write message: {} \n into target kafka topic: {},  hence pushing into error topic: {}", msg, topic, errortopic);
                        kafkaProducerTemplate.send(errortopic, msg);
                    } else {
                        log.warn("Unable to write message: {} \n into target kafka topic: {}, please specify spring.kafka.producer.errortopic", msg, topic, errortopic);
                    }
                }
            });
        }
    }
}
