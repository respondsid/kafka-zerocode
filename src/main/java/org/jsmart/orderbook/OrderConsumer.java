package org.jsmart.orderbook;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.jsmart.orderbook.serializer.OrderDeserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;

import kafka.message.ExchangeMessage.Order;
import kafka.utils.ShutdownableThread;

public class OrderConsumer<K, V> extends ShutdownableThread {
	private final KafkaConsumer<K, V> consumer;
	private final String topic;

	public OrderConsumer(String topic) {
		super("KafkaConsumerExample", false);
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "OrderConsumer");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, OrderDeserializer.class.getName());
		consumer = new KafkaConsumer<>(props);
		this.topic = topic;
	}

	@Override
	public void doWork() {
		consumer.subscribe(Collections.singletonList(this.topic));
		ConsumerRecords<K, V> records = consumer.poll(1000);
		for (ConsumerRecord<K, V> record : records) {
			try {
				System.out.println("Received message: (" + JsonFormat.printer().print((MessageOrBuilder) record.value())
						+ ") at offset " + record.offset());
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public String name() {
		return null;
	}

	@Override
	public boolean isInterruptible() {
		return false;
	}
}