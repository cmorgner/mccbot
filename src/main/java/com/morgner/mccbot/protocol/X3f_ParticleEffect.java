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

package com.morgner.mccbot.protocol;

import com.morgner.mccbot.core.Packet;
import com.morgner.mccbot.pdu.ProtocolField;

/**
 *
 * @author Christian Morgner
 */
public class X3f_ParticleEffect extends Packet {

	@ProtocolField(0)
	private String name = null;
	
	@ProtocolField(1)
	private float x = 0;
	
	@ProtocolField(2)
	private float y = 0;
	
	@ProtocolField(3)
	private float z = 0;
	
	@ProtocolField(4)
	private float xOffset = 0;
	
	@ProtocolField(5)
	private float yOffset = 0;
	
	@ProtocolField(6)
	private float zOffset = 0;
	
	@ProtocolField(7)
	private float particleSpeed = 0;
	
	@ProtocolField(8)
	private int particleCount = 0;
	
	public X3f_ParticleEffect() {
		super(0x3f);
	}

	@Override
	public String toString() {
		return "ParticleEffect([" + x + ":" + y + ":" + z + "], [" + xOffset + ":" + yOffset + ":" + zOffset + "], " + particleSpeed + ", " + particleCount + ")";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public float getzOffset() {
		return zOffset;
	}

	public void setzOffset(float zOffset) {
		this.zOffset = zOffset;
	}

	public float getParticleSpeed() {
		return particleSpeed;
	}

	public void setParticleSpeed(float particleSpeed) {
		this.particleSpeed = particleSpeed;
	}

	public int getParticleCount() {
		return particleCount;
	}

	public void setParticleCount(int particleCount) {
		this.particleCount = particleCount;
	}
}
