package model;

import java.io.FileNotFoundException;
import java.util.Observable;

import settings.Settings;
import model.agenda.Agenda;
import model.annex.AnnexList;
import model.claim.ClaimList;

public class TalmanModel extends Observable {

	private Agenda agenda;
	private ClaimList cl;
	private AnnexList al;
	private Settings settings;

	public enum State {
		IDLE, // Dsek screen
		AGENDA, // Dagordning
		ANNEX, // Bilagor
		CLAIM, // Yrkande
		SING, // Sång - Rosa på bal
		TEXT
	}

	private State state = State.IDLE;

	public TalmanModel(Settings settings) throws FileNotFoundException {
		agenda = new Agenda(this);
		cl = new ClaimList();
		al = new AnnexList(settings);
		this.settings = settings;
	}

	public synchronized String getTitle() {
		return settings.getTitle();
	}

	public synchronized void setTitle(String title) {
		settings.updateTitle(title);
		setChanged();
		notifyObservers();
	}

	public synchronized void setState(State state) {
		this.state = state;
		setChanged();
		notifyObservers();
	}

	public synchronized State getState() {
		return state;
	}

	public synchronized Agenda getAgenda() {
		return agenda;
	}

	public synchronized ClaimList getClaimList() {
		return cl;
	}

	public synchronized AnnexList getAnnexList() {
		return al;
	}
	
	public synchronized String getParagraphSign() {
		return settings.getParagraph();
	}
	
	public synchronized String getAnnexName() {
		return settings.getAgenda();
	}

}
