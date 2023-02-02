package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.CommentDAO;
import com.springboot.pjt1.data.dao.FeedDAO;
import com.springboot.pjt1.data.dao.MemberDAO;
import com.springboot.pjt1.data.dto.CommentDTO;
import com.springboot.pjt1.data.dto.FeedDTO;
import com.springboot.pjt1.data.entity.Comment;
import com.springboot.pjt1.data.entity.Feed;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        commentDTO.setMemberSeq(comment.getMember().getMemberSeq());
        commentDTO.setCreateTime(comment.getCreateTime());
        commentDTO.setModifyTime(comment.getModifyTime());

        return commentDTO;
    }

    @Override
    public List<CommentDTO> getCommentByFeedSeq(long feedSeq) {
        List<CommentDTO> commentDTOs = new ArrayList<>();
        List<Comment> comments = commentDAO.SelectCommentByFeedSeq(feedSeq);

        for (int i = 0; i < comments.size(); i++){
            CommentDTO commentDTO = new CommentDTO();

            commentDTO.setCommentSeq(comments.get(i).getCommentSeq());
            commentDTO.setContent(comments.get(i).getContent());
            commentDTO.setMemberSeq(comments.get(i).getMember().getMemberSeq());
            commentDTO.setCreateTime(comments.get(i).getCreateTime());
            commentDTO.setModifyTime(comments.get(i).getModifyTime());

            commentDTOs.add(commentDTO);
        }

        return commentDTOs;
    }

    @Override
    public CommentDTO insertComment(CommentDTO commentDTO) throws Exception {
        // insert Entity
        Comment comment = new Comment();

        comment.setCommentSeq(commentDTO.getCommentSeq());
        comment.setContent(commentDTO.getContent());
        comment.setCreateTime(commentDTO.getCreateTime());
        comment.setModifyTime(commentDTO.getModifyTime());

        // insert FK
        Feed feed = feedDAO.SelectFeedById(commentDTO.getFeedSeq());
        comment.setFeed(feed);

        Member member = memberDAO.SelectMemberById(commentDTO.getMemberSeq());
        comment.setMember(member);

        // insert
        Comment savedComment = commentDAO.insertComment(comment);

        // return DTO
        CommentDTO rCommentDTO = new CommentDTO();

        rCommentDTO.setCommentSeq(savedComment.getCommentSeq());
        rCommentDTO.setContent(savedComment.getContent());
        rCommentDTO.setCreateTime(savedComment.getCreateTime());
        rCommentDTO.setModifyTime(savedComment.getModifyTime());
        rCommentDTO.setMemberSeq(commentDTO.getMemberSeq());

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
        rCommentDTO.setMemberSeq(updatedComment.getMember().getMemberSeq());

        return rCommentDTO;
    }
    @Override
    public void deleteComment(long commentSeq) throws Exception {
        commentDAO.deleteCommentById(commentSeq);
    }
}
