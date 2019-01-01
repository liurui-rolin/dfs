package youling.studio.server.dir;

/**
 * @author liurui
 * @date 2019/1/1 下午8:30
 *
 * 目录和文件节点的父类
 */
public abstract class Node {
    public String uri = "";
    public String name = "";
    public Long size = 0L;
    public Long createTime = System.currentTimeMillis();
    public Long updateTime = System.currentTimeMillis();

    public transient Node father = null;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Node getFather() {
        return father;
    }

    public void setFather(Node father) {
        this.father = father;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node(){
    }

    public Node(String uri){
        this.uri = uri;
    }
}
