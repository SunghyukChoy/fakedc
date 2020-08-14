package my.likeaglow.fakedc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import my.likeaglow.fakedc.model.PostListDTO;
import my.likeaglow.fakedc.model.PostVO;
import my.likeaglow.fakedc.service.PostService;

@Controller
@RequestMapping("/board")
public class BoardController {

  private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

  @Autowired
  private PostService postService;

  /*
   * 숙제 #3 PostService.getPosts 메서드가 DB에서 값을 가져올 수 있게 완성시키고 리스트가 정상적으로 가져와지는지 로그로
   * 확인하는 로직을 작성할 것
   * 
   * 추가: 리스트 페이지를 만들면 베리 굿
   */
  /**
   * 게시판 게시글 출력
   * 
   * @param boardId
   * @return
   */
  @GetMapping("/{boardId}")
  public ModelAndView postList(@PathVariable String boardId) {
    logger.info("BoardController.postList() 시작");

    ModelAndView mv = new ModelAndView("board/post_list");
    PostListDTO postListDTO = new PostListDTO();
    setPostListTest(boardId, postListDTO);

    List<PostVO> list = postService.getPosts(postListDTO);

    /*
     * for (PostVO postVO : list) { logger.info("각 게시글" + postVO); }
     */
    // String test = "mv 테스트";

    mv.addObject("postList", list);
    mv.addObject("vo", boardId);
    // mv.addObject("vo", test);

    return mv;
  }

  // 게시글 불러오기 테스트
  private void setPostListTest(String boardId, PostListDTO postListDTO) {
    /*
     * int searchType = 0; // 없음 int postType = 1; // 일반 String keyword = ""; int
     * pageSize = 10; int pageIndex = 0;
     */
    postListDTO.setBOARD_ID(boardId);
    postListDTO.setSEARCH_TYPE(0); // 없음
    postListDTO.setPOST_TYPE(1); // 일반
    postListDTO.setKEYWORD(null);
    postListDTO.setPAGING_SIZE(10);
    postListDTO.setPAGINE_INDEX(0);
  }

}
