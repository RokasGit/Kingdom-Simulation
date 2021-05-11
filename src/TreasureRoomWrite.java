import java.util.ArrayList;

public interface TreasureRoomWrite extends TreasureRoomRead {
    void addValuables(ArrayList<String> valuables);
    ArrayList<String> retrieveValuables(int num);
}
