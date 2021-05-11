import java.util.ArrayList;

public class ReadProxy implements TreasureRoomRead {
    private Guardsman guardsman;
    private TreasureRoomRead treasureRoom;

    public ReadProxy(Guardsman guardsman, TreasureRoom treasureRoom) {
        this.guardsman = guardsman;
        this.treasureRoom = treasureRoom;
    }
    public synchronized ArrayList<String> lookAtValuables(){
        if(!guardsman.hasReadAccess(Thread.currentThread())){
            throw new IllegalStateException("The current thread has no access");
        }
        return treasureRoom.lookAtValuables();
    }
}
