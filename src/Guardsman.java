import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Guardsman implements TreasureRoomDoor{
    private int readers;
    private int writers;
    private Queue<Thread> queue;
    private ArrayList<Thread> allowedReadAccess;
    private ArrayList<Thread> allowedWriteAccess;
    private ReadProxy readProxy;
    private WriteProxy writeProxy;

    public Guardsman(TreasureRoom treasureRoom){
        this.readers = 0;
        this.writers = 0;
        this.queue = new ArrayDeque<>();
        allowedReadAccess = new ArrayList<>();
        allowedWriteAccess = new ArrayList<>();
        writeProxy = new WriteProxy(this,treasureRoom);
        readProxy = new ReadProxy(this,treasureRoom);

    }

    @Override public synchronized ReadProxy acquireRead(){
        queue.offer(Thread.currentThread());
        while (queue.peek() != Thread.currentThread()){
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting");
                Loglist.getInstance().addlog(Thread.currentThread().getName() + " is waiting");
                wait();
            } catch (Exception e){
                //
            }
        }
        while (writers > 0){
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting");
                Loglist.getInstance().addlog(Thread.currentThread().getName() + " is waiting");
                wait();
            } catch (Exception e){
                //
            }
        }
        allowedReadAccess.add(Thread.currentThread());
        readers++;
        queue.remove();
        notifyAll();
        return readProxy;
    }

    @Override public synchronized void releaseRead(){
        readers--;
        allowedReadAccess.remove(Thread.currentThread());
        if(readers == 0){
            notifyAll();
        }
    }

    @Override public synchronized WriteProxy acquireWrite(){
        queue.offer(Thread.currentThread());
        while (queue.peek() != Thread.currentThread()){
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting");
                Loglist.getInstance().addlog(Thread.currentThread().getName() + " is waiting");
                wait();
            } catch (Exception e){
                //
            }
        }
        while (readers > 0 || writers > 0){
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting");
                Loglist.getInstance().addlog(Thread.currentThread().getName() + " is waiting");
                wait();
            } catch (Exception e){
                //
            }
        }
        allowedWriteAccess.add(Thread.currentThread());
        writers++;
        queue.remove();
        return writeProxy;
    }

    @Override public synchronized void releaseWrite(){
        writers--;
        allowedWriteAccess.remove(Thread.currentThread());
        notifyAll();
    }
    public boolean hasReadAccess(Thread t){
        return allowedReadAccess.contains(t);
    }
    public boolean hasWriteAccess(Thread t){
        return allowedWriteAccess.contains(t);
    }

}