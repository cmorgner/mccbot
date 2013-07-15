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
import com.morgner.mccbot.pdu.BulkChunk;
import com.morgner.mccbot.pdu.ProtocolField;
import java.io.IOException;

/**
 *
 * @author Christian Morgner
 */
public class X38_BulkChunkData extends ChunkPacket {

	@ProtocolField(0)
	private BulkChunk bulkChunkData = null;

	public X38_BulkChunkData() {
		super(0x38);
	}
	
	@Override
	public String toString() {
		return "BulkChunkData(" + bulkChunkData + ")";
	}

	public BulkChunk getBulkChunkData() {
		return bulkChunkData;
	}

	public void setBulkChunkData(BulkChunk bulkChunkData) {
		this.bulkChunkData = bulkChunkData;
	}
	
	@Override
	public void update(WorldState worldState) {
		
		try {
			parseChunk(worldState, bulkChunkData);
			
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}
}
