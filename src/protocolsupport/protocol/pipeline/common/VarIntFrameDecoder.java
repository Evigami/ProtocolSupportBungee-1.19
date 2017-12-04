package protocolsupport.protocol.pipeline.common;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

public class VarIntFrameDecoder extends ByteToMessageDecoder {

	private int packetLength = -1;

	@Override
	public void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> list)  {
		if (packetLength == -1) {
			input.markReaderIndex();
			int tmpPacketLength = 0;
			for (int i = 0; i < 3; ++i) {
				if (!input.isReadable()) {
					input.resetReaderIndex();
					return;
				}
				int part = input.readByte();
				tmpPacketLength |= (part & 0x7F) << (i * 7);
				if (part >= 0) {
					packetLength = tmpPacketLength;
					if (packetLength == 0) {
						packetLength = -1;
					}
					return;
				}
			}
			throw new CorruptedFrameException("Packet length varint length is more than 21 bits");
		}
        if (input.readableBytes() < packetLength) {
            return;
        }
        list.add(input.readBytes(packetLength));
        packetLength = -1;
	}

}
