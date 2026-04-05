package com.practice.springboot.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageKafkaConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageKafkaConsumer.class);

  @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
  public void consumer(String message) {
    LOGGER.info(String.format("Message received -> %s", message));
  }
}
