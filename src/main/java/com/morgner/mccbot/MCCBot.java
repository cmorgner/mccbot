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
package com.morgner.mccbot;

import com.morgner.mccbot.core.World;
import com.morgner.mccbot.core.WorldState;
import com.morgner.mccbot.engine.Bot;
import com.morgner.CleanerBot;

/**
 * This is the main class of this bot implementation. It creates
 * a new world, connects to a server on localhost and creates and
 * runs a new bot while the connection is alive.
 * 
 * @author Christian Morgner
 */
public class MCCBot {

	private static final String server           = "localhost";
	private static final int port                = 25565;
	
	public static void main(String[] args) {
		
		try {
			
			World world = new World("wurst", server, port);
			world.connect();
			
			// wait for the connection to be established
			// before sending anything
			try { Thread.sleep(1000); } catch (Throwable t) {}
			
			WorldState state = world.getWorldState();
			Bot testBot      = new CleanerBot();
			
			// wait again
			try { Thread.sleep(1000); } catch (Throwable t) {}
			
			// initialize bot
			testBot.initialize(state);
			
			// run main loop
			while (world.isRunning()) {

				// update every 50ms
				try { Thread.sleep(50); } catch (Throwable t) {}

				testBot.update(state);
			}
			
		} catch (Throwable t) {
			
			t.printStackTrace();
		}
	}
}
