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
import com.morgner.mccbot.pdu.BulkChunk;
import com.morgner.mccbot.pdu.Chunk;
import com.morgner.mccbot.util.Vector3d;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.zip.InflaterInputStream;

/**
 *
 * @author Christian Morgner
 */
public abstract class ChunkPacket extends Packet {

	public ChunkPacket(int packetId) {
		super(packetId);
	}
	
	protected void parseChunk(WorldState worldState, Chunk chunk) throws IOException {

		BufferedInputStream chunkStream = new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(chunk.getData())));
		parseChunk(worldState, chunk.getX(), chunk.getY(), chunk.getZ(), 0xffffffff, 0, chunkStream, true);
	}
	
	protected void parseChunk(WorldState worldState, BulkChunk bulkChunk) throws IOException {
	
		BufferedInputStream chunkStream = new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(bulkChunk.getData())));
		DataInputStream metaStream      = new DataInputStream(new ByteArrayInputStream(bulkChunk.getMetaData()));

		for (int i=0; i<bulkChunk.getChunkCount(); i++) {

			int x              = metaStream.readInt() << 4;
			int z              = metaStream.readInt() << 4;
			int primaryBitmask = metaStream.readUnsignedShort();
			int addBitmask     = metaStream.readUnsignedShort();

			parseChunk(worldState, x, 0, z, primaryBitmask, addBitmask, chunkStream, true);
		}
	}
	
	protected void parseChunk(WorldState worldState, int x, int y, int z, int primaryBitmask, int addBitmask, BufferedInputStream chunkStream, boolean containsSky) throws IOException {
		
		Vector3d v = new Vector3d();
		
		for (int j=0; j<16; j++) {

			byte[] blockTypes = new byte[4096];
			int bit           = 1 << j;

			if ((primaryBitmask & bit) == bit) {

				chunkStream.read(blockTypes, 0, 4096);

				v.set(x, y + j << 4, z);
				worldState.setChunk(v, blockTypes);
			}
		}

		for (int j=0; j<16; j++) {

			byte[] blockMetaData = new byte[2048];
			int bit         = 1 << j;

			if ((primaryBitmask & bit) == bit) {
				chunkStream.read(blockMetaData, 0, 2048);
			}
		}

		for (int j=0; j<16; j++) {

			byte[] blockLight = new byte[2048];
			int bit       = 1 << j;

			if ((primaryBitmask & bit) == bit) {
				chunkStream.read(blockLight, 0, 2048);
			}
		}

		if (containsSky) {

			for (int j=0; j<16; j++) {

				byte[] sky  = new byte[2048];
				int bit     = 1 << j;

				if ((primaryBitmask & bit) == bit) {
					chunkStream.read(sky, 0, 2048);
				}
			}
		}

		for (int j=0; j<16; j++) {

			byte[] addArray = new byte[2048];
			int bit         = 1 << j;

			if ((addBitmask & bit) == bit) {
				chunkStream.read(addArray, 0, 2048);
			}
		}

		byte[] biomeArray = new byte[256];
		chunkStream.read(biomeArray, 0, 256);
	}
}
