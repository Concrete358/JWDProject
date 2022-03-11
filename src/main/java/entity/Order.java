package entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import entity.currency.Byn;

public class Order {
	private int orderId;
	private int userId;
	private String userName; 
	private int carId;
	private String carName;
	private Byn carPrice;
	private int requestId;
	private LocalDate rentStartDate;
	private LocalDate rentFinishDate;
	private boolean damageStatus;
	private int repairOrderId;
	private Byn repairCost;
	private OrderStatusEnum orderStatus;

	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public OrderStatusEnum getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatusEnum orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
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
	public Byn getRentCost() {
		int period = (int) ChronoUnit.DAYS.between(rentStartDate, rentFinishDate);
		return carPrice.multiply(period);
	}

	public boolean isDamageStatus() {
		return damageStatus;
	}
	public void setDamageStatus(boolean damageStatus) {
		this.damageStatus = damageStatus;
	}
	public int getRepairOrderId() {
		return repairOrderId;
	}
	public void setRepairOrderId(int repairOrderId) {
		this.repairOrderId = repairOrderId;
	}
	public Byn getRepairCost() {
		return repairCost;
	}
	public void setRepairCost(Byn repairCost) {
		this.repairCost = repairCost;
	}
	public void setRepairCost(int repairCost) {
		this.repairCost = new Byn(repairCost);
	}
	public Byn getTotalCost() {
		if(repairCost != null) {
		return getRentCost().add(repairCost);
		} else {
		return getRentCost();	
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(orderId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return orderId == other.orderId;
	}

}
