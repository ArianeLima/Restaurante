package Persistencia;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;


public class Conexao {
	private String host;
	private String usuario;
	private String senha;
	private String banco;
	
	//construtor
	public Conexao(){
		this.banco = "Restaurante";
		this.host = "127.0.0.1";
		this.usuario = "root";
		this.senha = "Ahcl@1211";
	}
	public Connection getConexao(){
		String url = "jdbc:mysql://"+ this.host + "/" + this.banco;
		
		try{
			return DriverManager.getConnection(url, usuario, senha);
		}
		catch(SQLException ex){
			System.out.println("Deu ruim na conexao");
			ex.printStackTrace();
		//	Logger.getLogger(Conexao.class.getName().log(Level.SEVERE,))	
			
		}
		return null;
	}
}
