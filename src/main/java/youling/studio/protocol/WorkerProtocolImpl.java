package youling.studio.protocol;

import org.apache.avro.AvroRemoteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youling.studio.server.worker.Worker;

/**
 * @author liurui
 * @date 2018/12/31 下午11:07
 */
public class WorkerProtocolImpl implements WorkerProtocol {
    private static Logger log = LoggerFactory.getLogger("Worker");
    //持有Master引用
    private Worker worker = null;

    public WorkerProtocolImpl(Worker worker) {
        this.worker = worker;
    }

    @Override
    public ReadResponse delete(ReadRequest request) throws AvroRemoteException {
        log.info("收到Master的删除请求!");

        ReadResponse response = new ReadResponse();
        response.setStatus(0);
        response.setMsg("ok");
        return response;
    }
}
