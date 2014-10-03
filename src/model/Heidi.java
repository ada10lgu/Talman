package model;

import java.util.Observable;

import model.agenda.Agenda;

public class Heidi extends Observable {

	private Agenda agenda;
	private String title = "";

	public enum State {
		IDLE, // Dsek screen
		AGENDA, // Dagordning
		TEXT
	}

	private State state = State.IDLE;

	public Heidi() {
		agenda = new Agenda();
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

	public Agenda getAgenda() {
		return agenda;
	}

}
