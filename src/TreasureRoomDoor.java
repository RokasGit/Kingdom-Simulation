import java.util.ArrayList;

public interface TreasureRoomDoor {
    ReadProxy acquireRead();
    void releaseRead();
    WriteProxy acquireWrite();
    void releaseWrite();
}