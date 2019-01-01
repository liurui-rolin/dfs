package youling.studio.server.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youling.studio.protocol.HeartBeatRequest;
import youling.studio.protocol.MasterProtocol;
import youling.studio.server.Constants;
import youling.studio.server.Server;
import youling.studio.utils.LogUtils;

/**
 * @author liurui
 * @date 2018/12/31 下午11:26
 */
public class HeartBeatService implements Runnable,Server {
    // 日志记录
    private static Logger log = LoggerFactory.getLogger("Worker");

    private Worker worker = null;

    public HeartBeatService(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        while (true){
            try {
                log.info("执行一次心跳!");
                //心跳信息
                HeartBeatRequest request = new HeartBeatRequest();
                request.setWorderId(worker.getConf().workerGet(Constants.WORKER_HOST)+":"+worker.getConf().workerGet(Constants.WORKER_PORT));
                request.setHost(worker.getConf().workerGet(Constants.WORKER_HOST));
                request.setPort(worker.getConf().workerGetInt(Constants.WORKER_PORT));

                //发送心跳
                worker.getMaster().heartbeat(request);

                //睡眠
                Thread.sleep(worker.getConf().workerGetLong(Constants.WORKER_HEARTBEAT_INTERVAL));
            } catch (Exception e) {
                log.warn(LogUtils.getExceptionOut(e));
                //睡眠
                try {
                    Thread.sleep(worker.getConf().workerGetLong(Constants.WORKER_HEARTBEAT_INTERVAL));
                } catch (InterruptedException e1) {
                    log.warn(LogUtils.getExceptionOut(e1));
                }
            }
        }
    }

    @Override
    public void init() {
    }

    @Override
    public void startServer() {
        //启动心跳线程
        new Thread(this).start();
        log.info("心跳服务启动成功!");
    }

}
