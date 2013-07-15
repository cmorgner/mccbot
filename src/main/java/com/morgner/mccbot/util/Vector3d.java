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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * A very simple 3d vector implementation.
 * 
 * @author Christian Morgner
 */
public final class Vector3d {
	
	private static final Random random = new Random(System.currentTimeMillis());

	public static final Vector3d UnitX = new Vector3d(1.0, 0.0, 0.0);
	public static final Vector3d UnitY = new Vector3d(0.0, 1.0, 0.0);
	public static final Vector3d UnitZ = new Vector3d(0.0, 0.0, 1.0);
	
	public static final Vector3d UnitNegX = new Vector3d(-1.0, 0.0, 0.0);
	public static final Vector3d UnitNegY = new Vector3d(0.0, -1.0, 0.0);
	public static final Vector3d UnitNegZ = new Vector3d(0.0, 0.0, -1.0);
	
	private double x = 0.0;
	private double y = 0.0;
	private double z = 0.0;
	
	public Vector3d() {
		this(0.0, 0.0, 0.0);
		
	}

	public Vector3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3d(Vector3d v) {
		
		this();
		set(v);
	}
	
	@Override
	public String toString() {
		return "[" + x + ":" + y + ":" + z + "]";
	}
	
	public final Vector3d scale(double scale) {
		
		Vector3d v = new Vector3d();
		
		v.x(this.x * scale);
		v.y(this.y * scale);
		v.z(this.z * scale);
		
		return v;
	}
	
	public final double length() {
		return Math.sqrt((x*x) + (y*y) + (z*z));
	}
	
	public final synchronized void set(Vector3d v) {
		
		this.x = v.x();
		this.y = v.y();
		this.z = v.z();
	}
	
	public final Vector3d add(Vector3d v) {
		
		Vector3d add = new Vector3d();
		
		add.x(this.x + v.x());
		add.y(this.y + v.y());
		add.z(this.z + v.z());
		
		return add;
	}
	
	public final Vector3d sub(Vector3d v) {
		
		Vector3d sub = new Vector3d();
		
		sub.x(this.x - v.x());
		sub.y(this.y - v.y());
		sub.z(this.z - v.z());
		
		return sub;
	}
	
	public final double dot(Vector3d v) {
		return (this.x * v.x()) + (this.y * v.y()) + (this.z * v.z());
	}
	
	public final float fdot(Vector3d v) {
		return (float)dot(v);
	}

	public double angle(Vector3d v) {
		return Math.toDegrees(Math.acos(dot(v) / length() * v.length()));
	}

	public float fangle(Vector3d v) {
		
		return (float)angle(v);
	}
	
	public final Vector3d mult(Vector3d v) {
		
		Vector3d mult = new Vector3d();
		
		mult.x(this.y * v.z() - this.z * v.y());
		mult.y(this.z * v.x() - this.x * v.z());
		mult.z(this.x * v.y() - this.y * v.x());
		
		return mult;
	}

	public final void set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public final Vector3d normalize() {
		
		Vector3d norm = new Vector3d(this);
		norm.scale(norm.length());
		
		return norm;
	}

	public Vector3d negate() {
		
		Vector3d neg = new Vector3d();
		
		neg.x(-this.x);
		neg.y(-this.y);
		neg.z(-this.z);
		
		return neg;
	}
	
	public final double x() {
		return x;
	}
	
	public final void x(double x) {
		this.x = x;
	}

	public final double y() {
		return y;
	}

	public final void y(double y) {
		this.y = y;
	}

	public final double z() {
		return z;
	}

	public final void z(double z) {
		this.z = z;
	}

	public final void addX(double x) {
		this.x += x;
	}

	public final void addY(double y) {
		this.y += y;
	}

	public final void addZ(double z) {
		this.z += z;
	}

	public final Vector3d round() {
		
		return new Vector3d(
			Math.round(x),
			Math.round(y),
			Math.round(z)
		);
	}

	public final Vector3d round(int decimals) {
		
		BigDecimal bdX = new BigDecimal(x).setScale(decimals, RoundingMode.HALF_EVEN);
		BigDecimal bdY = new BigDecimal(y).setScale(decimals, RoundingMode.HALF_EVEN);
		BigDecimal bdZ = new BigDecimal(z).setScale(decimals, RoundingMode.HALF_EVEN);
		
		return new Vector3d(
			bdX.doubleValue(),
			bdY.doubleValue(),
			bdZ.doubleValue()
		);
	}

	public final Vector3d floor() {
		
		return new Vector3d(
			Math.floor(x),
			Math.floor(y),
			Math.floor(z)
		);
	}

	public final Vector3d ceil() {
		
		return new Vector3d(
			Math.ceil(x),
			Math.ceil(y),
			Math.ceil(z)
		);
	}
	
	public boolean equals(Vector3d v, double epsilon) {
		return sub(v).length() < epsilon;
	}
	
	public final Vector3d signum() {
		return new Vector3d(Math.signum(x), Math.signum(y), Math.signum(z));
	}
	
	public static Vector3d randomPositive(double range) {
		return randomPositive(range, true, true, true);
	}
	
	public static Vector3d randomPositive(double range, boolean x, boolean y, boolean z) {
		
		return new Vector3d(
			x ? random.nextDouble() * range : 0.0,
			y ? random.nextDouble() * range : 0.0,
			z ? random.nextDouble() * range : 0.0
		);
	}
	
	public static Vector3d random(double range) {
		return random(range, true, true, true);
	}
	
	public static Vector3d random(double range, boolean x, boolean y, boolean z) {
		
		double halfRange = range / 2.0;
		
		return new Vector3d(
			x ? (random.nextDouble() * range) - halfRange : 0.0,
			y ? (random.nextDouble() * range) - halfRange : 0.0,
			z ? (random.nextDouble() * range) - halfRange : 0.0
		);
	}
	
	public static Vector3d randomInteger(int range) {
		return randomInteger(range, true, true, true);
	}
	
	public static Vector3d randomInteger(int range, boolean x, boolean y, boolean z) {
		
		int halfRange = range / 2;
		
		return new Vector3d(
			x ? random.nextInt(range) - halfRange : 0.0,
			y ? random.nextInt(range) - halfRange : 0.0,
			z ? random.nextInt(range) - halfRange : 0.0
		);
	}
	
	public static void main(String[] args) {
		
		Vector3d v = new Vector3d(1.2345, 2.345678, 3.456789);
		
		System.out.println("0: " + v);
		System.out.println("1: " + v.round(1));
		System.out.println("2: " + v.round(2));
		System.out.println("3: " + v.round(3));
		System.out.println("4: " + v.round(4));
		System.out.println("5: " + v.round(5));
	}
}
