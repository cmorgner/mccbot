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

import com.morgner.mccbot.pdu.NBTData;
import com.morgner.mccbot.core.Packet;
import com.morgner.mccbot.pdu.ProtocolField;

/**
 *
 * @author Christian Morgner
 */
public class X84_UpdateTileEntity extends Packet {

	@ProtocolField(0)
	private int x = 0;

	@ProtocolField(1)
	private short y = 0;

	@ProtocolField(2)
	private int z = 0;

	@ProtocolField(3)
	private byte action = 0;
	
	@ProtocolField(4)
	private NBTData nbtData = null;
	
	public X84_UpdateTileEntity() {
		super(0x84);
	}

	@Override
	public String toString() {
		return "UpdateTileEntity([" + x + ":" + y + ":" + z + "], " + action + ", " + nbtData + ")";
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public short getY() {
		return y;
	}

	public void setY(short y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public byte getAction() {
		return action;
	}

	public void setAction(byte action) {
		this.action = action;
	}

	public NBTData getNbtData() {
		return nbtData;
	}

	public void setNbtData(NBTData nbtData) {
		this.nbtData = nbtData;
	}
}
