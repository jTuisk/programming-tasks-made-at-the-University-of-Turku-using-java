package fi.utu.tech.entities;

import java.io.Serializable;
import java.time.Instant;

/*
 * Entity class presenting a WakeUpGroup. The class is not complete.
 * You need to add some variables.
 */

public class WakeUpGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private Integer ID;
	private Instant alarmTime;
	private boolean isRaining;
	private boolean belowZero;

	public WakeUpGroup(Integer id, String name, Instant alarmTime, boolean canRain, boolean belowZero) {
		super();
		this.ID = id;
		this.name = name;
		this.alarmTime = alarmTime;
		this.isRaining = canRain;
		this.belowZero = belowZero;
	}

	public String getName() {
		return this.name;
	}

	public Integer getID() {
		return this.ID;
	}

	public Instant getAlarmTime(){ return this.alarmTime; }

	public boolean getIsRaining(){ return this.isRaining; }

	public boolean getBelowZero(){ return this.belowZero; }

	public void setName(String name) {
		this.name = name;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public boolean equals(Object o){
		if(!(o instanceof WakeUpGroup))
			return false;

		WakeUpGroup wug = (WakeUpGroup) o;
		return this.ID.equals(wug.getID());
	}
}
