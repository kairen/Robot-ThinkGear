
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.Modle.CenterModule;


public class Speed_Setting extends JFrame {
	
	private static JTextField First_Editext_A;
	private static JTextField First_Editext_M;
	private static JTextField Center_Editext_A;
	private static JTextField Center_Editext_M;
	private static JTextField Slow_Editext_A;
	private static JTextField Slow_Editext_M;
	
	private static JCheckBox First_CheckBox_A;
	private static JCheckBox First_CheckBox_M;
	private static JCheckBox Center_CheckBox_A;
	private static JCheckBox Center_CheckBox_M;
	private static JCheckBox Slow_CheckBox_A;
	private static JCheckBox Slow_CheckBox_M;

	/**
	 * Create the frame.
	 */
	public Speed_Setting(int x ,int y) {
		setTitle("\u901F\u5EA6\u8A2D\u5B9A");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.out.println("Speed_Setting is close");
				MAIN.frame.setEnabled(true);
			}
		});
		
		setAlwaysOnTop(true);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(x, y, 452, 328);
		getContentPane().setLayout(null);
		
		First_CheckBox_A = new JCheckBox("   A  >");
		First_CheckBox_A.setFont(new Font("新細明體", Font.PLAIN, 20));
		First_CheckBox_A.setBounds(111, 6, 89, 34);
		getContentPane().add(First_CheckBox_A);
		
		First_Editext_A = new JTextField();
//		First_Editext_A.setDocument(new IntegerRangeDocument(0, 100));
		First_Editext_A.setFont(new Font("新細明體", Font.PLAIN, 20));
		First_Editext_A.setDropMode(DropMode.INSERT);
		First_Editext_A.setBounds(200, 8, 96, 36);
		getContentPane().add(First_Editext_A);
		First_Editext_A.setColumns(3);
		
		First_Editext_M = new JTextField();
//		First_Editext_M.setDocument(new IntegerRangeDocument(0, 100));
		First_Editext_M.setFont(new Font("新細明體", Font.PLAIN, 20));
		First_Editext_M.setDropMode(DropMode.INSERT);
		First_Editext_M.setBounds(200, 53, 96, 36);
		getContentPane().add(First_Editext_M);
		First_Editext_M.setColumns(3);
		
		First_CheckBox_M = new JCheckBox("   M  >");
		First_CheckBox_M.setFont(new Font("新細明體", Font.PLAIN, 20));
		First_CheckBox_M.setBounds(111, 54, 89, 34);
		getContentPane().add(First_CheckBox_M);
		
		Center_CheckBox_A = new JCheckBox("   A  >");
		Center_CheckBox_A.setFont(new Font("新細明體", Font.PLAIN, 20));
		Center_CheckBox_A.setBounds(111, 108, 89, 34);
		getContentPane().add(Center_CheckBox_A);
		
		Center_CheckBox_M = new JCheckBox("   M  >");
		Center_CheckBox_M.setFont(new Font("新細明體", Font.PLAIN, 20));
		Center_CheckBox_M.setBounds(111, 154, 89, 34);
		getContentPane().add(Center_CheckBox_M);
		
		Slow_CheckBox_A = new JCheckBox("   A  >");
		Slow_CheckBox_A.setFont(new Font("新細明體", Font.PLAIN, 20));
		Slow_CheckBox_A.setBounds(111, 201, 89, 34);
		getContentPane().add(Slow_CheckBox_A);
		
		Slow_CheckBox_M = new JCheckBox("   M  >");
		Slow_CheckBox_M.setSelected(true);
		Slow_CheckBox_M.setFont(new Font("新細明體", Font.PLAIN, 20));
		Slow_CheckBox_M.setBounds(111, 248, 89, 34);
		getContentPane().add(Slow_CheckBox_M);
		
		Center_Editext_A = new JTextField();
//		Center_Editext_A.setDocument(new IntegerRangeDocument(0, 100));
		Center_Editext_A.setFont(new Font("新細明體", Font.PLAIN, 20));
		Center_Editext_A.setDropMode(DropMode.INSERT);
		Center_Editext_A.setColumns(3);
		Center_Editext_A.setBounds(200, 107, 96, 36);
		getContentPane().add(Center_Editext_A);
		
		Center_Editext_M = new JTextField();
//		Center_Editext_M.setDocument(new IntegerRangeDocument(0, 100));
		Center_Editext_M.setFont(new Font("新細明體", Font.PLAIN, 20));
		Center_Editext_M.setDropMode(DropMode.INSERT);
		Center_Editext_M.setColumns(3);
		Center_Editext_M.setBounds(200, 153, 96, 36);
		getContentPane().add(Center_Editext_M);
		
		Slow_Editext_A = new JTextField();
