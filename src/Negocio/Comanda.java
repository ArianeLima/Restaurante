package Negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Persistencia.Conexao;
import Persistencia.DAO;


public class Comanda implements DAO{
    private int numero;
    private Cliente cliente;
    private Garcom garcom;
    private ArrayList<Bebida> vetBebida;
    private ArrayList<Prato> vetPrato;
    

    public Comanda() {
    	this.cliente = new Cliente();
    	this.garcom = new Garcom();
        this.vetBebida =  new ArrayList<Bebida>();
        this.vetPrato = new ArrayList<Prato>();
      
    }

	public void setGarcom(Garcom garcom) {
		this.garcom = garcom;
	}


	public void setVetBebida(ArrayList<Bebida> vetBebida) {
		this.vetBebida = vetBebida;
	}


	public void setVetPrato(ArrayList<Prato> vetPrato) {
		this.vetPrato = vetPrato;
	}


	public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Garcom getGarcom() {
        return garcom;
    }

    
    public ArrayList<Bebida> getVetBebida() {
        return vetBebida;
    }

    
    public ArrayList<Prato> getVetPrato() {
        return vetPrato;
    }
    @Override
	public String toString(){
    	String resposta = "";
    	for (int i = 0; i < this.getVetBebida().size(); i++) {
			resposta+= "Id: " +this.getVetBebida().get(i).getId() + "\n" + "Marca: " + this.getVetBebida().get(i).getMarca()
					+ "\n" + "Tipo: " + this.getVetBebida().get(i).getTipo() + "\n" + "Volume: "  + this.getVetBebida().get(i).getVolume()+ "\n" 
					+ "Preço: " + this.getVetBebida().get(i).getPreco() + "\n";
		}
    	String resultado = "";
    	for (int j = 0; j < this.getVetPrato().size(); j++) {
			resultado += "Id: " +this.getVetPrato().get(j).getId() + "\n" + "Nome: " + this.getVetPrato().get(j).getNome()
					+ "\n" + "Valor: " + this.getVetPrato().get(j).getValor() + "\n";
		}
    	
    	
		return "Numero: " +this.numero + "\n" + "Cliente: " + this.cliente + "\n" + "Garcom: " + this.garcom+ "\n"
		+ "Bebidas da Comanda: " +"\n" + resposta + "\n" + "Comidas da Comanda: " + "\n" + resultado;
	}

	public boolean inserir() throws SQLException{
		Connection conexao = new Conexao().getConexao();
		PreparedStatement insert = (PreparedStatement) conexao.prepareStatement("INSERT INTO Comanda (cpfCliente, cpfGarcom, numero) VALUES ( ?, ?, ?);");
		insert.setInt(1, this.getCliente().getCpf());
		insert.setInt(2,this.getGarcom().getCpf());
		insert.setInt(3, this.getNumero());
		insert.executeUpdate();
		int i;
		for( i = 0 ; i < this.getVetBebida().size(); i++){
			PreparedStatement insert1 = (PreparedStatement) conexao.prepareStatement("INSERT INTO Bebida (marca, tipo, volume, preco, idComanda) VALUES ( ?, ?, ?, ?, ?);");
			insert1.setString(1, this.getVetBebida().get(i).getMarca());
			insert1.setString(2, this.getVetBebida().get(i).getTipo());
			insert1.setInt(3,this.getVetBebida().get(i).getVolume());
			insert1.setDouble(4, this.getVetBebida().get(i).getPreco());
			insert1.setDouble(5, this.getNumero());
			insert1.executeUpdate();
			
		}
		for(i = 0 ; i < this.getVetPrato().size(); i++){
			PreparedStatement insert2 = (PreparedStatement) conexao.prepareStatement("INSERT INTO Prato (nome, valor, idComanda) VALUES ( ?, ?, ?);");
			insert2.setString(1, this.getVetPrato().get(i).getNome());
			insert2.setDouble(2, this.getVetPrato().get(i).getValor());
			insert2.setDouble(3, this.getNumero());
			insert2.executeUpdate();
			
		}
		conexao.close();
		return true;
	}


	public boolean deletar(int numero) throws SQLException {
		Connection conexao = new Conexao().getConexao();
		PreparedStatement deletar = conexao.prepareStatement("DELETE FROM Comanda WHERE numero = ?;");
		deletar.setInt(1, numero);
		deletar.executeUpdate();	
		conexao.close();
		return true;
	}


