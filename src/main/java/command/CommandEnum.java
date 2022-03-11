package command;

import command.impl.LoginCommand;
import command.impl.LogoutCommand;
import command.impl.RegisterCommand;
import command.impl.admin.RequestApproveCommand;
import command.impl.admin.RequestRejectCommand;
import command.impl.admin.SetOrderActiveCommand;
import command.impl.RequestGenerateCommand;
import command.impl.admin.GoToAdminOrderListPageCommand;
import command.impl.admin.GoToAdminOrderPageCommand;
import command.impl.admin.GoToAdminRepairOrderListPageCommand;
import command.impl.admin.GoToAdminRequestListPageCommand;
import command.impl.admin.ChangeCarBlockCommand;
import command.impl.admin.ChangeCarParameterCommand;
import command.impl.admin.CloseOrderCommand;
import command.impl.admin.CreateCarCommand;
import command.impl.admin.GoToAdminCarAddPageCommand;
import command.impl.admin.GoToAdminCarEditPageCommand;
import command.impl.admin.GoToAdminCarInfoPageCommand;
import command.impl.admin.GoToAdminCarListPageCommand;
import command.impl.admin.GoToRepairOrderCreatePageCommand;
import command.impl.admin.GoToRepairOrderInfoPageCommand;
import command.impl.admin.RepairOrderCreateCommand;
import command.impl.admin.RepairOrderEditCommand;
import command.impl.goToCommands.GoToCarPageCommand;
import command.impl.goToCommands.GoToLoginPageCommand;
import command.impl.goToCommands.GoToMainPageCommand;
import command.impl.goToCommands.GoToRegisterPageCommand;
import command.impl.goToCommands.GoToUserInfoPageCommand;
import command.impl.user.CancelRequestCommand;
import command.impl.user.GoToRequestEditPageCommand;
import command.impl.user.GoToUserOrderListPageCommand;
import command.impl.user.GoToUserPageCommand;
import command.impl.user.GoToUserRequestListPageCommand;
import command.impl.user.RequestEditCommand;
import command.impl.user.UserChangeEmailCommand;
import command.impl.user.UserChangePasswordCommand;

public enum CommandEnum {
	//Go to common commands
	GO_TO_MAIN_PAGE(new GoToMainPageCommand()),
	GO_TO_LOGIN_PAGE(new GoToLoginPageCommand()),
	GO_TO_REGISTER_PAGE(new GoToRegisterPageCommand()),
	GO_TO_CAR_PAGE(new GoToCarPageCommand()),
	
	//Admin commands
	CREATE_CAR(new CreateCarCommand()),
	CHANGE_CAR_BLOCK(new ChangeCarBlockCommand()),
	SET_ORDER_ACTIVE(new SetOrderActiveCommand()),
	GO_TO_ADMIN_ORDER_PAGE(new GoToAdminOrderPageCommand()),
	GO_TO_ADMIN_REPAIR_ORDER_LIST_PAGE(new GoToAdminRepairOrderListPageCommand()),
	GO_TO_ADMIN_CAR_EDIT_PAGE(new GoToAdminCarEditPageCommand()),
	GO_TO_ADMIN_CAR_INFO_PAGE(new GoToAdminCarInfoPageCommand()),
	GO_TO_ADMIN_CAR_ADD_PAGE(new GoToAdminCarAddPageCommand()),
	GO_TO_USER_INFO_PAGE(new GoToUserInfoPageCommand()),
	GO_TO_ADMIN_REQUEST_LIST_PAGE(new GoToAdminRequestListPageCommand()),
	GO_TO_ADMIN_ORDER_LIST_PAGE(new GoToAdminOrderListPageCommand()),
	GO_TO_REPAIR_ORDER_INFO_PAGE(new GoToRepairOrderInfoPageCommand()),
	GO_TO_REPAIR_ORDER_CREATE_PAGE(new GoToRepairOrderCreatePageCommand()),
	GO_TO_ADMIN_CAR_LIST_PAGE(new GoToAdminCarListPageCommand()),
	CHANGE_CAR_PARAMETER(new ChangeCarParameterCommand()),
	REQUEST_APPROVE(new RequestApproveCommand()),
	REQUEST_REJECT(new RequestRejectCommand()),
	REPAIR_ORDER_EDIT(new RepairOrderEditCommand()),
	REPAIR_ORDER_CREATE(new RepairOrderCreateCommand()),
	CLOSE_ORDER(new CloseOrderCommand()),
	
	//Special common commands
	LOGIN(new LoginCommand()),
	REGISTER(new RegisterCommand()),
	LOGOUT(new LogoutCommand()),
	
	// User commands
	GO_TO_USER_PAGE(new GoToUserPageCommand()),
	GO_TO_REQUEST_EDIT_PAGE(new GoToRequestEditPageCommand()),
	GO_TO_USER_ORDER_LIST_PAGE(new GoToUserOrderListPageCommand()),
	GO_TO_USER_REQUEST_LIST_PAGE(new GoToUserRequestListPageCommand()),
	CHANGE_EMAIL(new UserChangeEmailCommand()),
	CHANGE_PASSWORD(new UserChangePasswordCommand()),
	REQUEST_GENERATE(new RequestGenerateCommand()),
	REQUEST_EDIT(new RequestEditCommand()),
	CANCEL_REQUEST(new CancelRequestCommand());
	
	private ICommand command;
	
	private CommandEnum(ICommand command) {
		this.command = command;
	}

	public ICommand getCommand() {
		return command;
	}

}
