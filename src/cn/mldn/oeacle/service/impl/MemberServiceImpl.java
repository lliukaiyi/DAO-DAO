package cn.mldn.oeacle.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.oeacle.dao.IMemberDAO;
import cn.mldn.oeacle.dao.impl.MemberDAOImpl;
import cn.mldn.oeacle.factory.DAOFactory;
import cn.mldn.oeacle.service.IMemberService;
import cn.mldn.oeacle.vo.Member;

public class MemberServiceImpl implements IMemberService {

	@Override
	public boolean add(Member vo) throws Exception {
		IMemberDAO dao = DAOFactory.getInstance(MemberDAOImpl.class);
		if(dao.findById(vo.getMid())==null){
			if(dao.findByPhone(vo.getPhone())==null){
				if(vo.getAge()<=0){
					vo.setAge(-1);
				}
				return dao.doCreate(vo);
			
			}
		}
		return false;
	}

	@Override
	public boolean edit(Member vo) throws Exception {
		IMemberDAO dao = DAOFactory.getInstance(MemberDAOImpl.class);
		Member temp = dao.findByPhone(vo.getPhone());
		if(temp==null){
			dao.doUpdate(vo);
		}else{
			if(vo.getPhone().equals(temp.getPhone())){
				dao.doUpdate(vo);
			}
		}
		return false;

	}

	@Override
	public boolean remove(Set<String> ids) throws Exception {
		if(ids==null||ids.size()==0){
			return false;
		}
		return DAOFactory.getInstance(MemberDAOImpl.class).doRemoveBatch(ids);

	}

	@Override
	public Member get(String id) throws Exception {
		return DAOFactory.getInstance(MemberDAOImpl.class).findById(id);
 
	}

	@Override
	public List<Member> list() throws Exception {
		return DAOFactory.getInstance(MemberDAOImpl.class).findAll();

	}

	@Override
	public Map<String, Object> list(Integer currentPage, Integer lineSize) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		IMemberDAO dao = DAOFactory.getInstance(MemberDAOImpl.class);
		map.put("allMember", dao.findAllSpliu(currentPage, lineSize));
		map.put("memberCount", dao.getAllCount());
		return map;
		
	}

	@Override
	public Map<String, Object> list(String column, String keyWord, Integer currentPage, Integer lineSize)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		IMemberDAO dao = DAOFactory.getInstance(MemberDAOImpl.class);
		if(column==null||keyWord==null||"".equals(keyWord)){
			map.put("allMember", dao.findAllSpliu(currentPage, lineSize));
			map.put("memberCount", dao.getAllCount());
		}
		map.put("allMember", dao.findAllSpliu(column, keyWord, currentPage, lineSize));
		map.put("memberCount", dao.getAllCount(column, keyWord));
		return map;
		
	}

}
