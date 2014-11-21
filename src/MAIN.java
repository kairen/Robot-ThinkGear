import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import lejos.pc.comm.NXTConnector;

import com.Modle.CenterModule;
import com.Modle.MoveSetModule;
import com.OrientationControl.After_Control;
import com.OrientationControl.Befor_Control;
import com.OrientationControl.Left_Control;
import com.OrientationControl.Pause_Control;
import com.OrientationControl.Right_Control;
import com.SystemControl.Finish_Control;
import com.SystemControl.Stop_Control;


public class MAIN extends JFrame  {
	private static JTextField MESSAGE;
	private static JTextField FOCUS ,RELAX ,WINK ,SPEED ,STATUS ,COMPORT;
	private JButton PAUSE, BEFOR ,LEFT ,RIGHT ,AFTER;
	private JButton EXIT ,STOP ,CONNECTION;
	private JButton SPEED_SETTING;
	private Befor_Setting Befor_Setting = null;
	private Speed_Setting Speed_Setting =null;
	public static boolean mindSet_Connecting = false;
	public static boolean nxt_Connecting = false;
	public static MAIN frame;
	
	public static boolean blinkNow = true ,isBlink = false,start = false;
	public static int blinkCount = 0,BlinkFA = 0;
	public static int direction = 5;
	public static int oldDir = 2;
	
	public static DataOutputStream outData;
	public static NXTConnector link;
	
