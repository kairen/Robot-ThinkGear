package com.SystemControl;

import javax.swing.JFrame;

public class Finish_Control {
	private Object myobject ;
	public Finish_Control(Object myobject){
		this.myobject = myobject;
		System.out.println("Onclick Finish_Button");
	}
	
	public void setFinish(){
		System.exit(0);
	}
}
