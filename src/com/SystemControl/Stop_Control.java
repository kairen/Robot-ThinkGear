package com.SystemControl;

import java.awt.event.ActionEvent;

public class Stop_Control {
	private Object myobject;
	private ActionEvent e;
	public Stop_Control(Object myobject ,ActionEvent e){
		this.myobject = myobject;
		this.e = e;
		System.out.println("Onclick Stop_Button");
	}
}
