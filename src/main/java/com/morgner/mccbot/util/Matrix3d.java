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

package com.morgner.mccbot.util;

/**
 * A very simple 3d matrix implementation.
 * 
 * @author Christian Morgner
 */
public class Matrix3d {

	private double[] a1 = new double[3];
	private double[] a2 = new double[3];
	private double[] a3 = new double[3];
	
	public Matrix3d(Vector3d v1, Vector3d v2, Vector3d v3) {
		
		this.a1[0] = v1.x();
		this.a1[1] = v2.x();
		this.a1[2] = v3.x();
		
		this.a2[0] = v1.y();
		this.a2[1] = v2.y();
		this.a2[2] = v3.y();
		
		this.a3[0] = v1.z();
		this.a3[1] = v2.z();
		this.a3[2] = v3.z();
	}

	@Override
	public String toString() {
		
		return
			a1[0] + " " + a2[0] + " " + a3[0] + "\n" +
			a1[1] + " " + a2[1] + " " + a3[1] + "\n" +
			a1[2] + " " + a2[2] + " " + a3[2] + "\n"
		;
	}
	
	public Vector3d mult(Vector3d v) {

		Vector3d mult = new Vector3d(
			
			a1[0]*v.x() + a2[0] * v.y() + a3[0] * v.z(),
			a1[1]*v.x() + a2[1] * v.y() + a3[1] * v.z(),
			a1[2]*v.x() + a2[2] * v.y() + a3[2] * v.z()
		);
		
		return mult;
	}
	
	public static Matrix3d rotateX(double angle) {
		
		double a = Math.toRadians(angle);
		
		Matrix3d matrix = new Matrix3d(
			new Vector3d(1.0,          0.0,         0.0),
			new Vector3d(0.0,  Math.cos(a), Math.sin(a)),
			new Vector3d(0.0, -Math.sin(a), Math.cos(a))
		);
		
		return matrix;
	}
	
	public static Matrix3d rotateY(double angle) {
		
		double a = Math.toRadians(angle);
		
		Matrix3d matrix = new Matrix3d(
			new Vector3d(Math.cos(a), 0.0, -Math.sin(a)),
			new Vector3d(        0.0, 1.0,         0.0),
			new Vector3d(Math.sin(a), 0.0,  Math.cos(a))
		);
		
		return matrix;
	}
	
	public static Matrix3d rotateZ(double angle) {
		
		double a = Math.toRadians(angle);
		
		Matrix3d matrix = new Matrix3d(
			new Vector3d( Math.cos(a), Math.sin(a), 0.0),
			new Vector3d(-Math.sin(a), Math.cos(a), 0.0),
			new Vector3d(         0.0,         0.0, 1.0)
		);
		
		return matrix;
	}
}
