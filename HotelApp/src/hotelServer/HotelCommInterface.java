/**
 * 
 */
package hotelServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;

import obj.Hotel;
import obj.RoomType;

/**
 * @author George Lambadas 7077076
 *
 */
public interface HotelCommInterface extends Remote {
	
	/**
	 * Method to be remotely invoked by client to reserve a room of a specified type between specified dates.
	 * @param guestID id of guest
	 * @param h hotel in which to reserve
	 * @param r room type to reserve
	 * @param checkInDate start date of reservation
	 * @param checkOutDate end date of reservation
	 * @return boolean representing whether or not the reservation went through
	 */
	public boolean reserveRoom(int guestID, String h, RoomType r, Calendar checkInDate, Calendar checkOutDate) throws RemoteException;
	
	/**
	 * Method to be remotely invoked by client to cancel an existing room reservation between specified dates.
	 * @param guestID id of the guest
	 * @param h hotel from which to cancel the reservation
	 * @param r room type being cancelled
	 * @param checkInDate beginning of cancellation
	 * @param checkOutDate end of cancellation
	 * @return boolean representing whether or not the cancellation was successful
	 */
	public boolean cancelReservation(int guestID, String h, RoomType r, Calendar checkInDate, Calendar checkOutDate) throws RemoteException;
	
	/**
	 * method to check availability of rooms
	 * @param guestID id of guest
	 * @param h preferred hotel of the guest
	 * @param r room type the guest desires
	 * @param checkInDate start date of availability check
	 * @param checkOutDate end date of availability check
	 * @return
	 */
	public boolean checkAvailability(int guestID, String h, RoomType r, Calendar checkInDate, Calendar checkOutDate) throws RemoteException;


}
