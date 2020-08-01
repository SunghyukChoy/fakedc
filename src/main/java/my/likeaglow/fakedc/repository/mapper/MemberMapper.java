package my.likeaglow.fakedc.repository.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import my.likeaglow.fakedc.model.MemberVO;

@Repository
public interface MemberMapper {

  public List<MemberVO> findAll();

}
