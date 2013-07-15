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

import com.morgner.mccbot.protocol.X02_Handshake;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class handles the connection to the minecraft server
 * 
 * @author Christian Morgner
 */
public class World extends Thread {

	public static final double POSITION_UPDATE        = 50.0;
	public static final double WORLD_STATE_UPDATE     = 20.0;
	
	private ConcurrentLinkedQueue<Packet> outputQueue = null;
	private WorldState worldState                     = null;
	private Receiver receiver                         = null;
	private Sender sender                             = null;
	private SocketAddress addr                        = null;
	private Socket socket                             = null;
	private String userName                           = null;
	private String server                             = null;

	private long currentTime                          = System.currentTimeMillis();
	private int port                                  = 25565;
	private boolean running                           = false;
	private long lastPositionUpdate                   = 0L;
	private long lastWorldStateUpdate                 = 0L;
	
	public World(String userName, String server, int port) {

		this.userName = userName;
		this.server = server;
		this.port = port;
		
		this.worldState = new WorldState(this);
		this.outputQueue = new ConcurrentLinkedQueue<>();
	}
	
	@Override
	public void run() {
		
		// initialize
		lastPositionUpdate   = currentTime;
		running              = true;

		try {

			this.addr   = new InetSocketAddress(server, port);
			this.socket = new Socket();

			this.socket.connect(addr);

			// start network communication
			sender   = new Sender(socket, this, outputQueue);
			receiver = new Receiver(socket, this);
			
			sender.start();
			receiver.start();
			
			// send handshake
			send(new X02_Handshake(userName, server, port));

			while (running) {

				currentTime = System.currentTimeMillis();
				if (worldState.isConfirmed() && currentTime > lastPositionUpdate + POSITION_UPDATE) {
					
					// worldState.display();
					
					lastPositionUpdate = System.currentTimeMillis();
					
					//send(worldState.getPositionAndLookPacket());
					send(worldState.getOnGroundPacket());
					send(worldState.getPositionPacket());
					send(worldState.getLookPacket());
				}
				
				currentTime = System.currentTimeMillis();
				if (currentTime > lastWorldStateUpdate + WORLD_STATE_UPDATE) {
					
					lastWorldStateUpdate = currentTime;
					worldState.update(0.02);
				}
			}
		
		} catch (Throwable t) {
			
			t.printStackTrace();
			running = false;
			
		} finally {
			
			
		}
		
		System.out.println("########## exiting main loop");
	}
	
	public WorldState getWorldState() {
		return worldState;
	}
	
	public void connect() {
		
		start();

		// wait for socket to be initialized
		while (socket == null) {
			try { Thread.sleep(10); } catch(Throwable t) {}
		}
	}
	
	public void disconnect() {
		
		try {

			running = false;
			socket.close();
			
		} catch (IOException ioex) {
			
			ioex.printStackTrace();
		}
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void send(Packet packet) throws IOException {
		outputQueue.add(packet);
	}
	
	private static class Receiver extends Thread {
		
		private DataInputStream inputStream = null;
		private WorldState worldState       = null;
		private World world                 = null;
		
		public Receiver(Socket socket, World world) throws IOException {

			this.inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			this.worldState  = world.getWorldState();
			this.world       = world;
		}
		
		@Override
		public void run() {
			
			while (world.running) {

				try {
					Packet packet = Packet.readPacket(inputStream);
					if (packet != null) {

						// System.out.println(packet);

						packet.respond(world);
						packet.update(worldState);

					} else {

						world.disconnect();
					}

				} catch (IOException ioex) {
					
					ioex.printStackTrace();
					world.running = false;
				}
			}
		}
	}
	
	private static class Sender extends Thread {

		private Queue<Packet> outputQueue     = null;
		private DataOutputStream outputStream = null;
		private World world                   = null;
		
		public Sender(Socket socket, World world, Queue<Packet> outputQueue) throws IOException {
			
			outputStream     = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			this.outputQueue = outputQueue;
			this.world       = world;
		}
		
		@Override
		public void run() {
			
			while (world.running) {
				
				Packet outputPacket = outputQueue.poll();
				if (outputPacket != null) {
					
					try {
						Packet.writePacket(outputPacket, outputStream);
						
					} catch (IOException ioex) {
						
						ioex.printStackTrace();
						world.running = false;
					}
				}
				
				// wait a millisecond..
				try { Thread.sleep(1); } catch (Throwable t) {}
			}
		}
	}
}
