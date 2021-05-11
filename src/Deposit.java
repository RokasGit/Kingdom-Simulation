import utility.collection.BoundedArrayQueue;
import utility.collection.QueueADT;

import java.util.ArrayList;

public class Deposit<String> implements Buffer<String>{
    private QueueADT<String> queueADT;

    public Deposit(int capacity){
            this.queueADT = new BoundedArrayQueue<>(capacity);
    }

    @Override
    public synchronized void put(String element, int num) {
        if (element==null){
            throw new IllegalArgumentException("illegal");
        }
        while (queueADT.isFull()){
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting");
                Loglist.getInstance().addlog(Thread.currentThread().getName() + " is waiting");
                wait();
            } catch (InterruptedException e){
                //
            }
        }
            queueADT.enqueue(element);
        notifyAll();
    }

    @Override
    public synchronized ArrayList<String> take(int num) {
        while (isEmpty() && queueADT.size()-num<=0){
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting");
                Loglist.getInstance().addlog(Thread.currentThread().getName() + " is waiting");
                wait();
            } catch (Exception e){
                //
            }
        }
        ArrayList<String> valuables = new ArrayList<>();
        if(queueADT.size()>num){
            for (int i = 0; i < num; i++) {
                valuables.add(queueADT.dequeue());
            }
        }
        notifyAll();
        return valuables;
    }

    @Override
    public synchronized String look() {
        if(queueADT.first() == null){
            return null;
        }
        return queueADT.first();
    }

    @Override
    public synchronized boolean isEmpty() {
        return queueADT.isEmpty();
    }

    @Override
    public synchronized boolean isFull() {
        return queueADT.isFull();
    }

    @Override
    public synchronized int size() {
        return queueADT.size();
    }
}
