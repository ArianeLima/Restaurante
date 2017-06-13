package Apresentação;

import java.sql.SQLException;
import java.util.Scanner;

import Negocio.Bebida;
import Negocio.Cliente;
import Negocio.Comanda;
import Negocio.Garcom;
import Negocio.Prato;


public class GeraMenu {
	private int opcao;
	private Scanner dados;
	public void mostrarMenu() throws SQLException{
		while(true){
			Scanner get = new Scanner(System.in);
			System.out.println("========== MENU =========");
			System.out.println("1) Inserir Bebida");
			System.out.println("2) Inserir Prato");
			System.out.println("3) Inserir Cliente");
			System.out.println("4) Inserir Garcom");
			System.out.println("5) Inserir Comanda");
			System.out.println("6) Deletar Bebida");
			System.out.println("7) Deletar Prato");
			System.out.println("8) Deletar Cliente");
			System.out.println("9) Deletar Garcom");
			System.out.println("10) Deletar Comanda");
			System.out.println("11) Atualizar Bebida");
			System.out.println("12) Atualizar Prato");
			System.out.println("13) Atualizar Cliente");
			System.out.println("14) Atualizar Garcom");
			System.out.println("15) Atualizar Comanda");
			System.out.println("16) Listar Bebida");
			System.out.println("17) Listar Prato");
			System.out.println("18) Listar Cliente");
			System.out.println("19) Listar Garcom");
			System.out.println("20) Listar Comanda");
			System.out.println("DIGITE UMA OPCAO: ");
			
			opcao = get.nextInt();
			if(opcao == 1){
				Scanner dados = new Scanner(System.in);
				System.out.println("Digite os dados da Bebida");
				Bebida b = new Bebida();
				System.out.println("Digite o ID: ");
				b.setId(dados.nextInt());
				System.out.println("Digite o Marca: ");
				b.setMarca(dados.next());
				System.out.println("Digite o Tipo: ");
				b.setTipo(dados.next());
				System.out.println("Digite o Preco: ");
				b.setPreco(dados.nextDouble());
				System.out.println("Digite o Volume: ");
				b.setVolume(dados.nextInt());
				b.inserir();
			}
			else if(opcao == 2){
				dados = new Scanner(System.in);
				System.out.println("Digite os dados do Prato");
				Prato p = new Prato();
				System.out.println("Digite o ID: ");
				p.setId(dados.nextInt());
				System.out.println("Digite o Nome: ");
				p.setNome(dados.next());
				System.out.println("Digite o Preco: ");
				p.setValor(dados.nextDouble());
				p.inserir();
			}
			else if(opcao == 3){
				dados = new Scanner(System.in);
				System.out.println("Digite os dados do Cliente");
				Cliente c = new Cliente();
				System.out.println("Digite o CPF: ");
				c.setCpf(dados.nextInt());
				System.out.println("Digite o Nome: ");
				c.setNome(dados.next());
				c.inserir();
			}
			else if(opcao == 4){
				dados = new Scanner(System.in);
				System.out.println("Digite os dados do Garcom");
				Garcom g = new Garcom();
				System.out.println("Digite o CPF: ");
				g.setCpf(dados.nextInt());
				System.out.println("Digite o Nome: ");
				g.setNome(dados.next());
				g.inserir();
			}
			else if(opcao == 5){
				dados = new Scanner(System.in);
				System.out.println("Digite os dados da Comanda");
				Comanda c = new Comanda();
				System.out.println("Digite o numero da Comanda");
				c.setNumero(dados.nextInt());
				System.out.println("Digite os dados do Cliente da Comanda");
				System.out.println("Digite o CPF: ");
				c.getCliente().setCpf(dados.nextInt());
				System.out.println("Digite o Nome: ");
				c.getCliente().setNome(dados.next());
				c.getCliente().inserir();
				System.out.println("Digite os dados do Garcom da Comanda");
				System.out.println("Digite o CPF: ");
				c.getGarcom().setCpf(dados.nextInt());
				System.out.println("Digite o Nome: ");
				c.getGarcom().setNome(dados.next());
				c.getGarcom().inserir();
				int quant;
				System.out.println("Digite a quantidade de bebidas da Comanda");
				quant = dados.nextInt();
				for (int i = 0; i < quant; i++) {
					System.out.println("Digite os dados da Bebida");
					Bebida b = new Bebida();
					System.out.println("Digite o ID: ");
					b.setId(dados.nextInt());
					System.out.println("Digite o Marca: ");
					b.setMarca(dados.next());
					System.out.println("Digite o Tipo: ");
					b.setTipo(dados.next());
					System.out.println("Digite o Preco: ");
					b.setPreco(dados.nextDouble());
					System.out.println("Digite o Volume: ");
					b.setVolume(dados.nextInt());
					c.getVetBebida().add(b);
				}
				System.out.println("Digite a quantidade de pratos da Comanda");
				quant = dados.nextInt();
				for (int j = 0; j < quant; j++) {
					System.out.println("Digite os dados do Prato");
					Prato p = new Prato();
					System.out.println("Digite o ID: ");
					p.setId(dados.nextInt());
					System.out.println("Digite o Nome: ");
					p.setNome(dados.next());
					System.out.println("Digite o Preco: ");
					p.setValor(dados.nextDouble());
					c.getVetPrato().add(p);
				}
				c.inserir();
			}
			else if(opcao == 6){
				Bebida b = new Bebida();
				dados = new Scanner(System.in);
				System.out.println("Digite o ID da Bebida que deseja deletar");
				b.deletar(dados.nextInt());
			}
			else if(opcao == 7){
				Prato p = new Prato();
				dados = new Scanner(System.in);
				System.out.println("Digite o ID do Prato que deseja deletar");
				p.deletar(dados.nextInt());
			}
			else if(opcao == 8){
				Cliente c = new Cliente();
				dados = new Scanner(System.in);
				System.out.println("Digite o CPF do Cliente que deseja deletar");
				c.deletar(dados.nextInt());
			}
			else if(opcao == 9){
				Garcom g = new Garcom();
				dados = new Scanner(System.in);
				System.out.println("Digite o CPF do Garcom que deseja deletar");
				g.deletar(dados.nextInt());
			}
			else if(opcao == 10){
				dados = new Scanner(System.in);
				System.out.println("Digite o numero da comanda que deseja deletar, lembrando que deletando uma "
						+ "comanda voce deleta as bebidas e os pratos que estao nessa comanda");
				Comanda c = new Comanda();
				c.deletar(dados.nextInt());
			}
			else if(opcao == 11){
				Scanner dados = new Scanner(System.in);
				System.out.println("Digite os dados da Bebida para ser atualizada");
				Bebida b = new Bebida();
				System.out.println("Digite o ID: ");
				b.setId(dados.nextInt());
				System.out.println("Digite o Marca: ");
				b.setMarca(dados.next());
				System.out.println("Digite o Tipo: ");
				b.setTipo(dados.next());
				System.out.println("Digite o Preco: ");
				b.setPreco(dados.nextDouble());
				System.out.println("Digite o Volume: " +b.getPreco());
				b.setVolume(dados.nextInt());
				b.atualizar();
			}
			else if(opcao == 12){
				dados = new Scanner(System.in);
				System.out.println("Digite os dados do Prato");
				Prato p = new Prato();
				System.out.println("Digite o ID: ");
				p.setId(dados.nextInt());
				System.out.println("Digite o Nome: ");
				p.setNome(dados.next());
				System.out.println("Digite o Preco: ");
				p.setValor(dados.nextDouble());
				p.atualizar();
			}
			else if(opcao == 13){
				dados = new Scanner(System.in);
				System.out.println("Digite os dados do Cliente");
				Cliente c = new Cliente();
				System.out.println("Digite o CPF: ");
				c.setCpf(dados.nextInt());
				System.out.println("Digite o Nome: ");
				c.setNome(dados.next());
				c.atualizar();
			}
			else if(opcao == 14){
				dados = new Scanner(System.in);
				System.out.println("Digite os dados do Garcom");
				Garcom g = new Garcom();
				System.out.println("Digite o CPF: ");
				g.setCpf(dados.nextInt());
				System.out.println("Digite o Nome: ");
				g.setNome(dados.next());
				g.atualizar();
			}
			else if(opcao == 15){
				dados = new Scanner(System.in);
				System.out.println("Digite os dados da Comanda que vai ser atualizada");
				Comanda c = new Comanda();
				System.out.println("Digite o numero da Comanda");
				c.setNumero(dados.nextInt());
				System.out.println("Digite os dados do Cliente da Comanda");
				System.out.println("Digite o CPF: ");
				c.getCliente().setCpf(dados.nextInt());
				System.out.println("Digite o Nome: ");
				c.getCliente().setNome(dados.next());
				c.getCliente().inserir();
				System.out.println("Digite os dados do Garcom da Comanda");
				System.out.println("Digite o CPF: ");
				c.getGarcom().setCpf(dados.nextInt());
				System.out.println("Digite o Nome: ");
				c.getGarcom().setNome(dados.next());
				c.getGarcom().inserir();
				int quant;
				System.out.println("Digite a quantidade de bebidas da Comanda");
				quant = dados.nextInt();
				for (int i = 0; i < quant; i++) {
					System.out.println("Digite os dados da Bebida");
					Bebida b = new Bebida();
					System.out.println("Digite o ID: ");
					b.setId(dados.nextInt());
					System.out.println("Digite o Marca: ");
					b.setMarca(dados.next());
					System.out.println("Digite o Tipo: ");
					b.setTipo(dados.next());
					System.out.println("Digite o Preco: ");
					b.setPreco(dados.nextDouble());
					
					System.out.println("Digite o Volume: ");
					b.setVolume(dados.nextInt());
					c.getVetBebida().add(b);
				}
				c.atualizar();
			}
			else if(opcao == 16){
				Bebida b = new Bebida();
				 System.out.println("Todas as bebidas!!!");
  
				 for (Bebida bebida : b.listar()) {	
			       	System.out.println(bebida);
				}
			}
			else if(opcao == 17){
				Prato p = new Prato();
				System.out.println("Todas os pratos!!!");
				  
				for (Prato prato : p.listar()) {	
			       	System.out.println(prato);
				}		
			}
			else if(opcao == 18){
				Cliente c = new Cliente();
				System.out.println("Todas os clientes!!!");
				  
				for (Cliente cliente : c.listar()) {	
			       	System.out.println(cliente);
				}
			}
			else if(opcao == 19){
				Garcom g = new Garcom();
				System.out.println("Todas os garcons!!!");
				  
				for (Garcom garcom : g.listar()) {	
			       	System.out.println(garcom);
				}
			}
			else if(opcao == 20){
				Comanda co = new Comanda();
				System.out.println("Todas as comandas!!!");
				  
				for (Comanda comanda : co.listar()) {	
			       	System.out.println(comanda);
				}
			}
		
		}
	}
}
