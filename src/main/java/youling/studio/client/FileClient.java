package youling.studio.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youling.studio.server.Configuration;
import youling.studio.utils.Constants;
import youling.studio.server.worker.FileUploadFile;

import java.io.File;

/**
 * @author liurui
 * @date 2019/2/7 下午6:14
 */
public class FileClient {
    // 日志记录
    private static Logger log = LoggerFactory.getLogger("Client");

    Bootstrap b = null;
    EventLoopGroup group = null;

    /**
     * 基于Netty执行上传
     * @param port
     * @param host
     * @param fileUploadFile
     * @throws InterruptedException
     */
    private void doUpload(int port,String host,final FileUploadFile fileUploadFile) throws InterruptedException {
        group = new NioEventLoopGroup();
        b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true).
                handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new ObjectEncoder());
                        channel.pipeline().addLast(new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(null)));
                        channel.pipeline().addLast(new FileClientHandler(fileUploadFile));
                    }
                });
        ChannelFuture f = b.connect(host,port).sync();
        f.channel().closeFuture().sync();
    }

    /**
     * 上传方法
     * @param fileStr
     * @param host
     */
    public void upload(String fileStr,String host) {
        try{
            FileUploadFile uploadFile = new FileUploadFile();
            File file = new File(fileStr);
            String fileMd5 = file.getName();
            uploadFile.setFile(file);
            uploadFile.setFileMd5(fileMd5);
            uploadFile.setStartPos(0);
            this.doUpload(Configuration.getConfiguration().workerGetInt(Constants.WORKER_FILESERVER_PORT),host,uploadFile);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void close(){
        b.clone(group);
    }
}
