package ru.otus.kafka;

import java.util.concurrent.ExecutionException;

public class Main
{
    public static void main( String[] args ) throws ExecutionException, InterruptedException {
        //Можно проверить данные с помощью kafkacat:
        //kcat -C -b localhost:19092 -t students -Z -K:

        KafkaProducerExample.getKafkaExample();
        System.out.println( "-------------------" );
        ConsumerKafka.consumerExample();
    }
}
