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
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GUIServidor extends JFrame {
	
	private JPanel contentPane;

	Gestor gt;
	Comunicar c;
	GUI g;
	String s = "";
	private JTextField textFieldClientes;
	
	public void run() {
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GUIServidor frame = new GUIServidor();
		frame.run();
	}

	/**
	 * Create the frame.
	 */
	public GUIServidor() {
		setBackground(Color.WHITE);
		gt = new Gestor();
		g = new GUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LabelReceber = new JLabel("Receber");
		LabelReceber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LabelReceber.setBounds(50, 11, 62, 14);
		contentPane.add(LabelReceber);
		
		JLabel LabelEnviar = new JLabel("Enviar");
		LabelEnviar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LabelEnviar.setBounds(50, 172, 62, 14);
		contentPane.add(LabelEnviar);
		
		JTextArea textArea = new JTextArea();
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
					c.receberMsg();
			}
		});
		textArea.setBounds(27, 34, 378, 125);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					c.enviarMsg(c.reta(30));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		textArea_1.setBounds(27, 197, 378, 125);
		contentPane.add(textArea_1);
		
		JLabel LabelClientes = new JLabel("Clientes");
		LabelClientes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LabelClientes.setBounds(50, 343, 46, 14);
		contentPane.add(LabelClientes);
		
		textFieldClientes = new JTextField();
		textFieldClientes.setBounds(106, 341, 33, 20);
		contentPane.add(textFieldClientes);
		textFieldClientes.setColumns(10);
		textFieldClientes.setText(s.valueOf(g.clientes));
		
		setVisible(true);
	}
	
//	public void myPrint(String s){
//		if(nv.getDebug())
//			textFieldClientes.setText(s);
//	}
}