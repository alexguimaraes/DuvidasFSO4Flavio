import java.io.IOException;
import java.nio.channels.FileLock;

public class Comunicar extends ProcessoM implements Mensagem{
	byte idCom;
	FileLock lock;
	Msg mRx;
	Msg mTx;
	byte[] zeros = new byte[30];
	
	Comunicar(String nameFile){
		super(nameFile);
		mRx = new Msg();
		mTx = new Msg();
	}

	public byte[] parar(boolean bool) {
		byte size = 0x03;
		byte [] array = new byte[size+1];
		array[0] = size;
		array[1] = idCom;
		array[2] = PARAR;
		array[3] = (byte) (bool? 0xFF : 0x00);
		return array;
	}

	public byte[] reta(int distancia) {
		byte size = 0x06;
		byte [] array = new byte[size+1];
		array[0] = size;
		array[1] = idCom;
		array[2] = RETA;
		array[3] = (byte) (distancia & 0xFF);
		array[4] = (byte) ((distancia >> 8) & 0xFF);
		array[5] = (byte) ((distancia >> 16) & 0xFF);
		array[6] = (byte) ((distancia >> 24) & 0xFF);
		return array;
	}

	public byte[] vmd(int offsetD) {
		byte size = 0x06;
		byte [] array = new byte[size+1];
		array[0] = size;
		array[1] = idCom;
		array[2] = VMD;
		array[3] = (byte) (offsetD & 0xFF);
		array[4] = (byte) ((offsetD >> 8) & 0xFF);
		array[5] = (byte) ((offsetD >> 16) & 0xFF);
		array[6] = (byte) ((offsetD >> 24) & 0xFF);
		return array;
	}

	public byte[] vme(int offsetE) {
		byte size = 0x06;
		byte [] array = new byte[size+1];
		array[0] = size;
		array[1] = idCom;
		array[2] = VME;
		array[3] = (byte) (offsetE & 0xFF);
		array[4] = (byte) ((offsetE >> 8) & 0xFF);
		array[5] = (byte) ((offsetE >> 16) & 0xFF);
		array[6] = (byte) ((offsetE >> 24) & 0xFF);
		return array;
	}

	public byte[] curvad(int r, int a) {
		byte size = 0x0A;
		byte [] array = new byte[size+1];
		array[0] = size;
		array[1] = idCom;
		array[2] = CURVAD;
		array[3] = (byte) (r & 0xFF);
		array[4] = (byte) ((r >> 8) & 0xFF);
		array[5] = (byte) ((r >> 16) & 0xFF);
		array[6] = (byte) ((r >> 24) & 0xFF);
		array[7] = (byte) (a & 0xFF);
		array[8] = (byte) ((a >> 8) & 0xFF);
		array[9] = (byte) ((a >> 16) & 0xFF);
		array[10] = (byte) ((a >> 24) & 0xFF);
		return array;
	}

	public byte[] curvae(int r, int a) {
		byte size = 0x0A;
		byte [] array = new byte[size+1];
		array[0] = size;
		array[1] = idCom;
		array[2] = CURVAE;
		array[3] = (byte) (r & 0xFF);
		array[4] = (byte) ((r >> 8) & 0xFF);
		array[5] = (byte) ((r >> 16) & 0xFF);
		array[6] = (byte) ((r >> 24) & 0xFF);
		array[7] = (byte) (a & 0xFF);
		array[8] = (byte) ((a >> 8) & 0xFF);
		array[9] = (byte) ((a >> 16) & 0xFF);
		array[10] = (byte) ((a >> 24) & 0xFF);
		return array;
	}
	
	public boolean OpenNXT(String nome){
		return false;
	}

	private void bloquear(){
		try {
			lock = canal.lock();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void libertar(){
		try {
			lock.release();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean GetandSetTx(Msg msg){
		bloquear();
		byte[] m;
//		m = msg.getMsg();
		m = receberMensagem();
		if(m[0] != 0x0){
			libertar();
			return false;
		}
		enviarMensagem(msg.getMsg());
		libertar();
		return true;
	}
	
	public boolean GetandSetRx(Msg msg){
		bloquear();
		msg.setMsg(receberMensagem());
		mRx = new Msg(msg);
		libertar();
		return mRx.getMsg()[0] != 0;
	}
	
	public String getMRX(){
		return mRx.getString();
	}
	
	public String getMTX(){
		return mTx.getString();
	}
	
	
	public void threadSleep(int millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
}
	