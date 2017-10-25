
public class Gestor {
	private byte estado;
	private final byte INICIAR = 0x0A;
	private final byte ESPERAR = 0x0B;
	private final byte LER = 0x0C;
	private final byte ESCREVER = 0x0D;
	private final byte TERMINAR = 0x0F;
	private final byte PARAR = 0x0;
	private final byte RETA = 0x01;
	private final byte VMD = 0x02;
	private final byte VME = 0x03;
	private final byte CURVAD = 0x04;
	private final byte CURVAE = 0x05;
	public static boolean flag;
	
	MyRobotLego robot;
	Comunicar Rx, Tx;
	Msg mRx, mTx;
	
	byte[] id;
	public Gestor() {
		criarCanais();
		robot = new MyRobotLego();
		flag = false;
		estado = INICIAR;
	}
	public void run() {
		GestorAutomato();
	}
	
	public void GestorAutomato(){
		switch(estado){
		case INICIAR:
			estado = ESPERAR;
			break;
		case ESPERAR:
			Rx.threadSleep(500);
			estado = LER;
			break;
		case LER:
			if(Rx.GetandSetRx(mRx)){
				id = mRx.getMsg();
				if(id[1] != 0x0)
					estado = id[2];
			}
//			System.out.println(mRx.getString());
			estado = ESCREVER;
			break;
		case ESCREVER:
			mTx.setMsg(Tx.curvad(1, 2));
			Tx.GetandSetTx(mTx);
			System.out.println(mTx.getString());
			estado = LER;
			break;
		case RETA:
			int d;
			d = (int) ((id[6]<<24)|(id[5]<<16)|(id[4]<<8)|(id[3]));
			robot.Reta(d);
			estado = ESPERAR;
			break;
		case PARAR:
			boolean b;
			b = ((id[3]&0xFF) == 0xFF)? true: false;
			robot.Parar(b);
			estado = ESPERAR;
			break;
		case VME:
			int oe;
			oe = (int) ((id[7]<<24)|(id[6]<<16)|(id[5]<<8)|(id[4]));
			robot.OffsetEsquerdo(oe);
			estado = ESPERAR;
			break;
		case VMD:
			int od;
			od = (int) ((id[6]<<24)|(id[5]<<16)|(id[4]<<8)|(id[3]));
			robot.OffsetDireito(od);
			estado = ESPERAR;
			break;
		case CURVAE:
			int r;
			r = (int) ((id[6]<<24)|(id[5]<<16)|(id[4]<<8)|(id[3]));
			int a;
			a = (int) ((id[10]<<24)|(id[9]<<16)|(id[8]<<8)|(id[7]));
			robot.CurvaEsquerda(r, a);
			estado = ESPERAR;
			break;
		case CURVAD:
			int rd;
			rd = (int) ((id[6]<<24)|(id[5]<<16)|(id[4]<<8)|(id[3]));
			int ad;
			ad = (int) ((id[10]<<24)|(id[9]<<16)|(id[8]<<8)|(id[7]));
			robot.CurvaDireita(rd, ad);
			estado = ESPERAR;
			break;
		case TERMINAR:
			flag = true;
			break;
		}
	}
	
	private void criarCanais() {
		Tx = new Comunicar("Rx");
	 	Rx = new Comunicar("Tx");
	 	mRx = new Msg();
		mTx = new Msg();
	}
//	public String getStringRx() {
//		return Rx.getMRX();
//	} 
//	public String getStringTx() {
//		return Tx.getMTX();
//	} 
	
	public String getStringRx() {
		return mRx.getString();
	} 
	public String getStringTx() {
		return mTx.getString();
	} 
}
