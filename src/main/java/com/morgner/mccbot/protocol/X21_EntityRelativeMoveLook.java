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
public class X21_EntityRelativeMoveLook extends Packet {

	@ProtocolField(0)
	private int entityId = 0;
	
	@ProtocolField(1)
	private byte dx = 0;
	
	@ProtocolField(2)
	private byte dy = 0;
	
	@ProtocolField(3)
	private byte dz = 0;
	
	@ProtocolField(4)
	private byte yaw = 0;
	
	@ProtocolField(5)
	private byte pitch = 0;
	
	public X21_EntityRelativeMoveLook() {
		super(0x21);
	}
	
	@Override
	public String toString() {
		return "EntityRelativeMoveLook(" + entityId + ", [" + dx + ":" + dy + ":" + dz + "], " + yaw + ", " + pitch + ")";
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public byte getDx() {
		return dx;
	}

	public void setDx(byte dx) {
		this.dx = dx;
	}

	public byte getDy() {
		return dy;
	}

	public void setDy(byte dy) {
		this.dy = dy;
	}

	public byte getDz() {
		return dz;
	}

	public void setDz(byte dz) {
		this.dz = dz;
	}

	public byte getYaw() {
		return yaw;
	}

	public void setYaw(byte yaw) {
		this.yaw = yaw;
	}

	public byte getPitch() {
		return pitch;
	}

	public void setPitch(byte pitch) {
		this.pitch = pitch;
	}
}
