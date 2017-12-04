package protocolsupport.protocol.packet.middleimpl.writeable.play.v_5_6;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.packet.ScoreboardDisplay;
import protocolsupport.protocol.packet.middleimpl.writeable.LegacySingleWriteablePacket;
import protocolsupport.protocol.serializer.StringSerializer;

public class ScoreboardDisplayPacket extends LegacySingleWriteablePacket<ScoreboardDisplay> {

	public ScoreboardDisplayPacket() {
		super(0xD1);
	}

	@Override
	protected void write(ByteBuf data, ScoreboardDisplay packet) {
		data.writeByte(packet.getPosition());
		StringSerializer.writeShortUTF16BEString(data, packet.getName());
	}

}
