import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {
	
	private JPanel contentPane;
	private JTextField OffsetEsquerdo_textField;
	private JTextField OffsetDireito_textField;
	private JTextField Robot_textField;
	private JTextField Raio_textField;
	private JTextField Angulo_textField;
	private JTextField Distancia_textField;
	private JTextField Log_textField;

	Variaveis nv;
	public int clientes = 0;
	public void run() {
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		TP1JFrame frame = new TP1JFrame();
		frame.run();
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setBackground(Color.WHITE);
		nv = new Variaveis();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		OffsetEsquerdo_textField = new JTextField("" + nv.getOffsetEsquerdo());
		OffsetEsquerdo_textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nv.setOffsetEsquerdo(Integer.parseInt(OffsetEsquerdo_textField.getText()));
				myPrint("Offset Esquerdo: " + nv.getOffsetEsquerdo());
			}
		});
		OffsetEsquerdo_textField.setBounds(10, 11, 20, 20);
		contentPane.add(OffsetEsquerdo_textField);
		OffsetEsquerdo_textField.setColumns(10);
		
		JLabel ldlOffsetEsquerdo = new JLabel("Offset Esquerdo");
		ldlOffsetEsquerdo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ldlOffsetEsquerdo.setBounds(40, 14, 89, 14);
		contentPane.add(ldlOffsetEsquerdo);
		
		OffsetDireito_textField = new JTextField("" + nv.getOffsetDireito());
		OffsetDireito_textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nv.setOffsetDireito(Integer.parseInt(OffsetDireito_textField.getText()));
				myPrint("Offset Direito: " + nv.getOffsetDireito());
			}
		});
		OffsetDireito_textField.setBounds(404, 11, 20, 20);
		contentPane.add(OffsetDireito_textField);
		OffsetDireito_textField.setColumns(10);
		
		JLabel ldlOffsetDireito = new JLabel("Offset Direito");
		ldlOffsetDireito.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ldlOffsetDireito.setBounds(321, 14, 73, 14);
		contentPane.add(ldlOffsetDireito);
		
		JLabel lblRobot = new JLabel("Robot");
		lblRobot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRobot.setBounds(93, 38, 33, 14);
		contentPane.add(lblRobot);
		
		Robot_textField = new JTextField(nv.getRobot());
		Robot_textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nv.setRobot(Robot_textField.getText());
				myPrint("Nome do Robot: " + nv.getRobot());
			}
		});
		Robot_textField.setBounds(136, 36, 109, 20);
		contentPane.add(Robot_textField);
		Robot_textField.setColumns(10);
		
		JRadioButton BottonOnoff = new JRadioButton("On/Off ");
		BottonOnoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!nv.getOnOff()){
					if(nv.robot().OpenNXT(nv.getRobot())){
						nv.setOnOff(nv.robot().OpenNXT(nv.getRobot()));
						clientes += 1;
						myPrint("Ligação do modelo Servidor-Cliente bem sucedida.");
					}else{
						BottonOnoff.isSelected();
						myPrint("Insucesso na ligação.");
						if(clientes > 0)
							clientes += 1;
					}
				}else{
					nv.robot().CloseNXT();
					nv.setOnOff(false);
				}
			}
		});
		BottonOnoff.setFont(new Font("Tahoma", Font.PLAIN, 12));
		BottonOnoff.setBounds(251, 35, 109, 23);
		BottonOnoff.setSelected(nv.getOnOff());
		contentPane.add(BottonOnoff);
		
		JLabel lblRaio = new JLabel("Raio");
		lblRaio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRaio.setBounds(10, 67, 22, 14);
		contentPane.add(lblRaio);
		
		Raio_textField = new JTextField("" + nv.getRaio());
		Raio_textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nv.setRaio(Integer.parseInt(Raio_textField.getText()));
				
				myPrint("Raio: " + nv.getRaio());
				
			}
		});
		Raio_textField.setBounds(40, 65, 20, 20);
		contentPane.add(Raio_textField);
		Raio_textField.setColumns(10);
		
		JLabel lblAngulo = new JLabel("\u00C2ngulo");
		lblAngulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAngulo.setBounds(103, 68, 46, 14);
		contentPane.add(lblAngulo);
		
		JLabel lblDistancia = new JLabel("Dist\u00E2ncia");
		lblDistancia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDistancia.setBounds(284, 68, 52, 14);
		contentPane.add(lblDistancia);
		
		Angulo_textField = new JTextField("" + nv.getAngulo());
		Angulo_textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nv.setAngulo(Integer.parseInt(Angulo_textField.getText()));
				
				myPrint("Angulo: " + nv.getAngulo());
				
			}
		});
		Angulo_textField.setColumns(10);
		Angulo_textField.setBounds(146, 65, 20, 20);
		contentPane.add(Angulo_textField);
		
		Distancia_textField = new JTextField("" + nv.getDistancia());
		Distancia_textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nv.setDistancia(Integer.parseInt(Distancia_textField.getText()));
				
				myPrint("Distancia: " + nv.getDistancia());
				
			}
		});
		Distancia_textField.setColumns(10);
		Distancia_textField.setBounds(340, 65, 20, 20);
		contentPane.add(Distancia_textField);
		
		JCheckBox chckbxDebug = new JCheckBox("Debug");
		chckbxDebug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nv.setDebug(chckbxDebug.isSelected());
				
				myPrint("Debug: " + nv.getDebug());
				
			}
		});
		chckbxDebug.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxDebug.setSelected(nv.getDebug());
		chckbxDebug.setBounds(62, 232, 61, 23);
		contentPane.add(chckbxDebug);
		
		JLabel lblLog = new JLabel("Logging");
		lblLog.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLog.setBounds(10, 235, 46, 16);
		contentPane.add(lblLog);
		
		Log_textField = new JTextField(nv.getLog());
		Log_textField.setBounds(10, 262, 414, 45);
		contentPane.add(Log_textField);
		Log_textField.setColumns(10);
		
		JButton Frente_button = new JButton("Frente");
		Frente_button.setBackground(Color.BLUE);
		Frente_button.setForeground(Color.BLACK);
		Frente_button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Frente_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//if(nv.getOnOff()){
					
					myPrint("Frente " + nv.getDistancia());
					
					nv.robot().Reta(nv.getDistancia());
					
					nv.robot().Parar(false);
					
				//}
			}
		});
		Frente_button.setBounds(145, 92, 118, 37);
		contentPane.add(Frente_button);
		
		JButton Parar_button = new JButton("Parar");
		Parar_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(nv.getOnOff()){
					
					nv.robot().Parar(true);
					
					myPrint("Parar");
					
				}
			}
		});
		Parar_button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Parar_button.setBackground(Color.RED);
		Parar_button.setBounds(145, 131, 118, 37);
		contentPane.add(Parar_button);
		
		JButton Direita_button = new JButton("Direita");
		Direita_button.setBackground(Color.GREEN);
		Direita_button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Direita_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(nv.getOnOff()){
					
					nv.robot().CurvaDireita(nv.getRaio(), nv.getAngulo());
					
					myPrint("Direita " + nv.getRaio() + " " + nv.getAngulo());
					
				}
			}
		});
		Direita_button.setBounds(265, 131, 112, 37);
		contentPane.add(Direita_button);
		
		JButton Retaguarda_button = new JButton("Retaguarda");
		Retaguarda_button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Retaguarda_button.setBackground(Color.YELLOW);
		Retaguarda_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(nv.getOnOff()){
				
					myPrint("Retaguarda -" + nv.getDistancia());
					
					nv.robot().Reta(-nv.getDistancia());
					
					nv.robot().Parar(false);
					
				}				
			}
		});
		Retaguarda_button.setBounds(145, 171, 118, 37);
		contentPane.add(Retaguarda_button);
		
		JButton Esquerda_button = new JButton("Esquerda");
		Esquerda_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nv.getOnOff()){
					
					nv.robot().CurvaEsquerda(nv.getRaio(), nv.getAngulo());
					
					myPrint("Esquerda " + nv.getRaio() + " " + nv.getAngulo());
					
				}
			}
		});
		Esquerda_button.setBackground(Color.PINK);
		Esquerda_button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Esquerda_button.setBounds(31, 131, 112, 37);
		contentPane.add(Esquerda_button);
		
		JButton ClearLogging_button = new JButton("Clear Logging");
		ClearLogging_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myPrint("");
			}
		});
		ClearLogging_button.setBounds(146, 212, 117, 23);
		contentPane.add(ClearLogging_button);
		
		setVisible(true);
	}
	
	public void myPrint(String s){
		if(nv.getDebug())
			Log_textField.setText(s);
	}
}