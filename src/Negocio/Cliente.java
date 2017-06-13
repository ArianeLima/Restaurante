package Negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Persistencia.Conexao;
import Persistencia.DAO;

public class Cliente implements DAO{
	private int cpf;
	private String nome;
	public int getCpf() {
			return cpf;
		}
    
	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public boolean inserir() throws SQLException{
		Connection conexao = new Conexao().getConexao();
		PreparedStatement insert = (PreparedStatement) conexao.prepareStatement("INSERT INTO Cliente (nome, cpf) VALUES ( ?, ?);");
		insert.setString(1, this.getNome());
		insert.setInt(2, this.getCpf());
		insert.executeUpdate();
		conexao.close();
		return true;
	}	
	@Override
	public String toString(){
		return "CPF: " +this.cpf + "\n" + "Nome: " + this.nome + "\n" ;
	}
	public boolean deletar(int cpf) throws SQLException {
		Connection conexao = new Conexao().getConexao();
		PreparedStatement deletar = conexao.prepareStatement("DELETE FROM Cliente WHERE cpf = ?;");
		deletar.setInt(1, cpf);
		deletar.executeUpdate();	
		conexao.close();
		return true;
	}

	public ArrayList<Cliente> listar() throws SQLException {
		Connection conexao = new Conexao().getConexao();
		ResultSet resultSet = conexao.prepareStatement("SELECT * FROM Cliente "+";").executeQuery();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		while(resultSet.next()){
			Cliente novo = new Cliente();
			novo.setCpf(resultSet.getInt("cpf"));
			novo.setNome(resultSet.getString("nome"));
			clientes.add(novo);
		}
		conexao.close();
		return clientes;
	}

	public boolean atualizar() throws SQLException {
		Connection conexao = new Conexao().getConexao();

		PreparedStatement atualizar = conexao.prepareStatement("UPDATE Cliente SET nome =? "
						+ " where cpf=?;");
		atualizar.setString(1, this.getNome());
		atualizar.setInt(2,this.getCpf() );
		atualizar.executeUpdate();
		conexao.close();
		return true;
	}
    
}
