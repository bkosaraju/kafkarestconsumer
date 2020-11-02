package io.github.bkosaraju.kafkarest.service.impl;

import io.github.bkosaraju.kafkarest.service.ConsumeRestSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class ConsumeRestSourceSourceImpl implements ConsumeRestSource {

    RestTemplate restTemplate;

    @Autowired
    public ConsumeRestSourceSourceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value( "${rest.endpoint.url}")
    private String restEndpoint;

    @Value("${rest.tag.name}")
    private String tagName;

    @Value("${rest.tag.list}")
    private String tagAttributes;

    String[] convertAttributestoList() {
        return tagAttributes.split(",");
    }

    String getMessageFromRset(String tagValue) {
           ResponseEntity<String> responseEntity = restTemplate.getForEntity(restEndpoint + "?tagName=" + tagValue, String.class);
           return responseEntity.getBody();
    }

    @Scheduled(fixedRate = 60000)
    @Async
    public List<String> ConsumeMessages() {
        List<String> msgs = new ArrayList<String>();
        for ( String tagValue: convertAttributestoList() ) {
            log.debug("extracting message for tagName: {}",tagValue);
            log.debug("got message: {}",getMessageFromRset(tagValue));
            msgs.add(getMessageFromRset(tagValue));
        }
        return msgs;
    }
}
