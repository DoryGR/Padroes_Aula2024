package view;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import model.dao.CursoDAO;
import model.dao.FactoryDAO;
import model.entities.Curso;

public class TelaCurso {

	static CursoDAO cursoDAO = FactoryDAO.createCursoDAO();
	
	public static Scanner menuCurso(Scanner console) throws InterruptedException {
		
		int opcao = 0;
		do {
			System.out.println("\n\n");
			System.out.println("    ###   Tela: Curso     ###");
			System.out.println("    =========================");
			System.out.println("    |     1 - Cadastrar     |");
			System.out.println("    |     2 - Listar        |");
			System.out.println("    |     3 - Alterar       |");
			System.out.println("    |     4 - Excluir       |");
			System.out.println("    |     0 - Retornar      |");
			System.out.println("    =========================");
			System.out.print("    Opção -> ");
			opcao = console.nextInt();
			console.nextLine();
			
			switch (opcao) {
			case 1: console = cadastrar(console);
					break;
			case 2:	console = listar(console);
					break;
			case 3: //console = alterar(console);
					break;
			case 4: //console = excluir(console);
					break;
			case 0:	console = TelaPrincipal.menuPrincipal(console);
					break;
			default:
				System.out.println("Opção inválida!");
				TimeUnit.SECONDS.sleep(1);
			}
		} while (opcao != 0);
		return console;
	}
	
	private static Scanner listar(Scanner console) {
		
		List<Curso> cursos = cursoDAO.findAll();
		System.out.println("\n\n");
		System.out.println("    ###   Curso-Lista ###");
		System.out.println(" #id  \t  Nome ###");
		
		for ( Curso c : cursos ) {
			System.out.println( c.getIdcurso() + "\t" + c.getNomeCurso());
		}
		console.nextLine();
		return console;
	}

	private static Scanner cadastrar(Scanner console) {
		
		Curso c = new Curso();
		
		System.out.println("\n\n");
		System.out.println("    ###   Curso-Cadastrar ###");
		System.out.println("    =========================");
		
		System.out.print("    |     Nome: "); 
	    c.setNomeCurso(console.nextLine());
		cursoDAO.insert(c);
		console.nextLine();
	    
	    System.out.println("    =========================");
	    
		return console;
	}

}
