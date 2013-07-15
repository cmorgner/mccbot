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

package com.morgner.mccbot.protocol;

import com.morgner.mccbot.core.Packet;
import com.morgner.mccbot.pdu.ProtocolField;

/**
 *
 * @author Christian Morgner
 */
public class X08_UpdateHealth extends Packet {

	@ProtocolField(0)
	private float health = 0.0f;
	
	@ProtocolField(1)
	private short food = 0;
	
	@ProtocolField(2)
	private float saturation = 0;
	
	public X08_UpdateHealth() {
		super(0x08);
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public short getFood() {
		return food;
	}

	public void setFood(short food) {
		this.food = food;
	}

	public float getSaturation() {
		return saturation;
	}

	public void setSaturation(float saturation) {
		this.saturation = saturation;
	}

	@Override
	public boolean doOutput() {
		return true;
	}
}
