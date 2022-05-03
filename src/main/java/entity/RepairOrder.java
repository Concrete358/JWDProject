package entity;

import java.util.Objects;

import entity.currency.Byn;

public class RepairOrder {
	private int repairOrderId;
	private int orderId;
	private Byn repairCost;
	private String damageDescription;
	
	public int getRepairOrderId() {
		return repairOrderId;
	}
	public void setRepairOrderId(int repairOrderId) {
		this.repairOrderId = repairOrderId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	public String getDamageDescription() {
		return damageDescription;
	}
	public void setDamageDescription(String damageDescription) {
		this.damageDescription = damageDescription;
	}
	@Override
	public int hashCode() {
		return Objects.hash(repairOrderId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RepairOrder other = (RepairOrder) obj;
		return repairOrderId == other.repairOrderId;
	}
	@Override
	public String toString() {
		return "RepairOrder [repairOrderId=" + repairOrderId + ", orderId=" + orderId + ", repairCost=" + repairCost
				+ ", damageDescription=" + damageDescription + "]";
	}
	
	

}
