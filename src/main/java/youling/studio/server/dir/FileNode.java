package youling.studio.server.dir;

/**
 * @author liurui
 * @date 2019/1/1 下午8:31
 *
 * 文件节点
 */
public class FileNode extends Node {
    public FileNode(){
        super();
    }

    public FileNode(String uri){
        super(uri);
        this.uri = uri;
    }
}
