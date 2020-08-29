package my.likeaglow.fakedc.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import my.likeaglow.fakedc.common.exceptions.NotFoundVOException;
import my.likeaglow.fakedc.model.DeletePostDTO;
import my.likeaglow.fakedc.model.LoginMemberDTO;
import my.likeaglow.fakedc.model.PostVO;
import my.likeaglow.fakedc.model.PostViewCountDTO;
import my.likeaglow.fakedc.model.RecommendPostDTO;
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
   * @throws UnsupportedEncodingException
   */
  @GetMapping("/write/{boardId}")
  public ModelAndView write(@PathVariable String boardId) throws UnsupportedEncodingException {

    if (!hasLoginMember()) {
      ModelAndView mv = new ModelAndView(
          "redirect:/member/login?returnUrl=" + URLEncoder.encode("/post/write/" + boardId, "UTF-8"));
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
    // redirect: + 상대경로. 현재 레벨 + 상대경로
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

    ModelAndView mv = new ModelAndView();

    PostViewCountDTO postViewCountDTO = new PostViewCountDTO(postId);
    String result = postService.viewCount(postViewCountDTO);
    if (result == null) {
      // ModelAndView mv = new ModelAndView("redirect:/error");
      mv.setViewName("common/back");
      mv.addObject("alertMessage", "해당 게시글이 존재하지 않습니다.");
      return mv;
    }

    PostVO postVO = postService.detail(postId);
    mv.setViewName("post/detail");
    mv.addObject("vo", postVO);

    return mv;
  }

  /**
   * 게시글 업데이트
   * 
   * @param postId
   * @return
   * @throws UnsupportedEncodingException
   */
  @GetMapping("/update/{postId}")
  public ModelAndView update(@PathVariable long postId) throws UnsupportedEncodingException {
    // 원래는 try-catch로 처리해야 하나 간소하게 하기 위해 throws Exception 함.
    logger.info("받은 postId : " + postId);
    ModelAndView mv = new ModelAndView();

    if (!hasLoginMember()) { // 세션에 저장된 로그인 정보가 없는 경우. 즉 로그인 상태가 아닌 경우
      mv.setViewName("redirect:/member/login?returnUrl=" + URLEncoder.encode("/post/update/" + postId, "UTF-8"));
      // url의 구성 : Protocol + HostName + path/to/resource + parameters
      // redirect는 url(path)을 바꿈(브라우저의 입력되는 url을 바꿈)
      // 브라우저에 url을 입력하여 접속 시 get 메서드 요청
      // ModelAndView 객체의 뷰페이지 설정 시 redirect가 붙으면 context path 아래의 해당 url을 찾는다.
      // 즉, 프로젝트에서 해당 url로 매핑된 메서드를 찾음.
      // context path : 어플레케이션명. server.xml에서 Context 태그의 path 속성값.
      // 현재 프로젝트의 context path : /fakedc
      // redirect: + 상대경로. // redirect:/ + 절대경로
      // mv.setViewName("redirect:/member/login?returnUrl=" +
      // URLEncoder.encode("/post/update/" + postId, "UTF-8")) :
      // fakedc/member/login get 메서드를 요청하는데 아래의 파라미터를 갖음(path 이하의 부분. ?부터)
      // parameter : returnUrl을 속성(key), /post/update/ + postId를 속성값(value)
      // URLEncoder.encode(인코딩할 문자열, CharSet)
      // "/"을 url 구분자와 구별하기 위하여 인코딩을 해줌.
      // 인코딩 하지 않으면 url에 member/login?returnUrl=/post/update/...... 의 값이 들어감.

      return mv;
    }

    // TODO : postVO.create_user와 loginMember.MEM_ID와 비교
    // 다르면 다른 페이지로 돌림(이전 페이지, 아예 수정 화면을 띄우지 않음)
    PostVO postVO = postService.detail(postId);

    if (postVO == null) {
      mv.setViewName("common/back"); // 뒤로가기 페이지
      mv.addObject("alertMessage", "해당 게시글이 존재하지 않습니다."); // 자바스크립트의 함수로 지정된 메세지 출력
      return mv;
    }

    if (!postVO.getCREATE_USER().equals(getLoginMember().getMEM_ID())) {
      mv.setViewName("common/back");
      mv.addObject("alertMessage", "게시물 작성자 본인이 아닙니다.");
      return mv;
    }

    mv.setViewName("post/update");
    // ModelAndView 객체의 뷰페이지 설정 시 servlet-context에 InternalResourceViewResolver bean
    // 클래스의 설정을 따름.
    // prefix : 경로. suffix : 파일 확장자. 현재의 pefix와 suffix는 /WEB-INF/views/ 와 .jsp
    // 지정된 경로 아래서 (/WEB-INF/views/post)에서 update.jsp를 찾음.
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

    // 08.22 수정 :
    // PostAuthCheckDTO 객체를 사용하지 않고 로그인한 멤버의 ID를 updatePostDTO에 포함시킴
    // 작업 단위를 너무 잘게 자를 필요 없음. 게시글 본인인증과 게시물 수정은 동시에 일어나는 작업이므로 나누어서 처리할 필요 없음.
    // 로그인한 글쓴이의 사용자 정보를 세팅한다
    LoginMemberDTO logimMember = getLoginMember();
    updatePostDTO.setMEM_ID(logimMember.getMEM_ID());
    // 세션에서 로그인 정보를 가져와 MEM_ID 필드에 값을 넣지 않고 수정 페이지의 vo.CREATE_USER 값을
    // MEM_ID 필드로 넘겨주어도 됨. 수정 화면에 접근하기 이전 게시글 작성자 = 로그인 멤버임이 확인되었으므로.

    PostVO updatedPost = postService.update(updatePostDTO);

    logger.info("PostController.updateProcess() 업데이트 PostVO : " + updatedPost);

    /*
     * if (updatedPost == null) { // 쿼리 수행이 실패하거나 게시글 작성자 ID와 로그인한 ID가 달라
     * postService.update()의 리턴값이 null인 경우 ModelAndView mv = new
     * ModelAndView("common/back"); mv.addObject("alertMessage",
     * "게시물 작성자 본인이 아닙니다."); return mv; }
     */
    // 게시글의 본인 인증은 수정한 게시글의 데이터를 받아 처리하는 과정에서 일어나는 게 아니라 게시글의 수정 화면에 접근하기 전에 일어나야함
    // 위의 코드는 post 메서드가 아닌 get 메서드에서 실행되어야 함.

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
   * @throws UnsupportedEncodingException
   */
  @GetMapping("/delete/{boardId}/{postId}") // @PathVariable로 매개변수 여러 개 받을 수 있음.
  public ModelAndView delete(@PathVariable String boardId, @PathVariable long postId)
      throws UnsupportedEncodingException {

    logger.info("PostController.delete() 메서드 시작 ");
    logger.info("받은 postId : " + postId);
    logger.info("받은 boardId : " + boardId);

    if (!hasLoginMember()) {
      ModelAndView mv = new ModelAndView(
          "redirect:/member/login?returnUrl=" + URLEncoder.encode("/post/delete/" + boardId + "/" + postId, "UTF-8"));
      return mv;
    }
    LoginMemberDTO loginMember = getLoginMember();

    DeletePostDTO deletePostDTO = new DeletePostDTO();
    deletePostDTO.setPOST_ID(postId);
    // postId : 삭제하려는 게시물의 POST_ID
    deletePostDTO.setMEM_ID(loginMember.getMEM_ID());

    String result;
    try {
      result = postService.delete(deletePostDTO);
    } catch (NotFoundVOException e) {
      ModelAndView mv = new ModelAndView("common/back");
      mv.addObject("alertMessage", "게시물을 삭제할 수 없습니다.");
      return mv;
    }

    ModelAndView mv = new ModelAndView("redirect:/board/" + boardId);
    // 게시물 삭제 후 게시물 목록으로 이동
    return mv;

  }

  /**
   * 게시글 추천/비추천
   * 
   * @param postId
   * @param unrecommend
   * @return
   * @throws UnsupportedEncodingException
   */
  @GetMapping("/recommend/{postId}/{unrecommend}")
  public ModelAndView recommend(@PathVariable long postId, @PathVariable int unrecommend)
      throws UnsupportedEncodingException {
    logger.info("PostController.recommdend() 시작");

    ModelAndView mv = new ModelAndView();
    if (!hasLoginMember()) {
      mv.setViewName("redirect:/member/login?returnUrl=" + URLEncoder.encode("/post/" + postId, "UTF-8"));
      return mv;
    }

    LoginMemberDTO loginMember = getLoginMember();

    RecommendPostDTO recommendPostDTO = new RecommendPostDTO(postId, loginMember.getMEM_ID(), unrecommend);
    int result = postService.recommend(recommendPostDTO);

    if (result == -1) {
      mv.setViewName("common/back");
      mv.addObject("alertMessage", "게시물이 존재하지 않습니다.");
      return mv;
    } else if (result == -2) {
      mv.setViewName("common/back");
      mv.addObject("alertMessage", "이미 추천 또는 비추천 한 게시글입니다.");
      return mv;
    }
    return new ModelAndView("redirect:/post/" + postId);
  }

}