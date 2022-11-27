package com.efubChat.chat.domain.stomprabbit;

import com.efubChat.chat.domain.chat.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Log4j2
public class StompRabbitController {

    private final RabbitTemplate template;

    private final static String CHAT_EXCHANGE_NAME = "chat.exchange";
    private final static String CHAT_QUEUE_NAME = "chat.queue";

    @MessageMapping("chat.enter.{chatRoomId}")
    public void enter(ChatDto chat, @DestinationVariable String chatRoomId){
        chat.setNickname(chat.getNickname());
        chat.setMessage("입장하셨습니다.");
        chat.setRegisterDate(LocalDateTime.now());

        //template.convertAndSend(CHAT_EXCHANGE_NAME, "room." + chatRoomId, chat); // exchange
        //template.convertAndSend("room." + chatRoomId, chat); //queue
        template.convertAndSend("amq.topic", "room." + chatRoomId, chat); //topic
    }

    @MessageMapping("chat.message.{chatRoomId}")
    public void send(ChatDto chat, @DestinationVariable String chatRoomId){
        chat.setRegisterDate(LocalDateTime.now());

        //template.convertAndSend(CHAT_EXCHANGE_NAME, "room." + chatRoomId, chat);
        //template.convertAndSend( "room." + chatRoomId, chat);
        template.convertAndSend("amq.topic", "room." + chatRoomId, chat);
    }

    //receive()는 단순히 큐에 들어온 메세지를 소비만 한다. (현재는 디버그용도)
    @RabbitListener(queues = CHAT_QUEUE_NAME)
    public void receive(ChatDto chat){
        System.out.println("received : " + chat.getMessage());
    }
}