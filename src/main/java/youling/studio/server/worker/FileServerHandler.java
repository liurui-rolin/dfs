package youling.studio.server.worker;

import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youling.studio.server.Configuration;
import youling.studio.utils.Constants;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @author liurui
 * @date 2019/2/6 下午7:58
 */
public class FileServerHandler extends ChannelInboundHandlerAdapter {
    // 日志记录
    private static Logger log = LoggerFactory.getLogger("Worker");
    private int byteRead;
    private volatile int start = 0;
    private String file_dir = Configuration.getConfiguration().workerGet(Constants.WORKER_FILESERVER_DIRS);

    @Override
    public void channelRead(io.netty.channel.ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("处理读取请求 dir:" + file_dir);
        //上传文件对象
        if(msg instanceof FileUploadFile){
            log.info("服务端处理传送文件需求!");
            FileUploadFile uploadFile = (FileUploadFile) msg;
            //文件字节
            byte[] bytes = uploadFile.getBytes();
            byteRead = uploadFile.getEndPos();
            String md5 = uploadFile.getFileMd5();
            String path = file_dir + File.separator + md5;
            File file = new File(path);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
            randomAccessFile.seek(start);
            randomAccessFile.write(bytes);
            start = start + byteRead;
            if(byteRead>0){
                ctx.writeAndFlush(start);
            }else {
                randomAccessFile.close();
                ctx.close();
            }
        }
    }

    @Override
    public void exceptionCaught(io.netty.channel.ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
