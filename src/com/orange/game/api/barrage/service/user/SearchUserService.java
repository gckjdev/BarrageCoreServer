package com.orange.game.api.barrage.service.user;

import com.orange.game.api.barrage.common.CommonBarrageService;
import com.orange.protocol.message.MessageProtos;

/**
 * Created by pipi on 14/12/24.
 */
public class SearchUserService extends CommonBarrageService {

    private static SearchUserService ourInstance = new SearchUserService();

    public static SearchUserService getInstance() {
        return ourInstance;
    }

    private SearchUserService() {
    }

    @Override
    public boolean validateRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {
        return false;
    }

    @Override
    public void handleRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {

    }
}
