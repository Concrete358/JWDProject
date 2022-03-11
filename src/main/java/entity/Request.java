package entity;

import java.time.LocalDate;
import java.util.Objects;
import java.time.temporal.ChronoUnit;

import entity.currency.Byn;

public class Request {
	private int requestId;
	private int userId;
	private String userName;
	private int carId;
	private String carName;
	private Byn carPrice;
	private int orderId;
	private LocalDate rentStartDate;
	private LocalDate rentFinishDate;
	private boolean reviewStatus;
	private boolean requestApproveStatus;
	private String rejectReason;
	 	
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public Byn getCarPrice() {
		return carPrice;
	}
	public void setCarPrice(Byn carPrice) {
		this.carPrice = carPrice;
	}
	public void setCarPrice(int carPrice) {
		this.carPrice = new Byn(carPrice);
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public LocalDate getRentStartDate() {
		return rentStartDate;
	}
	public void setRentStartDate(LocalDate rentStartDate) {
		this.rentStartDate = rentStartDate;
	}
	public LocalDate getRentFinishDate() {
		return rentFinishDate;
	}
	public void setRentFinishDate(LocalDate rentFinishDate) {
		this.rentFinishDate = rentFinishDate;
	}
	public boolean isReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(boolean reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	public boolean isRequestApproveStatus() {
		return requestApproveStatus;
	}
	public void setRequestApproveStatus(boolean requestApproveStatus) {
		this.requestApproveStatus = requestApproveStatus;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public Byn getRentCost() {
		int period = (int) ChronoUnit.DAYS.between(rentStartDate, rentFinishDate);
		return carPrice.multiply(period);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(requestId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return requestId == other.requestId;
	}
	
	
}
