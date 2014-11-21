package com.OrientationControl;

import java.awt.event.ActionEvent;

public class After_Control {
	private Object myobject;
	private ActionEvent e;
	public After_Control(Object myobject ,ActionEvent e){
		
		this.myobject = myobject;
		this.e = e;
		System.out.println("Onclick After_Button");
		
	}
}
