package com.orange.game.api.barrage.service.yun.qiniu;

/**
 * Created by pipi on 14/12/8.
 */
public class QiuNiuService {
    private static QiuNiuService ourInstance = new QiuNiuService();

    public static QiuNiuService getInstance() {
        return ourInstance;
    }

    private QiuNiuService() {
    }
}
