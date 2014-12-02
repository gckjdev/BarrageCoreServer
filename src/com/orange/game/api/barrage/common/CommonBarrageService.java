package com.orange.game.api.barrage.common;

import com.orange.protocol.message.MessageProtos;

/**
 * Created by pipi on 14/12/2.
 */
public abstract class CommonBarrageService {

    public abstract boolean validateRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder);
    public abstract void handleRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder);
}
