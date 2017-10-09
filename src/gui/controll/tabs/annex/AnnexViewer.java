package gui.controll.tabs.annex;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import model.annex.Annex;
import model.annex.AnnexList;

@SuppressWarnings("serial")
public class AnnexViewer extends JPanel {

	private AnnexList al;
	private JLabel title;
	private Annex active;
	private ScaleScroll sc;
	private PositionScroll ps;
	private PageScroll pg;
	private JLabel zoom;
	private JLabel possition;
	private JLabel page;

	public AnnexViewer(AnnexList al) {
		this.al = al;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		title = new JLabel();
		zoom = new JLabel("Scale");
		sc = new ScaleScroll();
		possition = new JLabel("Possition");
		ps = new PositionScroll();
		page = new JLabel("Page");
		pg = new PageScroll();

		add(title);
		add(zoom);
		add(sc);
		add(possition);
		add(ps);
		add(page);
		add(pg);

	}

	public void showClaim(Annex a) {
		active = a;
		al.setActive(a);
		title.setText(a.getTitle());
		pg.setMaximum(a.getNumberOfPages() - 1);
	}

	private class ScaleScroll extends JScrollBar implements AdjustmentListener,
			Observer {

		public ScaleScroll() {
			super(JScrollBar.HORIZONTAL);
			addAdjustmentListener(this);
			setMinimum(1);
			setMaximum(410);
			setValue(100);
			al.addObserver(this);
		}

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			if (active != null)
				active.setZoom(getValue() / 100.0f);
			zoom.setText("Scale " + getValue() + "%");

		}

		@Override
		public void update(Observable arg0, Object arg1) {
			setValue((int) (100 * active.getZoom()));

		}

	}

	private class PositionScroll extends JScrollBar implements
			AdjustmentListener, Observer {

		public PositionScroll() {
			super(JScrollBar.HORIZONTAL);
			addAdjustmentListener(this);
			setMinimum(0);
			setMaximum(100);
			setValue(100);
			al.addObserver(this);
		}

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			if (active != null)
				active.setPossition(getValue());
			possition.setText("Possition " + getValue() + "%");
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			setValue(active.getPossition());

		}

	}

	private class PageScroll extends JScrollBar implements AdjustmentListener,
			Observer {

		public PageScroll() {
			super(JScrollBar.HORIZONTAL);
			addAdjustmentListener(this);
			setMinimum(0);
			setMaximum(0);
			setValue(100);
			al.addObserver(this);
		}

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			if (active != null)
				active.setPage(getValue());
			page.setText("Page " + (getValue() + 1));
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			setValue(active.getPage());
		}
	}

}
