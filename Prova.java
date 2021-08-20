import java.lang.Math;
import java.io.IOException;
import java.util.Arrays;

public class Prova implements InterfaceProva{
	
  protected double a;
  protected double b;
  protected double c;
  protected int tamanhoDaProva;
  protected double[][] parametros;
  protected double[][] resultados;
  protected double[] thetas;
  protected int indice;
  protected int coluna;
  protected int col;
  protected double theta;
  protected double beta = 0.1;//precisão do cálculo
  protected int ondeComeca;
  protected double t;//Onde comeca a testar os theta
  protected int []gabarito;
  
  



  /* Construtor da classe Prova
   *
   * Quantidade de interações são o tamanho que o vetor e linhas  * da matriz representa.
   */

	public Prova(int ondeComeca,int tamanhoDaProva, int []gabarito) {
	this.tamanhoDaProva = tamanhoDaProva;
  this.ondeComeca = ondeComeca;
  this.t = ondeComeca;
	this.parametros = new double[3][tamanhoDaProva];
  this.gabarito = new int[45];
  this.gabarito = gabarito;

  this.thetas = new double[(int)(ALCANCE/beta)];
	this.resultados = new double[(int)(ALCANCE/beta)][tamanhoDaProva];
	}






  // -----------Método que calcula a questão que Acertou------------//
	public void funcaoLogLikelihoodAcertou(double a, double b, double c) throws IOException{
  
  //System.out.println("\n ----------ACERTOU----------\n");

  try{

  int lin = 0;
	while(t < this.thetas.length){
  
  //Condição de parada, chegou no final das interações
  if(this.resultados.length == lin){
  this.t = ondeComeca;
  this.col += 1;
  return;
  }


  if(lin < this.thetas.length){//Preencher as linhas
  double resultado = Math.log(c + (1-c) * (Math.exp(a*(t-b)))/(1+Math.exp(a*(t-b))));

  this.resultados[lin][col] = resultado;
 
  this.thetas[lin] = t;//theta que foi testado
  //System.out.println("theta: "+ t+" L: " + lin+ " C: "+ col+" R: "+ resultado);
  }
  //Resetar as variáveis.
  lin += 1;
  t += beta;
	         }
	                  } 

catch(Exception e){
  System.out.println("Erro funcaoLogLikelihoodAcertou");
}
                           }






  // -----------Método que calcula a questão que Errou---------------//

	public void funcaoLogLikelihoodErrou(double a, double b, double c) throws IOException{
  //System.out.println("\n-----------ERROU-----------\n");

  try{

	int lin = 0;
 
	while(t < this.thetas.length){
 
   //Condição de parada, chegou no final das interações
  if(this.resultados.length == lin) {
  
  this.t = ondeComeca;
  this.col += 1;
  return;
  }

  if(lin < this.thetas.length){
  double resultado = Math.log(1-(c + (1-c) * (Math.exp(a*(t-b)))/(1+Math.exp(a*(t-b)))));

  this.resultados[lin][col] = resultado;
  this.thetas[lin] = t;

  //System.out.println("theta: "+ t+" L: " + lin+ " C: "+ col+" R: "+ resultado);
  }
  lin += 1;
  t += beta;
           }
           	      } 
  catch(Exception e){
    System.out.println("Erro funcaoLogLikelihoodErrou");
  }
                        }





  //------------- Função que chama o Pr(xi/θ) e 1-Pr(xi/θ)------------//


  public void funcaoCCI(double a, double b, double c) throws IOException{
  try{

  if(coluna < tamanhoDaProva){
	this.parametros[0][coluna] = a;
	this.parametros[1][coluna] = b;
	this.parametros[2][coluna] = c;
	coluna++;
	}
  
  //ln(P(Uji = 1/θj))
	if(this.gabarito[indice] == ACERTOU) funcaoLogLikelihoodAcertou(a,b,c);

	//ln(1 - P(Uji = 1/θj))
	else if(this.gabarito[indice] == ERROU) funcaoLogLikelihoodErrou(a,b,c);
	indice++;
	}
  catch(Exception e){
    System.out.println("Erro funcaoCCI");
  }
  }


  // ---------------------MÉTODOS AUXILIARES------------------------//

  public void reset(){
  indice = 0;
  col = 0;
  }
  
  public void comecaTheta(double theta){
  this.t = theta; 
  }

  public void acertos(){
  int quantidade = 0;
  for(int i = 0; i < this.gabarito.length; i++){
  if(this.gabarito[i] == ACERTOU){
  quantidade++;
  }
        }
  //System.out.println(quantidade);
             }


  // ----------------------IMPRIME-------------------------//
  
  public void imprimeTRI(){

	//System.out.println("\n\n--------- IMPRIMINDO RESULTADOS TRI ----------\n");

	//System.out.println("\nN° de Itens " + tamanhoDaProva);


	for(int i = 0; i < this.tamanhoDaProva; i++) {

	//System.out.println("\nQuestão " + (i+1));

	//System.out.println("\nParametro a"+(i+1)+": " + this.parametros[0][i]);
	//System.out.println("\nParametro b"+(i+1)+": " + this.parametros[1][i]);
	//System.out.println("\nParametro c"+(i+1)+": " + this.parametros[2][i]);

	if(this.gabarito[i] == ACERTOU){
	//System.out.println("\nACERTOU ");
	}else{
	//System.out.println("\nERROU");
	}
  }
	System.out.println();

	System.out.print("Nota:");

	System.out.print(" " + this.theta);

	//System.out.println("\n");
	}


}