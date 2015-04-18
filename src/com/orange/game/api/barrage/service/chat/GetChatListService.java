package com.orange.game.api.barrage.service.chat;

import com.orange.game.api.barrage.common.CommonBarrageService;
import com.orange.protocol.message.MessageProtos;

/**
 * Created by pipi on 15/4/18.
 */
public class GetChatListService extends CommonBarrageService {

    private static GetChatListService ourInstance = new GetChatListService();

    public static GetChatListService getInstance() {
        return ourInstance;
    }

    private GetChatListService() {
    }

    @Override
    public boolean validateRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {
        return true;
    }

    @Override
    public void handleRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {

    }
}
