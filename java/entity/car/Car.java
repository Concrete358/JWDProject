package entity.car;

import java.util.Objects;
import entity.currency.Byn;

public class Car {
	private int id;
	private String vinNumber;
	private String numberPlate;
	private String carMaker;
	private String carModel;
	private EngineTypeEnum engineType;
	private DriveTypeEnum driveType;
	private int power;
	private Byn pricePerDay;
	private boolean block;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVinNumber() {
		return vinNumber;
	}
	public void setVinNumber(String vinNumber) {
		this.vinNumber = vinNumber;
	}
	public String getNumberPlate() {
		return numberPlate;
	}
	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}
	public String getCarMaker() {
		return carMaker;
	}
	public void setCarMaker(String carMaker) {
		this.carMaker = carMaker;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public EngineTypeEnum getEngineType() {
		return engineType;
	}
	public void setEngineType(EngineTypeEnum engineType) {
		this.engineType = engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = EngineTypeEnum.valueOf(engineType);
	}
	public DriveTypeEnum getDrivenWheels() {
		return driveType;
	}
	public void setDrivenWheels(DriveTypeEnum driveType) {
		this.driveType = driveType;
	}
	
	public void setDrivenWheels(String driveType) {
		this.driveType = DriveTypeEnum.valueOf(driveType);
	}
	
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public Byn getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(Byn pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = new Byn(pricePerDay);
	}
	
	public boolean isBlock() {
		return block;
	}
	public void setBlock(boolean block) {
		this.block = block;
	}
	@Override
	public int hashCode() {
		return Objects.hash(vinNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(vinNumber, other.vinNumber);
	}
	@Override
	public String toString() {
		return "Car [numberPlate=" + numberPlate + ", carMaker=" + carMaker + ", carModel=" + carModel
				+ ", pricePerDay=" + pricePerDay + "]";
	}
	
	
	
}
