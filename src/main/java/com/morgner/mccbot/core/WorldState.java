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
package com.morgner.mccbot.core;

import com.morgner.mccbot.engine.Engine;
import com.morgner.mccbot.protocol.X03_ChatMessage;
import com.morgner.mccbot.protocol.X0a_OnGround;
import com.morgner.mccbot.protocol.X0b_PlayerPosition;
import com.morgner.mccbot.protocol.X0c_PlayerLook;
import com.morgner.mccbot.protocol.X0d_PlayerPositionLook;
import com.morgner.mccbot.util.Matrix3d;
import com.morgner.mccbot.util.Vector3d;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * The state of the (minecraft-) world from the player's point of view.
 *
 * @author Christian Morgner
 */
public class WorldState implements Engine {

	private Voxel voxelData = new Voxel(new Vector3d(), 1024);
	private World world = null;
	private Vector3d direction = Vector3d.UnitX;
	
	private boolean confirmed = false;
	private int forcedPositionTimeout = 0;
	private int playerId = 0;
	private long worldAge = 0L;
	private long timeOfDay = 0L;
	
	// player's position
	private Vector3d r   = new Vector3d();
	private Vector3d r0  = new Vector3d();
	private Vector3d F   = new Vector3d();
	private Vector3d tmp = new Vector3d();
	private double stance = 0.0;
	private boolean onGround = true;
	
	// player's look direction and angle
	private float yaw = 0.0f;
	private float pitch = 0.0f;
	
	// spawn position
	private int spawnX = 0;
	private int spawnY = 0;
	private int spawnZ = 0;
	
	private double walkSpeed = 0;
	private double flySpeed  = 0;

	private DecimalFormat df = new DecimalFormat("0.00000");
	
	public WorldState(World world) {
		this.world = world;
	}
	
	public void update(double dt) {

		// FIXME: this is not working correctly right now :(
		
		double groundLevel = getGroundLevel();
		
		Vector3d dr = r.sub(r0).scale(0.9);

		F.set(dr.x() * -10.0, -200.0, dr.y() * -10.0);
		
		tmp.set(r);

		r.set(r.add(dr.add(F.scale(dt*dt))));
		
		r0.set(tmp);
		
		// check ground level and set onGround accordingly..
		if (r.y() <= groundLevel) {
			
			r.y(groundLevel);
			onGround = true;
			
		} else {
			
			onGround = false;
		}
		
		// System.out.println(groundLevel);
		
		stance = r.y() + 1.5;
		
		// position was forced by server, do not move for some time..
		if (forcedPositionTimeout > 0) {
			forcedPositionTimeout--;
		}

		yaw      = Vector3d.UnitZ.fangle(direction) + (direction.equals(Vector3d.UnitX, 0.1) ? 180 : 0);
		pitch    = direction.fangle(Vector3d.UnitY) - 90;
		
		F.set(0.0, 0.0, 0.0);
	}

