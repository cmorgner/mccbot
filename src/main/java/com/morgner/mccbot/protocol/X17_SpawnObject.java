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

import com.morgner.mccbot.pdu.EntityMetadata;
import com.morgner.mccbot.pdu.ObjectData;
import com.morgner.mccbot.core.Packet;
import com.morgner.mccbot.pdu.ProtocolField;

/**
 *
 * @author Christian Morgner
 */
public class X17_SpawnObject extends Packet {

	@ProtocolField(0)
	private int entityId = 0;
	
	@ProtocolField(1)
	private byte type = 0;
	
	@ProtocolField(2)
	private int x = 0;
	
	@ProtocolField(3)
	private int y = 0;
	
	@ProtocolField(4)
	private int z = 0;
	
	@ProtocolField(5)
	private byte pitch = 0;
	
	@ProtocolField(6)
	private byte yaw = 0;
	
	@ProtocolField(7)
	private ObjectData objectData = null;
	
	public X17_SpawnObject() {
		super(0x17);
	}
	
	@Override
	public String toString() {
		return "SpawnObject(" + entityId + ", " + type + ", [" + x + ":" + y + ":" + z + "], " + pitch + ", " + yaw + ", " + objectData + ")";
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public byte getPitch() {
		return pitch;
	}

	public void setPitch(byte pitch) {
		this.pitch = pitch;
	}

	public byte getYaw() {
		return yaw;
	}

	public void setYaw(byte yaw) {
		this.yaw = yaw;
	}

	public ObjectData getObjectData() {
		return objectData;
	}

	public void setObjectData(EntityMetadata metaData) {
		this.objectData = objectData;
	}
}
