/**
 * 
 */
package hotelServer;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.HashMap;

import obj.Reservation;
import obj.RoomAvailability;
import obj.RoomType;

/**
 * @author George Lambadas 7077076
 * 
 */
public class HotelImpl extends UnicastRemoteObject implements
		HotelGuestInterface, HotelManagerInterface {

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
		System.out.println(hotelName);
		this.hotelName = hotelName;

		reservationDetails = new HashMap<Integer, Reservation>();

		roomsAvailable = new HashMap<Integer, RoomAvailability>();
		for (int i = 0; i < 365; i++) {
			roomsAvailable.put(i, new RoomAvailability(new int[] { 5, 5, 5 }));
		}
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
		if (h.equals(hotelName)) {
			boolean thereAreRooms = checkIfRoomAvailable(guestID, h, r,
					checkInDate, checkOutDate);
			if (thereAreRooms) {
				reservationDetails.put(guestID, new Reservation(guestID,
						checkInDate, checkOutDate, r));
				for (int i = 0; i < checkOutDate.get(Calendar.DAY_OF_YEAR)
						- checkInDate.get(Calendar.DAY_OF_YEAR)
						&& thereAreRooms; i++) {
					success &= roomsAvailable.get(
							checkInDate.get(Calendar.DAY_OF_YEAR) + i)
							.reserveRoom(r);
					System.out.println("reserved: "
							+ (checkInDate.get(Calendar.DAY_OF_YEAR) + i));
				}
			} else {
				success = false;
			}
		} else {
			// TODO get info from other hotel.
		}

		return success;
	}

	/**
	 * @param guestID
	 * @param h
	 * @param r
	 * @param checkInDate
	 * @param checkOutDate
	 * @return
	 */
	private boolean checkIfRoomAvailable(int guestID, String h, RoomType r,
			Calendar checkInDate, Calendar checkOutDate) {
		boolean thereAreRoomsAvailable = true;
		for (int i = 0; i < checkOutDate.get(Calendar.DAY_OF_YEAR)
				- checkInDate.get(Calendar.DAY_OF_YEAR)
				&& thereAreRoomsAvailable; i++) {
			thereAreRoomsAvailable = roomsAvailable.get(
					checkInDate.get(Calendar.DAY_OF_YEAR) + i)
					.getRoomsAvailable(r) > 0;
		}

		return thereAreRoomsAvailable;
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

		int cancelStartDay = cancelStart.get(Calendar.DAY_OF_YEAR), cancelEndDay = cancelEnd
				.get(Calendar.DAY_OF_YEAR);
		int reservationStart = reservation.getStartDate().get(
				Calendar.DAY_OF_YEAR), reservationEnd = reservation
				.getEndDate().get(Calendar.DAY_OF_YEAR);
		if (cancelStartDay <= reservationStart
				&& reservationEnd <= cancelEndDay) {
			reservationDetails.remove(guestID);
			for (int i = 0; i + reservationStart < reservationEnd; i++) {
				roomsAvailable.get(reservationStart + i).removeReservation(r);
			}
		} else if (cancelStartDay <= reservationStart
				&& reservationStart <= cancelEndDay
				&& cancelEndDay <= reservationEnd) {
			cancelEnd.add(Calendar.DATE, 1);
			reservationDetails.get(guestID).setStartDate(cancelEnd);
			for (int i = 0; i + reservationStart < cancelEndDay + 1; i++) {
				roomsAvailable.get(reservationStart + i).removeReservation(r);
			}
		} else if (reservationStart <= cancelStartDay
				&& cancelStartDay <= reservationEnd
				&& reservationEnd <= cancelEndDay) {
			cancelStart.add(Calendar.DATE, -1);
			reservationDetails.get(guestID).setEndDate(cancelStart);
			for (int i = 0; i + cancelStartDay < reservationEnd; i++) {
				roomsAvailable.get(cancelStartDay + i).removeReservation(r);
			}
		} else {
			success = false;
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
	public String checkAvailability(int guestID, String h, RoomType r,
			Calendar checkInDate, Calendar checkOutDate) {
		// TODO remake this entire thing to include multiple hotels
		int numberOfRooms = Integer.MAX_VALUE;
		for (int i = 0; i < checkOutDate.get(Calendar.DAY_OF_YEAR)
				- checkInDate.get(Calendar.DAY_OF_YEAR)
				&& numberOfRooms > 0; i++) {
			numberOfRooms = Math.min(
					roomsAvailable.get(
							checkInDate.get(Calendar.DAY_OF_YEAR) + i)
							.getRoomsAvailable(r), numberOfRooms);
		}

		return "Preferred hotel: " + numberOfRooms;
	}

	public String getName() {
		return this.hotelName;
	}

	@Override
	public String toString() {
		return "kittenz go meow";
	}
}
