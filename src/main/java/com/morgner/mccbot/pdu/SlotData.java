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

package com.morgner.mccbot.pdu;

/**
 * Represents an element of slot data. This class has
 * its own serializer implementation.
 *
 * @author Christian Morgner
 */
public class SlotData {

	private short itemId = -1;
	private byte itemCount = 0;
	private short itemDamage = 0;
	private short dataLength = 0;

	@Override
	public String toString() {
		return "SlotData(" + itemId + ", " + itemCount + ", " + itemDamage + ", " + dataLength + ")";
	}
	
	public short getItemId() {
		return itemId;
	}

	public void setItemId(short itemId) {
		this.itemId = itemId;
	}

	public byte getItemCount() {
		return itemCount;
	}

	public void setItemCount(byte itemCount) {
		this.itemCount = itemCount;
	}

	public short getItemDamage() {
		return itemDamage;
	}

	public void setItemDamage(short itemDamage) {
		this.itemDamage = itemDamage;
	}

	public short getDataLength() {
		return dataLength;
	}

	public void setDataLength(short dataLength) {
		this.dataLength = dataLength;
	}
	
	
}
