package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.entity.Comment;

import java.util.List;

public interface CommentDAO {
    Comment insertComment(Comment comment) throws Exception;
    Comment SelectCommentById(Long commentSeq);
    List<Comment> SelectCommentByFeedSeq(long feedSeq);
    Comment updateCommentById(Long commentSeq, String content) throws Exception;
    void deleteCommentById(Long commentSeq) throws Exception;
    List<Comment> deleteCommentByMemberSeq(long memberSeq);

    List<Comment> SelectCommentByMemberSeq(long memberSeq);
}
