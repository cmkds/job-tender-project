package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.CommentDTO;
import org.springframework.stereotype.Service;

public interface CommentService {
    CommentDTO getComment(long commentSeq);
    CommentDTO insertComment(CommentDTO commentDTO) throws Exception;
    CommentDTO updateComment(long commentSeq, String context)throws Exception;
    void deleteComment(long commentSeq)throws Exception;
}
