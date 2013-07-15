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
 * Represents multi block data in the minecraft protocol.
 * This class has its own serializer implementation.
 *
 * @author Christian Morgner
 */
public class MultiBlockData {

	private int count = 0;
	private int dataSize = 0;
	private List<BlockData> blockData = new LinkedList<>();

	@Override
	public String toString() {
		return "MultiBlockData(" + count + ")";
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}

	public List<BlockData> getBlockData() {
		return blockData;
	}
	
	public void addBlockData(int source) {
		
		BlockData data = new BlockData();
		
		// FIXME: not sure here...
		data.setMetaData(source & 0x0000000f);
		data.setBlockId((source & 0x0000fff0) >>  4);
		data.setY((source & 0x00ff0000) >> 16);
		data.setZ((source & 0x0f000000) >> 24);
		data.setX((source & 0xf0000000) >> 28);
		
		this.blockData.add(data);
	}

	public int getDataSize() {
		return dataSize;
	}

	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}
	
	public static class BlockData {
		
		private int metaData = 0;
		private int blockId = 0;
		private int y = 0;
		private int z = 0;
		private int x = 0;

		@Override
		public String toString() {
			return "BlockData(" + metaData + ", " + blockId + ", [" + x + ":" + y + ":" + z + "])";
		}

		public int getMetaData() {
			return metaData;
		}

		public void setMetaData(int metaData) {
			this.metaData = metaData;
		}

		public int getBlockId() {
			return blockId;
		}

		public void setBlockId(int blockId) {
			this.blockId = blockId;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getZ() {
			return z;
		}

		public void setZ(int z) {
			this.z = z;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}
	}
}
