package com.springboot.pjt1.controller;

import com.springboot.pjt1.data.dto.*;
import com.springboot.pjt1.data.dto.custom.*;
import com.springboot.pjt1.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
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

    @ApiOperation(
            value = "nickname 존재 여부 판단"
            , notes = "nickname 존재 여부 판단. Boolean으로 반환")
    @GetMapping("/account/{nickname}")
    public ResponseEntity<Boolean> findNickname(String nickname) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findNickname(nickname));
    }

    @ApiOperation(
            value = "email 존재 여부 판단"
            , notes = "email 존재 여부 판단. Boolean으로 반환")
    @GetMapping("/account/{email}")
    public ResponseEntity<Boolean> findMemberByEmail(String email) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findMemberByEmailReturnBool(email));
    }

    @ApiOperation(
            value = "member 정보 조회"
            , notes = "memberSeq로 member 정보 조회")
    @GetMapping("/account/{memberSeq}")
    public ResponseEntity<MemberDTO> getMemberByMemberSeq(@PathVariable long memberSeq) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMember(memberSeq));
    }

    @ApiOperation(
            value = "member 생성"
            , notes = "기본 데이터로 member 생성")
    @PostMapping("/account")
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDto) throws Exception {
//        if (memberService.findNickname(memberDto.getNickname()))
//            throw new Exception();
//
//        if (memberService.findMemberByEmailReturnBool(memberDto.getEmail()))
//            throw new Exception();

        MemberDTO rMemberDto = memberService.insertMember(memberDto);

        return ResponseEntity.status(HttpStatus.OK).body(rMemberDto);
    }
    @ApiOperation(
            value = "여러 member 생성"
            , notes = "기본 데이터로 여러 member 생성")
    @PostMapping("/accounts")
    public ResponseEntity<List<MemberDTO>> createMembers(@RequestBody List<MemberDTO> memberDtos) throws Exception {
        List<MemberDTO> rMemberDtos = memberService.insertMembers(memberDtos);

        return ResponseEntity.status(HttpStatus.OK).body(rMemberDtos);
    }
    @ApiOperation(
            value = "member 수정"
            , notes = "member 수정")
    @PutMapping("/account/{memberSeq}")
    public ResponseEntity<MemberDTO> updateMemberInit(@RequestBody MemberInitDTO memberInitDTO, @PathVariable long memberSeq) throws  Exception{
        MemberDTO rmemberDTO = memberService.updateMember(memberSeq, memberInitDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rmemberDTO);
    }
    // --- care ---
    @ApiOperation(
            value = "member 삭제"
            , notes = "member 및 member 활동 흔적 삭제")
    @DeleteMapping("/account/{memberSeq}")
    public ResponseEntity<Void> deleteMember(@PathVariable long memberSeq) throws Exception{
        storeService.deleteStoreByMemberSeq(memberSeq);
        commentService.deleteCommentByMemberSeq(memberSeq);
        heartService.deleteHeartByMemberSeq(memberSeq);
        connectService.deleteConnectByMemberSeq(memberSeq);
        noticeService.deleteNoticeByMemberSeq(memberSeq);
        feedService.deleteFeedByMemberSeq(memberSeq);

        memberService.deleteMember(memberSeq);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    // --- finish ---
    @ApiOperation(
            value = "feed 생성"
            , notes = "feed 생성")
    @PostMapping("/main")
    public ResponseEntity<FeedDTO> createHotFeeds(@RequestBody FeedInputDTO feedInputDTO) throws Exception{


        FeedDTO rfeedDTO = feedService.insertFeed(feedInputDTO);
        return ResponseEntity.status(HttpStatus.OK).body(rfeedDTO);
    }

    @ApiOperation(
            value = "hot feed 조회"
            , notes = "feed를 좋아요 순서로 조회")
    @GetMapping("/main/hot")
    public ResponseEntity<List<FeedDTO>> getHotFeed(){
        List<FeedDTO> feedDTOList = feedService.getFeedAllOrderByHeart();
        return ResponseEntity.status(HttpStatus.OK).body(feedDTOList);
    }

    @ApiOperation(
            value = "new feed 조회"
            , notes = "feed를 최신 순서로 조회")
    @GetMapping("/main/new")
    public ResponseEntity<List<FeedDTO>> getNewFeed(){
        List<FeedDTO> feedDTOList = feedService.getFeedAllOrderByCreateTime();
        return ResponseEntity.status(HttpStatus.OK).body(feedDTOList);
    }

    @ApiOperation(
            value = "city 별로 hot feed 조회"
            , notes = "feed를 좋아요 순서로 조회")
    @GetMapping("/main/hot/{city}")
    public ResponseEntity<List<FeedDTO>> getHotFeedByCity(@PathVariable long MachineLocationSeq){
        List<FeedDTO> feedDTOList = feedService.getFeedAllOrderByHeartByMachineLocationSeq(MachineLocationSeq);
        return ResponseEntity.status(HttpStatus.OK).body(feedDTOList);
    }

    @ApiOperation(
            value = "city 별로 new feed 조회"
            , notes = "feed를 최신 순서로 조회")
    @GetMapping("/main/new/{city}")
    public ResponseEntity<List<FeedDTO>> getNewFeedByCity(@PathVariable long MachineLocationSeq){
        List<FeedDTO> feedDTOList = feedService.getFeedAllOrderByCreateTimeByMachineLocationSeq(MachineLocationSeq);
        return ResponseEntity.status(HttpStatus.OK).body(feedDTOList);
    }

    @ApiOperation(
            value = "heart 생성"
            , notes = "어떤 member가 어떤 feed에 좋아요를 눌렀는지 생성")
    @PostMapping("/heart")
    public ResponseEntity<HeartDTO> createHeart(@RequestBody HeartDTO heartDTO) throws Exception{
        HeartDTO rHeartDTO = heartService.insertHeart(heartDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rHeartDTO);
    }

    @ApiOperation(
            value = "heart 삭제"
            , notes = "heart를 feedSeq와 memberSeq를 이용하여 삭제")
    @DeleteMapping("/heart/{feedSeq}/{memberSeq}")
    public ResponseEntity<Void> deleteHeartByFeedAndMember(@PathVariable long feedSeq, @PathVariable long memberSeq) throws Exception{
        HeartDTO heartDTO = heartService.getHeartByFeedAndMember(feedSeq, memberSeq);
        if (heartDTO == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        else {
            heartService.deleteHeart(heartDTO.getHeartSeq());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    @ApiOperation(
            value = "heart 삭제"
            , notes = "heart를 HeartSeq 삭제")
    @DeleteMapping("/heart/{heartSeq}")
    public ResponseEntity<Void> deleteHeart(@PathVariable long heartSeq) throws Exception{
        heartService.deleteHeart(heartSeq);
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "heart 존재여부 조회"
            , notes = "heart를 feedSeq와 memberSeq를 이용하여 존재 여부 조회. boolean으로 반환")
    @GetMapping("/heart/{feedSeq}/{memberSeq}")
    public ResponseEntity<Boolean> getHeart(@PathVariable long feedSeq, @PathVariable long memberSeq){
        HeartDTO heartDTO = heartService.getHeartByFeedAndMember(feedSeq, memberSeq);

        if (heartDTO == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);

        else
            return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(
            value = "팔로잉 조회"
            , notes = "memberSeq의 모든 팔로잉의 모든 피드 데이터 조회")
    @GetMapping("/social/{memberSeq}")
    public ResponseEntity<List<FeedDTO>> getFollowingFeed(long memberSeq){
        List<Long> memberSeqs = connectService.getFollowings(memberSeq);
        List<FeedDTO> feedDtos = feedService.getFollowingFeed(memberSeqs);

        return ResponseEntity.status(HttpStatus.OK).body(feedDtos);
    }

    @ApiOperation(
            value = "comment 조회"
            , notes = "feedSeq를 기반으로 모든 comment 조회")
    @GetMapping("/comment/{feedSeq}")
    public ResponseEntity<List<CommentDTO>> getCommentByFeed(@PathVariable long feedSeq){
        List<CommentDTO> commentDTOs = commentService.getCommentByFeedSeq(feedSeq);
        return ResponseEntity.status(HttpStatus.OK).body(commentDTOs);
    }

    @ApiOperation(
            value = "comment 생성"
            , notes = "comment 생성")
    @PostMapping("/comment")
    public ResponseEntity<CommentDTO> insertComment(CommentDTO commentDTO) throws Exception {
        CommentDTO rCommentDTO = commentService.insertComment(commentDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rCommentDTO);
    }

    @ApiOperation(
            value = "comment 변경"
            , notes = "commentSeq 기반으로 comment 변경")
    @PutMapping("/comment/{commentSeq}")
    public ResponseEntity<CommentDTO> putComment(@PathVariable long commentSeq, String context) throws Exception {
        CommentDTO rCommentDTO = commentService.updateComment(commentSeq, context);

        return ResponseEntity.status(HttpStatus.OK).body(rCommentDTO);
    }
    @ApiOperation(
            value = "comment 삭제"
            , notes = "commentSeq 기반 comment 삭제")
    @DeleteMapping("/comment/{commentSeq}")
    public ResponseEntity<Void> deleteComment(@PathVariable long commentSeq) throws Exception{
        commentService.deleteComment(commentSeq);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "왜만들었지"
            , notes = "왜 만들었지 이거 ㅎㅎㅎ")
    @GetMapping("/search/{nickname}")
    public ResponseEntity<List<MemberSearchInfoDTO>> getMemberSearchInfo(@PathVariable String nickname){
        List<MemberSearchInfoDTO> rMemberSearchInfoDTOs = memberService.getMemberSearchInfo(nickname);

        return ResponseEntity.status(HttpStatus.OK).body(rMemberSearchInfoDTOs);
    }

    @ApiOperation(
            value = "follower 조회"
            , notes = "nickname 기반으로 follower 기본 정보 조회")
    @GetMapping("/search/follower/{memberSeq}") // not enough
    public ResponseEntity<List<MemberSearchInfoDTO>> getFollowerMemberSearchInfoByMemberSeq(@PathVariable long memberSeq){
        List<Long> lists = connectService.getFollowings(memberSeq);
        List<MemberSearchInfoDTO> rMemberSearchInfoDTOs = new ArrayList<>();

        for(Long list:lists){
            MemberSearchInfoDTO rMemberSearchInfoDTO = memberService.getMemberSearchInfoByMemberSeq(list);
            rMemberSearchInfoDTOs.add(rMemberSearchInfoDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(rMemberSearchInfoDTOs);
    }

    @ApiOperation(
            value = "follower 조회"
            , notes = "memberSeq 기반으로 follower 기본 정보 조회")
    @GetMapping("/search/following/{memberSeq}") // not enough
    public ResponseEntity<List<MemberSearchInfoDTO>> getFollowingMemberSearchInfoByMemberSeq(@PathVariable long memberSeq){
        List<Long> lists = connectService.getFollowings(memberSeq);
        List<MemberSearchInfoDTO> rMemberSearchInfoDTOs = new ArrayList<>();

        for(Long list:lists){
            MemberSearchInfoDTO rMemberSearchInfoDTO = memberService.getMemberSearchInfoByMemberSeq(list);
            rMemberSearchInfoDTOs.add(rMemberSearchInfoDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(rMemberSearchInfoDTOs);
    }

    @ApiOperation(
            value = "팔로우 여부 조회"
            , notes = "시작 맴버와 도착 맴버를 입력하여 관계 확인. boolean 반환")
    @GetMapping("/profile/{srcMemberSeq}/{dstMemberSeq}")
    public ResponseEntity<Boolean> getIsFollow(@PathVariable long srcMemberSeq, @PathVariable long dstMemberSeq){
        Boolean IsFollow = connectService.getIsFollow(srcMemberSeq, dstMemberSeq);

        return ResponseEntity.status(HttpStatus.OK).body(IsFollow);
    }

    @ApiOperation(
            value = "connect 생성"
            , notes = "connect 생성")
    @PostMapping("/profile/follow")
    public ResponseEntity<ConnectDTO> insertConnect(ConnectDTO connectDTO) throws Exception {
        ConnectDTO rConnectDTO = connectService.insertConnect(connectDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rConnectDTO);
    }
    @ApiOperation(
            value = "connect 삭제"
            , notes = "팔로우, 팔로잉 (memberSeq) 입력")
    @DeleteMapping("/profile/follow/{srcMemberSeq}/{dstMemberSeq}")
    public ResponseEntity<Void> deleteConnect(@PathVariable long srcMemberSeq, @PathVariable long dstMemberSeq) throws Exception{
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

        FeedInputDTO feedInputDTO = feedService.ConvertToInputDTO(feedDTO);
        FeedDTO rfeedDTO = feedService.insertFeed(feedInputDTO);

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

    @PutMapping("/mypage/{memberSeq}")
    public ResponseEntity<MemberDTO> updateMemberProfile(@RequestBody MemberInitDTO memberInitDTO, @PathVariable long memberSeq) throws  Exception{
        MemberDTO rmemberDTO = memberService.updateMember(memberSeq, memberInitDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rmemberDTO);
    }

    @PostMapping("/machine/location")
    public  ResponseEntity<MachineLocationDTO> createMachineLocation(@RequestBody MachineLocationInputDTO machineLocationInputDTO) throws Exception{
        MachineLocationDTO rMachineLocationDTO = machineLocationService.insertMachineLocation(machineLocationInputDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rMachineLocationDTO);
    }

    @PostMapping("/machine")
    public  ResponseEntity<MachineDTO> createMachine(@RequestBody MachineInputDTO machineInputDTO) throws Exception{

        if (!machineLocationService.IsExistByMachineLocationSeq(machineInputDTO.getMachineLocationSeq())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found");
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        MachineDTO rMachineDTO = machineService.insertMachine(machineInputDTO);
        System.out.println(machineLocationService.getMachineLocation(rMachineDTO.getMachineLocationSeq()));

        return ResponseEntity.status(HttpStatus.OK).body(rMachineDTO);
    }

    @PostMapping("/machine/data")
    public  ResponseEntity<MachineDataDTO> createMachineData(@RequestBody MachineDataInputDTO machineDataInputDTO) throws Exception{
        MachineDataDTO rMachineDataDTO = machineDataService.insertMachineData(machineDataInputDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rMachineDataDTO);
    }

    @GetMapping("/feed/heart")
    public ResponseEntity<HeartDTO> getHeartByFeedSeq(@PathVariable long feedSeq) {
        return ResponseEntity.status(HttpStatus.OK).body(heartService.getHeartByFeed(feedSeq));
    }
}
