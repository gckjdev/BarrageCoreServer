package com.orange.game.api.barrage.service.barrage;

import com.googlecode.protobuf.format.JsonFormat;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import com.orange.barrage.model.feed.Feed;
import com.orange.barrage.model.feed.UserTimelineFeedManager;
import com.orange.barrage.service.feed.FeedService;
import com.orange.game.api.barrage.common.CommonBarrageService;
import com.orange.network.game.protocol.model.DrawProtos;
import com.orange.protocol.message.BarrageProtos;
import com.orange.protocol.message.MessageProtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pipi on 14/12/8.
 */
public class GetFeedListService extends CommonBarrageService {

    private static GetFeedListService ourInstance = new GetFeedListService();

    public static GetFeedListService getInstance() {
        return ourInstance;
    }

    private GetFeedListService() {
    }

    @Override
    public boolean validateRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {
        return true;
    }

    @Override
    public void handleRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {

        MessageProtos.PBGetUserTimelineFeedRequest req = dataRequest.getGetUserTimelineFeedRequest();
        String userId = dataRequest.getUserId();
        String offsetFeedId = req.getOffsetFeedId();
        int limit = req.getLimit();
        List<Feed> list = UserTimelineFeedManager.getInstance().getUserTimeline(userId, offsetFeedId, limit, true);

        // convert feed list
        List<BarrageProtos.PBFeed> pbFeedList = new ArrayList<BarrageProtos.PBFeed>();
        for (Feed feed : list){

            BarrageProtos.PBFeed pbFeed = feed.toProtoBufModel();
            pbFeedList.add(pbFeed);

//            BasicDBObject obj = (BasicDBObject)feed.getDbObject();
//            obj.remove("_id");
//            String json = JSON.serialize(obj);
//            log.info("feed json = "+json);
//            BarrageProtos.PBFeed.Builder builder = BarrageProtos.PBFeed.newBuilder();
//            try {
//                JsonFormat.merge(json, builder);
//                BarrageProtos.PBFeed pbFeed = builder.build();
//                pbFeedList.add(pbFeed);
//            } catch (JsonFormat.ParseException e) {
//                log.error("catch exception while convert pb feed, exception="+e.toString(), e);
//            }
        }

        MessageProtos.PBGetUserTimelineFeedResponse.Builder rspBuilder = MessageProtos.PBGetUserTimelineFeedResponse.newBuilder();
        if (list != null){
            rspBuilder.addAllFeeds(pbFeedList);
        }

        MessageProtos.PBGetUserTimelineFeedResponse rsp = rspBuilder.build();

        responseBuilder.setResultCode(0);
        responseBuilder.setGetUserTimelineFeedResponse(rsp);
    }
}
