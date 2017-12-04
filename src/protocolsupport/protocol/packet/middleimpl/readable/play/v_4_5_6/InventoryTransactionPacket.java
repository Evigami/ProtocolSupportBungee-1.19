package protocolsupport.protocol.packet.middleimpl.readable.play.v_4_5_6;

import protocolsupport.protocol.packet.middleimpl.readable.LegacyFixedLengthPassthroughReadableMiddlePacket;

public class InventoryTransactionPacket extends LegacyFixedLengthPassthroughReadableMiddlePacket {

	public static final int PACKET_ID = 0x6A;

	public InventoryTransactionPacket() {
		super(PACKET_ID, Byte.BYTES + Short.BYTES + Byte.BYTES);
	}

}
