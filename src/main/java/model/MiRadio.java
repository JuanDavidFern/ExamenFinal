package model;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JRadioButton;

public class MiRadio extends JRadioButton {
	private int i;
	
	public MiRadio(String text, int i) {
		super(text);
		this.i = i;
	}

	public MiRadio() {
		// TODO Auto-generated constructor stub
	}

	public MiRadio(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	public MiRadio(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public MiRadio(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public MiRadio(Icon icon, boolean selected) {
		super(icon, selected);
		// TODO Auto-generated constructor stub
	}

	public MiRadio(String text, boolean selected) {
		super(text, selected);
		// TODO Auto-generated constructor stub
	}

	public MiRadio(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}

	public MiRadio(String text, Icon icon, boolean selected) {
		super(text, icon, selected);
		// TODO Auto-generated constructor stub
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
	
	

}
