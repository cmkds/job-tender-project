package com.springboot.pjt1.data.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class ConnectDTO {
    private long connectSeq;
    private Date createTime;

    private long follower;

    private long following;
}
