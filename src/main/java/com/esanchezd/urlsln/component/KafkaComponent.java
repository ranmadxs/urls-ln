package com.esanchezd.urlsln.component;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaComponent {

	@Value("${cloudkafka.topic}")
	private String topic;

	@Value("${cloudkafka.brokers}")
	private String brokers;

	@Value("${cloudkafka.username}")
	private String username;

	@Value("${cloudkafka.password}")
	private String password;

	private Producer<String, String> producer;
	
	@PostConstruct
	public void init() {
		String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
		
		String jaasCfg = String.format(jaasTemplate, username, password);

		String serializer = StringSerializer.class.getName();
		String deserializer = StringDeserializer.class.getName();
		Properties props = new Properties();
		props.put("bootstrap.servers", brokers);
		props.put("group.id", username + "-consumer");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("auto.offset.reset", "earliest");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", deserializer);
		props.put("value.deserializer", deserializer);
		props.put("key.serializer", serializer);
		props.put("value.serializer", serializer);
		props.put("security.protocol", "SASL_SSL");
		props.put("sasl.mechanism", "SCRAM-SHA-256");
		props.put("sasl.jaas.config", jaasCfg);
		producer = new KafkaProducer<>(props);
		
	}

	public void produce(String message) {
		this.producer.send(new ProducerRecord<>(topic, message));
	}

}
