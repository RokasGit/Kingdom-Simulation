import java.util.Random;

public class Miner implements Runnable{
    private Buffer<String> deposit;
    private Random random;

    public Miner(Buffer<String> deposit){
        this.deposit = deposit;
    }

    @Override public void run(){

            while (true){
                int num = (int) (Math.random()*25 + 5);
                for(int i=0;i<num;i++) {
                    deposit.put(Valuables.getInstance((int) (Math.random() * 100) + "").getType(), num);
                }

                System.out.println(Thread.currentThread().getName() + " put: " + num + " amount of valuables." );
                Loglist.getInstance().addlog(Thread.currentThread().getName() + " put: "  + num + " amount of valuables.");
                try {
                    Thread.sleep(5000);
                } catch (Exception e){

                }
            }

    }
}
