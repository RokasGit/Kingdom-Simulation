import java.util.ArrayList;
import java.util.Random;

public class ValuableTransporter implements Runnable{
    private Buffer<String> deposit;
    private ArrayList<String> valuables;
    private TreasureRoomDoor guardsman;
    private WriteProxy treasureRoom;

    public ValuableTransporter(Buffer<String> deposit,TreasureRoomDoor guardsman) {
        this.deposit = deposit;
        this.guardsman=guardsman;
    }

    @Override
    public void run() {

            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (Exception e){

                }
                int num = (int) (Math.random() * 150 +50);
                valuables = deposit.take(num);
                // action
                try {
                    Thread.sleep(1000);
                } catch (Exception e){

                }
                treasureRoom = guardsman.acquireWrite();
                treasureRoom.addValuables(valuables);
                if (!valuables.isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + " took: " + valuables.size() + " amount of valuables." );
                    Loglist.getInstance().addlog(Thread.currentThread().getName() + " took: " + valuables.size() + " amount of valuables.");
                }
                guardsman.releaseWrite();
                valuables.clear();
            }


    }
}
