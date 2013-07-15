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
public class X64_OpenWindow extends Packet {
	
	@ProtocolField(0)
	private byte windowId = 0;
	
	@ProtocolField(1)
	private byte inventoryType = 0;
	
	@ProtocolField(2)
	private String windowTitle = null;
	
	@ProtocolField(3)
	private byte numSlots = 0;
	
	@ProtocolField(4)
	private boolean useProvidedTitle = false;
	
	public X64_OpenWindow() {
		super(0x64);
	}

	@Override
	public String toString() {
		return "OpenWindow(" + windowId + ", " + inventoryType + ", " + windowTitle + ", " + numSlots + ", " + useProvidedTitle + ")";
	}

	public byte getWindowId() {
		return windowId;
	}

	public void setWindowId(byte windowId) {
		this.windowId = windowId;
	}

	public byte getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(byte inventoryType) {
		this.inventoryType = inventoryType;
	}

	public String getWindowTitle() {
		return windowTitle;
	}

	public void setWindowTitle(String windowTitle) {
		this.windowTitle = windowTitle;
	}

	public byte getNumSlots() {
		return numSlots;
	}

	public void setNumSlots(byte numSlots) {
		this.numSlots = numSlots;
	}

	public boolean isUseProvidedTitle() {
		return useProvidedTitle;
	}

	public void setUseProvidedTitle(boolean useProvidedTitle) {
		this.useProvidedTitle = useProvidedTitle;
	}
}
