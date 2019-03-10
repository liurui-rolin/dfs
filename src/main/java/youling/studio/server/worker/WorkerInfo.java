package youling.studio.server.worker;

import youling.studio.server.RoleInfo;

/**
 * @author liurui
 * @date 2019/1/1 上午11:10
 */
public class WorkerInfo extends RoleInfo {
    private Double capacity = 0d;
    private Float userProportion = 0.0f;

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Float getUserProportion() {
        return userProportion;
    }

    public void setUserProportion(Float userProportion) {
        this.userProportion = userProportion;
    }
}
