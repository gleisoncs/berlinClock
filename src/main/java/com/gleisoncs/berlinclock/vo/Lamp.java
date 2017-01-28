package com.gleisoncs.berlinclock.vo;

import com.gleisoncs.berlinclock.utils.LampStatus;

/**
 * <p>Representation of a lamp object in Berlin clock. Each lamp has 
 * one LampStatus </p>
 * 
 * @author gleisoncs
 *
 */
public class Lamp {
	private LampStatus color;

	public LampStatus getColor() {
		return color;
	}

	public void setColor(LampStatus color) {
		this.color = color;
	}
}