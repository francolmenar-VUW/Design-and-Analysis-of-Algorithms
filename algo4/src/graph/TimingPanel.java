package graph;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class TimingPanel extends JFrame implements ActionListener{
	private String text;
	protected JTextPane textArea;//The place where the text is going to be placed


	public TimingPanel(long result) {

	text = "         The result of the timing is " + result + " miliseconds";
	textArea = new JTextPane();
	textArea.setText(text);
	textArea.setEditable(false);
	textArea.setAlignmentX(CENTER_ALIGNMENT);

	this.add(textArea);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    pack();
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

	this.setMinimumSize(new Dimension(350, 100));
    this.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
