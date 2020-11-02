package io.github.bkosaraju.kafkarest.service;

import java.util.List;

public interface ConsumeRestSource {
    List<String> ConsumeMessages();
}
