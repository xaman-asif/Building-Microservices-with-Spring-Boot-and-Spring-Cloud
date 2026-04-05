package com.practice.springboot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

  @Value("${spring.kafka.topic.name}")
  private String topic1;

  @Value("${spring.kafka.topic-json.name}")
  private String topic2;

  @Bean
  public NewTopic anotherTopic() {
    return TopicBuilder.name(topic1).build();
  }

  @Bean
  public NewTopic anotherJsonTopic() {
    return TopicBuilder.name(topic2).build();
  }
}
