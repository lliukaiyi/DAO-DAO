package cn.mldn.oeacle.dao;

import cn.mldn.oeacle.vo.Member;

public interface IMemberDAO extends IDAO<String,Member>{
	public Long getAllCount(String column,String keyWord)throws Exception;

}
