package com.springboot.pjt1.controller;

import com.springboot.pjt1.data.dto.*;
import com.springboot.pjt1.data.dto.custom.*;
import com.springboot.pjt1.handler.OAuthRestTemplateErrorHandler;
import com.springboot.pjt1.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.models.links.Link;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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
    private final S3Service s3Service;

    // oauth


    @Autowired
    public PJTController(CommentService commentService, ConnectService connectService, FeedService feedService, MachineDataService machineDataService,
                         MachineLocationService machineLocationService, MachineService machineService, MemberService memberService,
                         NoticeService noticeService, StoreService storeService, HeartService heartService, S3Service s3Service) {
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
        this.s3Service = s3Service;
    }

    @GetMapping("/loginInfo")
    public String oauthLoginInfo(Authentication authentication){
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        return attributes.toString();
    }

    @PostMapping("/send-data")
    public void uploadFile(@RequestParam MultipartFile multipartFile) throws Exception {
        s3Service.saveUploadFile(multipartFile);
    }

    @PostMapping("/data")
    public ResponseEntity<StoreDTO> sendFile(@RequestBody StoreInputDTO storeInputDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(storeService.insertStore(storeInputDTO));
    }
    // =====================================================================

    @ApiOperation(
            value = "nickname 존재 여부 판단"
            , notes = "nickname 존재 여부 판단. Boolean으로 반환")
    @GetMapping("/account/nickname/{nickname}")
    public ResponseEntity<Boolean> findNickname(@PathVariable("nickname") String nickname) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findNickname(nickname));
    }

    @ApiOperation(
            value = "email 존재 여부 판단. 작동 오류"
            , notes = "email 존재 여부 판단. Boolean으로 반환")
    @GetMapping("/account/email/{email}")
    public ResponseEntity<Boolean> findMemberByEmail(@PathVariable("email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findMemberByEmailReturnBool(email));
    }

    @ApiOperation(
            value = "member 정보 조회"
            , notes = "memberSeq로 member 정보 조회")
    @GetMapping("/account/{memberSeq}")
    public ResponseEntity<MemberDTO> getMemberByMemberSeq(@PathVariable("memberSeq") long memberSeq) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMember(memberSeq));
    }

    @ApiOperation(value = "member 정보 전체 조회", notes="member 정보 전체 조회")
    @GetMapping("/accounts")
    public ResponseEntity<List<MemberDTO>> getMemberAll(){
        List<MemberDTO> memberDTOs = memberService.getMembers();

        return ResponseEntity.status(HttpStatus.OK).body(memberDTOs);
    }
    @ApiOperation(
            value = "member 생성"
            , notes = "기본 데이터로 member 생성")
    @PostMapping("/account")
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberInputDTO memberInputDTO) throws Exception {
        MemberDTO rMemberDto = memberService.insertMember(memberInputDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rMemberDto);
    }
    @ApiOperation(
            value = "여러 member 생성"
            , notes = "기본 데이터로 여러 member 생성")
    @PostMapping("/accounts")
    public ResponseEntity<List<MemberDTO>> createMembers(@RequestBody List<MemberInputDTO> memberInputDtos) throws Exception {
        List<MemberDTO> rMemberDtos = memberService.insertMembers(memberInputDtos);

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
        List<FeedDTO> rfeedDTO = new ArrayList<>();
        List<HeartDTO> heartDTOs = heartService.getHeartAll();
        Map<Long, Integer> map = new HashMap<>();

        for(HeartDTO heartDTO:heartDTOs){
            if(map.containsKey(heartDTO.getFeedSeq()))
                map.put(heartDTO.getFeedSeq(), map.get(heartDTO.getFeedSeq()) + 1);

            else
                map.put(heartDTO.getFeedSeq(), 1);
        }

        List<Map.Entry<Long, Integer>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for(Map.Entry<Long, Integer> entry : entryList) {

            rfeedDTO.add(feedService.getFeed(entry.getKey()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(rfeedDTO);
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
            value = "city 별로 hot feed 조회. 아직 작동 x"
            , notes = "feed를 좋아요 순서로 조회")
    @GetMapping("/main/hot/{MachineLocationSeq}")
    public ResponseEntity<List<FeedDTO>> getHotFeedByCity(@PathVariable("MachineLocationSeq") long MachineLocationSeq){
        List<FeedDTO> rfeedDTO = new ArrayList<>();
        List<HeartDTO> heartDTOs = heartService.getHeartAll();
        Map<Long, Integer> map = new HashMap<>();

        for(HeartDTO heartDTO:heartDTOs){
            if(map.containsKey(heartDTO.getFeedSeq()))
                map.put(heartDTO.getFeedSeq(), map.get(heartDTO.getFeedSeq()) + 1);

            else
                map.put(heartDTO.getFeedSeq(), 1);
        }

        System.out.println(MachineLocationSeq);
        List<Map.Entry<Long, Integer>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for(Map.Entry<Long, Integer> entry : entryList) {
            FeedDTO feedDTO = feedService.getFeed(entry.getKey());
            System.out.println(feedDTO.getFeedSeq() + " " + feedDTO.getMachineLocationSeq());

            if (feedDTO.getMachineLocationSeq() == MachineLocationSeq)
                rfeedDTO.add(feedDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(rfeedDTO);
    }

    @ApiOperation(
            value = "city 별로 new feed 조회. 아직 작동 x"
            , notes = "feed를 최신 순서로 조회")
    @GetMapping("/main/new/{MachineLocationSeq}")
    public ResponseEntity<List<FeedDTO>> getNewFeedByCity(@PathVariable("MachineLocationSeq") long MachineLocationSeq){
        List<FeedDTO> feedDTOList = feedService.getFeedAllOrderByCreateTimeByMachineLocationSeq(MachineLocationSeq);
        return ResponseEntity.status(HttpStatus.OK).body(feedDTOList);
    }

    @ApiOperation(
            value = "heart 생성"
            , notes = "어떤 member가 어떤 feed에 좋아요를 눌렀는지 생성")
    @PostMapping("/heart")
    public ResponseEntity<HeartDTO> createHeart(@RequestBody HeartInputDTO heartInputDTO) throws Exception{
        HeartDTO rHeartDTO = heartService.insertHeart(heartInputDTO);

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
    public ResponseEntity<Boolean> getHeart(@PathVariable("feedSeq") long feedSeq, @PathVariable("memberSeq") long memberSeq){
        HeartDTO heartDTO = heartService.getHeartByFeedAndMember(feedSeq, memberSeq);

        if (heartDTO == null)
            return ResponseEntity.status(HttpStatus.OK).body(false);

        else
            return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(
            value = "팔로잉 조회"
            , notes = "memberSeq의 모든 팔로잉의 모든 피드 데이터 조회")
    @GetMapping("/social/{memberSeq}")
    public ResponseEntity<List<FeedDTO>> getFollowingFeed(@PathVariable("memberSeq")  long memberSeq){
        List<Long> memberSeqs = connectService.getFollowings(memberSeq);
        List<FeedDTO> feedDtos = feedService.getFollowingFeedAll(memberSeqs);

        return ResponseEntity.status(HttpStatus.OK).body(feedDtos);
    }

    @ApiOperation(
            value = "comment 조회"
            , notes = "feedSeq를 기반으로 모든 comment 조회")
    @GetMapping("/comment/{feedSeq}")
    public ResponseEntity<List<CommentDTO>> getCommentByFeed(@PathVariable("feedSeq")  long feedSeq){
        List<CommentDTO> commentDTOs = commentService.getCommentByFeedSeq(feedSeq);
        return ResponseEntity.status(HttpStatus.OK).body(commentDTOs);
    }

    @ApiOperation(
            value = "comment 생성"
            , notes = "comment 생성")
    @PostMapping("/comment")
    public ResponseEntity<CommentDTO> insertComment(CommentInputDTO commentInputDTO) throws Exception {
        CommentDTO rCommentDTO = commentService.insertComment(commentInputDTO);

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
    public ResponseEntity<List<MemberSearchInfoDTO>> getMemberSearchInfo(@PathVariable("nickname") String nickname){
        List<MemberSearchInfoDTO> rMemberSearchInfoDTOs = memberService.getMemberSearchInfo(nickname);

        return ResponseEntity.status(HttpStatus.OK).body(rMemberSearchInfoDTOs);
    }

    @ApiOperation(
            value = "follower 조회"
            , notes = "memberSeq 기반으로 follower 기본 정보 조회")
    @GetMapping("/search/follower/{memberSeq}") // not enough
    public ResponseEntity<List<MemberSearchInfoDTO>> getFollowerMemberSearchInfoByMemberSeq(@PathVariable("memberSeq") long memberSeq){
        List<Long> followers = connectService.getFollowers(memberSeq);
        List<MemberSearchInfoDTO> rMemberSearchInfoDTOs = new ArrayList<>();

        for(Long follower:followers){
            MemberSearchInfoDTO rMemberSearchInfoDTO = memberService.getMemberSearchInfoByMemberSeq(follower);
            rMemberSearchInfoDTOs.add(rMemberSearchInfoDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(rMemberSearchInfoDTOs);
    }

    @ApiOperation(
            value = "following 조회"
            , notes = "memberSeq 기반으로 follower 기본 정보 조회")
    @GetMapping("/search/following/{memberSeq}") // not enough
    public ResponseEntity<List<MemberSearchInfoDTO>> getFollowingMemberSearchInfoByMemberSeq(@PathVariable("memberSeq") long memberSeq){
        List<Long> followings = connectService.getFollowings(memberSeq);
        List<MemberSearchInfoDTO> rMemberSearchInfoDTOs = new ArrayList<>();

        for(Long following:followings){
            MemberSearchInfoDTO rMemberSearchInfoDTO = memberService.getMemberSearchInfoByMemberSeq(following);
            rMemberSearchInfoDTOs.add(rMemberSearchInfoDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(rMemberSearchInfoDTOs);
    }

    @ApiOperation(
            value = "팔로우 여부 조회"
            , notes = "시작 맴버와 도착 맴버를 입력하여 관계 확인. boolean 반환")
    @GetMapping("/profile/{srcMemberSeq}/{dstMemberSeq}")
    public ResponseEntity<Boolean> getIsFollow(@PathVariable("srcMemberSeq") long srcMemberSeq, @PathVariable("dstMemberSeq") long dstMemberSeq){
        Boolean IsFollow = connectService.getIsFollow(srcMemberSeq, dstMemberSeq);

        return ResponseEntity.status(HttpStatus.OK).body(IsFollow);
    }

    @ApiOperation(
            value = "connect 생성"
            , notes = "connect 생성")
    @PostMapping("/profile/follow")
    public ResponseEntity<ConnectDTO> insertConnect(ConnectInputDTO connectInputDTO) throws Exception {
        ConnectDTO rConnectDTO = connectService.insertConnect(connectInputDTO);

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
    public ResponseEntity<MemberDTO> getProfile(@PathVariable("memberSeq") long memberSeq){
        MemberDTO rMemberDTO = memberService.getMember(memberSeq);

        return ResponseEntity.status(HttpStatus.OK).body(rMemberDTO);
    }

    @GetMapping("/mypage")
    public ResponseEntity<List<StoreDTO>> getStoreByMemberSeq(@PathVariable("memberSeq") long memberSeq){
        List<StoreDTO> rStoreDTO = storeService.getStoreByMemberSeq(memberSeq);

        return ResponseEntity.status(HttpStatus.OK).body(rStoreDTO);
    }

    @ApiOperation(
            value = "StoreSeq와 context를 입력으로 feed 데이터 생성"
            , notes = "StoreSeq, context를 입력하여 feed 데이터 생성")
    @PostMapping("/mypage")
    public ResponseEntity<FeedDTO> createFeed(long storeSeq, String context) throws Exception{
        StoreDTO storeDTO = storeService.getStore(storeSeq);

        FeedDTO feedDTO = feedService.StoreToFeed(storeDTO, context);

        FeedInputDTO feedInputDTO = feedService.ConvertToInputDTO(feedDTO);
        FeedDTO rfeedDTO = feedService.insertFeed(feedInputDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rfeedDTO);
    }

    @ApiOperation(
            value = "단독 feed 생성"
            , notes = "단독 feed 생성. 일반적으로 사용 x")
    @PostMapping("/mypage2")
    public ResponseEntity<FeedDTO> createFeed2(FeedInputDTO feedInputDTO) throws Exception{
        FeedDTO rFeedDTO = feedService.insertFeed(feedInputDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rFeedDTO);
    }
    @ApiOperation(
            value = "feed 조회"
            , notes = "memberSeq 기반으로 feed 조회")
    @GetMapping("/main/{memberSeq}")
    public  ResponseEntity<List<FeedDTO>> getFeedByMemberSeq(@PathVariable("memberSeq") long memberSeq){
        List<FeedDTO> rFeedDTOs = feedService.getFeedAllByMemberSeq(memberSeq);

        return ResponseEntity.status(HttpStatus.OK).body(rFeedDTOs);
    }

    @ApiOperation(
            value = "machineDataSeq 기반 machineLocationSeq 조회"
            , notes = "machineDataSeq 기반 machineLocationSeq 조회")
    @GetMapping("/machine/loc/{machineDataSeq}")
    public ResponseEntity<Long> getMachineLocationByMachineDataSeq(@PathVariable("machineDataSeq") long machineDataSeq){
        long machineSeq = machineDataService.getMachineData(machineDataSeq).getMachineSeq();
        long machineLocationSeq = machineService.getMachine(machineSeq).getMachineLocationSeq();

        return ResponseEntity.status(HttpStatus.OK).body(machineLocationSeq);
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
        MachineDTO rMachineDTO = machineService.insertMachine(machineInputDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rMachineDTO);
    }

    @PostMapping("/machine/data")
    public  ResponseEntity<MachineDataDTO> createMachineData(@RequestBody MachineDataInputDTO machineDataInputDTO) throws Exception{
        MachineDataDTO rMachineDataDTO = machineDataService.insertMachineData(machineDataInputDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rMachineDataDTO);
    }

    @ApiOperation(
            value = "machineData 조회"
            , notes = "machineDataSeq 기반 machineData 조회")
    @GetMapping("machine/data/{machineDataSeq}")
    public ResponseEntity<MachineDataDTO> selectMachineData(@PathVariable("machineDataSeq") long machineDataSeq){
        MachineDataDTO rMachineDataDTO = machineDataService.getMachineData(machineDataSeq);

        return ResponseEntity.status(HttpStatus.OK).body(rMachineDataDTO);
    }

    @GetMapping("/feed/heart/{feedSeq}")
    public ResponseEntity<List<HeartDTO>> getHeartByFeedSeq(@PathVariable("feedSeq") long feedSeq) {
        return ResponseEntity.status(HttpStatus.OK).body(heartService.getHeartsByFeed(feedSeq));
    }

    @GetMapping("/machine/new")
    public ResponseEntity<Long> getMachineDataSeqRecent(){
        MachineDataDTO machineDataDTO = machineDataService.getRecentMachineData();
        long result = (machineDataDTO == null) ? -1 : machineDataService.getRecentMachineData().getMachineDataSeq();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(
            value = "store 생성"
            , notes = "store 생성")
    @PostMapping("/store")
    public ResponseEntity<StoreDTO> createStore(@RequestBody StoreInputDTO storeInputDTO) throws Exception{
        StoreDTO rStoreDTO = storeService.insertStore(storeInputDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rStoreDTO);
    }

    // oauth
    @GetMapping("/account/naver")
    public ResponseEntity<MemberDTO> createMemberByNaver(@RequestParam String code, @RequestParam String state) throws Exception{
        System.out.println("createMemberByNaver");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new OAuthRestTemplateErrorHandler());

        JSONObject jsonObject = restTemplate.getForObject("https://nid.naver.com/oauth2.0/token?" +
                "grant_type=authorization_code" +
                "&client_id=1cdhp17WpXR_m9BDcOcE" +
                "&client_secret=z5jEYpcFLE" +
                "&code=" + code +
                "&state" + state,
                JSONObject.class);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jsonObject.get("access_token"));
        HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(headers);

        MemberDTO memberDTO = restTemplate.postForObject("https://openapi.naver.com/v1/nid/me", entity, MemberDTO.class);
        String email = (String)memberDTO.getResponse().get("email");
        String name = (String)memberDTO.getResponse().get("name");

        // if data exist
        if (memberService.findMemberByEmailReturnBool(email))
            return ResponseEntity.status(HttpStatus.OK).body(memberService.findMemberByEmail(email));

        // add to DB
        MemberInputDTO memberInputDTO = new MemberInputDTO();
        memberInputDTO.setEmail(email);
        memberInputDTO.setName(name);
        memberInputDTO.setIsAdmin("N");
        memberInputDTO.setNickname("empty");

        MemberDTO rMemberDto = memberService.insertMember(memberInputDTO);

        return ResponseEntity.status(HttpStatus.OK).body(rMemberDto);
    }

    @GetMapping("/mypage/post-new/member/{memberSeq}")

    public ResponseEntity<List<StoreDTO>> getStoreByMemberSeqDesc(@PathVariable("memberSeq") long memberSeq){
        List<StoreDTO> storeDTOs = storeService.getStoreByMemberSeqDesc(memberSeq);

        return ResponseEntity.status(HttpStatus.OK).body(storeDTOs);
    }

    @GetMapping("/mypage/post-new/store/{storeSeq}")
    public ResponseEntity<StoreDTO> getStoreByStoreSeqDesc(@PathVariable("storeSeq") long storeSeq){
        StoreDTO storeDTO = storeService.getStore(storeSeq);

        return ResponseEntity.status(HttpStatus.OK).body(storeDTO);
    }

}
