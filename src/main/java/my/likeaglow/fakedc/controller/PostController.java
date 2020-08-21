package my.likeaglow.fakedc.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import my.likeaglow.fakedc.common.GlobalVariable;
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
public class PostController {

  private static final Logger logger = LoggerFactory.getLogger(PostController.class);

  @Autowired
  private PostService postService;

  /**
   * 끌쓰기
   * 
   * @return
   */
  @GetMapping("/write/{boardId}")
  public ModelAndView write(HttpSession session, @PathVariable String boardId) {

    if (session.getAttribute(GlobalVariable.LOGINMEMBERDTO_SESSION_KEY) == null) {
      ModelAndView mv = new ModelAndView("member/request_login");
      return mv;
    }

    LoginMemberDTO loginMember = (LoginMemberDTO) session.getAttribute(GlobalVariable.LOGINMEMBERDTO_SESSION_KEY);

    ModelAndView mv = new ModelAndView("post/write");

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
  public ModelAndView update(@PathVariable long postId, HttpSession session) {
    logger.info("받은 postId : " + postId);

    if (session.getAttribute(GlobalVariable.LOGINMEMBERDTO_SESSION_KEY) == null) {
      ModelAndView mv = new ModelAndView("member/request_login");
      return mv;
    }

    PostVO postVO = postService.detail(postId);

    ModelAndView mv = new ModelAndView("post/update");

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
  public ModelAndView updateProcess(UpdatePostDTO updatePostDTO, HttpSession session) {
    logger.info("받은 updatePostDTO : " + updatePostDTO);

    LoginMemberDTO logimMember = (LoginMemberDTO) session.getAttribute(GlobalVariable.LOGINMEMBERDTO_SESSION_KEY);

    PostAuthCheckDTO postAuthCheckDTO = new PostAuthCheckDTO(updatePostDTO.getPOST_ID(), logimMember.getMEM_ID());
    // postAuthCheckDTO : 수정하려는 게시물의 POST_ID와 현재 로그인한 멤버의 MEM_ID를 담은 객체

    PostVO updatedPost = postService.update(postAuthCheckDTO, updatePostDTO);

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
   * @param session
   * @return
   */
  @GetMapping("/delete/{boardId}/{postId}") // @PathVariable로 매개변수 여러 개 받을 수 있음.
  public ModelAndView delete(@PathVariable String boardId, @PathVariable long postId, DeletePostDTO deletePostDTO,
      HttpSession session) {

    logger.info("받은 postId : " + postId);
    logger.info("받은 boardId : " + boardId);
    logger.info("deletePostDTO : " + deletePostDTO);

    deletePostDTO.setPOST_ID(postId);
    // postId : 삭제하려는 게시물의 POST_ID
    logger.info("deletePostDTO : " + deletePostDTO);

    if (session.getAttribute(GlobalVariable.LOGINMEMBERDTO_SESSION_KEY) == null) {
      ModelAndView mv = new ModelAndView("member/request_login");
      return mv;
    }

    LoginMemberDTO logimMember = (LoginMemberDTO) session.getAttribute(GlobalVariable.LOGINMEMBERDTO_SESSION_KEY);

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