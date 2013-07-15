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

import com.morgner.mccbot.core.WorldState;
import com.morgner.mccbot.core.Packet;
import com.morgner.mccbot.pdu.ProtocolField;

/**
 *
 * @author Christian Morgner
 */
public class Xca_PlayerAbilities extends Packet {
	
	@ProtocolField(0)
	private byte flags = 0;
	
	@ProtocolField(1)
	private float flyingSpeed = 0;
	
	@ProtocolField(2)
	private float walkingSpeed = 0;
	
	public Xca_PlayerAbilities() {
		super(0xca);
	}

	@Override
	public String toString() {
		return "PlayerAbilities(" + flags + ", " + flyingSpeed + ", " + walkingSpeed + ")";
	}

	public byte getFlags() {
		return flags;
	}

	public void setFlags(byte flags) {
		this.flags = flags;
	}

	public float getFlyingSpeed() {
		return flyingSpeed;
	}

	public void setFlyingSpeed(float flyingSpeed) {
		this.flyingSpeed = flyingSpeed;
	}

	public float getWalkingSpeed() {
		return walkingSpeed;
	}

	public void setWalkingSpeed(float walkingSpeed) {
		this.walkingSpeed = walkingSpeed;
	}

	@Override
	public void update(WorldState worldState) {
		worldState.setWalkSpeed(walkingSpeed);
		worldState.setFlySpeed(flyingSpeed);
	}
}
