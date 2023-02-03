package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {
    CommentDTO getComment(long commentSeq);
    List<CommentDTO> getCommentByFeedSeq(long feedSeq);
    long getCommentSeqByFeedSeq(long feedSeq);
    CommentDTO insertComment(CommentDTO commentDTO) throws Exception;
    CommentDTO updateComment(long commentSeq, String context)throws Exception;
    void deleteComment(long commentSeq)throws Exception;
}
