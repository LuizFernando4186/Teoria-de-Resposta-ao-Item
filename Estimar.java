import java.lang.Math;
import java.io.IOException;
import java.util.Arrays;


public class Estimar extends Prova {
protected double aproximado;


 /**
     * Classe usando a derivada do Log-Likelihood para encontrar
     * o ponto mais próximo de 0, logo é o theta.
     *
     * @Construtor classe Estimar
     */

public Estimar(int ondeComeca, int tamanhoDaProva, int []gabarito){
  super(ondeComeca,tamanhoDaProva,gabarito);
  
}


public static void obterMemoriaUsada(){
final int MB = 1024 * 1024;//converter bytes para megas;
Runtime runtime = Runtime.getRuntime();
System.out.println("MEMORIA USADA \t" + ((runtime.totalMemory() - runtime.freeMemory())/MB) + " mb");
}

   /**
     * Método para fazer a primeira interação para estimar o theta
     *
     * @estimarNota
     */


public void estimarNota() throws IOException{
//System.out.println("\n\nMÉTODO ESTIMAÇÃO DA NOTA\n");

//Limpar memória
Runtime.getRuntime().runFinalization();
Runtime.getRuntime().gc();

try{
double valor = 0,posicao = 0;

for(int lin = 0; lin < this.thetas.length; lin++){//Linhas
for(int col = 0; col < tamanhoDaProva;col++){//Colunas

//System.out.println("theta: "+this.thetas[lin]+" L: " + lin+ " C: "+ col+" R: " + this.resultados[lin][col]);

valor += resultados[lin][col];
if(col == tamanhoDaProva - 1){
//System.out.println("Acumulo " + valor);
if(aproximado <= valor || aproximado == 0){
aproximado = valor;
posicao = this.thetas[lin];
valor = 0;
}
valor = 0;
   }
      }
         }
this.theta = posicao;
aproximacaoTheta(posicao,aproximado);
             }
catch(Exception e){
System.out.println("Erro na estimação");
}


}



   /**
     * Método para fazer a aproximação do theta real.
     *
     * @aproximacaoTheta
     */

public double aproximacaoTheta(double posicao, double valor){
//System.out.println("\n\nAPROXIMACAO DO THETA EXATO\n");

//Limpar memória
Runtime.getRuntime().runFinalization();
Runtime.getRuntime().gc();

try{
double comecandoTheta = posicao;
aproximado = 0;
valor = 0;

//Volta uma posição para usar uma nova precisão
if(posicao - ((double)ALCANCE/50) >= this.ondeComeca){
comecandoTheta = posicao - ((double)ALCANCE/50);
}

reset();
//Recalcula as derivadas do Log-Likelihood com novos thetas
for(int i = 0; i < tamanhoDaProva; i++){
if(this.gabarito[i] < tamanhoDaProva){
this.beta = beta/1000000000;//Nova precisão, usar em uma pequena região
comecaTheta(comecandoTheta);
funcaoCCI(this.parametros[0][i], this.parametros[1][i], this.parametros[2][i]);
}
}

for(int lin = 0; lin < this.thetas.length; lin++){//Linhas
for(int col = 0; col < tamanhoDaProva;col++){//Colunas

//System.out.println("theta: "+this.thetas[lin]+" L: " + lin+ " C: "+ col+" R: " + this.resultados[lin][col]);

valor += resultados[lin][col];
if(col == tamanhoDaProva - 1){
//System.out.println("Acumulo " + valor);
if(aproximado <= valor || aproximado == 0){
aproximado = valor;
posicao = this.thetas[lin];
this.theta = posicao;
valor = 0;
}
valor = 0;
   }
      }
         }

             }
catch(Exception e){
System.out.println("Erro na aproximação");
}

return this.theta;
}


}