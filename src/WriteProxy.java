import java.util.ArrayList;

public class WriteProxy implements TreasureRoomWrite {
    private TreasureRoomWrite treasureRoom;
    private Guardsman guardsman;

    public WriteProxy(Guardsman guardsman, TreasureRoom treasureRoom) {
        this.treasureRoom = treasureRoom;
        this.guardsman = guardsman;
    }

    @Override
    public synchronized ArrayList<String> lookAtValuables() {
        if (!guardsman.hasWriteAccess(Thread.currentThread())) {
            throw new IllegalStateException("The current thread has no access");
        }
       return treasureRoom.lookAtValuables();
    }

    @Override
    public synchronized void addValuables(ArrayList<String> valuables) {
        if (!guardsman.hasWriteAccess(Thread.currentThread())) {
            throw new IllegalStateException("The current thread has no access");
        }
        treasureRoom.addValuables(valuables);
    }

    @Override
    public synchronized ArrayList<String> retrieveValuables(int num) {
        if (!guardsman.hasWriteAccess(Thread.currentThread())) {
            throw new IllegalStateException("The current thread has no access");
        }
        return treasureRoom.retrieveValuables(num);
    }
}
