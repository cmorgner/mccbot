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
public class X1c_EntityVelocity extends Packet {

	@ProtocolField(0)
	private int entityId = 0;
	
	@ProtocolField(1)
	private short vx = 0;
	
	@ProtocolField(2)
	private short vy = 0;
	
	@ProtocolField(3)
	private short vz = 0;
	
	public X1c_EntityVelocity() {
		super(0x1c);
	}
	
	@Override
	public String toString() {
		return "EntityVelocity(" + entityId + ", [" + vx + ":" + vy + ":" + vz + "])";
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
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
