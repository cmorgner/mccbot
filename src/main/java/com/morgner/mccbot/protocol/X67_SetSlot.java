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
import com.morgner.mccbot.pdu.SlotData;

/**
 *
 * @author Christian Morgner
 */
public class X67_SetSlot extends Packet {

	@ProtocolField(0)
	private byte windowId = 0;

	@ProtocolField(1)
	private short slotIndex = 0;
	
	@ProtocolField(2)
	private SlotData slotData = null;
	
	public X67_SetSlot() {
		super(0x67);
	}

	@Override
	public String toString() {
		return "SetSlot(" + windowId + ", " + slotIndex + ", " + slotData + ")";
	}
	
	public byte getWindowId() {
		return windowId;
	}

	public void setWindowId(byte windowId) {
		this.windowId = windowId;
	}

	public short getSlotIndex() {
		return slotIndex;
	}

	public void setSlotIndex(short slotIndex) {
		this.slotIndex = slotIndex;
	}

	public SlotData getSlotData() {
		return slotData;
	}

	public void setSlotData(SlotData slotData) {
		this.slotData = slotData;
	}
}
