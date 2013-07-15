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

import com.morgner.mccbot.pdu.MultiSlotData;
import com.morgner.mccbot.pdu.SlotData;
import com.morgner.mccbot.pdu.BulkChunk;
import com.morgner.mccbot.pdu.ByteData;
import com.morgner.mccbot.pdu.Chunk;
import com.morgner.mccbot.pdu.EntityList;
import com.morgner.mccbot.pdu.ProtocolField;
import com.morgner.mccbot.pdu.ObjectData;
import com.morgner.mccbot.pdu.NBTData;
import com.morgner.mccbot.pdu.EntityMetadata;
import com.morgner.mccbot.pdu.MultiBlockData;
import com.morgner.mccbot.pdu.Properties;
import com.morgner.mccbot.protocol.X38_BulkChunkData;
import com.morgner.mccbot.protocol.X03_ChatMessage;
import com.morgner.mccbot.protocol.X33_ChunkData;
import com.morgner.mccbot.protocol.Xcd_ClientStatuses;
import com.morgner.mccbot.protocol.X1d_DestroyEntity;
import com.morgner.mccbot.protocol.Xff_Disconnect;
import com.morgner.mccbot.protocol.Xfd_EncryptionKeyRequest;
import com.morgner.mccbot.protocol.X05_EntityEquipment;
import com.morgner.mccbot.protocol.X23_EntityHeadLook;
import com.morgner.mccbot.protocol.X1e_EntityInfo;
import com.morgner.mccbot.protocol.X20_EntityLook;
import com.morgner.mccbot.protocol.X28_EntityMetadata;
import com.morgner.mccbot.protocol.X1f_EntityRelativeMove;
import com.morgner.mccbot.protocol.X21_EntityRelativeMoveLook;
import com.morgner.mccbot.protocol.X26_EntityStatus;
import com.morgner.mccbot.protocol.X1c_EntityVelocity;
import com.morgner.mccbot.protocol.X46_GameStateChange;
import com.morgner.mccbot.protocol.X02_Handshake;
import com.morgner.mccbot.protocol.X00_KeepAlive;
import com.morgner.mccbot.protocol.X01_LoginRequest;
import com.morgner.mccbot.protocol.X3f_ParticleEffect;
import com.morgner.mccbot.protocol.Xca_PlayerAbilities;
import com.morgner.mccbot.protocol.Xc9_PlayerListItem;
import com.morgner.mccbot.protocol.X0c_PlayerLook;
import com.morgner.mccbot.protocol.X0b_PlayerPosition;
import com.morgner.mccbot.protocol.X0d_PlayerPositionLook;
import com.morgner.mccbot.protocol.Xfe_ServerListPing;
import com.morgner.mccbot.protocol.X67_SetSlot;
import com.morgner.mccbot.protocol.X68_SetWindowItems;
import com.morgner.mccbot.protocol.X10_SlotSelectionChange;
import com.morgner.mccbot.protocol.X18_SpawnMob;
import com.morgner.mccbot.protocol.X17_SpawnObject;
import com.morgner.mccbot.protocol.X06_SpawnPosition;
import com.morgner.mccbot.protocol.X04_TimeUpdate;
import com.morgner.mccbot.protocol.X08_UpdateHealth;
import com.morgner.mccbot.protocol.X0a_OnGround;
import com.morgner.mccbot.protocol.X12_Animation;
import com.morgner.mccbot.protocol.X13_EntityAction;
import com.morgner.mccbot.protocol.X14_SpawnNamedEntity;
import com.morgner.mccbot.protocol.X16_CollectItem;
import com.morgner.mccbot.protocol.X1a_SpawnXPOrbs;
import com.morgner.mccbot.protocol.X22_EntityTeleport;
import com.morgner.mccbot.protocol.X27_AttachEntity;
import com.morgner.mccbot.protocol.X2a_RemoveEffect;
import com.morgner.mccbot.protocol.X2b_SetExperience;
import com.morgner.mccbot.protocol.X2c_EntityProperties;
import com.morgner.mccbot.protocol.X34_MultiBlockChange;
import com.morgner.mccbot.protocol.X35_BlockChange;
import com.morgner.mccbot.protocol.X37_BlockBreakAnimation;
import com.morgner.mccbot.protocol.X3d_Effect;
import com.morgner.mccbot.protocol.X3e_NamedSoundEffect;
import com.morgner.mccbot.protocol.X47_SpawnGlobalEntity;
import com.morgner.mccbot.protocol.X64_OpenWindow;
import com.morgner.mccbot.protocol.X84_UpdateTileEntity;
import com.morgner.mccbot.protocol.Xc8_IncrementStatistics;
import com.morgner.mccbot.protocol.Xcc_ClientSettings;
import com.morgner.mccbot.protocol.Xfa_PluginMessage;
import com.morgner.mccbot.serializer.BooleanSerializer;
import com.morgner.mccbot.serializer.ByteArraySerializer;
import com.morgner.mccbot.serializer.ByteSerializer;
import com.morgner.mccbot.serializer.BulkChunkSerializer;
import com.morgner.mccbot.serializer.ByteDataSerializer;
import com.morgner.mccbot.serializer.ChunkSerializer;
import com.morgner.mccbot.serializer.DoubleSerializer;
import com.morgner.mccbot.serializer.EntityListSerializer;
import com.morgner.mccbot.serializer.FloatSerializer;
import com.morgner.mccbot.serializer.IntSerializer;
import com.morgner.mccbot.serializer.LongSerializer;
import com.morgner.mccbot.serializer.EntityMetadataSerializer;
import com.morgner.mccbot.serializer.MultiBlockDataSerializer;
import com.morgner.mccbot.serializer.MultiSlotDataSerializer;
import com.morgner.mccbot.serializer.NBTDataSerializer;
import com.morgner.mccbot.serializer.ObjectDataSerializer;
import com.morgner.mccbot.serializer.PropertiesSerializer;
import com.morgner.mccbot.serializer.ShortSerializer;
import com.morgner.mccbot.serializer.SlotDataSerializer;
import com.morgner.mccbot.serializer.StringSerializer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Abstract base class for all packets of the minecraft protocol. This
 * class initializes all the serializers and the protocol packets for
 * the minecraft protocol version 74, as here: http://wiki.vg/Protocol
 * 
 * @author Christian Morgner
 */
