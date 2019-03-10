package youling.studio.protocol;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.avro.AvroRemoteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youling.studio.server.master.Master;

import java.util.List;
import java.util.Map;

/**
 * @author liurui
 * @date 2018/12/31 下午4:47
 */
public class MasterProtocolImpl implements MasterProtocol {
    private static Logger log = LoggerFactory.getLogger("Master");
    //持有Master引用
    private Master master = null;

    public MasterProtocolImpl(Master master) {
        this.master = master;
    }

    /**
     * 接受worker心跳
     * @param message
     * @return
     * @throws AvroRemoteException
     */
    @Override
    public HeartBeatResponse heartbeat(HeartBeatRequest message) throws AvroRemoteException {
        log.info("收到来自{}的心跳.",message.getWorderId());
        int res = master.heartbeat(message);

        HeartBeatResponse response = new HeartBeatResponse();
        response.setStatus(0);
        response.setMsg("ok");
        return response;
    }

    @Override
    public PutResponse put(PutRequest fileInfo) throws AvroRemoteException {
        log.info("收到来自{}的写入请求.",fileInfo.getClientId());
        PutResponse response = new PutResponse();

        Map<CharSequence,CharSequence> res = this.master.put(fileInfo);
        if(res==null){
            res = Maps.newHashMap();
            response.setStatus(1);
            response.setMsg("传入路径不正确!");
        }else {
            response.setStatus(0);
            response.setMsg("ok");
        }

        //获取存储文件块的datanodes
        List<Map<CharSequence,CharSequence>> datanodes = this.master.getDatenodesForFile(fileInfo);
        response.setDatanodes(datanodes);

        return response;
    }

    @Override
    public GetResponse get(GetRequest fileInfo) throws AvroRemoteException {
        log.info("收到来自{}的读取请求.",fileInfo.getClientId());

        GetResponse response = new GetResponse();
        response.setStatus(0);
        response.setMsg("ok");
        return response;
    }
}
