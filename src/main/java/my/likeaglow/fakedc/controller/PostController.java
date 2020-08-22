package my.likeaglow.fakedc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import my.likeaglow.fakedc.model.DeletePostDTO;
import my.likeaglow.fakedc.model.LoginMemberDTO;
import my.likeaglow.fakedc.model.PostAuthCheckDTO;
import my.likeaglow.fakedc.model.PostVO;
import my.likeaglow.fakedc.model.PostViewCountDTO;
import my.likeaglow.fakedc.model.UpdatePostDTO;
import my.likeaglow.fakedc.model.writePostDTO;
import my.likeaglow.fakedc.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController extends BaseController {

  private static final Logger logger = LoggerFactory.getLogger(PostController.class);

  @Autowired
  private PostService postService;

  /**
   * 끌쓰기
   * 
   * @return
   */
  @GetMapping("/write/{boardId}")
  public ModelAndView write(@PathVariable String boardId) {

    if (!hasLoginMember()) {
      ModelAndView mv = new ModelAndView("member/request_login");
      return mv;
    }

    ModelAndView mv = new ModelAndView("post/write");

    LoginMemberDTO loginMember = getLoginMember();

    setTestPost(mv, loginMember, boardId);

    return mv;
  }

  // 글쓰기 기본값 생성 메소드
  private void setTestPost(ModelAndView mv, LoginMemberDTO loginMember, String boardId) {
    if (boardId == null || boardId == "") {
      boardId = "music"; // 기본값으로 music board에 입력
    }

    writePostDTO writePostDTO = new writePostDTO();
    writePostDTO.setBOARD_ID(boardId);
    writePostDTO.setPOST_TITLE("테스트 게시글입니다.");
    writePostDTO.setPOST_CONTENT("게시글 테스트입니다.\n잘부탁드립니다.");
    writePostDTO.setPOST_PASSWORD("1111");
    writePostDTO.setPOST_TYPE(1);
    writePostDTO.setCREATE_USER(loginMember.getMEM_ID());

    mv.addObject("vo", writePostDTO);
  }

  /**
   * 글쓰기 프로세스
   * 
   * @param postVO
   * @return
   */
  @PostMapping("/write")
  public ModelAndView writeProcess(writePostDTO writePostDTO) {
    logger.info("writePostDTO에 담긴 데이터 : " + writePostDTO);
    long postId = postService.write(writePostDTO);

    ModelAndView mv = new ModelAndView();
    // failure
    if (postId < 0) {
      logger.info("글쓰기 실패");
      mv.setViewName("post/write");
      mv.addObject("vo", writePostDTO);
      return mv;
    }

    mv.setViewName("redirect:" + postId);
    // "redirect:" : 현재 클래스까지의 경로를 나타낸다.
    // "redirect:" + postId : http://localhost:8080/fakedc/post/postId
    return mv;
  }

  /**
   * 글 보기, 게시물 조회수 증가
   * 
   * @param postId
   * @return
   */
  @GetMapping("/{postId}")
  public ModelAndView detail(@PathVariable long postId) {
    // @PathVariable : 매핑명의 {탬플릿변수}에서 값을 받아온다(URL에서 값을 받아온다).
    // 매핑명의 {탬플릿변수}명과 매개변수의 이름은 같아야 한다. null이나 공백 값이 들어갈 수 있는 경우라면 사용하지 말아야 한다.

    PostViewCountDTO postViewCountDTO = new PostViewCountDTO(postId);
    String result = postService.viewCount(postViewCountDTO);
    if (result == null) {
      ModelAndView mv = new ModelAndView("redirect:/error");
      return mv;
    }

    ModelAndView mv = new ModelAndView("post/detail");
    PostVO postVO = postService.detail(postId);
    mv.addObject("vo", postVO);

    return mv;
  }

  /**
   * 게시글 업데이트
   * 
   * @param postId
   * @return
   */
  @GetMapping("/update/{postId}")
  public ModelAndView update(@PathVariable long postId) {
    logger.info("받은 postId : " + postId);
    ModelAndView mv = new ModelAndView();

    if (!hasLoginMember()) {
      mv.setViewName("member/request_login");
      return mv;
    }

    // TODO : postVO.create_user와 loginMember.MEM_ID와 비교
    // 다르면 다른 페이지로 돌림(이전 페이지, 아예 수정 화면을 띄우지 않음)
    PostVO postVO = postService.detail(postId);

    if (postVO == null) {
      mv.setViewName("common/back");
      mv.addObject("alertMessage", "해당 게시글이 존재하지 않습니다.");
      return mv;
    }

    mv.setViewName("post/update");
    mv.addObject("vo", postVO);

    return mv;
  }

  /**
   * 게시글 업데이트 프로세스
   * 
   * @param updatePostDTO
   * @param session
   * @return
   */
  @PostMapping("/update")
  public ModelAndView updateProcess(UpdatePostDTO updatePostDTO) {
    logger.info("받은 updatePostDTO : " + updatePostDTO);

    LoginMemberDTO logimMember = getLoginMember();

    // 로그인한 글쓴이의 사용자 정보를 세팅한다
    updatePostDTO.setMEM_ID(logimMember.getMEM_ID());

    PostVO updatedPost = postService.update(updatePostDTO);

    logger.info("PostController.updateProcess() 업데이트 PostVO : " + updatedPost);

    if (updatedPost == null) { // 쿼리 수행이 실패하거나 게시글 작성자 ID와 로그인한 ID가 달라 postService.update()의 리턴값이 null인 경우
      ModelAndView mv = new ModelAndView("redirect:/error");
      return mv;
    }

    logger.info("업데이트된 POST_ID : " + updatedPost.getPOST_ID());

    ModelAndView mv = new ModelAndView("redirect:" + updatedPost.getPOST_ID());

    mv.addObject("vo", updatedPost);

    return mv;
  }

  /**
   * 게시글 삭제
   * 
   * @param boardId
   * @param postId
   * @param deletePostDTO
   * @return
   */
  @GetMapping("/delete/{boardId}/{postId}") // @PathVariable로 매개변수 여러 개 받을 수 있음.
  public ModelAndView delete(@PathVariable String boardId, @PathVariable long postId, DeletePostDTO deletePostDTO) {

    logger.info("받은 postId : " + postId);
    logger.info("받은 boardId : " + boardId);
    logger.info("deletePostDTO : " + deletePostDTO);

    deletePostDTO.setPOST_ID(postId);
    // postId : 삭제하려는 게시물의 POST_ID
    logger.info("deletePostDTO : " + deletePostDTO);

    if (!hasLoginMember()) {
      ModelAndView mv = new ModelAndView("member/request_login");
      return mv;
    }

    LoginMemberDTO logimMember = getLoginMember();

    PostAuthCheckDTO postAuthCheckDTO = new PostAuthCheckDTO(postId, logimMember.getMEM_ID());
    // postAuthCheckDTO : 삭제하려는 게시물의 POST_ID와 현재 로그인한 멤버의 MEM_ID를 담은 객체

    String result = postService.delete(postAuthCheckDTO, deletePostDTO);
    if (result == null) {
      ModelAndView mv = new ModelAndView("redirect:/error");
      return mv;
    }

    ModelAndView mv = new ModelAndView("redirect:/board/" + boardId);
    // 게시물 삭제 후 게시물 목록으로 이동
    return mv;

  }

}