	public ArrayList<Comanda> listar() throws SQLException {
		Connection conexao = new Conexao().getConexao();
		ResultSet resultSet = conexao.prepareStatement("SELECT * FROM Comanda "+";").executeQuery();
		ArrayList<Comanda> comandas = new ArrayList<Comanda>();
		int id;
		while(resultSet.next()){
			Comanda novo = new Comanda();
			Cliente novoCliente = new Cliente();
			Garcom novoGarcom = new Garcom();
			novo.setNumero(resultSet.getInt("numero"));
			
			id = resultSet.getInt("cpfCliente");
			ResultSet resultSet1 = conexao.prepareStatement("SELECT * FROM Cliente where cpf = "+id+";").executeQuery();
			resultSet1.next();
			novoCliente.setCpf(resultSet1.getInt("cpf"));
			novoCliente.setNome(resultSet1.getString("nome"));
			novo.setCliente(novoCliente);
			
			id = resultSet.getInt("cpfGarcom");
			ResultSet resultSet2 = conexao.prepareStatement("SELECT * FROM Garcom where cpf = "+id+";").executeQuery();
			resultSet2.next();
			novoGarcom.setCpf(resultSet2.getInt("cpf"));
			novoGarcom.setNome(resultSet2.getString("nome"));
			novo.setGarcom(novoGarcom);
			
			id = resultSet.getInt("numero");
			ResultSet resultSet3 = conexao.prepareStatement("SELECT * FROM Bebida where idComanda = "+id+";").executeQuery();
			ArrayList<Bebida> bebs = new ArrayList<Bebida>();
			
			while(resultSet3.next()){
				Bebida novoBebida = new Bebida();
				novoBebida.setId(resultSet3.getInt("id"));
				novoBebida.setMarca(resultSet3.getString("marca"));
				novoBebida.setPreco(resultSet3.getInt("preco"));
				novoBebida.setTipo(resultSet3.getString("tipo"));
				novoBebida.setVolume(resultSet3.getInt("volume"));
				bebs.add(novoBebida);
			}
			novo.setVetBebida(bebs);
			
			ResultSet resultSet4 = conexao.prepareStatement("SELECT * FROM Prato where idComanda = "+id+";").executeQuery();
			ArrayList<Prato> pratos = new ArrayList<Prato>();
			
			while(resultSet4.next()){
				Prato novoPrato = new Prato();
				novoPrato.setId(resultSet4.getInt("id"));
				novoPrato.setNome(resultSet4.getString("nome"));
				novoPrato.setValor(resultSet4.getInt("valor"));
				pratos.add(novoPrato);
				
			}
			novo.setVetPrato(pratos);
			
			comandas.add(novo);
		}
		conexao.close();
		return comandas;
	}

	public boolean atualizar() throws SQLException {
		Connection conexao = new Conexao().getConexao();
		PreparedStatement atualizar = conexao.prepareStatement("UPDATE Comanda SET cpfCliente =?, "
				+ "cpfGarcom=? where numero=?;");
		atualizar.setInt(1, this.getCliente().getCpf());
		atualizar.setInt(1, this.getGarcom().getCpf());
		int i;
		for( i = 0 ; i < this.getVetBebida().size(); i++){
			PreparedStatement atualizar1 = conexao.prepareStatement("UPDATE Bebida SET marca =?, "
							+ "volume=?, preco=?, tipo=?, id=? where numero=?;");
			atualizar1.setString(1, this.getVetBebida().get(i).getMarca());
			atualizar1.setInt(2, this.getVetBebida().get(i).getVolume());
			atualizar1.setDouble(3, this.getVetBebida().get(i).getPreco());
			atualizar1.setString(4, this.getVetBebida().get(i).getTipo());
			atualizar1.setInt(5, this.getVetBebida().get(i).getId());
			atualizar1.setInt(6, this.getNumero());
			atualizar1.executeUpdate();
		}
		for( i = 0 ; i < this.getVetPrato().size(); i++){
			PreparedStatement atualizar2 = conexao.prepareStatement("UPDATE Prato SET nome =?, "
							+ "valor=?, id=? where numero=?;");
			atualizar2.setString(1, this.getVetPrato().get(i).getNome());
			atualizar2.setDouble(2, this.getVetPrato().get(i).getValor());
			atualizar2.setInt(3, this.getVetPrato().get(i).getId());
			atualizar2.setInt(4, this.getNumero());
			atualizar2.executeUpdate();
		}
		conexao.close();
		return true;
	}
    
    
    
}
