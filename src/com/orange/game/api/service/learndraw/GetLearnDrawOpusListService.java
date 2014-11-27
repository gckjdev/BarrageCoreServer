package com.orange.game.api.service.learndraw;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.orange.common.utils.StringUtil;
import com.orange.game.api.service.CommonGameService;
import com.orange.game.api.service.CommonServiceUtils;
import com.orange.game.constants.DBConstants;
import com.orange.game.constants.ServiceConstant;
import com.orange.game.model.dao.LearnDraw;
import com.orange.game.model.dao.UserAction;
import com.orange.game.model.manager.LearnDrawManager;
import com.orange.game.model.service.CreateDataFileService;

public class GetLearnDrawOpusListService extends CommonGameService {

	String userId;
	String appId;
	int offset;
	int limit;
	int type;
	int sortType;
	int sellContentType = DBConstants.C_SELL_CONTENT_TYPE_LEARN_DRAW;
	

	@Override
	public void handleData() {
		List<UserAction> opusList = LearnDrawManager.getOpusListByType(
				mongoClient, sellContentType, type, offset, limit, sortType);
		
		for (UserAction userAction : opusList){
			if (userAction.isLocalDataFileExist(false) == false){
				CreateDataFileService.getInstance().createFileAndUpdateAtBackground(mongoClient, userAction, true);
			}
		}
		
		log.info("<GetLearnDrawOpusListService> list count = " + opusList.size());
		byteData = CommonServiceUtils.feedListToProtocolBufferImage(opusList,
                0, false);
	}

	@Override
	public String toString() {
		return "GetLearnDrawOpusListService [userId=" + userId + ", appId="
				+ appId + ", offset=" + offset + ", limit=" + limit + ", type="
				+ type + ", sortType=" + sortType + ", sellContentType="
				+ sellContentType + "]";
	}

	@Override
	public boolean setDataFromRequest(HttpServletRequest request) {

		userId = request.getParameter(ServiceConstant.PARA_USERID);
		
		String sellContentTypeString = request.getParameter(ServiceConstant.PARA_SELL_CONTENT_TYPE);
		if (!StringUtil.isEmpty(sellContentTypeString)){
			sellContentType = Integer.parseInt(sellContentTypeString);
		}
		
/*
		if (!check(userId, ErrorCode.ERROR_PARAMETER_USERID_EMPTY,
				ErrorCode.ERROR_PARAMETER_USERID_NULL))
			return false;
*/
		offset = getIntValueFromRequest(request, ServiceConstant.PARA_OFFSET,
                0);
		limit = getIntValueFromRequest(request, ServiceConstant.PARA_LIMIT,
                ServiceConstant.CONST_DEFAULT_PAGE_COUNT);

		type = getIntValueFromRequest(request, ServiceConstant.PARA_TYPE,
                LearnDraw.LearnDrawTypeAll);

		sortType = getIntValueFromRequest(request,
                ServiceConstant.PARA_SORT_BY, LearnDrawManager.SortTypeTime);

		return true;

	}

}
