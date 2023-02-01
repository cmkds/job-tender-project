package com.springboot.pjt1.data.dao;

import com.springboot.pjt1.data.entity.Notice;

public interface NoticeDAO {
    Notice InsertNotice(Notice notice) throws Exception;
    Notice SelectNoticeById(long noticeSeq);
    Notice UpdateNoticeById(long noticeSeq, String content) throws Exception;
    void DeleteNoticeById(long noticeSeq) throws Exception;
}
