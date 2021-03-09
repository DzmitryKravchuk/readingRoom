package edu.devinc.readingRoom;

import edu.devinc.readingRoom.entity.BookDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

//@EnableKafka
@SpringBootApplication
public class ReadingRoomApplication {

//    @KafkaListener(topics="msg")
 //   public void orderListener(ConsumerRecord<Long, BookDTO> record){
 //       System.out.println(record.partition());
 //       System.out.println(record.key());
 //       System.out.println(record.value());
 //   }

    public static void main(String[] args) {
        SpringApplication.run(ReadingRoomApplication.class, args);
    }

}
