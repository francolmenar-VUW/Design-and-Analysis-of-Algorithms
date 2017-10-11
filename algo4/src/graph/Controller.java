package graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;



public class Controller implements ActionListener{
	Model model;
	private final String NEW_SIZE = "New Size";

	public Controller(Model m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource() instanceof JButton) {//All the actions clicking a JButton
				actionsOfJButton(e);
		}
		 else {
			 actionsSent(e);
		 }
	}

	/**
	 * I check all the possible actions that a JButton can make
	 * @param e the event
	 */
	private void actionsOfJButton(ActionEvent e) {
		JButton aux = (JButton)e.getSource();
		if(aux.getName() == "Timing") {
			model.timing();
		} else if(aux.getName() == "Set size") {
			model.setSize();
		} else if(aux.getName() == "Start") {
			model.start();
		}
		else if(aux.getName() == "Graph") {
			model.graph();
		}
	}

	/**
	 * It checks the action that has been sent
	 * @param e the event
	 */
	private void actionsSent(ActionEvent e) {
		if(e.getActionCommand().equals("New Size")) {
			model.newSize(e.getID());//I pass the new size to the model
		}
	}

	public String getNEW_SIZE() {
		return NEW_SIZE;
	}

}
