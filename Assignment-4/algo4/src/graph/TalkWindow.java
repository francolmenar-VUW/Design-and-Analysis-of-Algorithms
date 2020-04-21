package graph;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class TalkWindow extends JDialog implements PropertyChangeListener, ActionListener{

	private Controller control;
	private String typedText = null;
    private JTextField textField;
    private JOptionPane optionPane;
    private String btnString1 = "Enter";
    private String btnString2 = "Cancel";

    /**
     * Creates the reusable dialog.
     */
    public TalkWindow(Frame frame, Controller c) {
        super(frame, true);
        this.control = c;
        setTitle("Select the size of the board");

        textField = new JTextField(10);

        //Create an array of the text and components to be displayed.
        String msgString1 = "Enter the desire size(Example: \n9 creates a board of 9x9)";
        Object[] array = {msgString1, textField};

        //Create an array specifying the number of dialog buttons
        //and their text.
        Object[] options = {btnString1, btnString2};

        //Create the JOptionPane.
        optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION,
                null, options, options[0]);

        //Make this dialog display it.
        setContentPane(optionPane);

        //Handle window closing correctly.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        //Ensure the text field always gets the first focus.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent ce) {
                textField.requestFocusInWindow();
            }
        });

        //Register an event handler that puts the text into the option pane.
        textField.addActionListener(this);

        //Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
        pack();

    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    	this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

    }

    /**
     * This method handles events for the text field.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnString1);
    }

    /**
     * This method reacts to state changes in the option pane.
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();

        if (isVisible()
                && (e.getSource() == optionPane)
                && (JOptionPane.VALUE_PROPERTY.equals(prop)
                || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                //ignore reset
                return;
            }

            //Reset the JOptionPane's value.
            //If you don't do this, then if the user
            //presses the same button next time, no
            //property change event will be fired.
            optionPane.setValue(
                    JOptionPane.UNINITIALIZED_VALUE);

            if (btnString1.equals(value)) {
            	notClosed();
            } else { //user closed dialog or clicked cancel
            	dispose();
            }
        }
    }

    /**
     * Actions when the dialog is not closed by the user
     */
	private void notClosed() {
    	 typedText = textField.getText();
    	 if(isInteger(typedText)) {
    		 sendEvent(Integer.parseInt(typedText), control.getNEW_SIZE());
    		 dispose();
    	 }
	}

	/**
	 * It checks if a string can be converted into a integer
	 * @param s: the string to convert
	 * @return true if the string can be converted and false otherwise
	 */
	public static boolean isInteger(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch(NumberFormatException e) {
	        return false;
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}

	/**
     * It sends to the controller a specific event
     *
     * @param string: the name of the event
     * @param id: the value which wants to be passed
     */
    private void sendEvent(int id, String string) {
    	Object aux = new Object();
		ActionEvent event = new ActionEvent(aux, id, string);//I create the event
		control.actionPerformed(event);//I send the event to the controller
	}
}
