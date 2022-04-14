package integrador_1;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
	
	void createTable() throws SQLException;
    
    Optional<T> get(int id) throws SQLException;
    
    List<T> getAll() throws SQLException;
    
    void save(String[] t) throws SQLException;
    
}