//		Slow_Editext_A.setDocument(new IntegerRangeDocument(0, 100));
		Slow_Editext_A.setFont(new Font("新細明體", Font.PLAIN, 20));
		Slow_Editext_A.setDropMode(DropMode.INSERT);
		Slow_Editext_A.setColumns(3);
		Slow_Editext_A.setBounds(200, 200, 96, 36);
		getContentPane().add(Slow_Editext_A);
		
		Slow_Editext_M = new JTextField();
//		Slow_Editext_M.setDocument(new IntegerRangeDocument(0, 100));
		Slow_Editext_M.setFont(new Font("新細明體", Font.PLAIN, 20));
		Slow_Editext_M.setDropMode(DropMode.INSERT);
		Slow_Editext_M.setColumns(3);
		Slow_Editext_M.setBounds(200, 247, 96, 36);
		getContentPane().add(Slow_Editext_M);
		
		First_Editext_A.setText(String.valueOf(CenterModule.First_Editext_A_Value));
		First_Editext_M.setText(String.valueOf(CenterModule.First_Editext_M_Value));
		Center_Editext_A.setText(String.valueOf(CenterModule.Center_Editext_A_Value));
		Center_Editext_M.setText(String.valueOf(CenterModule.Center_Editext_M_Value));
		Slow_Editext_A.setText(String.valueOf(CenterModule.Slow_Editext_A_Value));
		Slow_Editext_M.setText(String.valueOf(CenterModule.Slow_Editext_M_Value));
		
		First_CheckBox_A.setSelected(CenterModule.First_CheckBox_A_Value);
		First_CheckBox_M.setSelected(CenterModule.First_CheckBox_M_Value);
		Center_CheckBox_A.setSelected(CenterModule.Center_CheckBox_A_Value);
		Center_CheckBox_M.setSelected(CenterModule.Center_CheckBox_M_Value);
		Slow_CheckBox_A.setSelected(CenterModule.Slow_CheckBox_A_Value);
		Slow_CheckBox_M.setSelected(CenterModule.Slow_CheckBox_M_Value);
		JLabel label = new JLabel("\u5FEB");
		label.setFont(new Font("新細明體", Font.PLAIN, 30));
		label.setBounds(45, 29, 36, 36);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u4E2D");
		label_1.setFont(new Font("新細明體", Font.PLAIN, 30));
		label_1.setBounds(45, 134, 36, 36);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u6162");
		label_2.setFont(new Font("新細明體", Font.PLAIN, 30));
		label_2.setBounds(45, 227, 36, 36);
		getContentPane().add(label_2);
		
		JButton Close = new JButton("\u53D6\u6D88");
		Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MAIN.frame.setEnabled(true);
				Speed_Setting.this.dispose();
				
			}
		});
		Close.setFont(new Font("新細明體", Font.PLAIN, 20));
		Close.setBounds(331, 209, 89, 54);
		getContentPane().add(Close);
		
		JButton Submit = new JButton("\u5132\u5B58");
		Submit.setFont(new Font("新細明體", Font.PLAIN, 20));
		Submit.setBounds(331, 134, 89, 54);
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CenterModule.First_Editext_A_Value= Integer.parseInt(First_Editext_A.getText());
				CenterModule.First_Editext_M_Value= Integer.parseInt(First_Editext_M.getText());
				CenterModule.Center_Editext_A_Value= Integer.parseInt(Center_Editext_A.getText());
				CenterModule.Center_Editext_M_Value= Integer.parseInt(Center_Editext_M.getText());
				CenterModule.Slow_Editext_A_Value= Integer.parseInt(Slow_Editext_A.getText());
				CenterModule.Slow_Editext_M_Value= Integer.parseInt(Slow_Editext_M.getText());
				
				CenterModule.First_CheckBox_A_Value = First_CheckBox_A.isSelected();
				CenterModule.First_CheckBox_M_Value = First_CheckBox_M.isSelected();
				CenterModule.Center_CheckBox_A_Value = Center_CheckBox_A.isSelected();
				CenterModule.Center_CheckBox_M_Value = Center_CheckBox_M.isSelected();
				CenterModule.Slow_CheckBox_A_Value = Slow_CheckBox_A.isSelected();
				CenterModule.Slow_CheckBox_M_Value = Slow_CheckBox_M.isSelected();
				
				MAIN.frame.setEnabled(true);
				Speed_Setting.this.dispose();
				
			}
		});
		getContentPane().add(Submit);
		
	}
}


