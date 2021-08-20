import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.*;
import java.io.IOException;


public class Leitura {
	private Leitura(){}//apenas para impedir de instanciarem
	
  //retorna matriz com respostas de todos os alunos
	public static int[][] getRespostasAlunos() {
		int[][] respostasProvas = new int[500][45];
		
		for (int i = 0; i < 500;i++) {
			respostasProvas[i] = getRespostasAluno(i);
		}
		
		return respostasProvas;
	}
	
	//retorna array de repostas do aluno, dado o ID
  public static int[] getRespostasAluno(int idAluno) {
		char[] provaChar = new char[45];
		
		try {
			String prova = getProvaAluno(idAluno);
			provaChar = prova.replace(" ","").toCharArray();
		} catch(IOException e) { 
			System.out.println("Nao foi possivel ler o arquivo");
		}
		
		return charToIntArray(provaChar);
	}
	
	private static int[] charToIntArray(char[] array){
		
		int[] provaInt = new int[array.length];
		int i = 0;
		
		for (char ch: array) {
			provaInt[i] = Character.getNumericValue(ch);
			i++;
		}
		
		return provaInt;
		
	}
	
	private static String getProvaAluno(int nAluno) throws IOException {
		
		Stream<String> lines = Files.lines(Paths.get("resp05.txt"));
		String line = lines.skip(nAluno + 5).findFirst().get();
		lines.close();
		
		return line;
	}
}