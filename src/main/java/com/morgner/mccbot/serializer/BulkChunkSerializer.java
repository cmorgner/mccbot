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

package com.morgner.mccbot.serializer;

import com.morgner.mccbot.pdu.BulkChunk;
import com.morgner.mccbot.core.Serializer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A bulk chunk serializer for the minecraft protocol.
 *
 * @author Christian Morgner
 */
public class BulkChunkSerializer implements Serializer<BulkChunk> {

	@Override
	public BulkChunk deserialize(DataInputStream is) throws IOException {
		
		BulkChunk chunkData = new BulkChunk();

		short chunkCount = is.readShort();
		int dataLength   = is.readInt();
		boolean skySent  = is.readBoolean();

		chunkData.setChunkCount(chunkCount);
		chunkData.setDataLength(dataLength);
		chunkData.setContainsSky(skySent);
		
		byte[] data      = new byte[dataLength];
		is.read(data, 0, dataLength);
		
		int metaDataLength = 12 * chunkCount;
		byte[] metaData = new byte[metaDataLength];
		is.read(metaData, 0, metaDataLength);
		
		chunkData.setData(data);
		chunkData.setMetaDataLength(metaDataLength);
		chunkData.setMetaData(metaData);
		
		return chunkData;
	}

	@Override
	public void serialize(BulkChunk data, DataOutputStream os) throws IOException {
		// noop
	}
}
