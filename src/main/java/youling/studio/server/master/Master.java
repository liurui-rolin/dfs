package youling.studio.server.master;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.specific.SpecificResponder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youling.studio.protocol.HeartBeatRequest;
import youling.studio.protocol.MasterProtocol;
import youling.studio.protocol.MasterProtocolImpl;
import youling.studio.protocol.PutRequest;
import youling.studio.server.Configuration;
import youling.studio.server.Constants;
import youling.studio.server.Server;
import youling.studio.server.dir.BlockMap;
import youling.studio.server.dir.DirNode;
import youling.studio.server.dir.FileNode;
import youling.studio.server.dir.Node;
import youling.studio.server.worker.WorkerInfo;
import youling.studio.utils.LogUtils;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liurui
 * @date 2018/12/31 下午3:55
 * Master节点
 * 功能:
 *  1.管理存储节点
 *  2.管理命名空间
 *  3.管理文件块
 *  4.元数据读写
 */
public class Master implements Server{
    // 日志记录
    private static Logger log = LoggerFactory.getLogger("Master");

    // 安全操作锁
    private static Lock lock = new ReentrantLock();

    //配置信息
    Configuration conf = Configuration.getConfiguration();

    // rpc server
    private NettyServer rpcServer = null;

    //worker列表
    private Map<String,WorkerInfo> workers = null;

    //命名空间根节点
    private DirNode root = new DirNode("/");

    //blockmap 存储文件快信息
    private BlockMap blockMap = BlockMap.getBlockMap();

    /**
     * 初始化
     */
    @Override
    public void init() {
        lock.lock();
        try{
            //启动rpcserver
            rpcServer = new NettyServer(
                    new SpecificResponder(MasterProtocol.class,new MasterProtocolImpl(this)),
                    new InetSocketAddress(conf.masterGet(Constants.MASTR_HOST),conf.masterGetInt(Constants.MASTR_PORT))
            );
            log.info("start rpc server successed!");

            //初始化workers列表
            workers = new ConcurrentHashMap<String,WorkerInfo>();

            //启动 Jetty server TODO

        } catch (Exception e) {
            log.error(LogUtils.getExceptionOut(e));
        }finally {
            lock.unlock();
        }
    }

    /**
     * 启动服务
     */
    @Override
    public void startServer() {
        lock.lock();
        try{
            init();

            log.info("启动Master服务!");
        } catch (Exception e) {
            log.error(LogUtils.getExceptionOut(e));
        }finally {
            lock.unlock();
        }
    }

    /**
     * 接受worker节点心跳信息
     * @param message
     * @return
     */
    public int heartbeat(HeartBeatRequest message) {
        String workId = message.getWorderId().toString();
        if(!this.workers.containsKey(workId)){
            WorkerInfo info = new WorkerInfo();
            info.setNodeId(workId);
            info.setHost("");
            info.setPort(0);
            //存储worker信息
            this.workers.put(workId,info);
        }

        log.info("节点信息:" + this.workers.toString());
        return 0;
    }

    public List<Map<CharSequence,CharSequence>> put(PutRequest fileInfo) {
        log.info("Put文件:" + fileInfo);
        List<Map<CharSequence,CharSequence>> res = Lists.newArrayList();

        //验证uri正确性
        String uri = fileInfo.getUri().toString();
        if(!uri.startsWith("/") || uri.endsWith("/")){
            return null;
        }
        //创建目录
        createDir(uri,root);

        //获取数据块应该分布的worker节点
        // TODO

        //打印目录树
        printDir(this.root);
        return res;
    }

    /**
     * 递归创建给定目录
     * @return
     */
    private int createDir(String uri, DirNode node){
        if(uri.startsWith("/"))
            uri = uri.substring(1,uri.length());

        String curName,releaseName = null;
        if (uri.contains("/")){
            curName = uri.substring(0,uri.indexOf("/"));
            releaseName = uri.substring(uri.indexOf("/"),uri.length());
        }else{
            curName = uri;
            releaseName = uri;
        }

        //判断是否存在
        boolean contains = false;
        Node containsDir = null;
        for(Node c : node.getChildren()){
            if(curName.equals(c.getName())){
                containsDir = c;
                contains = true;
                break;
            }
        }

        //判断是创建目录还是文件
        if(uri.contains("/")){ //是
            //创建
            if(!contains) {
                String newUri = node.getUri() + "/" + curName;
                if("/".equals(node.getUri())){
                    newUri = node.getUri() + curName;
                }
                DirNode dir = new DirNode(newUri);
                dir.setName(curName);
                dir.setFather(node);
                node.getChildren().add(dir);
                //递归
                createDir(releaseName, dir);
            }else{
                //递归
                createDir(releaseName, (DirNode)containsDir);
            }
        }else{//否
            //创建
            if(!contains) {
                String newUri = node.getUri() + "/" + curName;
                if("/".equals(node.getUri())){
                    newUri = node.getUri() + curName;
                }
                FileNode dir = new FileNode(newUri);
                dir.setName(curName);
                dir.setFather(node);
                node.getChildren().add(dir);
            }else {
                //最终要创建的文件已存在
                log.info("文件已存在! file:" + node.getUri() + "/" + curName);
                return 1;
            }
        }

        return 0;
    }

    /**
     * 递归打印文件目录树
     * @param node
     */
    private void printDir(DirNode node){
        for(Node n : node.getChildren()){
            if(n instanceof DirNode){
                log.info("Node Dir : " + n.getUri());
                printDir((DirNode) n);
            } else if(n instanceof FileNode) {
                log.info("Node File : " + n.getUri());
            }
        }
    }

    /**
     * Master服务启动入口
     * @param args
     */
    public static void main(String[] args) {
        //启动master服务
        Master master = new Master();
        master.startServer();
    }


}
