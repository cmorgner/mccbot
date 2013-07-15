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

import com.morgner.mccbot.core.Serializer;
import com.morgner.mccbot.pdu.MultiBlockData;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A multi block data serializer for the minecraft protocol.
 *
 * @author Christian Morgner
 */
public class MultiBlockDataSerializer implements Serializer<MultiBlockData> {

	@Override
	public MultiBlockData deserialize(DataInputStream is) throws IOException {
		
		MultiBlockData blockData = new MultiBlockData();
		
		int count    = is.readShort();
		int dataSize = is.readInt();
		
		blockData.setCount(count);
		blockData.setDataSize(dataSize);
		
		for (int i=0; i<count; i++) {
		
			int value = is.readInt();
			
			// parse and add block data
			blockData.addBlockData(value);
		}
		
		return blockData;
	}

	@Override
	public void serialize(MultiBlockData data, DataOutputStream os) throws IOException {
	}
}
