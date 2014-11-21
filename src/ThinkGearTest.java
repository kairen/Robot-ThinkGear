
import java.io.IOException;

public class ThinkGearTest {

	public static double poorsignal = 0;
	public static double raw = 0;
	public static double meditation = 0;
	public static double attention = 0;
	public static double blinkStr = 0;
	
	private static String comp;
	 public static void MindsetConncet (String comPort) throws IOException {
		// TODO Auto-generated method stub

		
		/* Read 10 ThinkGear Packets from the connection, 1 Packet at a time */
		
		comp = comPort;
		
		new Thread(new Runnable() {
			public void run() 
			{
				String currentDir = System.getProperty("user.dir");
				System.out.println("Working Directory = " + currentDir);
				System.load(currentDir + "\\bin\\thinkgear.dll");

				int dllVersion = ThinkGear.GetDriverVersion();
				System.out.println("TGVersion = " + dllVersion);

				int connectionId = ThinkGear.GetNewConnectionId();

				if (connectionId < 0) {

					System.out.println("ERROR: TG_GetNewConnectionId() returned"
							+ connectionId + "\n Press Any Key To Exit!");
				}

				/* Set/open stream (raw bytes) log file for connection */
				int errCode = ThinkGear.SetStreamLog(connectionId, "streamLog.txt");
				if (errCode < 0) {
					System.out.println("ERROR: TG_SetStreamLog() returned" + errCode
							+ "\n Press Any Key To Exit!");
				}

				/* Set/open data (ThinkGear values) log file for connection */
				errCode = ThinkGear.SetDataLog(connectionId, "dataLog.txt");
				if (errCode < 0) {
					System.out.println("ERROR: TG_SetDataLog() returned" + errCode
							+ "\n Press Any Key To Exit!");
				}

				/* Attempt to connect the connection ID handle to serial port "COM5" */
				String comPortName = comp;
				errCode = ThinkGear.Connect(connectionId, comPortName,
						ThinkGear.BAUD_57600, ThinkGear.STREAM_PACKETS);
				if (errCode < 0) {
					System.out.println("ERROR: TG_Connect() returned " + errCode
							+ "\n Press Any Key To Exit!");
					 MAIN.mindSet_Connecting = false;
					 MAIN.SetMessage("MindSet ¥¼³s±µ,");
				}
				else 
				{
					MAIN.mindSet_Connecting = true;
				}
				int packetsRead = 0;
				int count = 1;
				
				while (packetsRead < 200000) {

					/* Attempt to read a Packet of data from the connection */
					errCode = ThinkGear.ReadPackets(connectionId, 1);

					/* If TG_ReadPackets() was able to read a complete Packet of data... */
					if (errCode == 1) {
						packetsRead++;

						//attention value 
						if (ThinkGear.GetValueStatus(connectionId,
								ThinkGear.DATA_ATTENTION) != 0) {

						    attention =	ThinkGear.GetValue(connectionId, (int) ThinkGear.DATA_ATTENTION);
//							System.out.println("Attention: " + attention );
						} 
						//meditation value
						if (ThinkGear.GetValueStatus(connectionId,
								ThinkGear.DATA_MEDITATION) != 0) {

						    meditation =	ThinkGear.GetValue(connectionId, (int) ThinkGear.DATA_MEDITATION);
//							System.out.println("meditation: " + meditation );
						} 
						//POOR_SIGNAL value
						if (ThinkGear.GetValueStatus(connectionId,
								ThinkGear.DATA_POOR_SIGNAL) != 0) {

						    poorsignal =	ThinkGear.GetValue(connectionId, (int) ThinkGear.DATA_POOR_SIGNAL);
//							System.out.println("poorsignal: " + poorsignal);
						} 
						//Raw value
						if (ThinkGear.GetValueStatus(connectionId,
								ThinkGear.DATA_RAW) != 0) {
							raw = ThinkGear.GetValue(connectionId, (int) ThinkGear.DATA_RAW);
							BlinkDetector.AddEEGPoint((int)raw);		
		                    BlinkDetector.FindBlink(poorsignal);
  		                    if(BlinkDetector.ArraySize == 1 && poorsignal == 51)
  		                    {
  		                    	blinkStr = 1;
  		                    }
  		                    else 
  		                    {
  		                    	blinkStr = 0;
  		                    }
						}
						if (ThinkGear.GetValueStatus(connectionId,
								ThinkGear.DATA_RAW) != 0) {
							raw = ThinkGear.GetValue(connectionId, (int) ThinkGear.DATA_RAW);
						}
					} 
					if(packetsRead < 199999)
					{
						packetsRead = 0;
					}
				}
				
				ThinkGear.FreeConnection(connectionId);
				/* End program */
				System.out.println("Read Packets Done Hurray!!!!!! Press Any Key");

				try {
					System.in.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
				
			}}).start();
	}
}
