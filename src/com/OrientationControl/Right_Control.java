package com.OrientationControl;

import java.awt.event.ActionEvent;

public class Right_Control {
	private Object myobject;
	private ActionEvent e;
	public Right_Control(Object myobject ,ActionEvent e){
		this.myobject = myobject;
		this.e = e;
		System.out.println("Onclick Right_Button");
	}
}
