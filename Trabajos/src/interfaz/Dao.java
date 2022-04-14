package interfaz;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
	
	void createTable() throws SQLException;
    
    void save(String[] t) throws SQLException;
    
}