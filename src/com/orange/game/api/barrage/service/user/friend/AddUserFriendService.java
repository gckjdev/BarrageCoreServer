package com.orange.game.api.barrage.service.user.friend;

import com.orange.barrage.model.user.User;
import com.orange.barrage.model.user.UserManager;
import com.orange.game.api.barrage.common.CommonBarrageService;
import com.orange.protocol.message.MessageProtos;
import com.orange.protocol.message.UserProtos;

/**
 * Created by pipi on 14/12/24.
 */
public class AddUserFriendService extends CommonBarrageService {

    private static AddUserFriendService ourInstance = new AddUserFriendService();

    public static AddUserFriendService getInstance() {
        return ourInstance;
    }

    private AddUserFriendService() {
    }

    @Override
    public boolean validateRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {
        return false;
    }

    @Override
    public void handleRequest(MessageProtos.PBDataRequest dataRequest, MessageProtos.PBDataResponse.Builder responseBuilder) {


        MessageProtos.PBAddUserFriendRequest req = dataRequest.getAddUserFriendRequest();
        String friendId = null;
        User friend = null;
        switch (req.getSourceType()){
            case UserProtos.FriendAddSourceType.ADD_BY_SCAN_QRCODE_VALUE:
                // TODO
                break;

            case UserProtos.FriendAddSourceType.ADD_BY_SEARCH_VALUE:
                friendId = req.getFriend().getUserId();
                break;
        }

        User user = null;
        String userId = dataRequest.getUserId();

        friend = UserManager.getInstance().findUserById(friendId);
        user = UserManager.getInstance().findUserById(userId);



    }
}
