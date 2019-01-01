package youling.studio.server;


import java.io.File;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.google.common.base.Strings;
import youling.studio.utils.LogUtils;

/**
 * @author liurui
 * @date 2018/12/31 下午4:24
 *
 * 配置文件单例对象
 *
 */
public class Configuration {
    private final static Logger logger = LoggerFactory.getLogger(Configuration.class);
    private Properties masterProperties;
    private Properties workerProperties;

    private static volatile Configuration conf = null;
    private Configuration(){
        //读取配置文件
        loadMasterConfFile("classes/master-site.xml"); //线上
        loadMasterConfFile("src/main/resources/master-site.xml"); //开发

        loadWorkerConfFile("classes/worker-site.xml"); //线上
        loadWorkerConfFile("src/main/resources/worker-site.xml"); //开发
    }

    /**
     * 获取配置文件
     * @return
     */
    public static Configuration getConfiguration(){
        if(conf==null){
            synchronized (Configuration.class){
                if(conf==null){
                    conf = new Configuration();
                }
            }
        }
        return conf;
    }

    /**
     * 加载master配置文件
     * @param path
     */
    private void loadMasterConfFile(String path){
        try {
            Properties props = getMasterProps();
            File file = new File(path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList nl = doc.getElementsByTagName("property");
            for (int i = 0; i < nl.getLength(); i++) {
                String name = doc.getElementsByTagName("name").item(i).getFirstChild().getNodeValue();
                String value = doc.getElementsByTagName("value").item(i).getFirstChild().getNodeValue();
                if(!Strings.isNullOrEmpty(name) && !Strings.isNullOrEmpty(value)){
                    props.put(name, value);
                }
            }
            logger.debug("Properties : "+this.masterProperties.toString());
        } catch (Exception e) {
            logger.debug("Properties : "+this.masterProperties.toString());
            //logger.warn("读取默认配置文件失败, file : " + path + ",info: " + LogUtils.getExceptionOut(e));
        }
    }

    /**
     * 加载worker配置文件
     * @param path
     */
    private void loadWorkerConfFile(String path){
        try {
            Properties props = getWorkerProps();
            File file = new File(path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList nl = doc.getElementsByTagName("property");
            for (int i = 0; i < nl.getLength(); i++) {
                String name = doc.getElementsByTagName("name").item(i).getFirstChild().getNodeValue();
                String value = doc.getElementsByTagName("value").item(i).getFirstChild().getNodeValue();
                if(!Strings.isNullOrEmpty(name) && !Strings.isNullOrEmpty(value)){
                    props.put(name, value);
                }
            }
            logger.debug("Properties : "+this.workerProperties.toString());
        } catch (Exception e) {
            logger.debug("Properties : "+this.workerProperties.toString());
            //logger.warn("读取默认配置文件失败, file : " + path + ",info: " + LogUtils.getExceptionOut(e));
        }
    }

    private Properties getMasterProps(){
        if(masterProperties == null){
            synchronized (Configuration.class){
                masterProperties = new Properties();
            }
        }
        return masterProperties;
    }
    private Properties getWorkerProps(){
        if(workerProperties == null){
            synchronized (Configuration.class){
                workerProperties = new Properties();
            }
        }
        return workerProperties;
    }

    //master配置提取
    public String masterGet(String key){
        return getMasterProps().getProperty(key);
    }
    public Integer masterGetInt(String key){
        return Integer.parseInt(getMasterProps().getProperty(key));
    }
    public void masterSet(String key,String value){
        getMasterProps().setProperty(key, value);
    }

    //worker配置提取
    public String workerGet(String key){
        return getWorkerProps().getProperty(key);
    }
    public Integer workerGetInt(String key){
        return Integer.parseInt(getWorkerProps().getProperty(key));
    }
    public Long workerGetLong(String key){
        return Long.parseLong(getWorkerProps().getProperty(key));
    }
    public void workerSet(String key,String value){
        getWorkerProps().setProperty(key, value);
    }


    @Override
    public String toString() {
        return "Configuration [ master properties=" + getMasterProps() + "] \n [ worker properties=" + getWorkerProps() + "]";
    }

}


