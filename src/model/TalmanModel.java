package model;

import java.io.FileNotFoundException;
import java.util.Observable;

import model.agenda.Agenda;
import model.annex.AnnexList;
import model.claim.ClaimList;
import model.election.ElectionList;
import model.person.PersonList;
import settings.Settings;

public class TalmanModel extends Observable {

	private Agenda agenda;
	private ClaimList cl;
	private AnnexList al;
	private PersonList pl;
	private ElectionList el;
	private Settings settings;

	public enum State {
		IDLE, // Dsek screen
		AGENDA, // Dagordning
		ANNEX, // Bilagor
		CLAIM, // Yrkande
		SING, // Sång - Rosa på bal
		ELECTION, // Valskärm
		TEXT
	}

	private State state = State.IDLE;

	public TalmanModel(Settings settings) throws FileNotFoundException {
		this.settings = settings;

		agenda = new Agenda(this);
		cl = new ClaimList(settings);
		al = new AnnexList(settings);
		pl = new PersonList(settings);
		el = new ElectionList(settings, pl);
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

	public synchronized PersonList getPersonList() {
		return pl;
	}

	public synchronized ElectionList getElectionList() {
		return el;
	}

	public synchronized String getParagraphSign() {
		return settings.getParagraph();
	}

	public synchronized String getAnnexName() {
		return settings.getAgenda();
	}

}
