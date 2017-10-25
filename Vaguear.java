import java.util.Random;

public class Vaguear{
	int estado;
	public final int iniciar = 0;
	public final int reta = 1;
	public final int curvarD = 2;
	public final int curvarE = 3;
	public final int parar = 4;
	public final int close = 8;
	public final int terminar = 9;
	
	private Random r;
	private int re;
	private boolean booleanGenerated;
	private int tReta;
	private int tCurva;
	private int perimetro;
	private final int vRobot = 30;
	public static boolean flag;
	
	Comunicar Tx, Rx;
	Msg mTx, mRx;
	
	byte[] novaMensagem;
	
	
	public Vaguear(){
		criarCanais();
		r = new Random();
		flag = false;
		re = 0;
		booleanGenerated = true;
		tReta = 0;
		tCurva = 0;
		perimetro = 0;
		estado = iniciar;
	}
	public void run(){
		VaguearAutomato();
	}
	public void VaguearAutomato() {
		switch (estado){
		case iniciar: estado = randomEstado(1, 4); break;
		case reta: reta(); break;
		case curvarD: curvarD(); break;
		case curvarE: curvarE(); break;
		case parar: parar(); break;
		case close: Tx.fecharCanal(); Rx.fecharCanal(); estado = terminar; break;
		case terminar: flag = true; break;
		}
			
	}
	private void criarCanais() {
		Tx = new Comunicar("Rx");
	 	Rx = new Comunicar("Tx");
	 	mTx = new Msg();
	 	mRx = new Msg();
	 	
	}
	private void reta(){
		int distancia = randomNum(100);
		mRx.setMsg(Rx.reta(distancia));
		if(!Rx.GetandSetTx(mRx)){
			Rx.threadSleep(tReta(distancia));
			Rx.GetandSetTx(mRx);
		}
		estado = randomEstado(1,4);
//		System.out.println(estado + " " + distancia);
		//if(Tx.receberMensagem() == "terminar")
		//	Rx.enviarMensagem("terminei");
		//	estado = close;
	}
	private void curvarD(){
		int raioD = randomNum(100);
		int anguloD = randomNum(360);
		mRx.setMsg(Rx.curvad(raioD, anguloD));
		if(!Rx.GetandSetTx(mRx)){
			Rx.threadSleep(tCurva(raioD, anguloD));
			Rx.GetandSetTx(mRx);
		}
		estado = randomEstado(1, 4); 
//		System.out.println(estado + " " + raioD + ", " + anguloD);
		//if(c.receberMensagem() == "terminar")
		//	c.enviarMensagem("terminei");
		//	estado = close;
	}
	private void curvarE(){
		int raioE = randomNum(100);
		int anguloE = randomNum(100);
		mRx.setMsg(Rx.curvae(raioE, anguloE));
		if(!Rx.GetandSetTx(mRx)){
			Rx.threadSleep(tCurva(raioE, anguloE));
			Rx.GetandSetTx(mRx);
		}
		estado = randomEstado(1, 4);
//		System.out.println(estado + " " + raioE + ", " + anguloE);
		//if(c.receberMensagem() == "terminar")
		//	c.enviarMensagem("terminei");
		//	estado = close;
	}
	private void parar(){
		mRx.setMsg(Rx.parar(booleanGen()));
		if(!Rx.GetandSetTx(mRx)){
			Rx.threadSleep(4000);
			Rx.GetandSetTx(mRx);
		}
		 estado = randomEstado(1, 4);
//		 System.out.println(estado + " " + parar);
		 //if(c.receberMensagem() == "terminar")
		//		c.enviarMensagem("terminei");
		//		estado = close;
	}
	
	
	public int tReta(int distancia){
		tReta = distancia / vRobot;
		return tReta;
	}
	
	public int tCurva(int raio, int angulo){
		perimetro = raio * angulo;
		tCurva = perimetro / vRobot;
		return tCurva;
	}

	public boolean booleanGen(){
		int randomBool = (int) Math.random();
		if(randomBool > 0.5)
			booleanGenerated = true;
		else
			booleanGenerated = false;
		return booleanGenerated;
	}
	
	public int randomEstado(int min, int max) {
			re = r.nextInt((max - min) + 1) + min;
			return re;
	}
	
	public int randomNum(int num){
		int randomNum = r.nextInt(num);
		return randomNum;
	}
	
	public String getStringRx() {
		return mRx.getString();
	} 
	public String getStringTx() {
		return mTx.getString();
	} 

}