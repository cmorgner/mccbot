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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A byte array serializer for the minecraft protocol.
 *
 * @author Christian Morgner
 */
public class ByteArraySerializer implements Serializer<byte[]> {

	@Override
	public byte[] deserialize(DataInputStream is) throws IOException {
		
		int len    = is.readShort();
		byte[] buf = new byte[len];

		for (int i=0; i<len; i++) {
			buf[i] = (byte)is.readUnsignedByte();
		}
		
		return buf;
	}

	@Override
	public void serialize(byte[] data, DataOutputStream os) throws IOException {
		
		os.writeShort(data.length);
		os.write(data);
	}
}
