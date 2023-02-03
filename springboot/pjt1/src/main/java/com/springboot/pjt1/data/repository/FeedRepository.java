package com.springboot.pjt1.data.repository;

import com.springboot.pjt1.data.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    List<Feed> findAll();
    List<Feed> findAllByOrderByHeartAsc();
    List<Feed> findAllByOrderByCreateTimeAsc();
    //List<Feed> findAllByMachineLocationSeqByOrderByHeartAsc(String machineLocationSeq);
    //List<Feed> findAllByMachineLocationSeqByOrderByCreateTimeAsc(String machineLocationSeq);
    List<Feed> findByMemberSeq(long memberSeq);
}
