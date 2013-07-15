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
import com.morgner.mccbot.pdu.Chunk;
import com.morgner.mccbot.pdu.ProtocolField;
import java.io.IOException;

/**
 *
 * @author Christian Morgner
 */
public class X33_ChunkData extends ChunkPacket {

	@ProtocolField(0)
	private int x = 0;
	
	@ProtocolField(1)
	private int y = 0;
	
	@ProtocolField(2)
	private boolean groundUpContinuous = false;
	
	@ProtocolField(3)
	private short primaryBitmask = 0;
	
	@ProtocolField(4)
	private short addBitmask = 0;
	
	@ProtocolField(5)
	private Chunk chunk = null;

	public X33_ChunkData() {
		super(0x33);
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

	public boolean isGroundUpContinuous() {
		return groundUpContinuous;
	}

	public void setGroundUpContinuous(boolean groundUpContinuous) {
		this.groundUpContinuous = groundUpContinuous;
	}

	public short getPrimaryBitmask() {
		return primaryBitmask;
	}

	public void setPrimaryBitmask(short primaryBitmask) {
		this.primaryBitmask = primaryBitmask;
	}

	public short getAddBitmask() {
		return addBitmask;
	}

	public void setAddBitmask(short addBitmask) {
		this.addBitmask = addBitmask;
	}

	public Chunk getChunk() {
		return chunk;
	}

	public void setChunk(Chunk chunk) {
		this.chunk = chunk;
	}
	
	@Override
	public void update(WorldState worldState) {
		
		try {
			parseChunk(worldState, chunk);
			
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}
}
