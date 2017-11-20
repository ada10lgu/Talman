package gui.controll;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

@SuppressWarnings("serial")
public class Clock extends JLabel {

	public Clock() {
		new Ticker().execute();
		setFont(new Font("Verdana", Font.PLAIN, 20));
	}

	private class Ticker extends SwingWorker<Void, Void> {

		@Override
		protected Void doInBackground() throws Exception {
			while (true) {
				Thread.sleep(100);
				publish();
			}
		}

		@Override
		protected void process(List<Void> chunks) {
			SimpleDateFormat dt1 = new SimpleDateFormat("HH:mm:ss");
			setText(dt1.format(new Date()));
		}
	}
}
