package Negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Persistencia.Conexao;

public class Garcom implements DAO{
	private int cpf;
	private String nome;
	
	
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public int getCpf() {
		return cpf;
	}
	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @Override
	public String toString(){
		return "CPF: " +this.cpf + "\n" + "Nome: " + this.nome + "\n" ;
	}
    public boolean inserir() throws SQLException{
		Connection conexao = new Conexao().getConexao();
		PreparedStatement insert = (PreparedStatement) conexao.prepareStatement("INSERT INTO Garcom (nome, cpf) VALUES ( ?, ?);");
		insert.setString(1, this.getNome());
		insert.setInt(2, this.getCpf());
		insert.executeUpdate();
		conexao.close();
		return true;
	}
	public boolean deletar(int cpf) throws SQLException {
		Connection conexao = new Conexao().getConexao();
		PreparedStatement deletar = conexao.prepareStatement("DELETE FROM Garcom WHERE cpf = ?;");
		deletar.setInt(1, cpf);
		deletar.executeUpdate();	
		conexao.close();
		return true;
	}
	public ArrayList listar() throws SQLException {
		Connection conexao = new Conexao().getConexao();
		ResultSet resultSet = conexao.prepareStatement("SELECT * FROM Garcom "+";").executeQuery();
		ArrayList<Garcom> garcom = new ArrayList<Garcom>();
		
		while(resultSet.next()){
			Garcom novo = new Garcom();
			novo.setCpf(resultSet.getInt("cpf"));
			novo.setNome(resultSet.getString("nome"));
			garcom.add(novo);
		}
		conexao.close();
		return garcom;
	}
	public boolean atualizar() throws SQLException {
		Connection conexao = new Conexao().getConexao();

		PreparedStatement atualizar = conexao.prepareStatement("UPDATE Garcom SET nome =?, "
						+ " where cpf=?;");
		atualizar.setString(1, this.getNome());
		atualizar.setInt(2,this.getCpf() );
		atualizar.executeUpdate();
		conexao.close();
		return true;
	}
    
    
}
