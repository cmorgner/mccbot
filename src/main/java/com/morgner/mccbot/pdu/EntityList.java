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
 * Represents an entity list in the minecraft protocol.
 * This class has its own serializer implementation.
 *
 * @author Christian Morgner
 */
public class EntityList {

	private List<Integer> entityIds = new LinkedList<>();
	private int count = 0;

	public List<Integer> getEntityIds() {
		return entityIds;
	}
	
	@Override
	public String toString() {
		return "EntityList(" + entityIds + ")";
	}

	public void addEntityId(int entityId) {
		entityIds.add(entityId);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
