
public interface Mensagem {
	public final byte IDS = 0x00;
	public final byte IDC1 = 0x01;
	public final byte IDC2 = 0x02;
	
	public final byte PARAR = 0x00;
	public final byte RETA = 0x01;
	public final byte VMD = 0x02;
	public final byte VME = 0x03;
	public final byte CURVAD = 0x04;
	public final byte CURVAE = 0x05;
	
	abstract byte[] parar(boolean bool);
	abstract byte[] reta(int distancia);
	abstract byte[] vmd(int offsetD);
	abstract byte[] vme(int offsetE);
	abstract byte[] curvad(int r, int a);
	abstract byte[] curvae(int r, int a);
	
	
}
