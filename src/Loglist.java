import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Loglist {

    private ArrayList<Log> list;
    private static Loglist loglist;
    private static Object lock = new Object();


    private  Loglist(){
        this.list = new ArrayList<>();
    }

    public static Loglist getInstance(){
        if(loglist==null){
            synchronized (lock){
                if(loglist==null){
                    loglist = new Loglist();
                }
            }
        }
        return loglist;
    }

    public void addlog(String txt){
        Log logLine = new Log(txt);
        list.add(logLine);
        addToFile(logLine);
    }

    public int getLogSize() {
        return list.size();
    }

    private void addToFile(Log log)
    {
        if (log == null)
        {
            return;
        }
        BufferedWriter out = null;
        try
        {
            String filename = "Log-"
                    + log.getDateTime().getSortableDate() + ".txt";
            out = new BufferedWriter(new FileWriter(filename, true));
            out.write(log + "\n");
        }
        catch (Exception e) {e.printStackTrace();}
        finally
        {
            try
            {
                out.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
