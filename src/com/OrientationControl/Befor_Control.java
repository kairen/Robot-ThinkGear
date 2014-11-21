package com.OrientationControl;

import java.awt.event.ActionEvent;

public class Befor_Control {

	private Object myobject;
	private ActionEvent e;
	
	public Befor_Control(Object myobject ,ActionEvent e){
		this.myobject = myobject;
		this.e = e;
		
		System.out.println("Onclick Befor_Button");
	}
}
