package com.efubChat.chat.domain.chatRoom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping(value = "/chat")
public class ChatRoomController {
    @GetMapping("/rooms")
    public String getRooms(){
        return "rooms";
    }

    @GetMapping(value = "/room")
    public String getRoom(Long chatRoomId, String nickname, Model model){
        model.addAttribute("chatRoomId", chatRoomId);
        model.addAttribute("nickname", nickname);

        return "room";
    }
}
