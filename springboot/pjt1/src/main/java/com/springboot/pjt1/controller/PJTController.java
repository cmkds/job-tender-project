package com.springboot.pjt1.controller;

import com.springboot.pjt1.data.dto.*;
import com.springboot.pjt1.data.dto.custom.MemberInitDTO;
import com.springboot.pjt1.data.dto.custom.MemberSearchInfoDTO;
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

    @GetMapping("/social/{memberSeq}")
    public ResponseEntity<List<FeedDTO>> getFollowingFeed(long memberSeq){
        List<Long> memberSeqs = connectService.getFollowings(memberSeq);
        List<FeedDTO> feedDtos = feedService.getFollowingFeed(memberSeqs);

        return ResponseEntity.status(HttpStatus.OK).body(feedDtos);
    }

    @GetMapping("/comment/{feedSeq}")
    public ResponseEntity<List<CommentDTO>> getCommentByFeed(@PathVariable long feedSeq){
        List<CommentDTO> commentDTOs = commentService.getCommentByFeedSeq(feedSeq);
        return ResponseEntity.status(HttpStatus.OK).body(commentDTOs);
    }

    @PostMapping("/comment")
    public ResponseEntity<CommentDTO> insertComment(CommentDTO commentDTO) throws Exception {
        CommentDTO rCommentDTO = commentService.insertComment(commentDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rCommentDTO);
    }

    @PutMapping("/comment/{commentSeq}")
    public ResponseEntity<CommentDTO> putComment(@PathVariable long commentSeq, String context) throws Exception {
        CommentDTO rCommentDTO = commentService.updateComment(commentSeq, context);

        return ResponseEntity.status(HttpStatus.OK).body(rCommentDTO);
    }
    @DeleteMapping("/comment/{commentSeq}")
    public ResponseEntity<Void> deleteComment(@PathVariable long commentSeq) throws Exception{
        commentService.deleteComment(commentSeq);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/search/{nickname}")
    public ResponseEntity<List<MemberSearchInfoDTO>> getMemberSearchInfo(@PathVariable String nickname){
        List<MemberSearchInfoDTO> rMemberSearchInfoDTOs = memberService.getMemberSearchInfo(nickname);

        return ResponseEntity.status(HttpStatus.OK).body(rMemberSearchInfoDTOs);
    }

    @GetMapping("/search/follower/{nickname}") // not enough
    public ResponseEntity<List<MemberSearchInfoDTO>> getFollowerMemberSearchInfo(@PathVariable long memberSeq, @PathVariable String nickname){

        List<MemberSearchInfoDTO> rMemberSearchInfoDTOs = memberService.getMemberSearchInfo(nickname);

        return ResponseEntity.status(HttpStatus.OK).body(rMemberSearchInfoDTOs);
    }
    @GetMapping("/search/following/{nickname}") // not enough
    public ResponseEntity<List<MemberSearchInfoDTO>> getFollowingMemberSearchInfo(@PathVariable long memberSeq, @PathVariable String nickname){
        List<MemberSearchInfoDTO> rMemberSearchInfoDTOs = memberService.getMemberSearchInfo(nickname);

        return ResponseEntity.status(HttpStatus.OK).body(rMemberSearchInfoDTOs);
    }

    @GetMapping("/profile/{srcMemberSeq}/{dstMemberSeq}")
    public ResponseEntity<Boolean> getIsFollow(@PathVariable long srcMemberSeq, @PathVariable long dstMemberSeq){
        Boolean IsFollow = connectService.getIsFollow(srcMemberSeq, dstMemberSeq);

        return ResponseEntity.status(HttpStatus.OK).body(IsFollow);
    }

    @PostMapping("/profile/follow")
    public ResponseEntity<ConnectDTO> insertConnect(ConnectDTO connectDTO) throws Exception {
        ConnectDTO rConnectDTO = connectService.insertConnect(connectDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rConnectDTO);
    }

    @DeleteMapping("/profile/follow/{srcMemberSeq}/{dstMemberSeq}")
    public ResponseEntity<Void> delectConnect(@PathVariable long srcMemberSeq, @PathVariable long dstMemberSeq) throws Exception{
        long connecSeq = connectService.getConnectSeq(srcMemberSeq, dstMemberSeq);
        connectService.deleteConnect(connecSeq);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/profile/{memberSeq}")
    public ResponseEntity<MemberDTO> getProfile(@PathVariable long memberSeq){
        MemberDTO rMemberDTO = memberService.getMember(memberSeq);

        return ResponseEntity.status(HttpStatus.OK).body(rMemberDTO);
    }

    @GetMapping("/mypage")
    public ResponseEntity<List<StoreDTO>> getStoreByMemberSeq(long memberSeq){
        List<StoreDTO> rStoreDTO = storeService.getStoreByMemberSeq(memberSeq);

        return ResponseEntity.status(HttpStatus.OK).body(rStoreDTO);
    }

    @PostMapping("/mypage")
    public ResponseEntity<FeedDTO> createFeed(long storeSeq, String context) throws Exception{
        StoreDTO storeDTO = storeService.getStore(storeSeq);

        FeedDTO feedDTO = feedService.StoreToFeed(storeDTO, context);
        FeedDTO rfeedDTO = feedService.insertFeed(feedDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rfeedDTO);
    }

    @GetMapping("/mypage/photo/{feedSeq}")
    public ResponseEntity<String> getPhoto(long memberSeq){
        String url = storeService.getStore(memberSeq).getPhoto();

        return ResponseEntity.status(HttpStatus.OK).body(url);
    }
    @GetMapping("/mypage/video/{feedSeq}")
    public ResponseEntity<String> getVideo(long memberSeq){
        String url = storeService.getStore(memberSeq).getVideo();

        return ResponseEntity.status(HttpStatus.OK).body(url);
    }
    @GetMapping("/mypage/post/{feedSeq}")
    public ResponseEntity<String> getPost(long memberSeq){
        String url = storeService.getStore(memberSeq).getPost();

        return ResponseEntity.status(HttpStatus.OK).body(url);
    }

    @DeleteMapping("/mypage")
    public ResponseEntity<Void> deleteExistMember(long memberSeq) throws Exception{

        memberService.deleteMember(memberSeq);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
