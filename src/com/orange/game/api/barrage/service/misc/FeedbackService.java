package com.orange.game.api.barrage.service.misc;

import com.orange.barrage.model.feed.FeedManager;
import com.orange.game.api.barrage.common.CommonBarrageService;
import com.orange.protocol.message.BarrageProtos;
import com.orange.protocol.message.MessageProtos;

/**
 * Created by pipi on 15/3/6.
 */
public class FeedbackService extends CommonBarrageService {
    private static FeedbackService ourInstance = new FeedbackService();

    public static FeedbackService getInstance() {
        return ourInstance;
    }

    private FeedbackService() {
    }

    @Override
    public boolean validateRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {
        return true;
    }

    @Override
    public void handleRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {

        MessageProtos.PBReplyFeedRequest req = dataRequest.getReplyFeedRequest();
        BarrageProtos.PBFeedAction action = req.getAction();

        MessageProtos.PBReplyFeedResponse.Builder rspBuilder = MessageProtos.PBReplyFeedResponse.newBuilder();

        int resultCode = FeedManager.getInstance().replyFeed(action, rspBuilder);

        MessageProtos.PBReplyFeedResponse rsp = rspBuilder.build();

        responseBuilder.setResultCode(resultCode);
        responseBuilder.setReplyFeedResponse(rsp);
    }

}
