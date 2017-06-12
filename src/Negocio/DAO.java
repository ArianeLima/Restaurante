package Negocio;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO {
	public boolean inserir() throws SQLException;
    public boolean deletar (int id) throws SQLException;
    public ArrayList listar() throws SQLException;
    public boolean atualizar ()  throws SQLException;
}


