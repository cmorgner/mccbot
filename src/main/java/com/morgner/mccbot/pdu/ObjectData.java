/**
 * Copyright (C) 2010-2013 Christian Morgner <christian@morgner.de>
 *
 * This file is part of MCCBot <https://github.com/cmorgner/mccbot>.
 *
 * MCCBot is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * MCCBot is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with MCCBot.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.morgner.mccbot.pdu;

/**
 * Represents object data in the minecraft protocol.
 * This class has its own serializer implementation.
 * 
 * @author Christian Morgner
 */
public class ObjectData {

	private int value = 0;
	private short vx = 0;
	private short vy = 0;
	private short vz = 0;

	@Override
	public String toString() {
		return "ObjectData(" + value + ", [" + vx + ":" + vy + ":" + vz + "])";
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public short getVx() {
		return vx;
	}

	public void setVx(short vx) {
		this.vx = vx;
	}

	public short getVy() {
		return vy;
	}

	public void setVy(short vy) {
		this.vy = vy;
	}

	public short getVz() {
		return vz;
	}

	public void setVz(short vz) {
		this.vz = vz;
	}
}
