package protocolsupport.protocol.packet.middleimpl.writeable.play.v_4_5_6;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.packet.Login;
import protocolsupport.protocol.packet.middleimpl.writeable.LegacySingleWriteablePacket;
import protocolsupport.protocol.serializer.StringSerializer;

public class LoginPacket extends LegacySingleWriteablePacket<Login> {

	public LoginPacket() {
		super(0x01);
	}

	@Override
	protected void write(ByteBuf data, Login packet) {
		data.writeInt(packet.getEntityId());
		StringSerializer.writeShortUTF16BEString(data, packet.getLevelType());
		data.writeByte(packet.getGameMode());
		data.writeByte(packet.getDimension());
		data.writeByte(packet.getDifficulty());
		data.writeByte(0);
		data.writeByte(packet.getMaxPlayers());
	}

}
