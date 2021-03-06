package com.twgs.dao.mongo;

import com.twgs.dao.mongo.interfaces.IBaseMongoDao;
import com.twgs.dao.mongo.interfaces.IPlayerDao;
import com.twgs.entities.mongo.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Mehrdad on 16/12/11.
 */
@Repository
public class PlayerDao implements IPlayerDao {
    @Autowired
    MongoOperations mongoTemplate;

    @Override
    public Class<Player> getClazz() { return Player.class; }

    @Override
    public MongoOperations getMongoOperations() {return mongoTemplate;}




    @Override
    public Player findByEmail(String email) {
        Player rez = null;
        rez = mongoTemplate.findOne(Query.query(Criteria.where("email").is(email)), Player.class);
        return rez;
    }
}
