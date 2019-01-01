package youling.studio.server;

/**
 * @author liurui
 * @date 2018/12/31 下午3:44
 *
 * 所有的server实例要实现此接口  里面定义了服务常有的功能
 */
public interface Server {
    public void init();
    public void startServer();
}
