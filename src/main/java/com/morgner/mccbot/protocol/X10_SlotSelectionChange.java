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
public class X10_SlotSelectionChange extends Packet {
	
	@ProtocolField(0)
	private short slotId = 0;
	
	public X10_SlotSelectionChange() {
		super(0x10);
	}
	
	public X10_SlotSelectionChange(short slotId) {
		super(0x10);
		
		this.slotId = slotId;
	}

	@Override
	public String toString() {
		return "SlotSelectionChange(" + slotId + ")";
	}
	
	public short getSlotId() {
		return slotId;
	}

	public void setSlotId(short slotId) {
		this.slotId = slotId;
	}
}
