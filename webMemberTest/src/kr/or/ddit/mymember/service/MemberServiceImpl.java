package kr.or.ddit.mymember.service;

import java.util.List;

import kr.or.ddit.mymember.dao.MemberDaoImpl;
import kr.or.ddit.mymember.vo.MyMemberVO;

public class MemberServiceImpl implements IMemberService{
	
	private static MemberServiceImpl service;
	private MemberDaoImpl dao;
	
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}
	public static MemberServiceImpl getInstance() {
		if(service==null)service = new MemberServiceImpl();
		return service;
	}
	@Override
	public List<MyMemberVO> selectMemberList() {
		return dao.selectMemberList();
	}

	@Override
	public MyMemberVO selectMember(String Id) {
		return dao.selectMember(Id);
	}

	@Override
	public int insertMember(MyMemberVO vo) {
		return dao.insertMember(vo);
	}

	@Override
	public int updateMember(MyMemberVO vo) {
		return dao.updateMember(vo);
	}

	@Override
	public int deleteMember(String Id) {
		return dao.deleteMember(Id);
	}
	@Override
	public int checkId(String Id) {
		return dao.checkId(Id);
	}

}
