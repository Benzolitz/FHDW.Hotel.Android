package fhdw.hotel.BLL.Async.IListener;

import java.util.ArrayList;

import fhdw.hotel.DomainModel.Hotel;
import fhdw.hotel.DomainModel.Room;

/**
 * @author Lucas Engel
 * Interface for all Room related requests.
 */
public interface IAsyncRoomListener {
    String Controller = "Room";

    void GetRoomCollectionComplete(ArrayList<Room> p_result);
    void GetRoom(Room p_result);
}
