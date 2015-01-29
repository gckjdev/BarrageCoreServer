package com.orange.game.api.barrage.service.user.friend;

import com.orange.barrage.model.user.FriendManager;
import com.orange.barrage.model.user.FriendRequestManager;
import com.orange.barrage.model.user.User;
import com.orange.game.api.barrage.common.CommonBarrageService;
import com.orange.protocol.message.MessageProtos;
import com.orange.protocol.message.UserProtos;

import java.util.Collections;
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
        int type = dataRequest.getGetUserFriendListRequest().getType();


        List<User> friendList = null;
        List<UserProtos.PBUser> pbFriendList = null;

        List<User> requestFriendList = null;
        List<UserProtos.PBUser> pbRequestFriendList = null;
        int requestNewCount = 0;

        if (type == MessageProtos.PBGetUserFriendListType.TYPE_ALL_VALUE
                || type == MessageProtos.PBGetUserFriendListType.TYPE_FRIEND_LIST_VALUE) {

            friendList = FriendManager.getInstance().getFriendList(userId);
            pbFriendList = User.listToPB(friendList, null);
        }
        else if(type == MessageProtos.PBGetUserFriendListType.TYPE_ALL_VALUE
                || type == MessageProtos.PBGetUserFriendListType.TYPE_FRIEND_LIST_VALUE){

            requestFriendList = FriendRequestManager.getInstance().getRequestList(userId);
            requestNewCount = (int)FriendRequestManager.getInstance().getUnreadCount(userId);
            pbRequestFriendList = User.listToPB(friendList, null);
        }

        MessageProtos.PBGetUserFriendListResponse.Builder builder = MessageProtos.PBGetUserFriendListResponse.newBuilder();
        UserProtos.PBUserFriendList.Builder friendListBuilder = UserProtos.PBUserFriendList.newBuilder();
        if (pbFriendList != null){
            friendListBuilder.addAllFriends(pbFriendList);
        }
        if (pbRequestFriendList != null){
            friendListBuilder.addAllRequestFriends(pbRequestFriendList);
            friendListBuilder.setRequestNewCount(requestNewCount);
        }
        builder.setFriends(friendListBuilder.build());

        responseBuilder.setResultCode(0);
        responseBuilder.setGetUserFriendListResponse(builder.build());
    }
}
