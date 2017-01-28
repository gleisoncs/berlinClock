package com.gleisoncs.berlinclock.utils;

/**
 * LampStatus represents a ENUM with three colors in a Berlin Clock
 * 
 * @author gleisoncs
 *
 */
public enum LampStatus {

	RED("R"), YELLOW("Y"), OFF("O");

	private final String lcolour;

	private LampStatus(String scolor) {
		this.lcolour = scolor;
	}

	public String toString() {
		return lcolour;
	}
}