
import java.awt.event.ActionEvent;



public class Connection_Control {
	private Object myobject;
	private ActionEvent e;
	public Connection_Control(Object myobject ,ActionEvent e){
		this.myobject = myobject;
		this.e = e;
		MAIN.Connection();
	   
		
		System.out.println("Onclick Connection_Button");
	}
}
