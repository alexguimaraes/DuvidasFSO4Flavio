import java.io.*;
import java.nio.Buffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class ProcessoM {
	//  ficheiro
	File ficheiro; 
	
	// canal que liga o conteúdo do ficheiro ao Buffer 
	FileChannel canal; 
	
	// buffer 
	MappedByteBuffer buffer; 
	
	// dimensão máxima em bytes do buffer  
	final int BUFFER_MAX = 30;


	ProcessoM(String nameFile){
		ficheiro = new File("C:\\Users\\erica\\Desktop\\" + nameFile +".dat");
		//cria um canal de comunicação de leitura e escrita  
		try { 
			canal = new RandomAccessFile(ficheiro, "rw").getChannel(); 
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace(); 
		}
		// mapeia para memória o conteúdo do ficheiro 
		try { 
			buffer = canal.map(FileChannel.MapMode.READ_WRITE, 0, BUFFER_MAX); 
		} 
		catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	// recebe uma mensagem convertendo-a numa String 
	String receberString() { 
		String msg = new String(); 
		char c; 
		buffer.position(0); 
		while ((c = buffer.getChar()) != '\0') 
        	msg += c; 
        return msg;
	}
	
	// envia uma String como mensagem 
	void enviarString(String msg) { 
		char c; 
		buffer.position(0); 
		for (int i= 0 ; i< msg.length() ; ++i){ 
			c= msg.charAt(i); 
			buffer.putChar(c); 
		} 
		buffer.putChar('\0'); 
	}
	
	public byte[] receberMensagem() { 
		byte[] msg = new byte[BUFFER_MAX];
		
		for(int n = 0; n < BUFFER_MAX; n++)
			msg[n] = buffer.get(n);
		
        return msg;
	}
	
	// envia uma String como mensagem 
	public void enviarMensagem(byte[] msg) { 
		buffer.position(0); 
		for (int i = 0 ; i < msg.length ; ++i){
			buffer.put(msg[i]); 
		}  
	}
	
	// fecha o canal entre o buffer e o ficheiro 
	void fecharCanal() { 
		try { 
			canal.close(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}  
	} 
	
	// método run do processo 
	void run() {}	
	
	
}
