package Negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Persistencia.Conexao;
import Persistencia.DAO;


public class Bebida implements DAO {	
	private int id;
	private String marca;
	private String tipo;
	private int volume;
	private double preco;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	@Override
	public String toString(){
		return "Id: " +this.id + "\n" + "Marca: " + this.marca + "\n" + "Tipo: " + this.tipo + "\n" + "Volume: "  + this.volume + "\n" + "Preço: " + this.preco;
	}
	public boolean inserir() throws SQLException{
		Connection conexao = new Conexao().getConexao();
		PreparedStatement insert = (PreparedStatement) conexao.prepareStatement("INSERT INTO Bebida (marca, tipo, volume, preco, id) VALUES ( ?, ?, ?, ?, ?);");
		insert.setString(1, this.getMarca());
		insert.setString(2, this.getTipo());
		insert.setInt(3,this.getVolume());
		insert.setDouble(4, this.getPreco());
		insert.setInt(5, this.getId());
		insert.executeUpdate();
		conexao.close();
		return true;
	}

	public boolean deletar(int id) throws SQLException {
		Connection conexao = new Conexao().getConexao();
		PreparedStatement deletar = conexao.prepareStatement("DELETE FROM Bebida WHERE id = ?;");
		deletar.setInt(1, id);
		deletar.executeUpdate();	
		conexao.close();
		return true;
	}

	public ArrayList<Bebida> listar() throws SQLException {
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

	public boolean atualizar() throws SQLException {
		Connection conexao = new Conexao().getConexao();

		PreparedStatement atualizar = conexao.prepareStatement("UPDATE Bebida SET marca =?, "
						+ "volume=?, preco=?, tipo=? where id=?;");
		atualizar.setString(1, this.getMarca());
		atualizar.setInt(2, this.getVolume());
		atualizar.setDouble(3, this.getPreco());
		atualizar.setString(4, this.getTipo());
		atualizar.setInt(5, this.getId());
		atualizar.executeUpdate();
		conexao.close();
		return true;
	}

}
