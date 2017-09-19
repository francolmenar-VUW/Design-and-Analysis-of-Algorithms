package project;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MockController c = new MockController();//controller

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(true);

		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A');
		jf.getKeyListeners()[0].keyPressed(key);

		key = new KeyEvent(jf, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A');
		jf.getKeyListeners()[0].keyReleased(key);

		//key = new KeyEvent(jf, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A');
		jf.getKeyListeners()[0].keyTyped(key);
	}
}
 