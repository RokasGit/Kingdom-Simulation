import java.util.HashMap;
import java.util.Map;

public class Valuables {
    private static Map<String,Valuables> allInstances = new HashMap<>();
    private String type;
    private Valuables(String type){
        this.type=type;
    }
    public static Valuables getInstance(String key){
        Valuables instance = allInstances.get(key);
        if(instance==null){
            synchronized (allInstances){
                instance = allInstances.get(key);
                if(instance==null){
                    String[] types = {"Gold","Ruby","Diamonds"};
                    int valuableNo = (int) (Math.random()*3);
                    instance=new Valuables(types[valuableNo]);
                    allInstances.put(key,instance);
                }
            }
        }
        return instance;
    }
    public String getType(){
        return type;
    }
}
