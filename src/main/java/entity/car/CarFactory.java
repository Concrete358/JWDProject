package entity.car;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class CarFactory {
	

	public static Car getCarFromResultSet(ResultSet rs) throws SQLException {
		Car car = new Car();
		car.setId(rs.getInt("car_id"));
		car.setVinNumber(rs.getString("vin"));
		car.setNumberPlate(rs.getString("number_plate"));
		car.setCarMaker(rs.getString("car_maker"));
		car.setCarModel(rs.getString("car_model"));
		car.setEngineType(rs.getString("engine_type"));
		car.setDrivenWheels(rs.getString("drive_type"));
		car.setPower(rs.getInt("power"));
		car.setPricePerDay(rs.getInt("price"));
		car.setBlock(rs.getBoolean("block"));
		return car;
	}
	
	public static Car getCarFromRequest(HttpServletRequest request) {
		Car car = new Car();
		car.setVinNumber(request.getParameter("vin"));
		car.setNumberPlate(request.getParameter("number_plate"));
		car.setCarMaker(request.getParameter("car_maker"));
		car.setCarModel(request.getParameter("car_model"));
		car.setEngineType(EngineTypeEnum.valueOf(request.getParameter("engine_type")));
		car.setDrivenWheels(DriveTypeEnum.valueOf(request.getParameter("drive_type")));
		car.setPower(Integer.parseInt(request.getParameter("power")));
		int price = (int)Math.round(Double.parseDouble(request.getParameter("price"))*100);
		car.setPricePerDay(price);
		return car;
	}
}
