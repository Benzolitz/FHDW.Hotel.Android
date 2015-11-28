package fhdw.hotel.BLL.Async.IListener;

import java.util.ArrayList;

import fhdw.hotel.DomainModel.Hotel;

public interface IAsyncHotelListener {
    String Controller = "Hotel";

    void GetCollectionComplete(ArrayList<Hotel> p_result);
    void GetComplete(Hotel p_result);
}
