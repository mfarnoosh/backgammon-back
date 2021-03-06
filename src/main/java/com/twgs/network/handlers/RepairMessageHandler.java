package com.twgs.network.handlers;

import com.twgs.dao.mongo.interfaces.IGameObjectDao;
import com.twgs.entities.mongo.gameObjects.playerObjects.Tower;
import com.twgs.enums.TowerPropertyType;
import com.twgs.network.BaseMessageHandler;
import com.twgs.network.messages.SocketMessage;
import com.twgs.util.GameConfig;
import com.twgs.util.Spring;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Mehrdad on 16/12/11.
 */
@Component
@Scope("singleton")
public class RepairMessageHandler extends BaseMessageHandler {
    private Logger logger = Logger.getLogger(RepairMessageHandler.class);
    @Autowired
    IGameObjectDao gameObjectDao;
    @Override
    public SocketMessage handle(SocketMessage message) {
        String towerId = message.Params.get(0);
        logger.info("Repair tower request message: " + towerId);
        Tower tower = gameObjectDao.findTowerById(towerId);
        if (tower != null) {
            if (!tower.getPlayerId().equals(message.PlayerKey)) {
                message.ExceptionMessage = "Tower player id is not equal to playerId";
                return message;
            }
            tower.setCurrentHitPoint(Double.parseDouble(GameConfig.getTowerProperty(tower.getType(), tower.getLevel(), TowerPropertyType.HIT_POINT)));
            gameObjectDao.save(tower);
        }

        return message;
    }
}
