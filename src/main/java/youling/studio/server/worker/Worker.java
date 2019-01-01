package youling.studio.server.worker;

import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.NettyTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;
import org.apache.avro.ipc.specific.SpecificResponder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youling.studio.protocol.MasterProtocol;
import youling.studio.protocol.MasterProtocolImpl;
import youling.studio.protocol.WorkerProtocol;
import youling.studio.protocol.WorkerProtocolImpl;
import youling.studio.server.Configuration;
import youling.studio.server.Constants;
import youling.studio.server.Server;
import youling.studio.utils.LogUtils;

import java.net.InetSocketAddress;
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


            //启动 Jetty server TODO

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


            log.info("启动Worker服务!");
        } catch (Exception e) {
            log.error(LogUtils.getExceptionOut(e));
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        //启动worker服务
        Worker worker = new Worker();
        worker.startServer();

    }
}
