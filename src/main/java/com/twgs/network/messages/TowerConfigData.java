package com.twgs.network.messages;

import com.twgs.enums.TowerPropertyType;
import com.twgs.enums.TowerType;
import com.twgs.util.GameConfig;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mehrdad on 16/12/17.
 */
public class TowerConfigData {
    public int Type = -1;
    public int Level = 0;
    public double BuildTime = 0.0;
    public double Value = 0.0;
    public double HitPoint = 0.0;
    public double AirDamage = 0.0;
    public double LandDamage = 0.0;
    public double FireRate = 0.0;
    public double Range = 0.0;
    public double MaxCapacity = 0.0;
    public double UpgradePrice = 0.0;
    public double UpgradeTime = 0.0;
    public int MaxHouseSpace = 0;

    public boolean MaxLevel = false;


    public static List<TowerConfigData> getAllTowerConfigs() {
        List<TowerConfigData> result = new LinkedList<>();
        for (TowerType type : TowerType.values()) {
            int maxLevel = GameConfig.getTowerMaxLevel(type);
            for (int level = 1; level <= maxLevel; level++) {
                TowerConfigData td = new TowerConfigData();

                td.Type = type.getValue();
                td.Level = level;

                td.BuildTime = Double.parseDouble(GameConfig.getTowerProperty(type,level, TowerPropertyType.BUILD_TIME));
                td.Value = Double.parseDouble(GameConfig.getTowerProperty(type,level, TowerPropertyType.VALUE));
                td.HitPoint = Double.parseDouble(GameConfig.getTowerProperty(type,level, TowerPropertyType.HIT_POINT));
                td.AirDamage = Double.parseDouble(GameConfig.getTowerProperty(type,level, TowerPropertyType.AIR_DAMAGE));
                td.LandDamage = Double.parseDouble(GameConfig.getTowerProperty(type,level, TowerPropertyType.LAND_DAMAGE));
                td.FireRate = Double.parseDouble(GameConfig.getTowerProperty(type,level, TowerPropertyType.FIRE_RATE));
                td.Range = Double.parseDouble(GameConfig.getTowerProperty(type,level, TowerPropertyType.RANGE));
                td.MaxCapacity = Double.parseDouble(GameConfig.getTowerProperty(type,level, TowerPropertyType.MAX_CAPACITY));
                td.UpgradePrice = Double.parseDouble(GameConfig.getTowerProperty(type,level, TowerPropertyType.UPGRADE_PRICE));
                td.UpgradeTime = Double.parseDouble(GameConfig.getTowerProperty(type,level, TowerPropertyType.UPGRADE_TIME));
                td.MaxHouseSpace = Integer.parseInt(GameConfig.getTowerProperty(type,level, TowerPropertyType.MAX_HOUSE_SPACE));

                if(level == maxLevel)
                    td.MaxLevel = true;
                result.add(td);
            }
        }
        return result;
    }
}
