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
import com.morgner.mccbot.core.World;
import com.morgner.mccbot.pdu.ProtocolField;
import java.io.IOException;

/**
 *
 * @author Christian Morgner
 */
public class X0c_PlayerLook extends Packet{

	@ProtocolField(0)
	private float yaw = 0;

	@ProtocolField(1)
	private float pitch = 0;
	
	@ProtocolField(2)
	private boolean onGround = false;
	
	public X0c_PlayerLook() {
		super(0x0c);
	}
	
	@Override
	public String toString() {
		return "PlayerLook(" + yaw + ", " + pitch + ", " + onGround + ")";
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public boolean isOnGround() {
		return onGround;
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}
	
	@Override
	public void respond(World world) throws IOException {
		world.send(this);
	}
	
	@Override
	public void update(WorldState worldState) {
		worldState.setPosition(yaw, pitch, onGround);
	}
}
