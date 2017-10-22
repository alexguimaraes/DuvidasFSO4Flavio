import java.nio.channels.FileChannel;

public class Vaguear{
	FileChannel Rx;
	FileChannel Tx;
	VaguearHelper vh;
	Comunicar c;
	
	public final int iniciar = 0;
	public final int reta = 1;
	public final int curvarD = 2;
	public final int curvarE = 3;
	public final int parar = 4;
	public final int close = 8;
	public final int terminar = 9;
	int estado;
	
	MyRobotLego robot;
	
	public Vaguear(){
		estado = iniciar;
	}
	public void run() throws InterruptedException{
		while (estado != terminar){
			automato();
		}
	}
	public void automato() throws InterruptedException{
		switch (estado){
		case iniciar: criarCanais(); break;
		case reta: reta(); break;
		case curvarD: curvarD(); break;
		case curvarE: curvarE(); break;
		case parar: parar(); break;
		case close: c.fecharCanal(); estado = terminar; break;
		}
			
	}
	private void criarCanais() {
		c.cliente = "cliente1";
		c.canal = Rx;
		c.canal = Tx;
		
	}
	private int reta() throws InterruptedException{
		int distancia = vh.randomNum(100);
		robot.Reta(distancia); 
		Thread.sleep(vh.tReta(distancia));
		estado = vh.randomEstado(1,4);
		if(c.receberMensagem() == "terminar")
			c.enviarMensagem("terminei");
			estado = close;
		return estado;
	}
	private int curvarD() throws InterruptedException{
		int raioD = vh.randomNum(100);
		int anguloD = vh.randomNum(360);
		robot.CurvaDireita(raioD, anguloD); 
		Thread.sleep(vh.tCurva(raioD, anguloD)); 
		estado = vh.randomEstado(1, 4); 
		if(c.receberMensagem() == "terminar")
			c.enviarMensagem("terminei");
			estado = close;
		return estado;
	}
	private int curvarE() throws InterruptedException{
		int raioE = vh.randomNum(100);
		int anguloE = vh.randomNum(100);
		robot.CurvaEsquerda(raioE, anguloE); 
		Thread.sleep(vh.tCurva(raioE, anguloE));
		estado = vh.randomEstado(1, 4);
		if(c.receberMensagem() == "terminar")
			c.enviarMensagem("terminei");
			estado = close;
		return estado;
	}
	private int parar() throws InterruptedException{
		 robot.Parar(vh.booleanGen()); 
		 Thread.sleep(4000);
		 estado = vh.randomEstado(1, 4);
		 if(c.receberMensagem() == "terminar")
				c.enviarMensagem("terminei");
				estado = close;
		 return estado;
	}
}