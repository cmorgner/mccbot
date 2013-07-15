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

package com.morgner;

import com.morgner.mccbot.engine.Bot;
import com.morgner.mccbot.engine.Engine;
import com.morgner.mccbot.util.Vector3d;

/**
 * A bot implementation that iteratively covers every block of the
 * floor of a confined space, sweeping each position at least
 * CLEAN_THRESHOLD times. This bot only works in a superflat
 * world with an at least one block high boundary around the
 * space that has to be sweeped.
 * 
 * 
 * @author Christian Morgner
 */
public class CleanerBot implements Bot {

	private static final int X_SIZE    = 256;
	private static final int Y_SIZE    = 256;
	private static final int HALF_SIZE = 128;

	private static final int CLEAN_THRESHOLD = 10;
	
	int[][] field = new int[X_SIZE][Y_SIZE];
	int lowestValue = CLEAN_THRESHOLD;
	int direction = 0;
	int stuck = 0;
	int x = 0;
	int y = 0;
	
	@Override
	public void initialize(Engine engine) {
		
		engine.setDirection(Vector3d.UnitX);
	}
	
	@Override
	public void update(Engine engine) {
		
		if (lowestValue <= CLEAN_THRESHOLD) {

			// the place where we currently are can safely
			// be assumed to be free of obstacles!
			mark(x, y);

			if (!isBlockAhead(engine)) {

				moveForward(engine);

				stuck--;

			} else {



				if (!isBlockLeft(engine)) {

					turnLeft(engine);

				} else if (!isBlockRight(engine)) {

					turnRight(engine);

				} else if (!isBlockBehind(engine)) {

					turnAround(engine);

				} else {

					stuck++;
				}
			}
			
		} else {
			
			engine.say("All done!");
		}
//		
//		if (count++ > 40) {
//			engine.jump();
//			count = 0;
//		}
		
		display();
	}
	
	int count = 0;
	
	private boolean isBlockAhead(Engine engine) {

		int nextX = x;
		int nextY = y;
		
		switch (direction) {
			case 0: nextX++; break;
			case 1: nextY++; break;
			case 2: nextX--; break;
			case 3: nextY--; break;
		}

		if (engine.isBlockAhead(1.0)) {
			
			block(nextX, nextY);
			return true;
		}
		
		int next = get(nextX, nextY);
		if (next >= 0) {
			return next > stuck || stuck == 0;
		}
		
		return false;
	}
	
	private boolean isBlockLeft(Engine engine) {

		turnLeft(engine);
		boolean isBlock = isBlockAhead(engine);
		turnRight(engine);
		
		return isBlock;
	}
	
	private boolean isBlockRight(Engine engine) {

		turnRight(engine);
		boolean isBlock = isBlockAhead(engine);
		turnLeft(engine);
		
		return isBlock;
	}
	
	private boolean isBlockBehind(Engine engine) {

		turnRight(engine);
		turnRight(engine);
		boolean isBlock = isBlockAhead(engine);
		turnLeft(engine);
		turnLeft(engine);
		
		return isBlock;
	}
	
	private void turnLeft(Engine engine) {
		
		engine.turn(0.0, -90.0, 0.0);
		
		direction -= 1;
		
		if (direction < 0) {
			direction = 3;
		}
	}
	
	private void turnRight(Engine engine) {

		engine.turn(0.0, 90.0, 0.0);
		
		direction += 1;
		
		if (direction > 3) {
			direction = 0;
		}
	}
	
	private void turnAround(Engine engine) {
		turnLeft(engine);
		turnLeft(engine);
	}
	
	private void moveForward(Engine engine) {

		engine.move(1.0);
		
		switch (direction) {
			case 0: x++; break;
			case 1: y++; break;
			case 2: x--; break;
			case 3: y--; break;
		}
	}
	
	private void mark(int x, int y) {
		
		field[x+HALF_SIZE][y+HALF_SIZE]++;
	}
	
	private void block(int x, int y) {
		
		field[x+HALF_SIZE][y+HALF_SIZE] = -1;
	}
	
	private int get(int x, int y) {
		return field[x+HALF_SIZE][y+HALF_SIZE];
	}
	
	private void display() {
		
		boolean lowerValueFound = false;
		boolean hasXData        = false;
		
		for (int i=0; i<X_SIZE; i++) {
			
			boolean hasYData = false;
			
			for (int j=0; j<Y_SIZE; j++) {
				
				int value = field[j][i];
				if (value != 0) {

					hasXData = true;
					hasYData = true;
		
					if (value > 0 && value < lowestValue) {
						lowestValue = value;
						lowerValueFound = true;
					}
				}
				
				switch (value) {
					
					case 0:
						if (hasXData || hasYData) {
							System.out.print(" ");
						}
						break;
					
					case 1:
						System.out.print(" ");
						break;
					
					case -1:
						System.out.print("#");
						break;
					
					default:
						if (value < CLEAN_THRESHOLD) {
							System.out.print(".");
						} else {
							System.out.print(" ");
						}
						break;
				}
			}
			
			if (hasXData || hasYData) {
				
				System.out.println();
				hasXData = false;
			}
		}
		
		System.out.println("lowest value: " + lowestValue);

		if (!lowerValueFound) {
			lowestValue++;
		}
	}
}
