import java.util.Random;

public class VaguearHelper {
	private Random r;
	private int re;
	private boolean booleanGenerated;
	private int tReta;
	private int tCurva;
	private int perimetro;
	private final int vRobot = 30;
	
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
}
