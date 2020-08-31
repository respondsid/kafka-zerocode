package org.jsmart.orderbook;

public class OrderTest {
    public static void main(String[] args) {
        OrderProducer producerThread = new OrderProducer("zz");
        producerThread.start();

     //   OrderConsumer consumerThread = new OrderConsumer("zz");
        //consumerThread.start();

    }
}