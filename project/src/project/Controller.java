package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements ActionListener,KeyListener {
	//Model myModel;

	public Controller() {
	}

	/*
	public Controller(ModelInfo m) {
		myModel = m;
	}
	*/

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("typed");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE: {
			//exit();//method of the model
			break;
		}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
