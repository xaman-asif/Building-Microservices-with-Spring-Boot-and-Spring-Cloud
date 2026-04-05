package com.practice.springboot.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MessageKafkaProducer {

  @Value("${spring.kafka.topic.name}")
  private String messageTopic;
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageKafkaProducer.class);
  private final KafkaTemplate<String, String> kafkaTemplate;

  public MessageKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String s) {
    LOGGER.info(String.format("Message sent -> %s", s));
    Message<String> message = MessageBuilder.withPayload(s).setHeader(KafkaHeaders.TOPIC, messageTopic).build();
    kafkaTemplate.send(message);
  }
}
