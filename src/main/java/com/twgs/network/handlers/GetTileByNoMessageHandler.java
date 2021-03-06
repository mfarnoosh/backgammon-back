package com.twgs.network.handlers;

import com.google.gson.Gson;
import com.twgs.dao.mongo.interfaces.IGameObjectDao;
import com.twgs.entities.mongo.gameObjects.playerObjects.Tower;
import com.twgs.network.BaseMessageHandler;
import com.twgs.network.messages.SocketMessage;
import com.twgs.network.messages.TileData;
import com.twgs.network.messages.TowerData;
import com.twgs.service.Tile;
import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mehrdad on 16/12/11.
 */
@Component
@Scope("singleton")
public class GetTileByNoMessageHandler extends BaseMessageHandler {
    private Logger logger = Logger.getLogger(GetTileByNoMessageHandler.class);

    @Autowired
    private IGameObjectDao gameObjectDao;

    @Override
    public SocketMessage handle(SocketMessage message) {
        logger.info("Get Tile by no msg: " + message);
        int tileX = Integer.parseInt(message.Params.get(0));
        int tileY = Integer.parseInt(message.Params.get(1));

        Tile tile = new Tile(tileX,tileY);

        TileData tileData = new TileData();
        tileData.ImageBytes = Base64.encode(tile.getImage());

        tileData.CenterLat = (float) tile.getCenter().getX();
        tileData.CenterLon = (float) tile.getCenter().getY();

        tileData.North = (float) tile.getBoundingBox().north;
        tileData.East = (float) tile.getBoundingBox().east;
        tileData.South = (float) tile.getBoundingBox().south;
        tileData.West = (float) tile.getBoundingBox().west;

        //tileData.PositionX = tile.getTileX();
        //tileData.PositionY = tile.getTileY();

        List<Tower> towers = gameObjectDao.getAllTowersInBox(
                new double[]{tile.getBoundingBox().south, tile.getBoundingBox().west},
                new double[]{tile.getBoundingBox().north, tile.getBoundingBox().east});

        List<TowerData> towersData = new LinkedList<>();
        for(Tower tower : towers) {
            towersData.add(new TowerData(tower, gameObjectDao.findUnitByOwnerTower(tower)));
        }

        tileData.towers = towersData;

/*  TODO: Farnoosh: unit data transferred with it's owner tower.
    TODO: but when it's owner tower not in the bound of tile, that is better to send just unit data to client
    TODO: and in client render these moving or attacking units without their owner towers

        List<Unit> units = gameObjectDao.getAllUnitsInBox(
                new double[]{tile.getBoundingBox().south, tile.getBoundingBox().west},
                new double[]{tile.getBoundingBox().north, tile.getBoundingBox().east});

        List<UnitData> unitsData = new LinkedList<>();
        unitsData.addAll(units.stream().map(UnitData::new).collect(Collectors.toList()));
        tileData.units = unitsData;*/

        message.Params.clear();
        message.Params.add(new Gson().toJson(tileData));

        return message;
    }
}
