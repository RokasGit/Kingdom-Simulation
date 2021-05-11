import java.util.ArrayList;

public class Accountant implements Runnable{
    private TreasureRoomDoor guardsman;
    private int ruby,gold,diamonds;
    private ArrayList<String> valuables;
    private TreasureRoomRead treasureRoom;

    public Accountant(TreasureRoomDoor guardsman){
        this.guardsman = guardsman;
        ruby = 0;
        gold = 0;
        diamonds = 0;
        valuables = new ArrayList<>();
    }

    @Override public void run(){
        while (true){

            treasureRoom = guardsman.acquireRead();
            valuables = treasureRoom.lookAtValuables();
            guardsman.releaseRead();
            try{
                Thread.sleep(4000);
            } catch (Exception e){

            }
            for (int i = 0; i<valuables.size(); i++){
                switch (valuables.get(i)){
                    case "Ruby":
                        ruby += 1;
                        break;
                    case "Gold":
                        gold += 1;
                        break;
                    case "Diamonds":
                        diamonds += 1;
                        break;
                }
            }
            System.out.println(Thread.currentThread().getName()+  " has: Ruby: " + ruby + ", Gold: " + gold + ", Diamonds: "+ diamonds);
            Loglist.getInstance().addlog(Thread.currentThread().getName() + " has: Ruby: " + ruby + ", Gold: " + gold + ", Diamonds: " + diamonds);
            ruby=0;
            gold=0;
            diamonds=0;

            try{
                Thread.sleep(10000);
            } catch (Exception e){

            }
        }
    }}