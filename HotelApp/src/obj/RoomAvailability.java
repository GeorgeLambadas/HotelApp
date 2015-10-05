/**
 * 
 */
package obj;

/**
 * @author George Lambadas 7077076
 * 
 */
public class RoomAvailability {
	private int[] roomsAvailable;

	public RoomAvailability(int[] roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}

	/**
	 * @return the roomsAvailable
	 */
	public int getRoomsAvailable(RoomType r) {
		switch (r) {
		case SINGLE:
			return roomsAvailable[0];
		case DOUBLE:
			return roomsAvailable[1];
		case FAMILY:
			return roomsAvailable[2];
		}
		return 0;
	}
	
	public boolean reserveRoom(RoomType r) {
		boolean success = true;
		if (getRoomsAvailable(r) > 0) {
			switch (r) {
			case SINGLE:
				roomsAvailable[0]--;
				break;
			case DOUBLE:
				roomsAvailable[1]--;
				break;
			case FAMILY:
				roomsAvailable[2]--;
				break;
			}
		} else {
			success = false;
		}
		
		return success;
	}
	
	public boolean removeReservation(RoomType r){
		boolean success = true;
		if (getRoomsAvailable(r) > 0) {
			switch (r) {
			case SINGLE:
				roomsAvailable[0]++;
				break;
			case DOUBLE:
				roomsAvailable[1]++;
				break;
			case FAMILY:
				roomsAvailable[2]++;
				break;
			}
		} else {
			success = false;
		}
		
		return success;
	}

}
