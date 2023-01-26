package com.springboot.pjt1.controller;

import com.springboot.pjt1.data.dto.MemberDTO;
import com.springboot.pjt1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PJTController {
    private final CommentService commentService;
    private final ConnectService connectService;
    private final FeedService feedService;
    private final MachineDataService machineDataService;
    private final MachineLocationService machineLocationService;
    private final MachineService machineService;
    private final MemberService memberService;
    private final NoticeService noticeService;
    private final StoreService storeService;

    @Autowired
    public PJTController(CommentService commentService, ConnectService connectService, FeedService feedService, MachineDataService machineDataService,
                         MachineLocationService machineLocationService, MachineService machineService, MemberService memberService,
                         NoticeService noticeService, StoreService storeService) {
        this.commentService = commentService;
        this.connectService = connectService;
        this.feedService = feedService;
        this.machineDataService = machineDataService;
        this.machineLocationService = machineLocationService;
        this.machineService = machineService;
        this.memberService = memberService;
        this.noticeService = noticeService;
        this.storeService = storeService;
    }

    @GetMapping("/member")
    public ResponseEntity<MemberDTO> getMember(long memberSeq) {
        MemberDTO memberDTO = memberService.getMember(memberSeq);

        return ResponseEntity.status(HttpStatus.OK).body(memberDTO);
    }

    @PostMapping("/member")
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDto) throws Exception {
        System.out.println("controller");
        System.out.println(memberDto.getIsAdmin());
        MemberDTO rMemberDto = memberService.insertMember(memberDto);

        return ResponseEntity.status(HttpStatus.OK).body(rMemberDto);
    }
}
