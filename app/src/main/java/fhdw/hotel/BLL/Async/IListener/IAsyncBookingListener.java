package fhdw.hotel.BLL.Async.IListener;

import fhdw.hotel.DomainModel.Booking;

/**
 * @author Lucas Engel
 * Interface for all Booking related requests.
 */
public interface IAsyncBookingListener {
    String Controller = "Booking";

    void BookingComplete(Booking p_booking);
}
