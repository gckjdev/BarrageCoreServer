package com.orange.game.api.barrage.service.user;

import com.orange.game.api.barrage.common.CommonBarrageService;
import com.orange.protocol.message.MessageProtos;

/**
 * Created by pipi on 14/12/25.
 */
public class VerifyInviteCodeService extends CommonBarrageService {

    private static VerifyInviteCodeService ourInstance = new VerifyInviteCodeService();

    public static VerifyInviteCodeService getInstance() {
        return ourInstance;
    }

    private VerifyInviteCodeService() {
    }

    @Override
    public boolean validateRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {
        return false;
    }

    @Override
    public void handleRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {

    }
}
