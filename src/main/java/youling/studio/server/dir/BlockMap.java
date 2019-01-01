package youling.studio.server.dir;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liurui
 * @date 2019/1/1 下午8:35
 *
 * 存储文件快到worker节点的映射关系
 *
 */
public class BlockMap {
    // 日志记录
    private static Logger log = LoggerFactory.getLogger("Master");

    // 存储块信息的map
    private ConcurrentHashMap<FileNode,List<BlockInfo>> blocks = new ConcurrentHashMap<FileNode,List<BlockInfo>>(2^10);

    // blockmap
    private static volatile BlockMap blockMap = null;

    private BlockMap(){
    }

    public static BlockMap getBlockMap(){
        if(blockMap==null){
            synchronized (BlockMap.class){
                if(blockMap==null){
                    blockMap = new BlockMap();
                }
            }
        }
        return blockMap;
    }


}
