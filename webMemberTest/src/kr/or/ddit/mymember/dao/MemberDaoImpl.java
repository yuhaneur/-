package kr.or.ddit.mymember.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.mymember.vo.MyMemberVO;
import kr.or.ddit.util.MybatisUtil;

public class MemberDaoImpl implements IMemberDao{
	private static MemberDaoImpl dao;
	private MemberDaoImpl() {
	}
	public static MemberDaoImpl getInstance() {
		if(dao==null)dao = new MemberDaoImpl();
		return dao;
	}
	@Override
	public List<MyMemberVO> selectMemberList() {
		List<MyMemberVO> list = new ArrayList<MyMemberVO>();
		SqlSession session = null;
		try {
			session= MybatisUtil.getSqlSession();
			list = session.selectList("member.selectMemberList");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)session.close();
		}
		return list;
	}

	@Override
	public MyMemberVO selectMember(String Id) {
		MyMemberVO vo = new MyMemberVO();
		SqlSession session = null;
		try {
			session=MybatisUtil.getSqlSession();
			vo = session.selectOne("member.selectMember", Id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)session.close();
		}
		return vo;
	}

	@Override
	public int insertMember(MyMemberVO vo) {
		int cnt = 0;
		SqlSession session=null;
		try {
			session=MybatisUtil.getSqlSession();
			cnt = session.insert("member.insertMember", vo);
			if(cnt>0)session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)session.close();
		}
		return cnt;
	}

	@Override
	public int updateMember(MyMemberVO vo) {
		int cnt = 0;
		SqlSession session=null;
		try {
			session=MybatisUtil.getSqlSession();
			cnt = session.update("member.updateMember", vo);
			if(cnt>0)session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)session.close();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String Id) {
		int cnt = 0;
		SqlSession session=null;
		try {
			session=MybatisUtil.getSqlSession();
			cnt = session.delete("member.deleteMember", Id);
			if(cnt>0)session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)session.close();
		}
		return cnt;
	}
	@Override
	public int checkId(String Id) {
		int cnt=0;
		SqlSession session =null;
		try {
			session=MybatisUtil.getSqlSession();
			cnt = session.selectOne("member.checkId", Id);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)session.close();
		}
		return cnt;
	}

}
