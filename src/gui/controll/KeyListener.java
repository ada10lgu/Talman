package gui.controll;

import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.JFrame;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyListener implements NativeKeyListener {

	private TreeMap<String, ActionListener> binds;
	private JFrame window;

	public KeyListener(JFrame window) {
		this.window = window;
		binds = new TreeMap<>();
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err
					.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}
		GlobalScreen.getInstance().addNativeKeyListener(this);
	}

	public void addActionListner(String key, ActionListener al) {
		binds.put(key, al);
	}

	public void nativeKeyPressed(NativeKeyEvent e) {
		String key = NativeKeyEvent.getKeyText(e.getKeyCode());
		if (window != null && window.isFocused()) {
			ActionListener al = binds.get(key);
			if (al != null)
				al.actionPerformed(null);
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
	}

}
