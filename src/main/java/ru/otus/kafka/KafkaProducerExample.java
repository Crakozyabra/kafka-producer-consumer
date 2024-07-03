package ru.otus.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProducerExample {

    public static String topicName = "students";

    public static void getKafkaExample() throws ExecutionException, InterruptedException {

        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        System.out.println("mark1");
        String k1 = "student001";
        String v1 = "{'Name': 'Ivan', 'Surname': 'Ivanov'}";
        ProducerRecord<String, String> record1 =
                new ProducerRecord<>(topicName, k1, v1);
        System.out.println("mark2");
        String k2 = "student002";
        String v2 = "{'Name': 'Anna', 'Surname': 'Popova'}";
        ProducerRecord<String, String> record2 =
                new ProducerRecord<>(topicName, k2, v2);
        System.out.println("mark3");
        String k3 =  "student003";
        String v3 = "{'Name': 'Petr', 'Surname': 'Petrov'}";
        ProducerRecord<String, String> record3 =
                new ProducerRecord<>(topicName,k3, v3);

        //Send first message with blocks until completed
        producer.send(record1);
        System.out.println("mark5");
        //Send second message async: callback with lambda expression
        producer.send(record2, (recordMetadata, e) -> {
            if(e != null) {
                System.out.println("error message callback!");
            } else {
                System.out.println("success message callback!");
            }
        });

        //Send third message: Async with a callback class
        producer.send(record3, new CustomCallback());
        System.out.println("mark6");
        //Wait for all previously sent messages, then close
        producer.close();

        //OR Wait for 60 seconds, then close
        producer.close(Duration.ofSeconds(1));

    }
}
