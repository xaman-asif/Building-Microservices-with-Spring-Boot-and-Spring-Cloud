package com.practice.springboot.kafka;

import com.practice.springboot.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

  @KafkaListener(topics = "another-topic", groupId = "myGroup")
  public void consumer(String message) {
    LOGGER.info(String.format("Message received -> %s", message));
  }

  @KafkaListener(topics = "another-json-topic", groupId = "myGroup")
  public void consumer(User user) {
    LOGGER.info(String.format("Message received -> %s", user.toString()));
  }
}
