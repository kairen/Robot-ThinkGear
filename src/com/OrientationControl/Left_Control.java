package com.OrientationControl;

import java.awt.event.ActionEvent;

public class Left_Control {
	private Object myobject;
	private ActionEvent e;
	public Left_Control(Object myobject ,ActionEvent e){
		this.myobject = myobject;
		this.e = e;
		System.out.println("Onclick Left_Button");
	}
}
