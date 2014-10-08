package model;

import java.util.Observable;

import model.agenda.Agenda;
import model.annex.AnnexList;
import model.claim.ClaimList;

public class Heidi extends Observable {

	private Agenda agenda;
	private ClaimList cl;
	private AnnexList al;
	private String title = "";

	public enum State {
		IDLE, // Dsek screen
		AGENDA, // Dagordning
		ANNEX, // Bilagor
		CLAIM, // Yrkande
		TEXT
	}

	private State state = State.IDLE;

	public Heidi() {
		agenda = new Agenda();
		cl = new ClaimList();
		al = new AnnexList();
	}

	public synchronized String getTitle() {
		return title;
	}

	public synchronized void setTitle(String title) {
		this.title = title;
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

}
