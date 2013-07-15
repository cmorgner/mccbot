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

import com.morgner.mccbot.pdu.EntityMetadata;
import com.morgner.mccbot.core.Serializer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * An entity metdata serializer for the minecraft protocol.
 *
 * @author Christian Morgner
 */
public class EntityMetadataSerializer implements Serializer<EntityMetadata> {

	@Override
	public EntityMetadata deserialize(DataInputStream is) throws IOException {
		
		EntityMetadata metaData = new EntityMetadata();
		int keyAndType = is.readUnsignedByte();

		while (keyAndType != 127) {
			
			int index    = keyAndType & 0x1f;
			int type     = keyAndType >> 5;
			Object value = null;
			
			switch (type) {
				
				case 0:
					value = is.readByte();
					break;
					
				case 1:
					value = is.readShort();
					break;
					
				case 2:
					value = is.readInt();
					break;
					
				case 3:
					value = is.readFloat();
					break;
					
				case 4:
					value = new StringSerializer().deserialize(is);
					break;
					
				case 5:
					value = new SlotDataSerializer().deserialize(is);
					break;
					
				case 6:
					List<Integer> list = new LinkedList<>();
					list.add(is.readInt());
					list.add(is.readInt());
					list.add(is.readInt());
					value = list;
					break;
			}

			if (value != null) {
				metaData.add(index, value);
			}
			
			// read next
			keyAndType = is.readUnsignedByte();
		}
		
		return metaData;
	}

	@Override
	public void serialize(EntityMetadata data, DataOutputStream os) throws IOException {
	}
}
