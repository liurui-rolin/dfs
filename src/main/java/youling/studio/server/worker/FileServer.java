package youling.studio.server.worker;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youling.studio.server.Configuration;
import youling.studio.utils.Constants;
import youling.studio.server.Server;
import youling.studio.utils.LogUtils;

/**
 * 文件上传服务器
 * @author liurui
 * @date 2019/2/6 下午7:47
 */
public class FileServer implements Server {
    // 日志记录
    private static Logger log = LoggerFactory.getLogger("Worker");
    private Configuration conf = null;

    public FileServer(Configuration conf){
        this.conf = conf;
    }

    @Override
    public void init() {
    }

    /**
     * 启动fileupload服务
     * @throws InterruptedException
     */
    @Override
    public void startServer() {
        //获取fileserver端口
        Integer port = conf.workerGetInt(Constants.WORKER_FILESERVER_PORT);
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG,1024).
                    childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new ObjectEncoder());
                            channel.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(null)));
                            channel.pipeline().addLast(new FileServerHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        }catch (InterruptedException ie){
            log.error("启动文件传送服务器失败:" + LogUtils.getExceptionOut(ie));
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
