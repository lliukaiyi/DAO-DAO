package cn.mldn.oeacle.factory;

import cn.mldn.oeacle.proxy.ServiceProxy;

public class ServiceFactory {
	private ServiceFactory() {
		// TODO Auto-generated constructor stub
	}
	public static <T>T getInstance(Class<T> cls){
		try {
			return new ServiceProxy().bind(cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
