import java.util.ArrayList;

public class TreasureRoom implements TreasureRoomWrite {
    private ArrayList<String> valuables;

    public TreasureRoom() {
        valuables = new ArrayList<>();
    }

    @Override
    public ArrayList<String> lookAtValuables() {
        return valuables;
    }

    @Override
    public void addValuables(ArrayList<String> valuables) {
        this.valuables.addAll(valuables);
    }

    @Override
    public ArrayList<String> retrieveValuables(int num) {
        ArrayList<String> expenditureForParty = new ArrayList<>();
        if (valuables.size()>num) {
            for (int i = 0; i < num; i++) {
                expenditureForParty.add(valuables.get(i));
            }
            for(int i=0;i<num;i++){
                valuables.remove(0);
            }
            return expenditureForParty;
        } else {
            valuables.addAll(expenditureForParty);
            expenditureForParty.clear();
            return expenditureForParty;
        }
    }
}
