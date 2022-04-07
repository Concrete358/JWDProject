package entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;

public class RequestFactory {
	
	public static Request getRequestFromFactory(HttpServletRequest req){
		String startDateStr = req.getParameter("rent_start_date");
		LocalDate startDate = Date.valueOf(startDateStr).toLocalDate();
		String finishDateStr = req.getParameter("rent_finish_date");
		LocalDate finishDate = Date.valueOf(finishDateStr).toLocalDate();
		int car_id = Integer.parseInt(req.getParameter("car_id"));
		int user_id = Integer.parseInt(req.getParameter("user_id"));
		Request rentRequest = new Request();
		rentRequest.setCarId(car_id);
		rentRequest.setUserId(user_id);
		rentRequest.setRentStartDate(startDate);
		rentRequest.setRentFinishDate(finishDate);
		rentRequest.setReviewStatus(false);		
		return rentRequest;
	}
	
	public static Request getRequestFromFactory(ResultSet rs) throws SQLException{
		Request rentRequest = new Request();
		rentRequest.setRequestId(rs.getInt("req_id"));
		rentRequest.setUserId(rs.getInt("user_id"));
		rentRequest.setUserName(rs.getString("user_name"));
		rentRequest.setCarId(rs.getInt("car_id"));
		rentRequest.setCarName(rs.getString("car_name"));
		rentRequest.setCarPrice(rs.getInt("price"));
		if(rs.getObject("order_id") != null) {
		rentRequest.setOrderId(rs.getInt("order_id"));}
		rentRequest.setRentStartDate(rs.getDate("rent_start_date").toLocalDate());
		rentRequest.setRentFinishDate(rs.getDate("rent_finish_date").toLocalDate());
		rentRequest.setReviewStatus(rs.getBoolean("review_status"));
		rentRequest.setRequestApproveStatus(rs.getBoolean("req_approve_status"));
		rentRequest.setRejectReason(rs.getString("reject_reason"));		
		return rentRequest;
	}
}
