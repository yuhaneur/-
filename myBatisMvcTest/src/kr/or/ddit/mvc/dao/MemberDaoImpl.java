package kr.or.ddit.mvc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.MybatisUtil;

public class MemberDaoImpl implements IMemberDao{

	private static MemberDaoImpl dao;
	
	private MemberDaoImpl() {}
	
	public static MemberDaoImpl getInstance() {
		if(dao ==null) dao = new MemberDaoImpl();
		
		return dao;
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
		int insertCnt = 0;
		SqlSession session = null;
		try {
			session = MybatisUtil.getSqlSession();
			
			insertCnt = session.insert("member.insertMember", memVo);
			
			if(insertCnt>0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)session.close();
		}
		return insertCnt;
	}

	@Override
	public int deleteMember(String memId) {
		int delCnt = 0;
		SqlSession session =null;
		try {
			session = MybatisUtil.getSqlSession();
			delCnt = session.delete("member.deleteMember", memId);
			if(delCnt>0)session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
		return delCnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		int upCnt = 0;
		SqlSession session =null;
		try {
			session = MybatisUtil.getSqlSession();
			upCnt = session.update("member.updateMember", memVo);
			if(upCnt>0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
		return upCnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		
		SqlSession session = null;
		List<MemberVO> list = null;
		try {
			session = MybatisUtil.getSqlSession();
			list =new ArrayList<MemberVO>();
			list = session.selectList("member.selectAllMember");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
		return list;
	}

	@Override
	public int getMemberCount(String memId) {
		
		int memCnt= 0;
		SqlSession session = null;
		try {
			session = MybatisUtil.getSqlSession();
			memCnt = session.selectOne("member.countMember", memId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
		return memCnt;
	}

	@Override
	public int updateMember2(HashMap<String, String> updateData) {
		SqlSession session = null;
		int uCnt =0;
		try {
			session =MybatisUtil.getSqlSession();
			uCnt = session.update("member.updateMember2", updateData);
			if(uCnt>0)session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return uCnt;
	}

}
