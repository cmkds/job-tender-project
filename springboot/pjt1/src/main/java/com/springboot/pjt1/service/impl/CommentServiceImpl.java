package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.CommentDAO;
import com.springboot.pjt1.data.dto.CommentDTO;
import com.springboot.pjt1.data.entity.Comment;
import com.springboot.pjt1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentDAO commentDAO;

    @Autowired
    public CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }
    @Override
    public CommentDTO getComment(long commentSeq) {
        Comment comment = commentDAO.SelectCommentById(commentSeq);
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setCommentSeq(comment.getCommentSeq());
        commentDTO.setContent(comment.getContent());
        commentDTO.setCreateTime(comment.getCreateTime());
        //commentDTO.setFeedSeq(comment.getFeedSeq());
        //commentDTO.setOwnerSeq(comment.getOwnerSeq());
        commentDTO.setModifyTime(comment.getModifyTime());

        return commentDTO;
    }
    @Override
    public CommentDTO insertComment(CommentDTO commentDTO) throws Exception {
        Comment comment = new Comment();

        comment.setCommentSeq(commentDTO.getCommentSeq());
        comment.setContent(commentDTO.getContent());
        //comment.setOwnerSeq(comment.getOwnerSeq());
        //comment.setFeedSeq(commentDTO.getFeedSeq());
        comment.setCreateTime(commentDTO.getCreateTime());
        comment.setModifyTime(commentDTO.getModifyTime());

        Comment savedComment = commentDAO.insertComment(comment);
        CommentDTO rCommentDTO = new CommentDTO();

        rCommentDTO.setCommentSeq(savedComment.getCommentSeq());
        rCommentDTO.setContent(savedComment.getContent());
        //rCommentDTO.setOwnerSeq(savedComment.getOwnerSeq());
        //rCommentDTO.setFeedSeq(savedComment.getFeedSeq());
        rCommentDTO.setCreateTime(savedComment.getCreateTime());
        rCommentDTO.setModifyTime(savedComment.getModifyTime());

        return rCommentDTO;
    }
    @Override
    public CommentDTO updateComment(long commentSeq, String context) throws Exception {
        Comment updatedComment = commentDAO.updateCommentById(commentSeq, context);
        CommentDTO rCommentDTO = new CommentDTO();

        rCommentDTO.setCommentSeq(updatedComment.getCommentSeq());
        rCommentDTO.setContent(updatedComment.getContent());
        //rCommentDTO.setOwnerSeq(updatedComment.getOwnerSeq());
        //rCommentDTO.setFeedSeq(updatedComment.getFeedSeq());
        rCommentDTO.setCreateTime(updatedComment.getCreateTime());
        rCommentDTO.setModifyTime(updatedComment.getModifyTime());

        return rCommentDTO;
    }
    @Override
    public void deleteComment(long commentSeq) throws Exception {
        commentDAO.deleteCommentById(commentSeq);
    }
}