public abstract class Packet {

	private static final Map<Class, Serializer> serializerMap = new LinkedHashMap<>();
	private static final Map<Class, List<Field>> fieldMap     = new LinkedHashMap<>();
	private static final Map<Integer, Class> protocolMap      = new LinkedHashMap<>();
	
	static {
		
		// register serializers
		serializerMap.put(String.class, new StringSerializer());
		serializerMap.put(Integer.class, new IntSerializer());
		serializerMap.put(Integer.TYPE, new IntSerializer());
		serializerMap.put(Long.class, new LongSerializer());
		serializerMap.put(Long.TYPE, new LongSerializer());
		serializerMap.put(Short.class, new ShortSerializer());
		serializerMap.put(Short.TYPE, new ShortSerializer());
		serializerMap.put(Byte.class, new ByteSerializer());
		serializerMap.put(Byte.TYPE, new ByteSerializer());
		serializerMap.put(byte[].class, new ByteArraySerializer());
		serializerMap.put(Double.class, new DoubleSerializer());
		serializerMap.put(Double.TYPE, new DoubleSerializer());
		serializerMap.put(Float.class, new FloatSerializer());
		serializerMap.put(Float.TYPE, new FloatSerializer());
		serializerMap.put(Boolean.class, new BooleanSerializer());
		serializerMap.put(Boolean.TYPE, new BooleanSerializer());
		serializerMap.put(Chunk.class, new ChunkSerializer());
		serializerMap.put(BulkChunk.class, new BulkChunkSerializer());
		serializerMap.put(SlotData.class, new SlotDataSerializer());
		serializerMap.put(MultiSlotData.class, new MultiSlotDataSerializer());
		serializerMap.put(EntityMetadata.class, new EntityMetadataSerializer());
		serializerMap.put(ObjectData.class, new ObjectDataSerializer());
		serializerMap.put(NBTData.class, new NBTDataSerializer());
		serializerMap.put(EntityList.class, new EntityListSerializer());
		serializerMap.put(MultiBlockData.class, new MultiBlockDataSerializer());
		serializerMap.put(ByteData.class, new ByteDataSerializer());
		serializerMap.put(Properties.class, new PropertiesSerializer());
		
		// register PDUs
		Packet.registerPacket(0x00, X00_KeepAlive.class);
		Packet.registerPacket(0x01, X01_LoginRequest.class);
		Packet.registerPacket(0x02, X02_Handshake.class);
		Packet.registerPacket(0x03, X03_ChatMessage.class);
		Packet.registerPacket(0x04, X04_TimeUpdate.class);
		Packet.registerPacket(0x05, X05_EntityEquipment.class);
		Packet.registerPacket(0x06, X06_SpawnPosition.class);
		Packet.registerPacket(0x08, X08_UpdateHealth.class);
		Packet.registerPacket(0x0a, X0a_OnGround.class);
		Packet.registerPacket(0x0b, X0b_PlayerPosition.class);
		Packet.registerPacket(0x0c, X0c_PlayerLook.class);
		Packet.registerPacket(0x0d, X0d_PlayerPositionLook.class);
		Packet.registerPacket(0x10, X10_SlotSelectionChange.class);
		Packet.registerPacket(0x12, X12_Animation.class);
		Packet.registerPacket(0x13, X13_EntityAction.class);
		Packet.registerPacket(0x14, X14_SpawnNamedEntity.class);
		Packet.registerPacket(0x16, X16_CollectItem.class);
		Packet.registerPacket(0x17, X17_SpawnObject.class);
		Packet.registerPacket(0x18, X18_SpawnMob.class);
		Packet.registerPacket(0x1a, X1a_SpawnXPOrbs.class);
		Packet.registerPacket(0x1c, X1c_EntityVelocity.class);
		Packet.registerPacket(0x1d, X1d_DestroyEntity.class);
		Packet.registerPacket(0x1e, X1e_EntityInfo.class);
		Packet.registerPacket(0x1f, X1f_EntityRelativeMove.class);
		Packet.registerPacket(0x20, X20_EntityLook.class);
		Packet.registerPacket(0x21, X21_EntityRelativeMoveLook.class);
		Packet.registerPacket(0x22, X22_EntityTeleport.class);
		Packet.registerPacket(0x23, X23_EntityHeadLook.class);
		Packet.registerPacket(0x26, X26_EntityStatus.class);
		Packet.registerPacket(0x27, X27_AttachEntity.class);
		Packet.registerPacket(0x28, X28_EntityMetadata.class);
		Packet.registerPacket(0x2a, X2a_RemoveEffect.class);
		Packet.registerPacket(0x2b, X2b_SetExperience.class);
		Packet.registerPacket(0x2c, X2c_EntityProperties.class);
		Packet.registerPacket(0x33, X33_ChunkData.class);
		Packet.registerPacket(0x34, X34_MultiBlockChange.class);
		Packet.registerPacket(0x35, X35_BlockChange.class);
		Packet.registerPacket(0x37, X37_BlockBreakAnimation.class);
		Packet.registerPacket(0x38, X38_BulkChunkData.class);
		Packet.registerPacket(0x3d, X3d_Effect.class);
		Packet.registerPacket(0x3e, X3e_NamedSoundEffect.class);
		Packet.registerPacket(0x3f, X3f_ParticleEffect.class);
		Packet.registerPacket(0x46, X46_GameStateChange.class);
		Packet.registerPacket(0x47, X47_SpawnGlobalEntity.class);
		Packet.registerPacket(0x64, X64_OpenWindow.class);
		Packet.registerPacket(0x67, X67_SetSlot.class);
		Packet.registerPacket(0x68, X68_SetWindowItems.class);
		Packet.registerPacket(0x84, X84_UpdateTileEntity.class);
		Packet.registerPacket(0xc8, Xc8_IncrementStatistics.class);
		Packet.registerPacket(0xc9, Xc9_PlayerListItem.class);
		Packet.registerPacket(0xca, Xca_PlayerAbilities.class);
		Packet.registerPacket(0xcc, Xcc_ClientSettings.class);
		Packet.registerPacket(0xcd, Xcd_ClientStatuses.class);
		Packet.registerPacket(0xfa, Xfa_PluginMessage.class);
		Packet.registerPacket(0xfd, Xfd_EncryptionKeyRequest.class);
		Packet.registerPacket(0xfe, Xfe_ServerListPing.class);
		Packet.registerPacket(0xff, Xff_Disconnect.class);
	}
	
