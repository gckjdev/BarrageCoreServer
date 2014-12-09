package com.orange.game.api.barrage.service.barrage;

import com.orange.game.api.barrage.common.CommonBarrageService;
import com.orange.protocol.message.MessageProtos;

/**
 * Created by pipi on 14/12/8.
 */
public class ReplyFeedService extends CommonBarrageService {

    private static ReplyFeedService ourInstance = new ReplyFeedService();

    public static ReplyFeedService getInstance() {
        return ourInstance;
    }

    private ReplyFeedService() {
    }

    @Override
    public boolean validateRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {
        return false;
    }

    @Override
    public void handleRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {

    }
}
