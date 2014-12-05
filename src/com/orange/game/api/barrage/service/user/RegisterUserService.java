package com.orange.game.api.barrage.service.user;

import com.orange.barrage.service.user.LoginService;
import com.orange.barrage.service.user.RegisterService;
import com.orange.game.api.barrage.common.CommonBarrageService;
import com.orange.protocol.message.ErrorProtos;
import com.orange.protocol.message.MessageProtos;
import com.orange.protocol.message.UserProtos;

/**
 * Created by pipi on 14/12/2.
 */
public class RegisterUserService  extends CommonBarrageService {
    private static RegisterUserService ourInstance = new RegisterUserService();

    public static RegisterUserService getInstance() {
        return ourInstance;
    }

    private RegisterUserService() {
    }

    @Override
    public boolean validateRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {
        return true;
    }

    @Override
    public void handleRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {

        UserProtos.PBUser user = dataRequest.getRegisterUserRequest().getUser();
        MessageProtos.PBRegisterUserRequest req = dataRequest.getRegisterUserRequest();
        MessageProtos.PBRegisterUserResponse.Builder rspBuilder = MessageProtos.PBRegisterUserResponse.newBuilder();

        int type = req.getType();
        int resultCode = ErrorProtos.PBError.ERROR_USER_REGISTER_UNKNOWN_TYPE_VALUE;
        switch (type){
            case MessageProtos.PBRegisterType.REGISTER_EMAIL_VALUE:
                resultCode = RegisterService.getInstance().registerByEmail(user, rspBuilder);
                break;

        }

        responseBuilder.setResultCode(resultCode);
        responseBuilder.setRegisterUserResponse(rspBuilder.build());
    }
}
