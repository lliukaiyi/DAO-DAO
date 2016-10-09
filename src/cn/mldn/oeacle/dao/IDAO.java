package cn.mldn.oeacle.dao;

import java.util.List;
import java.util.Set;

public interface IDAO<K,V> {
	public boolean doCreate(V vo)throws Exception;
	public boolean doUpdate(V vo)throws Exception;
	public boolean doRemoveBatch(Set<K> ids)throws Exception;
	public V findById(K id)throws Exception;
	public V findByPhone(K phone)throws Exception;
	public List<V> findAll()throws Exception;
	public List<V> findAllSpliu(Integer currentPage,Integer lineSize)throws Exception;
	public List<V> findAllSpliu(String column,String keyWord,Integer currentPage,Integer lineSize)throws Exception;
	public Long getAllCount()throws Exception;
	
	
}
