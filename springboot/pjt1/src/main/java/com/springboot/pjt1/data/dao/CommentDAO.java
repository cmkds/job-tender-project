package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.entity.Comment;

public interface CommentDAO {
    Comment insertComment(Comment comment) throws Exception;
    Comment SelectCommentById(Long commentSeq);
    Comment updateCommentById(Long commentSeq, String content) throws Exception;
    void deleteCommentById(Long commentSeq) throws Exception;
}
