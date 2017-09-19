package project;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.Test;

public class ControllerTest {
	MockController c = new MockController();//controller

	@Test
	/**
	 * It test that when a button is pressed the correct method is executed
	 */
	public void actionPerformedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JButton button = new JButton();//I create the button which will be pressed
		button.addActionListener(c);
		button.doClick();//I click the button

		String message = baos.toString();//I get the string which is supposed to be printed

		assertEquals(message, "perform\n");
	}

	@Test
	/**
	 * It test that when a button is pressed the correct method is executed
	 */
	public void keyPressedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(false);


		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A');
		jf.getKeyListeners()[0].keyPressed(key);

		String message = baos.toString();//I get the string which is supposed to be printed

		jf.dispose();

		assertEquals(message, "pressed\n");
	}

	@Test
	/**
	 * It test that when a key is typed the correct method is executed
	 */
	public void keyTypedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(true);

		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A');
		jf.getKeyListeners()[0].keyPressed(key);

		key = new KeyEvent(jf, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A');
		jf.getKeyListeners()[0].keyReleased(key);

		System.setOut(new PrintStream(baos));//I set the
		jf.getKeyListeners()[0].keyTyped(key);


		String message = baos.toString();//I get the string which is supposed to be printed

		assertEquals(message, "typed\n");
	}

	@Test
	/**
	 * It test that when a key is released the correct method is executed
	 */
	public void keyReleasedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(true);

		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A');
		jf.getKeyListeners()[0].keyReleased(key);

		String message = baos.toString();//I get the string which is supposed to be printed

		assertEquals(message, "released\n");
	}
}
