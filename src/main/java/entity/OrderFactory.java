package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

public class OrderFactory {
	
	public static Order getOrderFromRequest(HttpServletRequest request) {
		Order order = new Order();
		order.setUserId(Integer.parseInt(request.getParameter("user_id")));
		order.setCarId(Integer.parseInt(request.getParameter("car_id")));
		order.setRequestId(Integer.parseInt(request.getParameter("request_id")));
		order.setRentStartDate(LocalDate.parse(request.getParameter("rent_start_date")));
		order.setRentFinishDate(LocalDate.parse(request.getParameter("rent_finish_date")));
		
		return order;	
	}
	
	public static Order getOrderFromResultSet(ResultSet rs) throws SQLException {
		Order order = new Order();;
		order.setOrderId(rs.getInt("order_id"));
		order.setUserId(rs.getInt("user_id"));
		order.setUserName(rs.getString("user_name"));
		order.setCarId(rs.getInt("car_id"));
		order.setCarName(rs.getString("car_name"));
		order.setCarPrice(rs.getInt("price"));
		order.setRequestId(rs.getInt("request_id"));
		order.setRentStartDate(rs.getDate("rent_start_date").toLocalDate());
		order.setRentFinishDate(rs.getDate("rent_finish_date").toLocalDate());
		order.setDamageStatus(rs.getBoolean("damage_status"));
		if(rs.getObject("repair_order_id") != null) {
			order.setRepairOrderId(rs.getInt("repair_order_id"));
			order.setRepairCost(rs.getInt("repair_cost"));	
		}
		order.setOrderStatus(OrderStatusEnum.valueOf(rs.getString("order_status").toUpperCase()));

		return order;
	}

}
