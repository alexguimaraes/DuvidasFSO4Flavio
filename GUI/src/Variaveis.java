
public class Variaveis {
	private int OffsetEsquerdo;
	private int OffsetDireito;
	private String nomeRobot;
	private boolean OnOff;
	private int Raio, Angulo, Distancia;
	private boolean Debug;
	private String Log;
	private MyRobotLego Robot;
	
	public Variaveis(){
		OffsetEsquerdo = 0;
		OffsetDireito = 0;
		nomeRobot = "Link";
		OnOff = false;
		Raio = 0;
		Angulo = 0;
		Distancia = 0;
		Debug = true;
		Log = "";
		Robot = new MyRobotLego();
		
		//Robot.OpenNXT(nomeRobot);
	}
	
	
	public void setOffsetEsquerdo (int set){
		OffsetEsquerdo = set;
	}
	public int getOffsetEsquerdo(){
		return OffsetEsquerdo;
	}
	
	public void setOffsetDireito (int set){
		OffsetDireito = set;
	}
	public int getOffsetDireito(){
		return OffsetDireito;
	}
	
	public void setRobot (String set){
		nomeRobot = set;
	}
	public String getRobot(){
		return nomeRobot;
	}
	
	public void setOnOff (boolean set){
		OnOff = set;
	}
	public boolean getOnOff(){
		return OnOff;
	}
	
	public void setRaio (int set){
		Raio = set;
	}
	public int getRaio(){
		return Raio;
	}
	
	public void setAngulo (int set){
		Angulo = set;
	}
	public int getAngulo(){
		return Angulo;
	}
	
	public void setDistancia(int set) {
		Distancia = set;
	}
	public int getDistancia() {
		return Distancia;
	}
	
	public void setDebug(boolean set) {
		Debug = set;
	}
	public boolean getDebug() {
		return Debug;
	}
	
	public String getLog(){
		return Log;
	}
	//Trocar pa Comunicar Robot?
	public MyRobotLego robot(){
		return Robot;
	}
	
	
}
