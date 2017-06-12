package Negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Persistencia.Conexao;
import Persistencia.DAO;

public class Prato implements DAO {

	private int id;
    private double valor;
    private String nome;
    public int getId() {
		return id;
	}
    public void setId(int id) {
		this.id = id;
	}
	public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @Override
	public String toString(){
		return "Id: " +this.id + "\n" + "Nome: " + this.nome + "\n" + "Valor: " + this.valor + "\n";
	}
    
    public boolean inserir() throws SQLException{
  		Connection conexao = new Conexao().getConexao();
  		PreparedStatement insert = (PreparedStatement) conexao.prepareStatement("INSERT INTO Prato (nome, valor, id) VALUES ( ?, ?, ?);");
  		insert.setString(1, this.getNome());
  		insert.setDouble(2, this.getValor());
  		insert.setInt(3, this.getId());
  		insert.executeUpdate();
  		conexao.close();
  		return true;
  	}
	
	public boolean deletar(int id) throws SQLException {
		Connection conexao = new Conexao().getConexao();
		PreparedStatement deletar = conexao.prepareStatement("DELETE FROM Prato WHERE id = ?;");
		deletar.setInt(1, id);
		deletar.executeUpdate();	
		conexao.close();
		return true;
	}
	public ArrayList<Prato> listar() throws SQLException {
		Connection conexao = new Conexao().getConexao();
		ResultSet resultSet = conexao.prepareStatement("SELECT * FROM Prato "+";").executeQuery();
		ArrayList<Prato> pratos = new ArrayList<Prato>();
		
		while(resultSet.next()){
			Prato novo = new Prato();
			novo.setId(resultSet.getInt("id"));
			novo.setNome(resultSet.getString("nome"));
			novo.setValor(resultSet.getInt("valor"));
			pratos.add(novo);
		}
		conexao.close();
		return pratos;
	}
	public boolean atualizar() throws SQLException {
		Connection conexao = new Conexao().getConexao();

		PreparedStatement atualizar = conexao.prepareStatement("UPDATE Prato SET nome =?, "
						+ "valor=? where id=?;");
		atualizar.setString(1, this.getNome());
		atualizar.setDouble(2, this.getValor());
		atualizar.setInt(3, this.getId());
		atualizar.executeUpdate();
		conexao.close();
		return true;
	}
      
  
    
}
