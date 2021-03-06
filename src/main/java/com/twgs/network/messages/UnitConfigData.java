package com.twgs.network.messages;

import com.twgs.enums.UnitPropertyType;
import com.twgs.enums.UnitType;
import com.twgs.util.GameConfig;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mehrdad on 16/12/17.
 */
public class UnitConfigData {
    public int Type = -1;
    public int Level = 0;
    public double BuildTime = 0.0;
    public double Value = 0.0;
    public double HitPoint = 0.0;
    public double AttackDamage = 0.0;
    public double DefenceDamage = 0.0;
    public double FireRate = 0.0;
    public double Range = 0.0;
    public double Speed = 0.0;
    public double UpgradePrice = 0.0;
    public double UpgradeTime = 0.0;
    public int HouseSpace = 0;

    public boolean MaxLevel = false;

    public static List<UnitConfigData> getAllUnitConfigs() {
        List<UnitConfigData> result = new LinkedList<>();
        for (UnitType type : UnitType.values()) {
            int maxLevel = GameConfig.getUnitMaxLevel(type);
            for (int level = 1; level <= maxLevel; level++) {
                UnitConfigData td = new UnitConfigData();

                td.Type = type.getValue();
                td.Level = level;

                td.BuildTime = Double.parseDouble(GameConfig.getUnitProperty(type,level, UnitPropertyType.BUILD_TIME));
                td.Value = Double.parseDouble(GameConfig.getUnitProperty(type,level, UnitPropertyType.VALUE));
                td.HitPoint = Double.parseDouble(GameConfig.getUnitProperty(type,level, UnitPropertyType.HIT_POINT));
                td.AttackDamage = Double.parseDouble(GameConfig.getUnitProperty(type,level, UnitPropertyType.ATTACK_DAMAGE));
                td.DefenceDamage = Double.parseDouble(GameConfig.getUnitProperty(type,level, UnitPropertyType.DEFENCE_DAMAGE));
                td.FireRate = Double.parseDouble(GameConfig.getUnitProperty(type,level, UnitPropertyType.FIRE_RATE));
                td.Range = Double.parseDouble(GameConfig.getUnitProperty(type,level, UnitPropertyType.RANGE));
                td.Speed = Double.parseDouble(GameConfig.getUnitProperty(type,level, UnitPropertyType.SPEED));
                td.UpgradePrice = Double.parseDouble(GameConfig.getUnitProperty(type,level, UnitPropertyType.UPGRADE_PRICE));
                td.UpgradeTime = Double.parseDouble(GameConfig.getUnitProperty(type,level, UnitPropertyType.UPGRADE_TIME));
                td.HouseSpace = Integer.parseInt(GameConfig.getUnitProperty(type,level,UnitPropertyType.HOUSE_SPACE));

                if(level == maxLevel)
                    td.MaxLevel = true;

                result.add(td);
            }
        }
        return result;
    }
}
