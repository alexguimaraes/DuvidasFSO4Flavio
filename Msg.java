import java.util.Arrays;

public class Msg{
	byte[] mensagem;
	public final int BUFFER_MAX=30;
	
	public Msg(){
		mensagem = new byte[BUFFER_MAX];
	}
	public Msg(Msg m){
		mensagem = new byte[BUFFER_MAX];
		for(int i = 0 ; i< BUFFER_MAX ; i++){
			mensagem[i] = m.getMsg()[i];
		}
	}
	public byte[] getMsg() { 
        return mensagem;
	}
	
	// envia uma String como mensagem 
	public void setMsg(byte[] msg) { 
		for(int i = 0 ; i < BUFFER_MAX ; i++){
			if(i >= msg.length)
				mensagem[i] = 0;
			else
				mensagem[i] = msg[i];
		} 
	}
	
	public String getString() {
		String s = "";
		for(int n = 0; n < BUFFER_MAX; n++){
			s += mensagem[n];
		}
		return s;
	}
	
	public byte byteAt(int i) {
		return mensagem[i];
	}
}

