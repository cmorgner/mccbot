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
import com.morgner.mccbot.core.Packet;
import com.morgner.mccbot.pdu.ProtocolField;

/**
 *
 * @author Christian Morgner
 */
public class X18_SpawnMob extends Packet {

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
	private byte headPitch = 0;
	
	@ProtocolField(7)
	private byte yaw = 0;
	
	@ProtocolField(8)
	private short vx = 0;
	
	@ProtocolField(9)
	private short vy = 0;
	
	@ProtocolField(10)
	private short vz = 0;
	
	@ProtocolField(11)
	private EntityMetadata metaData = null;
	
	public X18_SpawnMob() {
		super(0x18);
	}
	
	@Override
	public String toString() {
		return "SpawnMob(" + entityId + ", " + type + ", [" + x + ":" + y + ":" + z + "], " + pitch + ", " + headPitch + ", " + yaw + ", [" + vx + ":" + vy + ":" + vz + "], " + metaData + ")";
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

	public EntityMetadata getMetaData() {
		return metaData;
	}

	public void setMetaData(EntityMetadata metaData) {
		this.metaData = metaData;
	}

	public byte getHeadPitch() {
		return headPitch;
	}

	public void setHeadPitch(byte headPitch) {
		this.headPitch = headPitch;
	}
}
