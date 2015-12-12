package fhdw.hotel.BLL.Async.IListener;

import fhdw.hotel.DomainModel.Guest;

/**
 * @author Lucas Engel
 * Interface for all Guest related requests.
 */
public interface IAsyncGuestListener {
    String Controller = "Guest";

    void CheckLoginComplete(Guest p_guest);
    void RegisterNewUserComplete(Guest p_guest);
}
