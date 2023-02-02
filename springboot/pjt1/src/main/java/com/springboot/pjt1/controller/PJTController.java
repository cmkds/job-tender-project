package com.springboot.pjt1.controller;

import com.springboot.pjt1.data.dto.*;
import com.springboot.pjt1.data.dto.custom.MemberInitDTO;
import com.springboot.pjt1.data.entity.Comment;
import com.springboot.pjt1.data.entity.Member;
import com.springboot.pjt1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    private final HeartService heartService;


    @Autowired
    public PJTController(CommentService commentService, ConnectService connectService, FeedService feedService, MachineDataService machineDataService,
                         MachineLocationService machineLocationService, MachineService machineService, MemberService memberService,
                         NoticeService noticeService, StoreService storeService, HeartService heartService) {
        this.commentService = commentService;
        this.connectService = connectService;
        this.feedService = feedService;
        this.machineDataService = machineDataService;
        this.machineLocationService = machineLocationService;
        this.machineService = machineService;
        this.memberService = memberService;
        this.noticeService = noticeService;
        this.storeService = storeService;
        this.heartService = heartService;
    }

    @GetMapping("/member")
    public ResponseEntity<MemberDTO> getMember(long memberSeq) {
        MemberDTO memberDTO = memberService.getMember(memberSeq);

        return ResponseEntity.status(HttpStatus.OK).body(memberDTO);
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberDTO>> getAllMember() {
        List<MemberDTO> memberDTOs = memberService.getMembers();

        return ResponseEntity.status(HttpStatus.OK).body(memberDTOs);
    }

    @PostMapping("/member")
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDto) throws Exception {
        MemberDTO rMemberDto = memberService.insertMember(memberDto);

        return ResponseEntity.status(HttpStatus.OK).body(rMemberDto);
    }

    @PostMapping("/store")
    public ResponseEntity<StoreDTO> createStore(@RequestBody StoreDTO storeDTO) throws Exception{
        StoreDTO rStoreDto = storeService.insertStore(storeDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rStoreDto);
    }
    @PostMapping("/feed")
    public ResponseEntity<FeedDTO> createFeed(@RequestBody FeedDTO feedDTO) throws Exception{
        FeedDTO rFeedDto = feedService.insertFeed(feedDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rFeedDto);
    }

    @PostMapping("/comment")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) throws Exception{
        CommentDTO rCommentDto = commentService.insertComment(commentDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rCommentDto);
    }

    @PostMapping("/mac-loc")
    public ResponseEntity<MachineLocationDTO> createMachineLocation(@RequestBody MachineLocationDTO machineLocationDTO) throws Exception{
        MachineLocationDTO rMachineLocationDto = machineLocationService.insertMachineLocation(machineLocationDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rMachineLocationDto);
    }

    @PostMapping("/mac")
    public ResponseEntity<MachineDTO> createMachine(@RequestBody MachineDTO machineDTO) throws Exception{
        MachineDTO rMachineDto = machineService.insertMachine(machineDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rMachineDto);
    }

    @PostMapping("/notice")
    public ResponseEntity<NoticeDTO> createNotice(@RequestBody NoticeDTO noticeDTO) throws Exception{
        NoticeDTO rNoticeDto = noticeService.insertNotice(noticeDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rNoticeDto);
    }

    @PostMapping("/connect")
    public ResponseEntity<ConnectDTO> createConnect(@RequestBody ConnectDTO connectDTO) throws Exception{
        ConnectDTO rConnectDto = connectService.insertConnect(connectDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rConnectDto);
    }

    @PostMapping("/mac-data")
    public ResponseEntity<MachineDataDTO> createMachineData(@RequestBody MachineDataDTO machineDataDTO) throws Exception{
        MachineDataDTO rMachineDataDto = machineDataService.insertMachineData(machineDataDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rMachineDataDto);
    }

    //
    @GetMapping("/account/{nickname}")
    public ResponseEntity<Boolean> findNickname(@PathVariable String nickname) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findNickname(nickname));
    }

    @PutMapping("/account/{memberSeq}")
    public ResponseEntity<MemberDTO> updateMemberInit(@RequestBody MemberInitDTO memberInitDTO, @PathVariable long memberSeq) throws  Exception{
        MemberDTO rmemberDTO = memberService.updateMember(memberSeq, memberInitDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rmemberDTO);
    }

    @DeleteMapping("/account/{memberSeq}")
    public ResponseEntity<Void> deleteMember(@PathVariable long memberSeq) throws Exception{
        memberService.deleteMember(memberSeq);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/main/hot")
    public ResponseEntity<List<FeedDTO>> getHotFeed(){
        List<FeedDTO> feedDTOList = feedService.getFeedAllOrderByHeart();
        return ResponseEntity.status(HttpStatus.OK).body(feedDTOList);
    }

    @GetMapping("/main/new")
    public ResponseEntity<List<FeedDTO>> getNewFeed(){
        List<FeedDTO> feedDTOList = feedService.getFeedAllOrderByCreateTime();
        return ResponseEntity.status(HttpStatus.OK).body(feedDTOList);
    }

    @GetMapping("/main/hot/{city}")
    public ResponseEntity<List<FeedDTO>> getHotFeedByCity(@PathVariable String city){
        List<FeedDTO> feedDTOList = feedService.getFeedAllOrderByHeartByCity(city);
        return ResponseEntity.status(HttpStatus.OK).body(feedDTOList);
    }

    @GetMapping("/main/new/{city}")
    public ResponseEntity<List<FeedDTO>> getNewFeedByCity(@PathVariable String city){
        List<FeedDTO> feedDTOList = feedService.getFeedAllOrderByCreateTimeByCity(city);
        return ResponseEntity.status(HttpStatus.OK).body(feedDTOList);
    }

    @PostMapping("/heart")
    public ResponseEntity<HeartDTO> createHeart(@RequestBody HeartDTO heartDTO) throws Exception{
        HeartDTO rHeartDTO = heartService.insertHeart(heartDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rHeartDTO);
    }

    @DeleteMapping("/heart/{feedSeq}/{memberSeq}")
    public ResponseEntity<Void> deleteHeart(@PathVariable long feedSeq, @PathVariable long memberSeq) throws Exception{
        HeartDTO heartDTO = heartService.getHeartByFeedAndMember(feedSeq, memberSeq);
        if (heartDTO == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        else {
            heartService.deleteHeart(heartDTO.getHeartSeq());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    @GetMapping("/heart/{feedSeq}/{memberSeq}")
    public ResponseEntity<Boolean> getHeart(@PathVariable long feedSeq, @PathVariable long memberSeq){
        HeartDTO heartDTO = heartService.getHeartByFeedAndMember(feedSeq, memberSeq);

        if (heartDTO == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);

        else
            return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @GetMapping("/abcdefg")
    public ResponseEntity<List<FeedDTO>> getFollowerFeed(long memberSeq){
        List<FeedDTO> feedDtos = feedService.getFollowerFeed(memberSeq);

        return ResponseEntity.status(HttpStatus.OK).body(feedDtos);
    }

    @GetMapping("/comment/{feedSeq}")
    public ResponseEntity<List<CommentDTO>> getCommentByFeed(@PathVariable long feedSeq){
        List<CommentDTO> commentDTOs = commentService.getCommentByFeedSeq(feedSeq);
        return ResponseEntity.status(HttpStatus.OK).body(commentDTOs);
    }

    @PostMapping("/comment")
    public ResponseEntity<List<Comment>> getComment(CommentDTO commentDTO){
        return ResponseEntity.status(HttpStatus.OK).body();
    }

    @PutMapping("/comment/{feedSeq}")
    public ResponseEntity<List<Comment>> getComment(@PathVariable long feedSeq, CommentDTO commentDTO){
        return ResponseEntity.status(HttpStatus.OK).body();
    }

    @DeleteMapping("/comment/{feedSeq}")
    public ResponseEntity<List<Comment>> getComment(@PathVariable long feedSeq){
        return ResponseEntity.status(HttpStatus.OK).body();
    }
}
