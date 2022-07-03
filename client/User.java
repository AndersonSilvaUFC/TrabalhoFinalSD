package client;

import model.Funcionario;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
	private static void printMenu(){
		System.out.println("#########################");
		System.out.println("Selecione uma operação: ");
		System.out.println("1. Cacular folha de pagamento");
		System.out.println("2. Caclcular bônus de férias");
		System.out.println("3. Pagar FGTS");
		System.out.println("0. Sair");
	}
	
	public static void main(String[] args) {
		Funcionario f1 = new Funcionario("Almir",10,3000,0,false,true,false);
		Funcionario f2 = new Funcionario("Anderson",8,2800,0,false,false,false);
		Funcionario f3 = new Funcionario("Luis",11,3500,0,false,true,false);

		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(f1);
		funcionarios.add(f2);
		funcionarios.add(f3);

		boolean sair = false;
		Scanner sc = new Scanner(System.in);
		String opcao, aux;
		double resposta;

		while(!sair) {
			RHManagerProxy proxy = new RHManagerProxy();
			printMenu();
			opcao = sc.nextLine();

			switch (opcao) {
				case "1":
					resposta = proxy.calculaFolhaDePagamento( funcionarios );
					System.out.println("Total da folha de pagamento: " + resposta );
					break;
				case "2":
					System.out.println("Selecione o funcionário: ");
					for(int i=1; i<=funcionarios.size(); i++){
						System.out.println(i+" - "+funcionarios.get(i-1).getNome());
					}
					opcao = sc.nextLine();
					try {
						int opcaoInt = Integer.parseInt(opcao);
						resposta = proxy.calculaBonusDeFerias( funcionarios.get(opcaoInt - 1) );
						System.out.println("Bônus de férias do funcionário "+ funcionarios.get(opcaoInt-1).getNome() +": "+ resposta);
					}catch (NumberFormatException e){
						break;
					}
					break;
				case "0":
					sair = true;
					break;
				default:
					opcao = "";
					System.out.println("Opção inválida");
					break;
			}
			proxy.close();
		}
	}

}
