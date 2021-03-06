package com.twgs.network.handlers;

import com.google.gson.Gson;
import com.twgs.dao.mongo.interfaces.IGameObjectDao;
import com.twgs.entities.mongo.gameObjects.playerObjects.Tower;
import com.twgs.network.BaseMessageHandler;
import com.twgs.network.messages.SocketMessage;
import com.twgs.network.messages.TowerData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Mehrdad on 16/12/11.
 */
@Component
@Scope("singleton")
public class GetTowerDataMessageHandler extends BaseMessageHandler {
    @Autowired
    IGameObjectDao gameObjectDao;
    private Logger logger = Logger.getLogger(GetTowerDataMessageHandler.class);

    @Override
    public SocketMessage handle(SocketMessage message) {
//        logger.info("Get Tower Data msg: " + message);

        String id = String.valueOf(message.Params.get(0));
        message.Params.clear();

        Tower t = gameObjectDao.findTowerById(id);
        if (t != null) {
            message.Params.add(new Gson().toJson(new TowerData(t,gameObjectDao.findUnitByOwnerTower(t))));
        }else{
            message.ExceptionMessage = "Invalid tower.";
        }
        return message;
    }
}
