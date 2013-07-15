# MCCBot - Minecraft Client Bot
A Java Minecraft client implementation. This client was initially intended to provide a simulation environment for the test of cleaning algorithms for vacuum cleaning robots. After this initial goal was met, I wanted to transform the client into a programming environment for Minecraft bot and AI programming.

Since the world simulation in Minecraft is almost completely done by the client - while the server only enforces constraints and kicks misbehaving clients - the effort to develop this client into a completely functional (and well-behaving) Minecraft client vastly exceeds the amount of time I can spare for such a project.

## Current status
With MCCBot in its current state, you can

- connect to a vanilla Minecraft 1.6 server in offline mode
- receive position updates, server and player information
- receive voxel data ("chunks") and block changes
- receive updates on the positions of entities (mobs, players, etc.)
- send and receive chat messages (may need some work for the JSON objects)
- move in a superflat world
- extract information about the surroundings of the player (with a very basic voxel datastructure)

### Issues
- The physics engine that powers the world needs some additional work
- The protocol implementation is complete, i.e. all PDUs are correctly parsed, but the world does not interpret all the packets (some of them don't even make sense for a bot implementation)
- The client protocol implementation does not support encryption yet, i.e. the bot can only be run on an offline server (with `online-mode=false`).

### Current status summary
MCCBot definitely needs some work on the physics engine (or maybe integration of an existing voxel engine) and on protocol encryption.

## Goal
The goal of this project is to develop a fully-functional simulation environment for Minecraft bots. This includes further development of the `Engine` interface to support full interaction with the surrounding world, enhancement of the physics engine to send correct position updates to the server, etc.

Feel free to fork this project, play around with it, extend it, develop your own Bot engine etc, and send back pull requests, questions, comments, etc.

## License
MCCBot is published under the [GNU Affero General Public License](http://www.gnu.org/licenses/agpl-3.0.html), which means that you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

## Credits
The Minecraft client/server protocol implementation is entirely based on the great work done by the folks at [MinecraftCoalition](http://wiki.vg). Thanks for the detailed specs and documentation!
