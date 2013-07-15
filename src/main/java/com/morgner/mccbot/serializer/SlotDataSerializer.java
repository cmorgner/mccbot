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
import com.morgner.mccbot.pdu.SlotData;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A slot data serializer for the minecraft protocol.
 * 
 * @author Christian Morgner
 */
public class SlotDataSerializer implements Serializer<SlotData> {

	@Override
	public SlotData deserialize(DataInputStream is) throws IOException {
		
		SlotData data = new SlotData();
		
		short itemId = is.readShort();
		
		data.setItemId(itemId);
		
		if (itemId != -1) {
			
			byte itemCount = (byte)(is.readUnsignedByte() & 255);
			short itemDamage = is.readShort();
			short dataLength = is.readShort();
			
			if (dataLength > 0) {
				
				// discard data
				is.skipBytes(dataLength);
			}
			
			data.setItemCount(itemCount);
			data.setItemDamage(itemDamage);
		}
		
		return data;
	}

	@Override
	public void serialize(SlotData data, DataOutputStream os) throws IOException {

		os.writeShort(data.getItemId());
		os.writeByte(data.getItemCount());
		os.writeShort(data.getItemDamage());
		
		// no additional data for now..
		os.writeShort(-1);
	}
}
