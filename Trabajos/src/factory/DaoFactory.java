package factory;

public abstract class DaoFactory {
	
	public static final int MYSQL_JDBC = 1;
	
	public static DaoFactory getDaoFactory(int whichFactory) {
		switch (whichFactory) {
			case MYSQL_JDBC : return new MySqlJDBC();
			default : return null;
		}
	}

}
