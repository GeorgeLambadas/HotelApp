/**
 * 
 */
package obj;

import java.util.Calendar;

/**
 * @author George Lambadas 7077076
 *
 */
public class Reservation {
	

	private int guestId;
	private Calendar startDate;
	private Calendar endDate;
	private RoomType r;
	
	public Reservation(int guestId, Calendar startDate, Calendar endDate, RoomType r) {
		this.guestId = guestId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.r = r;
	}
	/**
	 * @return the startDate
	 */
	public Calendar getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Calendar getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the r
	 */
	public RoomType getR() {
		return r;
	}
	/**
	 * @param r the r to set
	 */
	public void setR(RoomType r) {
		this.r = r;
	}
	/**
	 * @return the guestId
	 */
	public int getGuestId() {
		return guestId;
	}
	/**
	 * @param guestId the guestId to set
	 */
	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}

}
