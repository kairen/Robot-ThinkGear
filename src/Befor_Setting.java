

import java.awt.BorderLayout;
import java.awt.EventQueue;
import com.Modle.CenterModule;
import com.Modle.MoveSetModule;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Befor_Setting extends JFrame {

	private JPanel contentPane;
	private static JTextField Befor_BlinkCount;
	private static JTextField Befor_BlinkTime;
	public Befor_Setting(int x ,int y,int BlinkConut,int BlinkTime,final String title) {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.out.println("Speed_Setting is close");
				MAIN.frame.setEnabled(true);
				
			}
		});
		setAlwaysOnTop(true);
		setTitle(title + "設定" );
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(x, y, 350, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Befor_Cancel = new JButton("\u53D6\u6D88");
		Befor_Cancel.setFont(new Font("新細明體", Font.PLAIN, 20));
		Befor_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MAIN.frame.setEnabled(true);
				Befor_Setting.this.dispose();
			}
		});
		Befor_Cancel.setBounds(226, 107, 94, 72);
		contentPane.add(Befor_Cancel);
		
		JButton Befor_Save = new JButton("\u5132\u5B58");
		Befor_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveSetting(title);
				MAIN.frame.setEnabled(true);
				Befor_Setting.this.dispose();
			}
		});
		Befor_Save.setFont(new Font("新細明體", Font.PLAIN, 20));
		Befor_Save.setBounds(226, 25, 94, 72);
		contentPane.add(Befor_Save);
		
		JLabel label = new JLabel("\u7728\u773C\u6B21\u6578 :");
		label.setFont(new Font("新細明體", Font.PLAIN, 20));
		label.setBounds(10, 43, 134, 39);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5728\u5E7E\u79D2\u5167\u7728\u773C :");
		label_1.setFont(new Font("新細明體", Font.PLAIN, 20));
		label_1.setBounds(10, 124, 142, 39);
		contentPane.add(label_1);
		
		Befor_BlinkCount = new JTextField();
		Befor_BlinkCount.setFont(new Font("新細明體", Font.BOLD, 20));
		Befor_BlinkCount.setBounds(149, 45, 49, 39);
		contentPane.add(Befor_BlinkCount);
		Befor_BlinkCount.setText("" + BlinkConut);
		Befor_BlinkCount.setColumns(10);
		
		Befor_BlinkTime = new JTextField();
		Befor_BlinkTime.setFont(new Font("新細明體", Font.BOLD, 20));
		Befor_BlinkTime.setColumns(10);
		Befor_BlinkTime.setText("" + BlinkTime);
		Befor_BlinkTime.setBounds(149, 124, 49, 39);
		contentPane.add(Befor_BlinkTime);
	}
	
	public static void SaveSetting(String title)
	{
		if(title.equals("前進"))
		{
			if(Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Later_Count ||
			   Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.left_Count ||
			   Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Right_Count ||
			   Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Stop_Count )
			{
				JOptionPane.showMessageDialog(MAIN.frame,
					    "已有重複動作設定此參數!!");
			}
			else
			{
				MoveSetModule.Forward_Count = Integer.parseInt (Befor_BlinkCount.getText());
			}
			MoveSetModule.Forward_Time = MoveSetModule.Later_Time = MoveSetModule.left_Time = MoveSetModule.Stop_Time = MoveSetModule.Right_Time = Integer.parseInt (Befor_BlinkTime.getText());
		}
		else if(title.equals("後退"))
		{
			if( Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Forward_Count ||
				Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.left_Count ||
				Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Right_Count ||
				Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Stop_Count )
				{
				JOptionPane.showMessageDialog(MAIN.frame,
					    "已有重複動作設定此參數!!");	
				}
				else
				{
					MoveSetModule.Later_Count = Integer.parseInt (Befor_BlinkCount.getText());
				}
			MoveSetModule.Later_Time = Integer.parseInt (Befor_BlinkTime.getText());
		}
		else if(title.equals("左轉"))
		{
			if( Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Forward_Count ||
				Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Later_Count ||
				Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Right_Count ||
				Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Stop_Count )
				{
				JOptionPane.showMessageDialog(MAIN.frame,
					    "已有重複動作設定此參數!!");		
				}
				else
				{
				 MoveSetModule.left_Count = Integer.parseInt (Befor_BlinkCount.getText());
				}
				MoveSetModule.Later_Time = Integer.parseInt (Befor_BlinkTime.getText());
		}
		else if(title.equals("右轉"))
		{
			if( Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Forward_Count ||
				Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.left_Count ||
				Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Later_Count ||
				Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Stop_Count )
				{
				JOptionPane.showMessageDialog(MAIN.frame,
					    "已有重複動作設定此參數!!");		
				}
				else
				{
					MoveSetModule.Right_Count = Integer.parseInt (Befor_BlinkCount.getText());
				}
				MoveSetModule.Right_Time = Integer.parseInt (Befor_BlinkTime.getText());
		}
		else if(title.equals("停止"))
		{
			if( Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Forward_Count ||
				Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.left_Count ||
				Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Right_Count ||
				Integer.parseInt (Befor_BlinkCount.getText()) == MoveSetModule.Stop_Count )
					{
				JOptionPane.showMessageDialog(MAIN.frame,
					    "已有重複動作設定此參數!!");	
					}
					else
					{
						MoveSetModule.Stop_Count = Integer.parseInt (Befor_BlinkCount.getText());
					}
				MoveSetModule.Stop_Time = Integer.parseInt (Befor_BlinkTime.getText());
		}
		
	}
	
}
