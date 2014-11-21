package com.OrientationControl;

import java.awt.event.ActionEvent;

public class Pause_Control {
	private Object myobject;
	private ActionEvent e;
	public Pause_Control(Object myobject ,ActionEvent e){
		this.myobject = myobject;
		this.e = e;
		System.out.println("Onclick Pause_Button");
	}
}
