import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Main {
  public static void main(String[] args) throws IOException{
     int quantidadeAlunos = 5;

    try{
    Estimar [] candidato = new Estimar[500];

    for(int  i = 0; i < quantidadeAlunos; i++){ 
    int[] gabarito = Leitura.getRespostasAluno(i);
    
    candidato[i] = new Estimar(-3,gabarito.length,gabarito);
    
    FileInputStream arquivo = new FileInputStream("itens.txt");
    InputStreamReader input = new InputStreamReader(arquivo);
    BufferedReader br = new BufferedReader(input);

    String linha;
    do{
    linha = br.readLine();
    if(linha != null){
    String[] palavras = linha.split(" ");

	  double a = (double) Double.parseDouble(palavras[1]);
	  double b = (double) Double.parseDouble(palavras[2]);
	  double c = (double) Double.parseDouble(palavras[3]);
    candidato[i].funcaoCCI(a,b,c);
   }


    } while(linha != null);
    
    
    candidato[i].estimarNota();
    candidato[i].imprimeTRI();
    //System.out.println("\t --  Aluno: " +(i));
    //candidato[i].obterMemoriaUsada();
    }
    }

    catch(IOException e) {
     e.printStackTrace();
     }

}
}