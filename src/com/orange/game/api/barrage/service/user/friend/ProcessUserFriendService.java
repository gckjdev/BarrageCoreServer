package com.orange.game.api.barrage.service.user.friend;

import com.orange.game.api.barrage.common.CommonBarrageService;
import com.orange.protocol.message.MessageProtos;

/**
 * Created by pipi on 14/12/24.
 */
public class ProcessUserFriendService extends CommonBarrageService {

    private static ProcessUserFriendService ourInstance = new ProcessUserFriendService();

    public static ProcessUserFriendService getInstance() {
        return ourInstance;
    }

    private ProcessUserFriendService() {
    }

    @Override
    public boolean validateRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {
        return false;
    }

    @Override
    public void handleRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {

    }
}
