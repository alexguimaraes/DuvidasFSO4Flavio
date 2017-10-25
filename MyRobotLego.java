
public class MyRobotLego {
	public boolean OpenNXT(String s){
		System.out.println("OpenNXT " + s);
		return true;
	}
	public void CloseNXT(){
		System.out.println("CloseNXT");
	}
	public void CurvaDireita(int r, int a){
		System.out.println("Curva à Direita: raio " + r + " angulo " + a);
	}
	public void CurvaEsquerda(int r, int a){
		System.out.println("Curva à Esquerda: raio " + r + " angulo " + a);
	}
	public void Parar(boolean b){
		System.out.println("Parar: " + b);
	}
	public void Reta(int d){
		System.out.println("Reta: " + d);
	}
	public void OffsetEsquerdo(int offset){
		System.out.println("Offset Esquerdo: " + offset);
	}
	public void OffsetDireito(int offset){
		System.out.println("Offset Direito: " + offset);
	}
}
