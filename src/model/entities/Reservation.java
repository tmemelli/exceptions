package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
	}

//	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException { // Para Exception
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {                        // Para RuntimeException
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

//	public void updateDates(Date checkIn, Date checkOut) {
//		Date now = new Date();
//		if (checkIn.before(now) || checkOut.before(now)) {
//			throw new IllegalAccessError("Reservation dates for update must be future dates");
//		} 
//		if (!checkOut.after(checkIn)) {
//			throw new IllegalAccessError("Check-out date must be after check-in date");
//		}
//		this.checkIn = checkIn;
//		this.checkOut = checkOut;
//	}
	
//	public void updateDates(Date checkIn, Date checkOut) throws DomainException { // Para Exception
	public void updateDates(Date checkIn, Date checkOut) {                        // Para RuntimeException
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates for update must be future dates");
		} 
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public String toString() {
		return "Room "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-Out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nigths";				
	}

}
