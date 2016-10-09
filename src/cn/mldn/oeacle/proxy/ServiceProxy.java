package cn.mldn.oeacle.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.mldn.oeacle.dbc.DatabaseConnection;

public class ServiceProxy implements InvocationHandler {
	private Object target;
	public <T>T bind(Class<T> cls){
		try {
			this.target = cls.newInstance();
			return (T)Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			Object ret = null;
			String methodName = method.getName();
			if(methodName.startsWith("add")||methodName.startsWith("edit")||methodName.startsWith("remove")){
				try {
					DatabaseConnection.getConnection().getAutoCommit();
					ret = method.invoke(this.target, args);
					DatabaseConnection.getConnection().commit();
				} catch (Exception e) {
					DatabaseConnection.getConnection().rollback();
				}
			}else{
				ret= method.invoke(this.target, args);
			}
			return ret;
		} catch (Exception e) {
			throw e;
		}finally{
			DatabaseConnection.colse();
		}
	}

}
