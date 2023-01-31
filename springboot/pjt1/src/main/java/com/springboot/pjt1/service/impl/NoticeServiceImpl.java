package com.springboot.pjt1.service.impl;

import com.springboot.pjt1.data.dao.MemberDAO;
import com.springboot.pjt1.data.dao.NoticeDAO;
import com.springboot.pjt1.data.dto.NoticeDTO;
import com.springboot.pjt1.data.dto.NoticeDTO;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.data.entity.Notice;
import com.springboot.pjt1.service.NoticeService;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
    private final NoticeDAO noticeDAO;
    private final MemberDAO memberDAO;

    public NoticeServiceImpl(NoticeDAO noticeDAO, MemberDAO memberDAO) {
        this.noticeDAO = noticeDAO;
        this.memberDAO = memberDAO;
    }
    @Override
    public NoticeDTO getNotice(long noticeSeq) {
        Notice notice = noticeDAO.SelectNoticeById(noticeSeq);
        NoticeDTO noticeDTO = new NoticeDTO();

        noticeDTO.setNoticeSeq(notice.getNoticeSeq());
        noticeDTO.setContent(notice.getContent());
        noticeDTO.setHit(notice.getHit());
        noticeDTO.setCreateTime(notice.getCreateTime());
        noticeDTO.setCreateSeq(notice.getCreateSeq());
        noticeDTO.setModifySeq(notice.getModifySeq());
        noticeDTO.setModifyTime(notice.getModifyTime());

        return noticeDTO;
    }

    @Override
    public NoticeDTO insertNotice(NoticeDTO noticeDTO) throws Exception {
        Notice notice = new Notice();

        notice.setNoticeSeq(noticeDTO.getNoticeSeq());
        notice.setContent(noticeDTO.getContent());
        notice.setHit(noticeDTO.getHit());
        notice.setCreateTime(noticeDTO.getCreateTime());
        notice.setCreateSeq(noticeDTO.getCreateSeq());
        notice.setModifySeq(noticeDTO.getModifySeq());
        notice.setModifyTime(noticeDTO.getModifyTime());

        Member mem = memberDAO.SelectMemberById(noticeDTO.getMemberSeq());
        notice.setMember(mem);

        Notice savedNotice = noticeDAO.InsertNotice(notice);
        NoticeDTO rNoticeDTO = new NoticeDTO();

        rNoticeDTO.setNoticeSeq(savedNotice.getNoticeSeq());
        rNoticeDTO.setContent(savedNotice.getContent());
        rNoticeDTO.setHit(savedNotice.getHit());
        rNoticeDTO.setCreateTime(savedNotice.getCreateTime());
        rNoticeDTO.setCreateSeq(savedNotice.getCreateSeq());
        rNoticeDTO.setModifySeq(savedNotice.getModifySeq());
        rNoticeDTO.setModifyTime(savedNotice.getModifyTime());

        return rNoticeDTO;
    }

    @Override
    public NoticeDTO updateNotice(long noticeSeq, String content, long modifySeq) throws Exception {
        Notice updatedNotice = noticeDAO.UpdateNoticeById(noticeSeq, content, modifySeq);
        NoticeDTO rNoticeDTO = new NoticeDTO();

        rNoticeDTO.setNoticeSeq(updatedNotice.getNoticeSeq());
        rNoticeDTO.setContent(updatedNotice.getContent());
        rNoticeDTO.setHit(updatedNotice.getHit());
        rNoticeDTO.setCreateTime(updatedNotice.getCreateTime());
        rNoticeDTO.setCreateSeq(updatedNotice.getCreateSeq());
        rNoticeDTO.setModifySeq(updatedNotice.getModifySeq());
        rNoticeDTO.setModifyTime(updatedNotice.getModifyTime());

        return rNoticeDTO;
    }

    @Override
    public void deleteNotice(long noticeSeq) throws Exception {
        noticeDAO.DeleteNoticeById(noticeSeq);
    }
}
