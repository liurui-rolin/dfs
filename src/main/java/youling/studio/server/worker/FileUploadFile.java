package youling.studio.server.worker;

import java.io.File;
import java.io.Serializable;

/**
 * @author liurui
 * @date 2019/2/6 下午9:20
 */
public class FileUploadFile implements Serializable {
    private static final long serialVersionUID = 1L;
    private File file;
    private String fileMd5;
    private int startPos;
    private byte[] bytes;
    private int endPos;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getEndPos() {
        return endPos;
    }

    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }
}
