package com.twgs.dao.mongo.interfaces;

import com.twgs.entities.mongo.events.AttackEvent;
import com.twgs.entities.mongo.events.MoveEvent;

/**
 * Created by alirezaghias on 10/19/2016 AD.
 */

public interface IAttackEventDao extends IBaseMongoDao<AttackEvent> {
    AttackEvent find(String unitId, String towerId);
    void delete(String unitId, String towerId);
}
