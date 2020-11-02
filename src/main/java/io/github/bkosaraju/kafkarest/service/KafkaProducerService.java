package io.github.bkosaraju.kafkarest.service;

import java.util.List;

public interface KafkaProducerService {
    void publishMessages(List<String> msgs);
}