	public void setConfirmed() {
		confirmed = true;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public long getWorldAge() {
		return worldAge;
	}

	public void setWorldAge(long worldAge) {
		this.worldAge = worldAge;
	}

	public long getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(long timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

	public void setPosition(double x, double y, double z, double stance) {
		this.r.set(x, y, z);
		this.r0.set(r);
		this.stance = stance;
	}

	public void setSpawnPosition(int x, int y, int z) {
		this.spawnX = x;
		this.spawnY = y;
		this.spawnZ = z;

		if (this.r.length() == 0) {
			this.r.set(x, y, z);
			this.r0.set(r);
		}
	}

	public void setPosition(double x, double y, double z, double stance, float yaw, float pitch) {
		this.r.set(x, y, z);
		this.r0.set(r);
		this.stance = stance;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public void setPosition(double x, double y, double z, double stance, float yaw, float pitch, boolean onGround) {
		this.r.set(x, y, z);
		this.r0.set(r);
		this.stance = stance;
		this.yaw = yaw;
		this.pitch = pitch;
		this.onGround = onGround;
	}

	public void setPosition(float yaw, float pitch, boolean onGround) {
		this.yaw = yaw;
		this.pitch = pitch;
		this.onGround = onGround;
	}

	public Packet getOnGroundPacket() {

		X0a_OnGround onGroundPacket = new X0a_OnGround();

		onGroundPacket.setOnGround(onGround);

		return onGroundPacket;
	}

	public Packet getPositionAndLookPacket() {

		X0d_PlayerPositionLook positionPacket = new X0d_PlayerPositionLook();

		positionPacket.set(r);

		positionPacket.setStance(stance);

		positionPacket.setOnGround(onGround);

		positionPacket.setYaw(yaw);
		positionPacket.setPitch(pitch);

		return positionPacket;
	}

	public Packet getPositionPacket() {

		X0b_PlayerPosition positionPacket = new X0b_PlayerPosition();

		positionPacket.set(r);
		positionPacket.setStance(stance);
		positionPacket.setOnGround(onGround);

		return positionPacket;
	}

	public Packet getLookPacket() {

		X0c_PlayerLook lookPacket = new X0c_PlayerLook();

		lookPacket.setOnGround(onGround);

		lookPacket.setYaw(yaw);
		lookPacket.setPitch(pitch);

		return lookPacket;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public void setChunk(Vector3d v, byte[] data) {
		voxelData.setChunk(v, data);
	}

	@Override
	public byte getBlock(Vector3d v) {
		return voxelData.get(v);
	}

	public void setBlock(Vector3d v, byte block) {
		voxelData.set(v, block);
	}

	public Vector3d getPlayerPosition() {
		return r;
	}

	public byte getBlockUnderPlayer() {
		return voxelData.get(r);
	}

	public boolean isOnGround() {
		return onGround;
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public double getStance() {
		return stance;
	}

	public void setStance(double stance) {
		this.stance = stance;
	}

	public void setWalkSpeed(double walkSpeed) {
		this.walkSpeed = walkSpeed / World.WORLD_STATE_UPDATE;
	}

	public void setFlySpeed(double flySpeed) {
		this.flySpeed = flySpeed / World.WORLD_STATE_UPDATE;
	}

	public void setForcedPosition() {
		this.forcedPositionTimeout = 10;
	}

	// ----- interface Engine -----
	@Override
	public boolean isBlockAhead(double distance) {
		return this.voxelData.isSet(r.add(direction.scale(distance * direction.length())));
	}
	
	@Override
	public Vector3d getPosition() {
		return r.floor();
	}
	
	@Override
	public void say(String message) {

		try {
			world.send(new X03_ChatMessage(message));
			
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}

	@Override
	public Vector3d getDirection() {
		return direction;
	}

	@Override
	public void setDirection(Vector3d direction) {
		this.direction = direction;
	}

	@Override
	public void move(double distance) {
		
		if (forcedPositionTimeout == 0) {
			r.set(r.add(direction.scale(distance)));
		}
	}

	@Override
	public void strafe(Vector3d direction, double distance) {

		if (forcedPositionTimeout == 0) {
			r.set(r.add(direction.scale(distance)));
		}
	}

	@Override
	public void turn(double xAngle, double yAngle, double zAngle) {
		
		Matrix3d turnX = Matrix3d.rotateZ(xAngle);
		Matrix3d turnY = Matrix3d.rotateY(yAngle);
		Matrix3d turnZ = Matrix3d.rotateX(zAngle);

		direction = turnX.mult(direction);
		direction = turnY.mult(direction);
		direction = turnZ.mult(direction);
	}
	
	@Override
	public void jump() {
		
		if (onGround) {
			
			onGround = false;
			strafe(Vector3d.UnitY, 1.0);
		}
	}
	
	public double getGroundLevel() {
		
		Vector3d g1        = new Vector3d(r).round();
		double groundLevel = getGroundLevel(g1);
		
		// check surroundings
		groundLevel = Math.max(groundLevel, getGroundLevel(g1.add(new Vector3d(1.0, 0.0, 0.0))));
		groundLevel = Math.max(groundLevel, getGroundLevel(g1.add(new Vector3d(1.0, 0.0, 1.0))));
		groundLevel = Math.max(groundLevel, getGroundLevel(g1.add(new Vector3d(0.0, 0.0, 1.0))));
		
		return groundLevel;
	}
	
	public double getGroundLevel(Vector3d pos) {
		
		Vector3d s         = new Vector3d(pos);

		// start at player's Y position + 1
		s.y(Math.ceil(s.y()));

		// this is the ground level
		double groundLevel = s.y();
		
		Vector3d g = new Vector3d(s);
		g.x(Math.floor(g.x()));
		g.z(Math.floor(g.z()));
		
		while (voxelData.get(g) == 0 && g.y() > -10) {
			
			g.y(g.y() - 1);
			
			groundLevel = g.y() + 1;
		}
		
		return groundLevel;
	}
}

