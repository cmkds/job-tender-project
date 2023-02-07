package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.CommentDTO;
import com.springboot.pjt1.data.dto.custom.CommentInputDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {
    CommentDTO getComment(long commentSeq);
    List<CommentDTO> getCommentByFeedSeq(long feedSeq);
    List<CommentDTO> getCommentByMemberSeq(long memberSeq);
    CommentDTO insertComment(CommentInputDTO commentInputDTO) throws Exception;
    CommentDTO updateComment(long commentSeq, String context)throws Exception;
    void deleteComment(long commentSeq)throws Exception;
    void deleteCommentByMemberSeq(long memberSeq);
}
