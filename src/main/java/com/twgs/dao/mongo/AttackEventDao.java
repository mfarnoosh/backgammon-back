package com.twgs.dao.mongo;

import com.twgs.dao.mongo.interfaces.IAttackEventDao;
import com.twgs.dao.mongo.interfaces.IMoveEventDao;
import com.twgs.entities.mongo.events.AttackEvent;
import com.twgs.entities.mongo.events.MoveEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by alirezaghias on 1/6/2017 AD.
 */
@Repository
public class AttackEventDao implements IAttackEventDao {
    @Autowired
    MongoOperations mongoTemplate;
    @Override
    public Class<AttackEvent> getClazz() {
        return AttackEvent.class;
    }

    @Override
    public MongoOperations getMongoOperations() {
        return mongoTemplate;
    }


    @Override
    public AttackEvent find(String unitId, String towerId) {
        return getMongoOperations().findOne(Query.query(Criteria.where("underAttackTowerId").is(towerId).and("gameObjectId").is(unitId)), AttackEvent.class);
    }

    @Override
    public void delete(String unitId, String towerId) {
        AttackEvent ae = find(unitId, towerId);
        if (ae != null)
            delete(ae);
    }
}
