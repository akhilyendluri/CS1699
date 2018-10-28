package com.cs1699.project.blogspot.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.cs1699.project.blogspot.datasource.Blog;

@Configuration
@EnableKafka
public class ConsumerConfiguration {

    /**
     * List of Kafka servers that comes from the configuration file.
     */
	@Value("${spring.kafka.bootstrapserver}")
    private String bootstrapServers;

    /**
     * @return Map of Kafka configuration options.
     */
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "foo");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return props;
    }
    
    @Bean
    public ConsumerFactory<String, Blog> consumerFactory() {
      return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
          new JsonDeserializer<>(Blog.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Blog> kafkaListenerContainerFactory() {
      ConcurrentKafkaListenerContainerFactory<String, Blog> factory =
          new ConcurrentKafkaListenerContainerFactory<>();
      factory.setConsumerFactory(consumerFactory());

      return factory;
    }
}