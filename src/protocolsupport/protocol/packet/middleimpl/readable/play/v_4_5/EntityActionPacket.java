package protocolsupport.protocol.packet.middleimpl.readable.play.v_4_5;

import protocolsupport.protocol.packet.middleimpl.readable.LegacyFixedLengthPassthroughReadableMiddlePacket;

public class EntityActionPacket extends LegacyFixedLengthPassthroughReadableMiddlePacket {

	public static final int PACKET_ID = 0x13;

	public EntityActionPacket() {
		super(PACKET_ID, Integer.BYTES + Byte.BYTES);
	}

}
