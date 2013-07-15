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

/**
 * Represents bulk chunk data in the minecraft protocol.
 * This class has its own serializer implementation.
 *
 * @author Christian Morgner
 */
public class BulkChunk {

	private short chunkCount = 0;
	private boolean containsSky = false;
	
	private int dataLength = 0;
	private byte[] data = null;
	
	private int metaDataLength = 0;
	private byte[] metaData = null;

	@Override
	public String toString() {
		return "BulkChunk(" + chunkCount + ", " + dataLength + ", " + containsSky + ", " + metaDataLength + ")";
	}
	
	public short getChunkCount() {
		return chunkCount;
	}

	public void setChunkCount(short chunkCount) {
		this.chunkCount = chunkCount;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public boolean isContainsSky() {
		return containsSky;
	}

	public void setContainsSky(boolean containsSky) {
		this.containsSky = containsSky;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public byte[] getMetaData() {
		return metaData;
	}

	public void setMetaData(byte[] metaData) {
		this.metaData = metaData;
	}

	public int getMetaDataLength() {
		return metaDataLength;
	}

	public void setMetaDataLength(int metaDataLength) {
		this.metaDataLength = metaDataLength;
	}
}
