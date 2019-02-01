package youling.studio.client;

import org.apache.avro.ipc.NettyTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youling.studio.protocol.MasterProtocol;
import youling.studio.protocol.PutRequest;
import youling.studio.protocol.PutResponse;
import youling.studio.server.Configuration;
import youling.studio.server.Constants;
import youling.studio.utils.LogUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @author liurui
 * @date 2019/1/1 下午4:09
 *
 * DFS客户端:
 *  1.提供文件写入功能
 *  2.提供文件读取功能
 */
public class DfsClient {
    // 日志记录
    private static Logger log = LoggerFactory.getLogger("Client");

    //配置信息
    Configuration conf = Configuration.getConfiguration();

    // Master代理
    private MasterProtocol master = null;
    NettyTransceiver masterProxy = null;

    public DfsClient() {
        try {
            //获取master代理
            masterProxy = new NettyTransceiver(new InetSocketAddress(conf.masterGet(Constants.MASTR_HOST),conf.masterGetInt(Constants.MASTR_PORT)));
            master = SpecificRequestor.getClient(MasterProtocol.class, masterProxy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MasterProtocol getMaster() {
        return this.master;
    }

    public void close(){
        //关闭avro rpc连接
        if(this.masterProxy!=null){
            this.masterProxy.close(true);
        }
    }

    public static void main(String[] args) {
        DfsClient client = null;
        try{
            //获取操作命令
            String cmd = "put";
            String fileName = "/Users/ruiliu/Desktop/tempfile1.txt";

            //构建dfs客户端
            client = new DfsClient();

            switch (cmd){
                case "put":
                    //构建请求对象
                    PutRequest request = new PutRequest();
                    request.setClientId("client-"+System.currentTimeMillis());
                    request.setHost(InetAddress.getLocalHost().getHostAddress());
                    request.setUri(fileName);

                    //发送put请求
                    PutResponse putResponse = client.getMaster().put(request);
                    log.info("Put result: " + putResponse.toString());

                    //写入文件块到worker节点


                    break;
                default:
                    log.info("你输入的命令不存在!");
                    break;
            }
        }catch (Exception e){
            log.error(LogUtils.getExceptionOut(e));
        }finally {
            if(client!=null){
                client.close();
            }
        }
    }
}
