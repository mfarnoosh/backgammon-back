package com.twgs.dao.mongo.interfaces;

import com.twgs.entities.mongo.events.MoveEvent;
import com.twgs.entities.mongo.gameObjects.BaseGameObject;
import com.twgs.entities.mongo.gameObjects.playerObjects.BasePlayerObject;
import com.twgs.entities.mongo.gameObjects.playerObjects.Tower;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by alirezaghias on 10/19/2016 AD.
 */

public interface IMoveEventDao extends IBaseMongoDao<MoveEvent> {

    void delete(String unitId, String towerId);
    MoveEvent find(String unitId, String towerId);
}
