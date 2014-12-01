package com.orange.game.api.barrage.common;

import com.google.protobuf.InvalidProtocolBufferException;
import com.orange.game.api.service.CommonGameService;
import com.orange.game.constants.ErrorCode;
import com.orange.protocol.message.ErrorProtos;
import com.orange.protocol.message.MessageProtos;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by pipi on 14/12/1.
 */
public class CommonDataRequestService extends CommonGameService {

    byte[] data;
    MessageProtos.PBDataRequest dataRequest;

    @Override
    public boolean setDataFromRequest(HttpServletRequest request) {
        try {
            data = readPostData(request.getInputStream());
        } catch (IOException e) {
            resultCode = ErrorProtos.PBError.ERROR_READ_POST_DATA_VALUE;
            log.error("catch exception while read post data, exception="+e.toString(), e);
            return true;
        }

        try {
            dataRequest = MessageProtos.PBDataRequest.parseFrom(data);
        } catch (Exception e) {
            int length = (data == null) ? 0 : data.length;
            resultCode = ErrorProtos.PBError.ERROR_PARSE_POST_DATA_VALUE;
            log.error("catch exception while parse post data, exception="+e.toString()+", data bytes="+length, e);
            return true;
        }

        // TODO check others

        return true;
    }

    @Override
    public void handleData() {

        if (resultCode != 0){
            // this is for prehandling check
        }

        MessageProtos.PBDataResponse.Builder responseBuilder = MessageProtos.PBDataResponse.newBuilder();
        responseBuilder.setRequestId(dataRequest.getRequestId());
        responseBuilder.setResultCode(0);
        MessageProtos.PBDataResponse response = responseBuilder.build();
        log.info("[SEND] response = "+response.toString());
        byteData = response.toByteArray();
    }
}
