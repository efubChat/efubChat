package com.efubChat.chat.domain.chat.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ChatDto {
    private Long id;
    private Long chatRoomId;
    private UUID memberId;
    private String nickname;
    private String message;
    private String region;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime registerDate;
}
