package youling.studio.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youling.studio.server.worker.FileUploadFile;

import java.io.RandomAccessFile;

/**
 * @author liurui
 * @date 2019/2/7 下午6:14
 */
public class FileClientHandler extends ChannelInboundHandlerAdapter {
    // 日志记录
    private static Logger log = LoggerFactory.getLogger("Client");

    private int byteRead;
    private volatile int start = 0;
    private volatile int lastLength = 0;
    public RandomAccessFile randomAccessFle;
    public FileUploadFile fileUploadFile;

    public FileClientHandler(FileUploadFile fileUploadFile) {
        if(fileUploadFile.getFile().exists()){
            if(!fileUploadFile.getFile().isFile()){
                log.info("Not a file:"+fileUploadFile.getFile());
                return;
            }
            this.fileUploadFile = fileUploadFile;
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        try{
            randomAccessFle = new RandomAccessFile(fileUploadFile.getFile(),"r");
            randomAccessFle.seek(fileUploadFile.getStartPos());
            lastLength = (int) randomAccessFle.length()/10;
            byte[] bytes = new byte[lastLength];
            if((byteRead=randomAccessFle.read(bytes))!=-1){
                fileUploadFile.setEndPos(byteRead);
                fileUploadFile.setBytes(bytes);
                ctx.writeAndFlush(fileUploadFile);
            }else {
                log.info("文件已经读完");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof Integer){
            start = (Integer) msg;
            if(start!=-1){
                randomAccessFle = new RandomAccessFile(fileUploadFile.getFile(),"r");
                randomAccessFle.seek(start);
                log.info("块长度:" + randomAccessFle.length()/10);
                log.info("长度:" + (randomAccessFle.length()-start));
                int a = (int)(randomAccessFle.length()-start);
                int b = (int)(randomAccessFle.length()/10);
                if(a<b){
                    lastLength = a;
                }
                byte[] bytes = new byte[lastLength];
                log.info("----------------------"+bytes.length);
                if((byteRead = randomAccessFle.read(bytes))!=-1 && (randomAccessFle.length()-start) > 0 ){
                    log.info("byte长度:" + bytes.length);
                    fileUploadFile.setEndPos(byteRead);
                    fileUploadFile.setBytes(bytes);
                    try{
                        ctx.writeAndFlush(fileUploadFile);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    randomAccessFle.close();
                    ctx.close();
                    log.info("文件已经读完--------:" + byteRead);
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
