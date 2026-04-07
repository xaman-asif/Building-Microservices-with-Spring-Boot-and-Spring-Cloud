package com.practice.springboot;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.HttpConnectStrategy;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WikimediaChangesProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final String topic;

  public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate,
                                  @Value("${spring.kafka.topic.name}") String topic) {
    this.kafkaTemplate = kafkaTemplate;
    this.topic = topic;
  }

  public void sendMessage() throws InterruptedException {
    String url = "https://stream.wikimedia.org/v2/stream/recentchange";
    BackgroundEventHandler backgroundEventHandler = new WikimediaChangesHandler(kafkaTemplate, topic);

    HttpConnectStrategy connectStrategy = HttpConnectStrategy.http(URI.create(url)).headers(
        Headers.of("User-Agent", "kafka-producer-wikimedia/1.0 (your-email@example.com)", "Accept",
            "text/event-stream"));

    EventSource.Builder eventSourceBuilder = new EventSource.Builder(connectStrategy);

    BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(backgroundEventHandler,
        eventSourceBuilder);


    try (BackgroundEventSource source = builder.build()) {
      source.start();
      System.out.println("source started");
      TimeUnit.MINUTES.sleep(2);
    }
  }
}
