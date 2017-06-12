package Persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Negocio.Bebida;

public class BebidaDAO {

	public Bebida listarPorId(int id) throws SQLException{
		Connection conexao = new Conexao().getConexao();
		ResultSet resultSet = conexao.prepareStatement("SELECT * FROM Bebida where id = "+id+";").executeQuery();
		Bebida bebs = new Bebida();
		resultSet.first();
		bebs.setId(resultSet.getInt("id"));
		bebs.setMarca(resultSet.getString("marca"));
		bebs.setPreco(resultSet.getInt("preco"));
		bebs.setTipo(resultSet.getString("tipo"));
		bebs.setVolume(resultSet.getInt("volume"));
		conexao.close();
		return bebs;
	}
	public ArrayList<Bebida> listarTodos() throws SQLException{
		Connection conexao = new Conexao().getConexao();
		ResultSet resultSet = conexao.prepareStatement("SELECT * FROM Bebida "+";").executeQuery();
		ArrayList<Bebida> bebs = new ArrayList<Bebida>();
		
		while(resultSet.next()){
			Bebida novo = new Bebida();
			novo.setId(resultSet.getInt("id"));
			novo.setMarca(resultSet.getString("marca"));
			novo.setPreco(resultSet.getInt("preco"));
			novo.setTipo(resultSet.getString("tipo"));
			novo.setVolume(resultSet.getInt("volume"));
			bebs.add(novo);
		}
		conexao.close();
		return bebs;
	}
	public Boolean inserir(Bebida bebida) throws SQLException{
		Connection conexao = new Conexao().getConexao();
		PreparedStatement insert = (PreparedStatement) conexao.prepareStatement("INSERT INTO Bebida (marca, tipo, volume, preco) VALUES ( ?, ?, ?, ?);").executeQuery();
		insert.setString(1, bebida.getMarca());
		insert.setString(2, bebida.getTipo());
		insert.setInt(3,bebida.getVolume());
		insert.setDouble(4, bebida.getPreco());
		return true;
	}
	
}
