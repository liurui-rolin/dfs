package youling.studio.server.worker;

import com.google.common.collect.Lists;
import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.NettyTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;
import org.apache.avro.ipc.specific.SpecificResponder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youling.studio.protocol.MasterProtocol;
import youling.studio.protocol.WorkerProtocol;
import youling.studio.protocol.WorkerProtocolImpl;
import youling.studio.server.Configuration;
import youling.studio.utils.Constants;
import youling.studio.server.Server;
import youling.studio.utils.LogUtils;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liurui
 * @date 2018/12/31 下午4:06
 */
public class Worker implements Server {
    // 日志记录
    private static Logger log = LoggerFactory.getLogger("Worker");

    // 安全操作锁
    private static Lock lock = new ReentrantLock();

    //配置信息
    Configuration conf = Configuration.getConfiguration();

    // rpc server
    private NettyServer rpcServer = null;

    // Master代理
    private MasterProtocol master = null;

    //附带服务列表
    private HeartBeatService heartbeatService = null;

    //文件上传服务器
    private FileServer fileServer = null;

    public Configuration getConf() {
        return conf;
    }

    public MasterProtocol getMaster() {
        return master;
    }

    @Override
    public void init() {
        lock.lock();
        try{
            log.info("开始初始化Worker服务!");
            //启动rpcserver
            rpcServer = new NettyServer(
                    new SpecificResponder(WorkerProtocol.class,new WorkerProtocolImpl(this)),
                    new InetSocketAddress(conf.workerGet(Constants.WORKER_HOST),conf.workerGetInt(Constants.WORKER_PORT))
            );
            log.info("start rpc server successed!");

            //获取master代理
            NettyTransceiver proxy = new NettyTransceiver(new InetSocketAddress(conf.masterGet(Constants.MASTR_HOST),conf.masterGetInt(Constants.MASTR_PORT)));
            master = SpecificRequestor.getClient(MasterProtocol.class,proxy);

            //心跳服务
            heartbeatService = new HeartBeatService(this);

            //启动 Jetty server 文件上传服务器
            fileServer = new FileServer(this.conf);

            log.info("完成初始化Worker服务!");
        } catch (Exception e) {
            log.error(LogUtils.getExceptionOut(e));
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void startServer() {
        lock.lock();
        try{
            log.info("开始启动Worker服务!");
            init();

            //启动心跳服务
            this.heartbeatService.startServer();

            //启动文件传送服务器
            this.fileServer.startServer();


            log.info("启动Worker服务!");
        } catch (Exception e) {
            log.error(LogUtils.getExceptionOut(e));
        }finally {
            lock.unlock();
        }
    }

    /**
     * 获取worker总磁盘容量
     * @return
     */
    public Double getCapacity(){
        //TODO

        return 0d;
    }

    /**
     * 获取所有文件块信息
     * @return
     */
    public List<WorkerBlockInfo> getAllBlocks(){
        List<WorkerBlockInfo> blocks = Lists.newArrayList();

        //TODO

        return blocks;
    }


    public static void main(String[] args) {
        //启动worker服务
        Worker worker = new Worker();
        worker.startServer();

    }
}
