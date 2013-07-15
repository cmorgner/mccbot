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
import com.morgner.mccbot.util.Vector3d;

/**
 *
 * @author Christian Morgner
 */
public class X35_BlockChange extends Packet {

	@ProtocolField(0)
	private int x = 0;
	
	@ProtocolField(1)
	private byte y = 0;
	
	@ProtocolField(2)
	private int z = 0;
	
	@ProtocolField(3)
	private short type = 0;
	
	@ProtocolField(4)
	private byte metaData = 0;
	
	public X35_BlockChange() {
		super(0x35);
	}
	
	@Override
	public String toString() {
		return "BlockChange([" + x + ":" + y + ":" + z + "], " + type + ", " + metaData + ")";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public byte getY() {
		return y;
	}

	public void setY(byte y) {
		this.y = y;
	}
	
	public Vector3d get() {
		return new Vector3d(x, y, z);
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public byte getMetaData() {
		return metaData;
	}

	public void setMetaData(byte metaData) {
		this.metaData = metaData;
	}
	
	@Override
	public void update(WorldState state) {

		Vector3d v    = get();
		byte byteType = (byte)(type & 255);
		
		state.setBlock(v, byteType);
		
	}
}
