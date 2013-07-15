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

import com.morgner.mccbot.util.Vector3d;

/**
 * A very basic (and incomplete) voxel space implementation. This
 * class handles the chunk and bulk chunk data from the minecraft
 * server. Not sure if it works correctly though.
 * 
 * @author Christian Morgner
 */
public class Voxel {

	private static final int X_FLAG = 1;
	private static final int Y_FLAG = 2;
	private static final int Z_FLAG = 4;

	private Voxel[] children = new Voxel[8];

	private double epsilon = 0.1f;
	private double size = 0;
	private double sx = 0;
	private double sy = 0;
	private double sz = 0;

	private byte data = 0;

	public Voxel(Vector3d v, int size) {
		
		Vector3d floor = v.floor();
		this.sx = floor.x();
		this.sy = floor.y();
		this.sz = floor.z();
		this.size = size;
	}

	private Voxel(double sx, double sy, double sz, double size) {
		this.sx = sx;
		this.sy = sy;
		this.sz = sz;
		this.size = size;
	}

	@Override
	public String toString() {
		return "Voxel(" + sx + ", " + sy + ", " + sz + ", " + size + ")";
	}
	
	public void setChunk(Vector3d v, byte[] data) {

		Vector3d floor = v.floor();
		int cx = (int)floor.x();
		int cy = (int)floor.y();
		int cz = (int)floor.z();
		int pos = 0;
		
		for (int y=0; y<16; y++) {
			
			for (int z=0; z<16; z++) {
				
				for (int x=0; x<16; x++) {
					
					byte value = data[pos++];
					if (value != 0) {
					
						set(new Vector3d(cx+x, cy+y, cz+z), value);
					}
				}
			}
		}
	}

	public boolean isSet(Vector3d v) {
		return get(v) != 0;
	}

	public byte get(Vector3d v) {

		Voxel voxel = find(v);
		if (voxel != null) {
			return voxel.data;
		}

		return 0;
	}

	public void set(Vector3d v, byte data) {
		Voxel voxel = find(v);
		voxel.data = data;
	}

	private Voxel find(Vector3d v) {

		Voxel voxel = this;

		// descend until size is 2..
		while (!voxel.isVoxel(v)) {
			voxel = voxel.getChild(v);
		}

		return voxel;
	}

	private Voxel getChild(Vector3d v) {

		int index = index(v);
		if (index == -1) {

			return this;
		}

		Voxel child = children[index];
		if (child == null) {

			child = createChild(index);
			children[index] = child;
		}

		return child;
	}

	private Voxel createChild(int forIndex) {

		double halfSize = size / 2.0f;
		double newX;
		double newY;
		double newZ;

		if ((forIndex & X_FLAG) == X_FLAG) {

			newX = (sx + halfSize);

		} else {

			newX = (sx - halfSize);
		}

		if ((forIndex & Y_FLAG) == Y_FLAG) {

			newY = (sy + halfSize);

		} else {

			newY = (sy - halfSize);
		}

		if ((forIndex & Z_FLAG) == Z_FLAG) {

			newZ = (sz + halfSize);

		} else {

			newZ = (sz - halfSize);
		}

		return new Voxel(newX, newY, newZ, halfSize);
	}

	private boolean isVoxel(Vector3d v) {
		
		return index(v) == -1;
	}

	private int index(Vector3d v) {

		Vector3d floor = v.floor();
		double x = floor.x();
		double y = floor.y();
		double z = floor.z();
		
		if (x == sx && y == sy && z == sz) {
			return -1;
		}

		if (size < epsilon) {
			return -1;
		}

		int index = 0;

		if (x > sx) {
			index |= X_FLAG;
		}

		if (y > sy) {
			index |= Y_FLAG;
		}

		if (z > sz) {
			index |= Z_FLAG;
		}

		return index;
	}
}
