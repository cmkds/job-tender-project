package com.springboot.pjt1.data.dao.impl;

import com.springboot.pjt1.data.dao.NoticeDAO;
import com.springboot.pjt1.data.entity.Notice;
import com.springboot.pjt1.data.entity.Notice;
import com.springboot.pjt1.data.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
public class NoticeDAOImpl implements NoticeDAO {
    private NoticeRepository noticeRepository;

    @Autowired
    public NoticeDAOImpl(NoticeRepository noticeRepository){
        this.noticeRepository = noticeRepository;
    }
    @Override
    public Notice InsertNotice(Notice notice) throws Exception {
        Notice savedNotice = noticeRepository.save(notice);

        return savedNotice;
    }

    @Override
    public Notice SelectNoticeById(long noticeSeq) {
        Notice selectedNotice = noticeRepository.getById(noticeSeq);

        return selectedNotice;
    }

    @Override
    public Notice UpdateNoticeById(long noticeSeq, String content, long modifySeq) throws Exception {
        // get data using ID
        Optional<Notice> selectedNotice = noticeRepository.findById(noticeSeq);
        Notice updatedNotice;

        if(selectedNotice.isPresent()){
            Notice notice = selectedNotice.get();

            notice.setContent(content);

            notice.setModifyTime(new Date());
            notice.setModifySeq(modifySeq);

            updatedNotice = noticeRepository.save(notice);
        }

        else
            throw new Exception();

        return updatedNotice;
    }

    @Override
    public void DeleteNoticeById(long noticeSeq) throws Exception {
        Optional<Notice> selectedNotice = noticeRepository.findById(noticeSeq);

        if (selectedNotice.isPresent()){
            Notice Notice = selectedNotice.get();
            noticeRepository.delete(Notice);
        }

        else
            throw new Exception();
    }
}
