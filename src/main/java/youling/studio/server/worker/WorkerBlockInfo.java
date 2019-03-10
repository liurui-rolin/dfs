package youling.studio.server.worker;

/**
 * @author liurui
 * @date 2019/2/9 上午11:37
 */
public class WorkerBlockInfo {
    private String filename = "";
    private Long blockSize = 0l;
    private String unit = "mb";
    private String blockUri = "";

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(Long blockSize) {
        this.blockSize = blockSize;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBlockUri() {
        return blockUri;
    }

    public void setBlockUri(String blockUri) {
        this.blockUri = blockUri;
    }
}
