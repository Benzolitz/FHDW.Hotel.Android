package fhdw.hotel.BLL.Async.IListener;

import fhdw.hotel.DomainModel.Guest;

public interface IAsyncGuestListener {
    String Controller = "Guest";

    void CheckLoginComplete(Guest p_guest);
    void RegisterNewUserComplete(Guest p_guest);
}
