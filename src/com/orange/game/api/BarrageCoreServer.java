package com.orange.game.api;

import com.mongodb.BasicDBObject;
import com.orange.barrage.service.yun.qiniu.QiuNiuService;
import com.orange.common.api.CommonApiServer;
import com.orange.common.api.service.ServiceHandler;
import com.orange.common.mongodb.MongoDBClient;
import com.orange.common.redis.RedisClient;
import com.orange.common.service.BlackUserService;
import com.orange.common.utils.DBObjectUtil;
import com.orange.common.utils.DateUtil;
import com.orange.common.utils.IntegerUtil;
import com.orange.game.api.service.GameServiceFactory;
import com.orange.game.constants.DBConstants;
import com.orange.game.model.common.IndexMonitorManager;
import com.orange.game.model.generator.TopUserDataGenerator;
import com.orange.game.model.manager.feed.FeedManager;
import com.orange.game.model.manager.feed.FeedProcessor;
import com.orange.game.model.manager.feed.HotFeedManagerFactory;
import com.orange.game.model.manager.group.GroupManager;
import com.orange.game.model.manager.guessopus.AwardManager;
import com.orange.game.model.manager.guessopus.GuessContestGeneratorManager;
import com.orange.game.model.manager.guessopus.UserGuessUtil;
import com.orange.game.model.service.DBService;
import com.orange.game.model.service.contest.ContestService;
import com.orange.game.model.service.opus.OpusService;
import com.orange.game.model.xiaoji.XiaojiFactory;
import com.orange.game.traffic.server.ServerMonitor;
import com.orange.network.game.protocol.model.TutorialProtos;

public class BarrageCoreServer extends CommonApiServer {
	
	public static final String VERSION_STRING = BarrageCoreServer.class.getName() + " Version 0.1";
	public static final String SPRING_CONTEXT_FILE = "classpath:/com/orange/game/api/applicationContext.xml";	
	public static final String LOG4J_FLE = "classpath:/log4j.properties";
//	public static final String MONGO_SERVER = "localhost";
//	public static final String MONGO_DB_NAME = "groupbuy";
//	public static final String MONGO_USER = "";
//	public static final String MONGO_PASSWORD = "";
	public static final GameServiceFactory serviceFactory = new GameServiceFactory();
	
	private static final MongoDBClient mongoClient = DBService.getInstance().getMongoDBClient(); //new MongoDBClient(DBConstants.D_GAME);
//	private static final MongoDBClient mongoClientForBlack = new MongoDBClient(DBConstants.D_GAME);

	@Override
	public String getAppNameVersion() {
		return VERSION_STRING;
	}

	@Override
	public String getLog4jFile() {
		return LOG4J_FLE;
	}

	@Override
	public ServiceHandler getServiceHandler() {
		return ServiceHandler.getServiceHandler(mongoClient, serviceFactory);
	}

	@Override
	public String getSpringContextFile() {
		return SPRING_CONTEXT_FILE;
	}
	
	@Override
	public int getPort() {
		String port = System.getProperty("server.port");
		if (port != null && !port.isEmpty()){
			return Integer.parseInt(port);
		}
		return 8100;
	}
	
    public static void main(String[] args) throws Exception{

//        BasicDBObject obj = new BasicDBObject();
//        long value = 4294967295l;
//        obj.put("long", value);
//        int intValue = DBObjectUtil.getInt(obj, "long");
//        System.out.println("intValue="+intValue);
//
//        int intValue2 = -1;
//        value = IntegerUtil.getUnsignedInt(intValue2);
//        System.out.println("longValue="+value);

//        BlackUserService.getInstance().load(mongoClient);
//
//    	XiaojiFactory.getInstance();
//
//    	FeedProcessor.getInstance().setMongoDBClient(mongoClient);
//    	HotFeedManagerFactory.setMongoDBClient(mongoClient);
//    	FeedManager.setMongoDBClient(mongoClient);
//
//    	// start to fetch hot feed firstly
//    	HotFeedManagerFactory.getHotFeedManager();
    	Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				log.info("===================== SHUTDOWN HOOK CATCH =====================");

                DBService.getInstance().shutdown();

				//HotFeedManagerFactory.getHotFeedManager().cacheHotFeedList();

				RedisClient.getInstance().destroyPool();
				log.info("===================== SHUTDOWN HOOK COMPLETE =====================");
			} 
		});


        // test
//        GroupManager.testAllGroupsFeeMonthly();

//        IndexMonitorManager.getInstance().resetOngoingIndex();

//    	TopUserDataGenerator.createTopUserData();

        // trigger timer to calculate guess contest award.
//        AwardManager.getInstance();
//        ContestService.getInstance();

//        OpusService.getInstance().startDailyTopRankService(XiaojiFactory.getInstance().getDraw(), "画画");
//        OpusService.getInstance().startDailyAwardService(XiaojiFactory.getInstance().getDraw());

		// This code is to initiate the listener.
//		ServerMonitor.getInstance().start();

//        QiuNiuService.getInstance().getUpToken();

        BarrageCoreServer server = new BarrageCoreServer();
		server.startServer();
    }

    }


