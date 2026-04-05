package com.practice.springboot.controller;

import com.practice.springboot.kafka.MessageKafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {
  private final MessageKafkaProducer messageKafkaProducer;

  public MessageController(MessageKafkaProducer messageKafkaProducer) {
    this.messageKafkaProducer = messageKafkaProducer;
  }

  @GetMapping("/publish")
  public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
    messageKafkaProducer.sendMessage(message);
    return ResponseEntity.ok("Message sent to Kafka topic");
  }
}
