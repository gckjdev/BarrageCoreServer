package com.orange.game.api.barrage.service.user.friend;

import com.orange.barrage.model.user.FriendManager;
import com.orange.barrage.model.user.User;
import com.orange.game.api.barrage.common.CommonBarrageService;
import com.orange.protocol.message.MessageProtos;
import com.orange.protocol.message.UserProtos;

import java.util.List;

/**
 * Created by pipi on 14/12/24.
 */
public class GetUserFriendListService extends CommonBarrageService {

    private static GetUserFriendListService ourInstance = new GetUserFriendListService();

    public static GetUserFriendListService getInstance() {
        return ourInstance;
    }

    private GetUserFriendListService() {
    }

    @Override
    public boolean validateRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {
        return true;
    }

    @Override
    public void handleRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {

        String userId = dataRequest.getUserId();

        List<User> friendList = FriendManager.getInstance().getFriendList(userId);
        List<UserProtos.PBUser> pbUserList = User.listToPB(friendList, null);

        MessageProtos.PBGetUserFriendListResponse.Builder builder = MessageProtos.PBGetUserFriendListResponse.newBuilder();
        UserProtos.PBUserFriendList.Builder friendListBuilder = UserProtos.PBUserFriendList.newBuilder();
        if (pbUserList != null){
            friendListBuilder.addAllFriends(pbUserList);
        }
        builder.setFriends(friendListBuilder.build());

        responseBuilder.setResultCode(0);
        responseBuilder.setGetUserFriendListResponse(builder.build());
    }
}