	private int packetId = -1;
	
	public Packet(int packetId) {
		
		this.packetId = Integer.valueOf(packetId).byteValue();
	}
	
	public static void registerPacket(int id, Class packetClass) {
		
		Map<Integer, Field> sortedFieldMap = new LinkedHashMap<>();
		
		// scan fields
		for (Field field : packetClass.getDeclaredFields()) {
			
			ProtocolField protocolFieldAnnotation = field.getAnnotation(ProtocolField.class);
			if (protocolFieldAnnotation != null) {
				
				int index = protocolFieldAnnotation.value();
				
				if (sortedFieldMap.containsKey(index)) {
				
					throw new IllegalStateException("!!!!!!!!!! index " + index + " already used in " + packetClass.getSimpleName());
					
				} else {

					sortedFieldMap.put(index, field);
				}
			}
		}
		
		if (!sortedFieldMap.isEmpty()) {
			
			fieldMap.put(packetClass, new LinkedList<>(sortedFieldMap.values()));
			protocolMap.put(id, packetClass);
		}
	}
	
	public static Packet readPacket(DataInputStream is) throws IOException {
		
		int packetId = is.readUnsignedByte();
		
		Class<Packet> packetClass = protocolMap.get(packetId);
		Packet packet = null;
		
		if (packetClass != null) {
			
			try {
				packet             = packetClass.newInstance();
				List<Field> fields = packet.getFields();

				for (Field field : fields) {

					Serializer serializer = serializerMap.get(field.getType());
					if (serializer != null) {

						try {
							Object value = serializer.deserialize(is);
							field.setAccessible(true);
							field.set(packet, value);
							
						} catch (Throwable t) {
							
							System.out.println("########## Error while parsing packet ID " + Integer.toHexString(packetId));
							System.out.println("This usually means that the protocol has changed.");
						}
					}
				}
				
			} catch (InstantiationException | IllegalAccessException iex) {
				iex.printStackTrace();
			}
			
		} else {

			System.out.println("########## Unknow packet ID " + Integer.toHexString(packetId));
			System.out.println("This usually means that the protocol has changed or that the");
			System.out.println("given packet ID is not yet implementd in this client.");
		}

		return packet;
	}
	
