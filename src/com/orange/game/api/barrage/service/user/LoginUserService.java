package com.orange.game.api.barrage.service.user;

import com.orange.barrage.service.user.LoginService;
import com.orange.game.api.barrage.common.CommonBarrageService;
import com.orange.protocol.message.ErrorProtos;
import com.orange.protocol.message.MessageProtos;

/**
 * Created by pipi on 14/12/2.
 */
public class LoginUserService extends CommonBarrageService {
    private static LoginUserService ourInstance = new LoginUserService();

    public static LoginUserService getInstance() {
        return ourInstance;
    }

    private LoginUserService() {
    }

    @Override
    public boolean validateRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder){
        return true;
    }

    @Override
    public void handleRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {
        MessageProtos.PBLoginUserRequest req = dataRequest.getLoginUserRequest();
        MessageProtos.PBLoginUserResponse.Builder rspBuilder = MessageProtos.PBLoginUserResponse.newBuilder();

        int type = req.getType();
        int resultCode = ErrorProtos.PBError.ERROR_USER_LOGIN_UNKNOWN_TYPE_VALUE;
        switch (type){
            case MessageProtos.PBLoginType.LOGIN_XIAOJI_VALUE:
                resultCode = LoginService.getInstance().loginByXiaoji(req.getXiaoji(), req.getPassword(), rspBuilder);
                break;

            case MessageProtos.PBLoginType.LOGIN_EMAIL_VALUE:
                resultCode = LoginService.getInstance().loginByEmail(req.getEmail(), req.getPassword(), rspBuilder);
                break;
        }

        responseBuilder.setResultCode(resultCode);
        responseBuilder.setLoginUserResponse(rspBuilder.build());
    }


}
