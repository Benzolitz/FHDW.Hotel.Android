package fhdw.hotel.BLL.Async.IListener;

import java.util.ArrayList;

import fhdw.hotel.DomainModel.Hotel;

/**
 * @author Lucas Engel
 * Interface for all Hotel related requests.
 */
public interface IAsyncHotelListener {
    String Controller = "Hotel";

    void GetHotelCollectionComplete(ArrayList<Hotel> p_result);
    void GetComplete(Hotel p_result);
}
