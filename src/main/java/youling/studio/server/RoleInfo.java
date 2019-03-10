package youling.studio.server;

/**
 * @author liurui
 * @date 2019/1/1 上午11:11
 */
public abstract class RoleInfo {
    private String nodeId = "";
    private String host = "";
    private int port = 0;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
