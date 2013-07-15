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
package com.morgner.mccbot.engine;

import com.morgner.mccbot.util.Vector3d;

/**
 * A first sketch of an abstraction layer
 * around the minecraft world to run a bot.
 * 
 * @author Christian Morgner
 */
public interface Engine {

	/**
	 * Moves the bot in the direction it is currently facing
	 * @param distance the distance in meters to move the bot
	 */
	public void move(double distance);
	
	/**
	 * Moves the bot in the desired direction without
	 * changing the direction it is facing.
	 * @param direction
	 * @param distance 
	 */
	public void strafe(Vector3d direction, double distance);

	public void jump();
	
	/**
	 * Modified the facing direction of the bot according to
	 * the given angles.
	 * 
	 * @param xAngle
	 * @param yAngle
	 * @param zAngle 
	 */
	public void turn(double xAngle, double yAngle, double zAngle);
	
	public boolean isBlockAhead(double distance);
	
	public byte getBlock(Vector3d position);

	/**
	 * Returns the current position of the bot
	 * 
	 * @return the current position of the bot
	 */
	public Vector3d getPosition();
	
	/**
	 * Returns the direction the bot is currently facing as a unit vector.
	 * 
	 * @return a unit vector indicating the current facing direction
	 */
	public Vector3d getDirection();

	/**
	 * Sets the direction the bot is currently facing.
	 * 
	 * @param direction the new direction of the bot
	 */
	public void setDirection(Vector3d direction);
	
	public void say(String message);
}


/**
 * For this engine, we make the following assumptions:
 *  - the task is to build a map of the (2d) world
 *    surrounding the bot
 *  - there is no (expensive) long-range imaging technology
 *    available, only a cheap short-range detector
 *    => the bot can only see its immediate surroundings
 *  - the world consists of cubes, lots of it
 *  - the bot has no notion of absolute coordinates
 *  - the bot can only move forward and backward
 *  - the bot can only turn in 90 degree steps
 */