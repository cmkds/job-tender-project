package com.springboot.pjt1.data.dto.custom;

import lombok.Data;

import java.util.Date;

@Data
public class ConnectInputDTO {
    private long connectSeq;

    private long follower;

    private long following;
}
