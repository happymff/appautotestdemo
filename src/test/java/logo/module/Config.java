package logo.module;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by mengfeifei on 2017/5/24.
 */
public class Config {
    static final String CONFIG_FILE= "src/android_config.properties";
    //当CONFIG_FILE="ios_config.properties"将读取ios配置文件的信息
    private Map<String, String> configMap = new HashMap<String, String>();
    private static Config testResManager;
    public static Config getInstance(){
        if(testResManager==null){
            testResManager= new Config();
        }
        return testResManager;
    }
    public Config(){
        configMap = loadFile(Config.CONFIG_FILE);
    }
    protected Map<String, String> loadFile(String filename){
        Map<String, String> map =null;
        if ((filename!=null)&&(!filename.trim().equals(""))){
            InputStream is =Config.class.getClassLoader().getResourceAsStream(filename);
            if(is !=null){
                map = new HashMap<String, String>();
                Properties prop = new Properties();
                try{
                    prop.load(is);
                    for (Map.Entry<Object,Object> enty:prop.entrySet()){
                        String key =(String) enty.getKey();
                        String value =(String) enty.getValue();
                        map.put(key,value);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
    public  String getCfg(String key){
        return configMap.get(key);
    }

}
