package kr.or.ddit.mymember.service;

import java.util.List;

import kr.or.ddit.mymember.vo.MyMemberVO;

public interface IMemberService {
	// 회원 전체 목록 보기
	public List<MyMemberVO> selectMemberList();
	
	// 회원 상세보기
	public MyMemberVO selectMember(String Id);
	
	// 회원정보 입력
	public int insertMember(MyMemberVO vo);
	
	// 회원정보 수정
	public int updateMember(MyMemberVO vo);
	
	// 회원정보 삭제
	public int deleteMember(String Id);
	
	// id 중복체크
	public int checkId(String Id);
}
