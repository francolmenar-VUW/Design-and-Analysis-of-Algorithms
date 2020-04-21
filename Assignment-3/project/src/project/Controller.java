package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

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
		 if(e.getSource() instanceof JButton) {//All the actions clicking a JButton
				actionsOfJButton(e);
			}
	}

	/**
	 * I check all the possible actions that a JButton can make
	 * @param e the event
	 */
	private void actionsOfJButton(ActionEvent e) {
		JButton aux = (JButton)e.getSource();
		if(aux.getName() == "Save") {
			//save();
		} else if(aux.getName() == "Load") {
			//load();
		}  else if(aux.getName() == "Exit") {
			//exit();
		} else if(aux.getName() == "Resume") {
			//resume();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE) {
			//exit();
		} else if ((keyCode == KeyEvent.VK_UP) || (keyCode == KeyEvent.VK_W)){
			//up();
		} else if ((keyCode == KeyEvent.VK_DOWN) || (keyCode == KeyEvent.VK_S)){
			//down();
		} else if ((keyCode == KeyEvent.VK_RIGHT) || (keyCode == KeyEvent.VK_D)){
			//right();
		} else if ((keyCode == KeyEvent.VK_LEFT) || (keyCode == KeyEvent.VK_A)){
			//left();
		} else if(keyCode == KeyEvent.VK_SPACE) {
			//interact();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
