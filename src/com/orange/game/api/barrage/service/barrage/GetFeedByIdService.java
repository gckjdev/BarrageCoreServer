package com.orange.game.api.barrage.service.barrage;

import com.orange.game.api.barrage.common.CommonBarrageService;
import com.orange.protocol.message.MessageProtos;

/**
 * Created by pipi on 15/3/24.
 */
public class GetFeedByIdService  extends CommonBarrageService {
    private static GetFeedByIdService ourInstance = new GetFeedByIdService();

    public static GetFeedByIdService getInstance() {
        return ourInstance;
    }

    private GetFeedByIdService() {
    }

    @Override
    public boolean validateRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {
        return true;
    }

    @Override
    public void handleRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {

    }
}
