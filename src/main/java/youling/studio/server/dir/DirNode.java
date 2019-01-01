package youling.studio.server.dir;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author liurui
 * @date 2019/1/1 下午8:31
 *
 * 目录节点
 */
public class DirNode extends Node {
    public List<Node> children = Lists.newArrayList();

    public DirNode(){
        super();
    }

    public DirNode(String uri){
        super(uri);
        this.uri = uri;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}
