package interfaz;

import java.sql.SQLException;

import org.apache.commons.csv.CSVParser;

public interface Dao<T> {
	
	void createTable() throws SQLException;
    
    void save(CSVParser t) throws SQLException;
    
}