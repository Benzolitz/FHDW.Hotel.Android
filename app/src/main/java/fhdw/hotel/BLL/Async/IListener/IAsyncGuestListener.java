package fhdw.hotel.BLL.Async.IListener;

import fhdw.hotel.DomainModel.Guest;

public interface IAsyncGuestListener {
    String Controller = "Guest";

    void InsertGuestTest(Guest p_guest);
}
