import java.io.IOException;

public interface InterfaceProva {
	public static final int  ACERTOU = 1;
	public static final int ERROU = 0;
        public static final int ALCANCE = 6;
	public void funcaoLogLikelihoodAcertou(double a, double b, double c) throws IOException;
        public void funcaoLogLikelihoodErrou(double a, double b, double c) throws IOException;
        public void funcaoCCI(double a, double b, double c) throws IOException;
        public void imprimeTRI();
}