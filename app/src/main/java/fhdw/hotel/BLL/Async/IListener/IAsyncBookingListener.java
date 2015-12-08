package fhdw.hotel.BLL.Async.IListener;

import fhdw.hotel.DomainModel.Booking;

public interface IAsyncBookingListener {
    public String Controller = "Booking";

    void BookingComplete(Booking p_booking);
}
