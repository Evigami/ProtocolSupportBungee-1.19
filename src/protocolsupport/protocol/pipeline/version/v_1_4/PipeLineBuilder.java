package protocolsupport.protocol.pipeline.version.v_1_4;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import net.md_5.bungee.netty.PipelineUtils;
import protocolsupport.api.Connection;
import protocolsupport.protocol.pipeline.IPipeLineBuilder;
import protocolsupport.protocol.pipeline.common.EncapsulatedHandshakeSender;
import protocolsupport.protocol.pipeline.common.NoOpFrameDecoder;
import protocolsupport.protocol.pipeline.common.NoOpFrameEncoder;
import protocolsupport.protocol.storage.NetworkDataCache;

public class PipeLineBuilder extends IPipeLineBuilder {

	@Override
	public void buildBungeeClientCodec(Channel channel, Connection connection) {
		ChannelPipeline pipeline = channel.pipeline();
		NetworkDataCache cache = new NetworkDataCache();
		cache.storeIn(connection);
		pipeline.replace(PipelineUtils.PACKET_DECODER, PipelineUtils.PACKET_DECODER, new FromClientPacketDecoder(connection, cache));
		pipeline.replace(PipelineUtils.PACKET_ENCODER, PipelineUtils.PACKET_ENCODER, new ToClientPacketEncoder(connection, cache));
	}

	@Override
	public void buildBungeeClientPipeLine(Channel channel, Connection connection) {
		ChannelPipeline pipeline = channel.pipeline();
		pipeline.replace(PipelineUtils.FRAME_DECODER, PipelineUtils.FRAME_DECODER, new NoOpFrameDecoder());
		pipeline.replace(PipelineUtils.FRAME_PREPENDER, PipelineUtils.FRAME_PREPENDER, new NoOpFrameEncoder());
	}

	@Override
	public void buildBungeeServer(Channel channel, Connection connection) {
		ChannelPipeline pipeline = channel.pipeline();
		pipeline.addFirst(new EncapsulatedHandshakeSender(null, false));
		NetworkDataCache cache = NetworkDataCache.getFrom(connection);
		pipeline.replace(PipelineUtils.PACKET_DECODER, PipelineUtils.PACKET_DECODER, new FromServerPacketDecoder(connection, cache));
		pipeline.replace(PipelineUtils.PACKET_ENCODER, PipelineUtils.PACKET_ENCODER, new ToServerPacketEncoder(connection, cache));
	}

}