	public static void writePacket(Packet packet, DataOutputStream os) throws IOException {

		// write type first
		os.writeByte(packet.packetId);
		
		// write fields
		List<Field> fields = packet.getFields();
		for (Field field : fields) {

			Serializer serializer = serializerMap.get(field.getType());
			if (serializer != null) {
				
				try {
					field.setAccessible(true);
					Object value = field.get(packet);
					serializer.serialize(value, os);
					
				} catch (IllegalAccessException iaex) {
					iaex.printStackTrace();
				}
				
			} else {
				// log error
			}
		}
		
		os.flush();
	}

	/**
	 * This method is called by the connection manager when a packet
	 * arrives. It allowes the client to respond to a given packet.
	 * Override this method and send the appropriate response for
	 * the particular packet type.
	 * 
	 * @param connectionManager
	 * @throws IOException 
	 */
	public void respond(World connectionManager) throws IOException {
		// default: do nothing
	}
	
	/**
	 * This method is called by the connection manager when a packet
	 * arrives. It allows the client to adapt its world state accordingly.
	 * 
	 * @param worldState 
	 */
	public void update(WorldState worldState) {
		// default: do nothing
	}
	
	public boolean doOutput() {
		return false;
	}
	
	private List<Field> getFields() {
		return fieldMap.get(getClass());
	}
}
