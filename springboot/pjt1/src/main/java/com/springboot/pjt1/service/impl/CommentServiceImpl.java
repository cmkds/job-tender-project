package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.CommentDAO;
import com.springboot.pjt1.data.dao.FeedDAO;
import com.springboot.pjt1.data.dao.MemberDAO;
import com.springboot.pjt1.data.dto.CommentDTO;
import com.springboot.pjt1.data.dto.FeedDTO;
import com.springboot.pjt1.data.dto.custom.CommentInputDTO;
import com.springboot.pjt1.data.entity.Comment;
import com.springboot.pjt1.data.entity.Feed;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentDAO commentDAO;
    private final FeedDAO feedDAO;
    private final MemberDAO memberDAO;

    @Autowired
    public CommentServiceImpl(CommentDAO commentDAO, FeedDAO feedDAO, MemberDAO memberDAO) {
        this.commentDAO = commentDAO;
        this.feedDAO = feedDAO;
        this.memberDAO = memberDAO;
    }
    @Override
    public CommentDTO getComment(long commentSeq) {
        Comment comment = commentDAO.SelectCommentById(commentSeq);
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setCommentSeq(comment.getCommentSeq());
        commentDTO.setContent(comment.getContent());
        commentDTO.setCreateTime(comment.getCreateTime());
        commentDTO.setModifyTime(comment.getModifyTime());

        return commentDTO;
    }

    @Override
    public List<CommentDTO> getCommentByFeedSeq(long feedSeq) {
        List<CommentDTO> commentDTOs = new ArrayList<>();
        List<Comment> comments = commentDAO.SelectCommentByFeedSeq(feedSeq);

        for(Comment comment: comments){
            CommentDTO commentDTO = new CommentDTO();

            commentDTO.setCommentSeq(comment.getCommentSeq());
            commentDTO.setContent(comment.getContent());
            commentDTO.setCreateTime(comment.getCreateTime());
            commentDTO.setModifyTime(comment.getModifyTime());
            commentDTO.setFeedSeq(comment.getFeedSeq());
            commentDTO.setMemberSeq(comment.getMemberSeq());

            commentDTOs.add(commentDTO);
        }

        return commentDTOs;
    }

    @Override
    public List<CommentDTO> getCommentByMemberSeq(long memberSeq) {
        List<CommentDTO> commentDTOs = new ArrayList<>();
        List<Comment> comments = commentDAO.SelectCommentByMemberSeq(memberSeq);

        for(Comment comment: comments){
            CommentDTO commentDTO = new CommentDTO();

            commentDTO.setCommentSeq(comment.getCommentSeq());
            commentDTO.setContent(comment.getContent());
            commentDTO.setCreateTime(comment.getCreateTime());
            commentDTO.setModifyTime(comment.getModifyTime());
            commentDTO.setFeedSeq(comment.getFeedSeq());
            commentDTO.setMemberSeq(comment.getMemberSeq());

            commentDTOs.add(commentDTO);
        }

        return commentDTOs;
    }


    @Override
    public CommentDTO insertComment(CommentInputDTO commentInputDTO) throws Exception {
        // insert Entity
        Comment comment = new Comment();

        comment.setCommentSeq(commentInputDTO.getCommentSeq());
        comment.setContent(commentInputDTO.getContent());
        comment.setCreateTime(new Date());
        comment.setModifyTime(new Date());
        comment.setFeedSeq(commentInputDTO.getFeedSeq());
        comment.setMemberSeq(commentInputDTO.getMemberSeq());

        // insert
        Comment savedComment = commentDAO.insertComment(comment);

        // return DTO
        CommentDTO rCommentDTO = new CommentDTO();

        rCommentDTO.setCommentSeq(savedComment.getCommentSeq());
        rCommentDTO.setContent(savedComment.getContent());
        rCommentDTO.setCreateTime(savedComment.getCreateTime());
        rCommentDTO.setModifyTime(savedComment.getModifyTime());
        rCommentDTO.setMemberSeq(savedComment.getMemberSeq());
        rCommentDTO.setFeedSeq(savedComment.getFeedSeq());

        return rCommentDTO;
    }
    @Override
    public CommentDTO updateComment(long commentSeq, String context) throws Exception {
        Comment updatedComment = commentDAO.updateCommentById(commentSeq, context);
        CommentDTO rCommentDTO = new CommentDTO();

        rCommentDTO.setCommentSeq(updatedComment.getCommentSeq());
        rCommentDTO.setContent(updatedComment.getContent());
        rCommentDTO.setCreateTime(updatedComment.getCreateTime());
        rCommentDTO.setModifyTime(updatedComment.getModifyTime());

        return rCommentDTO;
    }
    @Override
    public void deleteComment(long commentSeq) throws Exception {
        commentDAO.deleteCommentById(commentSeq);
    }

    @Override
    public void deleteCommentByMemberSeq(long memberSeq) {
        commentDAO.deleteCommentByMemberSeq(memberSeq);
    }
}
