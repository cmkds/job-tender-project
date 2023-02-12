package com.springboot.pjt1.data.dto.custom;

import lombok.Data;

import java.util.Date;

@Data
public class HeartInputDTO {
    private long heartSeq;
    private long memberSeq;
    private long feedSeq;
}
