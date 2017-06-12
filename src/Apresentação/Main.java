package Apresentação;

import java.sql.SQLException;

import Negocio.*;
import Persistencia.BebidaDAO;
import Persistencia.Conexao;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		GeraMenu gera = new GeraMenu();
		gera.mostrarMenu();
		
	}

}
