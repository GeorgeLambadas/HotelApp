/**
 * 
 */
package hotelServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.HashMap;

import obj.Hotel;
import obj.Reservation;
import obj.RoomAvailability;
import obj.RoomType;

/**
 * @author George Lambadas 7077076
 * 
 */
public class HotelImpl extends UnicastRemoteObject implements
		HotelCommInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9030195045299944770L;

	private HashMap<Integer, Reservation> reservationDetails;
	private HashMap<Integer, RoomAvailability> roomsAvailable;
	private String hotelName;

	/**
	 * @throws RemoteException
	 */
	protected HotelImpl(String hotelName) throws RemoteException {
		super();
		this.hotelName = hotelName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotelServer.HotelCommInterface#reserveRoom(int, obj.Hotel,
	 * obj.RoomType, java.util.Calendar, java.util.Calendar)
	 */
	@Override
	public boolean reserveRoom(int guestID, String h, RoomType r,
			Calendar checkInDate, Calendar checkOutDate) {
		// TODO make atomic
		boolean success = true;

		if (h == hotelName) {
			boolean thereAreRooms = checkAvailability(guestID, h, r,
					checkOutDate, checkOutDate);
			if (thereAreRooms) {
				for (int i = 0; i < checkOutDate.get(Calendar.DAY_OF_YEAR)
						- checkInDate.get(Calendar.DAY_OF_YEAR)
						&& thereAreRooms; i++) {
					success &= roomsAvailable.get(
							checkInDate.get(Calendar.DAY_OF_YEAR) + i)
							.reserveRoom(r);
				}
			} else {
				success = false;
			}
		}
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotelServer.HotelCommInterface#cancelReservation(int, obj.Hotel,
	 * obj.RoomType, java.util.Calendar, java.util.Calendar)
	 */
	@Override
	public boolean cancelReservation(int guestID, String h, RoomType r,
			Calendar cancelStart, Calendar cancelEnd) {
		boolean success = true;
		Reservation reservation = reservationDetails.get(guestID);
		int cancelStartDay = cancelStart.get(Calendar.DAY_OF_YEAR), cancelEndDay = cancelEnd.get(Calendar.DAY_OF_YEAR);
		int reservationStart = reservation.getStartDate().get(Calendar.DAY_OF_YEAR), 
				reservationEnd = reservation.getEndDate().get(Calendar.DAY_OF_YEAR);

		if ( cancelStartDay <= reservationStart && reservationEnd <= cancelEndDay) {
			reservationDetails.remove(guestID);
			for(int i = 0; i + reservationStart < reservationEnd; i++) {
				roomsAvailable.get(reservationStart + i).removeReservation(r);
			}
		} else if (cancelStartDay <= reservationStart && reservationStart <= cancelEndDay && cancelEndDay <= reservationEnd) {
			cancelEnd.add(Calendar.DATE, 1);
			reservationDetails.get(guestID).setStartDate(cancelEnd);
			for(int i = 0; i + reservationStart < cancelEndDay; i++) {
				roomsAvailable.get(reservationStart + i).removeReservation(r);
			}
		} else if (reservationStart <= cancelStartDay && cancelStartDay <= reservationEnd && reservationEnd <= cancelEndDay) {
			cancelStart.add(Calendar.DATE, -1 );
			reservationDetails.get(guestID).setEndDate(cancelStart);
			for(int i = 0; i + cancelStartDay < reservationEnd; i++) {
				roomsAvailable.get(cancelStartDay + i).removeReservation(r);
			}
		} else if (reservationStart <= cancelStartDay && cancelEndDay <= reservationEnd) {
			//TODO remove reservation
			// new reservation(resStart to cancelStart)
			// new reservation(cancelEnd to resEnd)
		}
		
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotelServer.HotelCommInterface#checkAvailability(int, obj.Hotel,
	 * obj.RoomType, java.util.Calendar, java.util.Calendar)
	 */
	@Override
	public boolean checkAvailability(int guestID, String h, RoomType r,
			Calendar checkInDate, Calendar checkOutDate) {
		boolean thereAreRooms = true;
		return true;
		/*for (int i = 0; i < checkOutDate.get(Calendar.DAY_OF_YEAR)
				- checkInDate.get(Calendar.DAY_OF_YEAR)
				&& thereAreRooms; i++) {
			thereAreRooms = roomsAvailable.get(
					checkInDate.get(Calendar.DAY_OF_YEAR) + i)
					.getRoomsAvailable(r) > 0;
		}

		return thereAreRooms;*/
	}

	public String getName() {
		return this.hotelName;
	}

	@Override
	public String toString() {
		return "kittenz go meow";
	}
}
