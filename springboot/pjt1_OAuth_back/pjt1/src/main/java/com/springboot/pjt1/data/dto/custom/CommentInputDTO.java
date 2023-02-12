package com.springboot.pjt1.data.dto.custom;

import lombok.Data;

import java.util.Date;

@Data
public class CommentInputDTO {
    private long commentSeq;
    private String content;
    private long FeedSeq;
    private long MemberSeq;
}
