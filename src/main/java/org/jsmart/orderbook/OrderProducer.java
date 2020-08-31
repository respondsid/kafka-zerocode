package org.jsmart.orderbook;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.jsmart.orderbook.serializer.OrderSerializer;

import kafka.message.ExchangeMessage.Order;


public class OrderProducer<K,V> extends Thread {
    private final KafkaProducer<Integer, String> producer;
    private final String topic;

    public OrderProducer(String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("client.id", "OrderProducer");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, OrderSerializer.class.getName());
		String messageClassType="kafka.message.ExchangeMessage.Order";
        producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    public void run() {
        int messageNo = 1000;
        while (true) {
            String order = "{\"oid\":\"xxx"+messageNo+"\"}";
            try {
            	JsonFormat.parser().merge(order, Order.newBuilder());
            	
                producer.send(new ProducerRecord<>(topic,
                        messageNo,
                        order)).get();
                System.out.println("Sent message: (" + messageNo + ", " + order.toString() + ")");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            ++messageNo;
        }
    }
}