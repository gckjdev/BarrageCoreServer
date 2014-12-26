package com.orange.game.api.barrage.service.user;

import com.orange.game.api.barrage.common.CommonBarrageService;
import com.orange.protocol.message.MessageProtos;

/**
 * Created by pipi on 14/12/24.
 */
public class UpdateUserInfoService extends CommonBarrageService {

    private static UpdateUserInfoService ourInstance = new UpdateUserInfoService();

    public static UpdateUserInfoService getInstance() {
        return ourInstance;
    }

    private UpdateUserInfoService() {
    }

    @Override
    public boolean validateRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {
        return true;
    }

    @Override
    public void handleRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {

    }
}
