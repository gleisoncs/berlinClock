package com.gleisoncs.berlinclock.vo;

import java.util.ArrayList;
import java.util.List;

import com.gleisoncs.berlinclock.utils.LampStatus;

/**
 * <p>Representation of a row in Berlin Clock. Each row has one or more lamps</p>
 * 
 * @author gleisoncs
 *
 */
public class Row {
	private List<Lamp> lamps = new ArrayList<Lamp>();

	public Row(int size, LampStatus lampStatus, int numOn, int iteract, LampStatus lampIteract) {
		for (int i = 0; i < size; i++) {
			Lamp lamp = new Lamp();
			if (i < numOn) {
				lamp.setColor(i % iteract == 2 ? lampIteract : lampStatus);
			} else
				lamp.setColor(LampStatus.OFF);
			lamps.add(lamp);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		lamps.stream().forEach(lamp -> sb.append(lamp.getColor().toString()));
		sb.append("\n");
		return sb.toString();
	}
}