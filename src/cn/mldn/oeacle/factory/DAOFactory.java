package cn.mldn.oeacle.factory;

public class DAOFactory {
	private DAOFactory() {
		// TODO Auto-generated constructor stub
	}
	public static <T>T getInstance(Class<T>cls){
		try {
			return cls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
