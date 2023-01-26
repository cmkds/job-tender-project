package com.springboot.pjt1.service;

import com.springboot.pjt1.data.dto.NoticeDTO;
import org.springframework.stereotype.Service;

public interface NoticeService {
    NoticeDTO getNotice(long noticeSeq);
    NoticeDTO insertNotice(NoticeDTO noticeDTO)throws Exception;
    NoticeDTO updateNotice(long noticeSeq, String content, long modifySeq)throws Exception;
    void deleteNotice(long noticeSeq)throws Exception;
}
