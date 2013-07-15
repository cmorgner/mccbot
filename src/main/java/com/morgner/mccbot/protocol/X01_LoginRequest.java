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

/**
 *
 * @author Christian Morgner
 */
public class X01_LoginRequest extends Packet {

	@ProtocolField(0)
	private int entityId = 0;

	@ProtocolField(1)
	private String worldType = null;
	
	@ProtocolField(2)
	private byte gameMode = 0;
	
	@ProtocolField(3)
	private byte dimension = 0;
	
	@ProtocolField(4)
	private byte difficulty = 0;
	
	@ProtocolField(5)
	private byte unused = 0;
	
	@ProtocolField(6)
	private byte maxPlayers = 0;
	
	public X01_LoginRequest() {
		super(0x01);
	}

	@Override
	public String toString() {
		return "LoginRequest(" + entityId + ", " + worldType + ", " + gameMode + ", " + dimension + ", " + difficulty + ", " + maxPlayers + ")";
	}
	
	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public String getWorldType() {
		return worldType;
	}

	public void setWorldType(String worldType) {
		this.worldType = worldType;
	}

	public byte getGameMode() {
		return gameMode;
	}

	public void setGameMode(byte gameMode) {
		this.gameMode = gameMode;
	}

	public byte getDimension() {
		return dimension;
	}

	public void setDimension(byte dimension) {
		this.dimension = dimension;
	}

	public byte getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(byte difficulty) {
		this.difficulty = difficulty;
	}

	public byte getUnused() {
		return unused;
	}

	public void setUnused(byte unused) {
		this.unused = unused;
	}

	public byte getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(byte maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	
	@Override
	public void update(WorldState worldState) {
		worldState.setPlayerId(entityId);
	}
}
