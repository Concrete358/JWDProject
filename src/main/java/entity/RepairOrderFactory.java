package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairOrderFactory {
	public static RepairOrder getRepairOrderFromResultSet(ResultSet rs) throws SQLException {
		RepairOrder repairOrder = new RepairOrder();
		repairOrder.setRepairOrderId(rs.getInt("rep_ord_id"));
		repairOrder.setOrderId(rs.getInt("order_id"));
		repairOrder.setRepairCost(rs.getInt("repair_cost"));
		repairOrder.setDamageDescription(rs.getString("damage_description"));
		return repairOrder;
	}

}
