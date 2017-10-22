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
	
	String cliente;
	// dimensão máxima em bytes do buffer  
	final int BUFFER_MAX = 30;

	ProcessoM(String cliente){ 
		// cria um ficheiro com o nome comunicacao.dat 
		ficheiro = new File(cliente + ".dat");
	}
	ProcessoM(FileChannel canal){
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
	String receberMensagem() { 
		String msg = new String(); 
		char c; 
		buffer.position(0); 
		while ((c = buffer.getChar()) != '\0') 
        	msg += c; 
        return msg;
	}
	
	// envia uma String como mensagem 
	void enviarMensagem(String msg) { 
		char c; 
		buffer.position(0); 
		for (int i= 0 ; i< msg.length() ; ++i){ 
			c= msg.charAt(i); 
			buffer.putChar(c); 
		} 
		buffer.putChar('\0'); 
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
	
	
	byte[] receberMsg() { 
		byte tamanho = buffer.get(0); 
		byte[] msg = new byte[tamanho];
		
		for(int n = 0; n < tamanho; n++)
			msg[n] = buffer.get(n+1);
		
        return msg;
	}
	
	// envia uma String como mensagem 
	void enviarMsg(byte[] msg) throws IOException { 
		byte m; 
		buffer.position(0); 
		for (int i = 0 ; i < msg.length ; ++i){ 
			m = msg[i]; 
			buffer.put(m); 
		}  
	}
	
}
