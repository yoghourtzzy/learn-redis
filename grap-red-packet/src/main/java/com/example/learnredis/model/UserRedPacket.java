package com.example.learnredis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRedPacket implements Serializable {
    private static final long serialVersionUID = 3205884790846589352L;
    private Long id;
    private Long redPacketId;
    private Long userId;
    private Double amount;
    private Timestamp grabTime;
    private String note;
}
