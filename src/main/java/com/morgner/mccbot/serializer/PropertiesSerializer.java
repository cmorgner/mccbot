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
import com.morgner.mccbot.pdu.Properties;
import com.morgner.mccbot.pdu.Properties.Property;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A properties serializer for the minecraft protocol.
 *
 * @author Christian Morgner
 */
public class PropertiesSerializer implements Serializer<Properties> {

	@Override
	public Properties deserialize(DataInputStream is) throws IOException {
		
		StringSerializer stringer = new StringSerializer();
		Properties properties     = new Properties();
		
		int count = is.readInt();
		for (int i=0; i<count; i++) {
			
			Property property = properties.newProperty();
			
			String key = stringer.deserialize(is);
			property.setKey(key);
			
			double value = is.readDouble();
			property.setValue(value);
			
			short listLength = is.readShort();
			for (int j=0; j<listLength; j++) {
				
				long uuidMSB   = is.readLong();
				long uuidLSB   = is.readLong();
				double amount  = is.readDouble();
				byte operation = is.readByte();
				
				property.addElement(uuidMSB, uuidLSB, amount, operation);
			}
		}
		
		return properties;
	}

	@Override
	public void serialize(Properties data, DataOutputStream os) throws IOException {
		// read only
	}
}
