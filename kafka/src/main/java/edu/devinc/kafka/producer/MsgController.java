package edu.devinc.kafka.producer;

import edu.devinc.readingRoom.entity.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("msg")
public class MsgController {


    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    @PostMapping
    public void sendMsg(Long msgId, String msg) {


        ListenableFuture<SendResult<Long, String>> future = kafkaTemplate.send("msg", msgId, msg);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }


}
