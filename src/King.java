import java.util.ArrayList;

public class King implements Runnable {
    private TreasureRoomDoor guardsman;
    private TreasureRoomWrite treasureRoom;
    private ArrayList<String> valuables;
    public King(TreasureRoomDoor guardsman) {
        this.guardsman = guardsman;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(25000);
            } catch (Exception e) {

            }
            treasureRoom = guardsman.acquireWrite();
            int num = (int) (Math.random() * 150 +50);
            valuables = new ArrayList<>();
            valuables.addAll(treasureRoom.retrieveValuables(num));
            if(valuables.size()==num){
                ArrayList<String> expenditureForParty = new ArrayList<>();
                for (int i = 0; i < valuables.size(); i++) {
                    expenditureForParty.add(valuables.get(i));
                }
                System.out.println(Thread.currentThread().getName() + " used " + expenditureForParty.size() + " valuables from treasury for the party.");
                Loglist.getInstance().addlog(Thread.currentThread().getName() + " used " + expenditureForParty.size() + " valuables from treasury for the party.");

            } else{
                System.out.println(Thread.currentThread().getName() + " is sad, because there's not enough funds for the party.");
                Loglist.getInstance().addlog(Thread.currentThread().getName() + " is sad, because there's not enough funds for the party.");

            }
            guardsman.releaseWrite();
            valuables.clear();
        }


    }
}