    static String comPort = "\\\\.\\COM";  //c omport 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MAIN();
					frame.setVisible(true);
					frame.getContentPane().setLayout(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public MAIN() {
		setTitle("\u8166\u6CE2\u6A5F\u5668\u4EBA\u8A2D\u8A08\u5E2B");
		setResizable(false);
		setAlwaysOnTop(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
		
		//LABLE SET VIEW
		Lable_SetView();
		
		//停
		PAUSE = new JButton("\u505C");
		PAUSE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Pause_Control(MAIN.this ,e);
				Befor_Setting = new Befor_Setting(getX() ,getY(),
						MoveSetModule.Stop_Count,MoveSetModule.Stop_Time,"停止");
			}
		});
		PAUSE.setFont(new Font("新細明體", Font.PLAIN, 20));
		PAUSE.setBounds(150, 199, 90, 90);
		getContentPane().add(PAUSE);
		//前進
		BEFOR = new JButton("\u524D\u9032");
		BEFOR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Befor_Control(MAIN.this ,e);
				Befor_Setting = new Befor_Setting(getX() ,getY(),
						MoveSetModule.Forward_Count,MoveSetModule.Forward_Time,"前進");
			}
		});
		BEFOR.setFont(new Font("新細明體", Font.PLAIN, 20));
		BEFOR.setBounds(150, 79, 90, 90);
		getContentPane().add(BEFOR);
		//左
		LEFT = new JButton("\u5DE6");
		LEFT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Left_Control(MAIN.this ,e);
				Befor_Setting = new Befor_Setting(getX() ,getY(),
						MoveSetModule.left_Count,MoveSetModule.left_Time,"左轉");
			}
		});
		LEFT.setFont(new Font("新細明體", Font.PLAIN, 20));
		LEFT.setBounds(23, 199, 90, 90);
		getContentPane().add(LEFT);
		//右
		RIGHT = new JButton("\u53F3");
		RIGHT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Right_Control(MAIN.this ,e);
				Befor_Setting = new Befor_Setting(getX() ,getY(),
						MoveSetModule.Right_Count,MoveSetModule.Right_Time,"右轉");
			}
		});
		RIGHT.setFont(new Font("新細明體", Font.PLAIN, 20));
		RIGHT.setBounds(276, 199, 90, 90);
		getContentPane().add(RIGHT);
		//後退
		AFTER = new JButton("\u5F8C\u9000");
		AFTER.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new After_Control(MAIN.this ,e);
				Befor_Setting = new Befor_Setting(getX() ,getY(),
						MoveSetModule.Later_Count,MoveSetModule.Later_Time,"後退");
			}
		});
		AFTER.setFont(new Font("新細明體", Font.PLAIN, 20));
		AFTER.setBounds(150, 321, 90, 90);
		getContentPane().add(AFTER);
		//訊息
		MESSAGE = new JTextField();
		MESSAGE.setEnabled(false);
		MESSAGE.setDisabledTextColor(Color.BLACK);
		MESSAGE.setFont(new Font("新細明體", Font.PLAIN, 15));
		MESSAGE.setBounds(585, 309, 193, 33);
		getContentPane().add(MESSAGE);
		MESSAGE.setColumns(10);
		//離開
		EXIT = new JButton("\u96E2\u958B");
		EXIT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Finish_Control(MAIN.this).setFinish();
			}
		});
		EXIT.setFont(new Font("新細明體", Font.PLAIN, 20));
		EXIT.setBounds(50, 480, 90, 50);
		getContentPane().add(EXIT);
		//暫停
		STOP = new JButton("\u505C\u6B62");
		STOP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Stop_Control(MAIN.this ,e);
			}
		});
		STOP.setFont(new Font("新細明體", Font.PLAIN, 20));
		STOP.setBounds(150, 480, 90, 50);
		getContentPane().add(STOP);
		//連線
		CONNECTION = new JButton("\u9023\u7DDA");
		CONNECTION.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Connection_Control(MAIN.this ,e);
				
			}
		});
		CONNECTION.setFont(new Font("新細明體", Font.PLAIN, 20));
		CONNECTION.setBounds(250, 480, 90, 50);
		getContentPane().add(CONNECTION);
		
		FOCUS = new JTextField();
		FOCUS.setText("0.0");
		FOCUS.setEnabled(false);
		FOCUS.setDisabledTextColor(Color.BLACK);
		FOCUS.setFont(new Font("新細明體", Font.PLAIN, 18));
		FOCUS.setBounds(585, 48, 64, 30);
		getContentPane().add(FOCUS);
		FOCUS.setColumns(3);
		
		RELAX = new JTextField();
		RELAX.setText("0.0");
		RELAX.setEnabled(false);
		RELAX.setDisabledTextColor(Color.BLACK);
		RELAX.setFont(new Font("新細明體", Font.PLAIN, 18));
		RELAX.setBounds(585, 99, 64, 30);
		getContentPane().add(RELAX);
		RELAX.setColumns(3);
		
		WINK = new JTextField();
		WINK.setText("0.0");
		WINK.setEnabled(false);
		WINK.setDisabledTextColor(Color.BLACK);
		WINK.setFont(new Font("新細明體", Font.PLAIN, 18));
		WINK.setBounds(585, 151, 64, 30);
		getContentPane().add(WINK);
		WINK.setColumns(3);
		
		SPEED = new JTextField();
		SPEED.setText("0.0");
		SPEED.setEnabled(false);
		SPEED.setDisabledTextColor(Color.BLACK);
		SPEED.setFont(new Font("新細明體", Font.PLAIN, 18));
		SPEED.setBounds(585, 200, 64, 30);
		getContentPane().add(SPEED);
		SPEED.setColumns(3);
		
		STATUS = new JTextField();
		STATUS.setText("0.0");
		STATUS.setEnabled(false);
		STATUS.setDisabledTextColor(Color.BLACK);
		STATUS.setFont(new Font("新細明體", Font.PLAIN, 18));
		STATUS.setBounds(585, 249, 64, 30);
		getContentPane().add(STATUS);
		STATUS.setColumns(3);
		
		//設定
		//速度設定
		SPEED_SETTING = new JButton("\u901F\u5EA6\u63A7\u5236\u8A2D\u5B9A");
		SPEED_SETTING.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Speed_Setting ==null ){
					frame.setEnabled(false);
					new Speed_Setting(getX() ,getY());
				}
			}
		});
		SPEED_SETTING.setFont(new Font("新細明體", Font.PLAIN, 20));
		SPEED_SETTING.setBounds(533, 489, 182, 41);
		getContentPane().add(SPEED_SETTING);
		
		JLabel lblComPort = new JLabel("Mindset COMPort :");
		lblComPort.setFont(new Font("新細明體", Font.PLAIN, 20));
		lblComPort.setBounds(399 , 368, 182, 27);
		getContentPane().add(lblComPort);
		
		COMPORT = new JTextField();
		COMPORT.setBounds(585, 367, 96, 33);
		getContentPane().add(COMPORT);
		COMPORT.setColumns(10);
	}
	
	private void Lable_SetView(){
		//STATUS LABLE
		JLabel FOCUS_LABLE = new JLabel("\u5C08\u6CE8\u529B  :");
		FOCUS_LABLE.setFont(new Font("新細明體", Font.PLAIN, 20));
		FOCUS_LABLE.setBounds(468, 47, 114, 30);
		getContentPane().add(FOCUS_LABLE);
		
		JLabel RELAX_LABLE = new JLabel("\u653E\u9B06\u5EA6  :");
		RELAX_LABLE.setFont(new Font("新細明體", Font.PLAIN, 20));
		RELAX_LABLE.setBounds(468, 99, 114, 30);
		getContentPane().add(RELAX_LABLE);
		
		JLabel WINK_LABLE = new JLabel("\u7728\u773C\u6B21\u6578  :");
		WINK_LABLE.setFont(new Font("新細明體", Font.PLAIN, 20));
		WINK_LABLE.setBounds(468, 150, 114, 30);
		getContentPane().add(WINK_LABLE);
		
		JLabel SPEED_LABLE = new JLabel("\u8F49   \u901F  :");
		SPEED_LABLE.setFont(new Font("新細明體", Font.PLAIN, 20));
		SPEED_LABLE.setBounds(468, 199, 114, 30);
		getContentPane().add(SPEED_LABLE);
		
		JLabel STATUS_LABLE = new JLabel("\u8A0A    \u865F  :");
		STATUS_LABLE.setFont(new Font("新細明體", Font.PLAIN, 20));
		STATUS_LABLE.setBounds(468, 248, 114, 30);
		getContentPane().add(STATUS_LABLE);
		
		//MESSAGE　LABLE

		JLabel MESSAGE_LABLE = new JLabel("\u72C0\u614B\u5217  :");
		MESSAGE_LABLE.setFont(new Font("新細明體", Font.PLAIN, 20));
		MESSAGE_LABLE.setBounds(468, 308, 79, 33);
		getContentPane().add(MESSAGE_LABLE);
	}
	
	public static void SetMessage(String VALUES){MESSAGE.setText(VALUES);}
	
	public static void SetFocus(String VALUES){FOCUS.setText(VALUES);}
	
	public static void SetRELAX(String VALUES){RELAX.setText(VALUES);}
	
	public static void SetWINK(String VALUES){WINK.setText(VALUES);}
	
	public static void SetSPEED(String VALUES){SPEED.setText(VALUES);}
	
	public static void SetSTATUS(String VALUES){STATUS.setText(VALUES);}
	
	public static  void  Connection()
	{
		MindSet_Connection();
		NXT_Connection();
    }
	
	 public static void MindSet_Connection()
	 {
		 if(!mindSet_Connecting){
			    try {
					ThinkGearTest.MindsetConncet(comPort + COMPORT.getText());
					new Thread(new Runnable() {
						public void run() 
						{
						while(true)
						{
							SetFocus("" + ThinkGearTest.attention);
							SetRELAX("" + ThinkGearTest.meditation);
							SetSTATUS("" + (200 - ThinkGearTest.poorsignal));
							
							if(nxt_Connecting)
							{
								Move_Fir_Action(ThinkGearTest.attention, ThinkGearTest.attention,direction);
								
								if(ThinkGearTest.poorsignal == 0 && !start)
								{
									start = ! start;
									blinkNow = false;
								}
								if(ThinkGearTest.blinkStr >= 1 && !blinkNow)
								{
									blinkCount++;
									SetWINK("" + blinkCount);
									Blink(MoveSetModule.Forward_Time);
									blinkNow = !blinkNow;
							    }
							}
							try{
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						}}).start();
					
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
	 }
	 
	 public static void NXT_Connection()
	 {
		 if(!nxt_Connecting){
				
				link = new NXTConnector();
				   if (!link.connectTo("btspp://NXT"))
				   {  
					   SetMessage( MESSAGE.getText()+ "NXT 未連接!");
					   nxt_Connecting = false;
				  	try{
				        outData.close();
				        link.close();
				        } 
				     catch (IOException ioe) {
				        System.out.println("\nIO Exception writing bytes");
				     }
				     System.out.println("\nClosed data streams");
				  } 
				   else
				      {
				      	outData = link.getDataOut();
				      	SetMessage( MESSAGE.getText()+ "MindSet , NXT 已連接!");
				      	nxt_Connecting = true;
				      }
			    }
	   }

	  public static void Move_Fir_Action(double att,double med,int dir) 
	  {
		 int Fri_A = CenterModule.First_CheckBox_A_Value? 1 : 0;
		 int Fri_M = CenterModule.First_CheckBox_M_Value? 1 : 0;
		 try {
		 if((Fri_A * att) >= CenterModule.First_Editext_A_Value &&
			(Fri_M * med) >= CenterModule.First_Editext_M_Value)
		 {
				outData.writeInt(dir);
				outData.writeInt(oldDir * 10);
				outData.writeInt(6);
				SetSPEED("" + 300);
		 }
		 else if ((Fri_A * att) >= CenterModule.First_Editext_A_Value &&
				  !CenterModule.First_CheckBox_M_Value)
		 {
			 outData.writeInt(dir);
			 outData.writeInt(oldDir * 10);
			 outData.writeInt(6);
			 SetSPEED("" + 300);
		 }
		 else if (!CenterModule.First_CheckBox_A_Value &&
				  (Fri_M * med) >= CenterModule.First_Editext_M_Value)
		 {
			 outData.writeInt(dir);
			 outData.writeInt(oldDir * 10);
			 outData.writeInt(6);
			 SetSPEED("" + 300);
		 }
		 else 
		 {
			 Move_Cen_Action(att, med,dir);
			 SetSPEED("" + 250);
		 }
		  outData.flush();
			} catch (IOException e) {
				e.printStackTrace();
		}
	  }
	 
	 public static void Move_Cen_Action(double att,double med,int dir)
	 {
		 int Cen_A = CenterModule.Center_CheckBox_A_Value? 1 : 0;
		 int Cen_M = CenterModule.Center_CheckBox_M_Value? 1 : 0;
		try { 
		 if(((Cen_A * att) < CenterModule.First_Editext_A_Value &&
			(Cen_M * med) < CenterModule.First_Editext_M_Value) &&
			((Cen_A * att) >= CenterModule.Center_Editext_A_Value &&
			(Cen_M * med) >= CenterModule.Center_Editext_M_Value)) 
		 {
			 outData.writeInt(dir);
			 outData.writeInt(oldDir * 10);
			 outData.writeInt(7);
			 SetSPEED("" + 225);
		 }
		 else if (((Cen_A * att) < CenterModule.First_Editext_A_Value &&
				 ((Cen_A * att) >= CenterModule.Center_Editext_A_Value))&&
				  !CenterModule.Center_CheckBox_M_Value)
		 {
			 outData.writeInt(dir);
			 outData.writeInt(oldDir * 10);
			 outData.writeInt(7);
			 SetSPEED("" + 225);
		 }
		 else if (!CenterModule.Center_CheckBox_A_Value  &&
				  ((Cen_M * med) < CenterModule.First_Editext_M_Value &&
				   (Cen_M * med) >= CenterModule.Center_Editext_M_Value))
		 {
			 outData.writeInt(dir);
			 outData.writeInt(oldDir * 10);
			 outData.writeInt(7);
			 SetSPEED("" + 225);
		 }
		 else 
		 {
			 Move_Low_Action(att, med,dir);
			 SetSPEED("" + 150);
		 }
		 outData.flush();
		 } catch (IOException e) {
				e.printStackTrace();
			}
	 }
	 
	 public static void Move_Low_Action(double att,double med,int dir)
	 {
		 int Low_A = CenterModule.Slow_CheckBox_A_Value? 1 : 0;
		 int Low_M = CenterModule.Slow_CheckBox_M_Value? 1 : 0;
		try { 
		 if(((Low_A * att) < CenterModule.Center_Editext_A_Value &&
			(Low_M * med) < CenterModule.Center_Editext_M_Value) && 
			((Low_A * att) >= CenterModule.Slow_Editext_A_Value &&
			 (Low_M * med) >= CenterModule.Slow_Editext_M_Value))
		 {
			 outData.writeInt(dir);
			 outData.writeInt(oldDir * 10);
			 outData.writeInt(8);
			 SetSPEED("" + 150);
		 }
		 else if (((Low_A * att) < CenterModule.Center_Editext_A_Value && 
				  (Low_A * att) >= CenterModule.Slow_Editext_A_Value)  &&
				  !CenterModule.Slow_CheckBox_M_Value)
		 {
			 outData.writeInt(dir);
			 outData.writeInt(oldDir * 10);
			 outData.writeInt(8);
			 SetSPEED("" + 150);
		 }
		 else if (!CenterModule.Slow_CheckBox_A_Value &&
				  ((Low_M * med) < CenterModule.Center_Editext_M_Value &&
				  (Low_M * med) >= CenterModule.Slow_Editext_M_Value))
		 {
			 outData.writeInt(dir);
			 outData.writeInt(oldDir * 10);
			 outData.writeInt(8);
			 SetSPEED("" + 150);
		 }
		 else 
		 {
			 outData.writeInt(5);
			 SetSPEED("" + 0);
			 SetMessage("正在停止 ...");
		 }
		 outData.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 
	 public static void Blink(final int Time)
	 {
		 new Thread(new Runnable() {
				public void run() 
				{
		 TimerTask DateTask = new  TimerTask() {
			public void run() {
				if(ThinkGearTest.blinkStr == 1 && !isBlink)
				{
					blinkCount++;
					SetWINK("" + blinkCount);
					isBlink = !isBlink;
					
					System.out.println("現在Blink：" + blinkCount);
				}
				else if (ThinkGearTest.blinkStr == 0 && isBlink)
				{
					isBlink = !isBlink;
				}
			}
		  };
		    Timer timer = new Timer();
	        timer.schedule(DateTask, 1000, 500);
	        System.out.println("現在時間：" + new Date());

	        try 
	        {
	            Thread.sleep(Time * 1000);
	        }
	        catch(InterruptedException e) {
	        }
	        timer.cancel();
	        
	        System.out.println("direction：" + direction + "old:" + oldDir);
	        
	      try {
	    	 
	        if(blinkCount == MoveSetModule.Forward_Count)
	        {
	        	 if(BlinkFA == 0 || BlinkFA == 2)
	    		  {
	    			 BlinkFA = 1;
	    			oldDir = 2;
	  	        	direction = 1;
	  	        	SetMessage("正在前進 ...");
	    		  }
	    		  else if (BlinkFA == 1)
	    		  {
	    			BlinkFA = 2;
	    			oldDir = 1;
	  	        	direction = 2;
	  	        	SetMessage("正在往後 ...");
	    		  }
	        } 
	        else if(blinkCount == MoveSetModule.left_Count)
	        {
				outData.writeInt(3);
				SetMessage("正在右轉 ...");
	        }
	        else if(blinkCount == MoveSetModule.Right_Count)
	        {
	        	outData.writeInt(4);
	        	SetMessage("正在左轉 ...");
	        }
	        else if(blinkCount == MoveSetModule.Stop_Count)
	        {
	        	outData.writeInt(5);
	        	SetMessage("正在停止...");
	        }
	      } catch (IOException e) {
				e.printStackTrace();
				}
	         
	        try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        try {
	        	outData.writeInt(30);
				outData.writeInt(40);
				if(direction == 1)
		         {
		        	 SetMessage("正在前進  ...");
		         }
		         else if(direction == 2)
		         {
		        	 SetMessage("正在往後  ...");
		         }
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        isBlink = false;
	        blinkNow = false;
	        blinkCount = 0;
	        SetWINK("" + blinkCount);
			}}).start();
	 }
}
   

