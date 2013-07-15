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
public class X3d_Effect extends Packet {

	@ProtocolField(0)
	private int effectId = 0;
	
	@ProtocolField(1)
	private int x = 0;
	
	@ProtocolField(2)
	private byte y = 0;
	
	@ProtocolField(3)
	private int z = 0;
	
	@ProtocolField(4)
	private int data = 0;
	
	@ProtocolField(5)
	private boolean disableRelativeVolume = false;
	
	public X3d_Effect() {
		super(0x3d);
	}
	
	@Override
	public String toString() {
		return "Effect(" + effectId + ", [" + x + ":" + y + ":" + z + "], " + data + ", " + disableRelativeVolume + ")";
	}

	public int getEffectId() {
		return effectId;
	}

	public void setEffectId(int effectId) {
		this.effectId = effectId;
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

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public boolean isDisableRelativeVolume() {
		return disableRelativeVolume;
	}

	public void setDisableRelativeVolume(boolean disableRelativeVolume) {
		this.disableRelativeVolume = disableRelativeVolume;
	}
}
