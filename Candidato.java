public class Candidato {
    /**
     * Propriedade protegida: double theta -- parâmetro de habilidade do candidato
     */
    protected double theta;
    protected int[] gabarito;

    
    public Candidato(double theta){
        this.theta = theta;
    }
   
  
    /**
     * Método getter para o parâmetro de habilidade e gabarito.
     *
     * @return  double
     */
    public double getTheta(){
        return this.theta;
    }
    public void setTheta(double theta){
      this.theta = theta;
    }
    public int[] getGabarito(){
      return this.gabarito;
    }
    public void setGabarito(int []gabarito){
      this.gabarito = gabarito;
    }
    
}