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

package com.morgner.mccbot.pdu;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the properties of an entity in minecraft.
 * This class has its own serializer implementation.
 *
 * @author Christian Morgner
 */
public class Properties {

	private List<Property> properties = new LinkedList<>();

	public List<Property> getProperties() {
		return properties;
	}

	public void addProperty(Property property) {
		properties.add(property);
	}

	public Property newProperty() {
		return new Property();
	}
	
	public class Property {
		
		private List<Element> elements = new LinkedList<>();
		private String key             = null;
		private double value           = 0.0;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}

		public List<Element> getElements() {
			return elements;
		}

		public void addElement(long uuidMSB, long uuidLSB, double amount, byte operation) {

			Element element = new Element();

			element.setUuidMSB(uuidMSB);
			element.setUuidLSB(uuidLSB);
			element.setAmount(amount);
			element.setOperation(operation);

			elements.add(element);
		}
	}
	
	public class Element {
		
		private long uuidMSB   = 0;
		private long uuidLSB   = 0;
		private double amount  = 0.0;
		private byte operation = 0;

		public long getUuidMSB() {
			return uuidMSB;
		}

		public void setUuidMSB(long uuidMSB) {
			this.uuidMSB = uuidMSB;
		}

		public long getUuidLSB() {
			return uuidLSB;
		}

		public void setUuidLSB(long uuidLSB) {
			this.uuidLSB = uuidLSB;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public byte getOperation() {
			return operation;
		}

		public void setOperation(byte operation) {
			this.operation = operation;
		}
	}
}
