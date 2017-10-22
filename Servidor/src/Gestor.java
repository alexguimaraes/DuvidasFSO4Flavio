
public class Gestor {
	private byte estado = 0x0A;
	private final byte ESPERAR = 0x0A;
	private final byte LER = 0x0B;
	private final byte TERMINAR = 0x0C;
	private final byte PARAR = 0x0;
	private final byte RETA = 0x01;
	private final byte VMD = 0x02;
	private final byte VME = 0x03;
	private final byte CURVAD = 0x04;
	private final byte CURVAE = 0x05;
	
	Variaveis nv;
	ProcessoM m;
	MyRobotLego robot;
	byte[] id;
	public Gestor() {
		robot = new MyRobotLego();	
		nv = new Variaveis();
		switch(estado){
			case ESPERAR:
				id[0] = 0x0;
				estado = LER;
				break;
			case LER:
				id = m.receberMsg();
				if(id[0] != 0x0)
					estado = id[1];
				estado = ESPERAR;
				break;
			case RETA:
				int d;
				d = (int) ((id[5]<<24)|(id[4]<<16)|(id[3]<<8)|(id[2]));
				robot.Reta(d);
				estado = ESPERAR;
				break;
			case PARAR:
				boolean b;
				b = ((id[2]&0xFF) == 0xFF)? true: false;
				robot.Parar(b);
				estado = ESPERAR;
				break;
			case VME:
				int oe;
				oe = (int) ((id[5]<<24)|(id[4]<<16)|(id[3]<<8)|(id[2]));
				robot.OffsetEsquerdo(oe);
				estado = ESPERAR;
				break;
			case VMD:
				int od;
				od = (int) ((id[5]<<24)|(id[4]<<16)|(id[3]<<8)|(id[2]));
				robot.OffsetDireito(od);
				estado = ESPERAR;
				break;
			case CURVAE:
				int r;
				r = (int) ((id[5]<<24)|(id[4]<<16)|(id[3]<<8)|(id[2]));
				int a;
				a = (int) ((id[9]<<24)|(id[8]<<16)|(id[7]<<8)|(id[6]));
				robot.CurvaEsquerda(r, a);
				estado = ESPERAR;
				break;
			case CURVAD:
				int rd;
				rd = (int) ((id[5]<<24)|(id[4]<<16)|(id[3]<<8)|(id[2]));
				int ad;
				ad = (int) ((id[9]<<24)|(id[8]<<16)|(id[7]<<8)|(id[6]));
				robot.CurvaDireita(rd, ad);
				estado = ESPERAR;
				break;
			case TERMINAR:
				
				break;
		}
	}
}
