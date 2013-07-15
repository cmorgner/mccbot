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
 * A string serializer for the minecraft protocol.
 *
 * @author Christian Morgner
 */
public class StringSerializer implements Serializer<String> {

	@Override
	public String deserialize(DataInputStream is) throws IOException {
		
		int len = is.readShort();
		
		if (len > 255) {
			System.out.println("Maximum string length exceeded, aborting");
			return "";
		}
		
		StringBuilder buf = new StringBuilder(len+1);
		
		for (int i=0; i<len; i++) {
			buf.append(is.readChar());
		}
		
		return buf.toString();
	}

	@Override
	public void serialize(String data, DataOutputStream os) throws IOException {
		
		os.writeShort(data.length());
		os.writeChars(data);
	}
}
