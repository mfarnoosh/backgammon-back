package com.twgs.service;

import com.twgs.enums.MapConfig;
import com.twgs.util.GameConfig;
import com.twgs.util.SharedPreference;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.commons.io.FileUtils;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Mehrdad on 16/11/23.
 */
public class MapService {
    public static int defaultZoomLevel = Integer.parseInt(GameConfig.getMapProperty(MapConfig.ZOOM_LEVEL));

    public MapService(){}


    public static byte[] getTileImage(String tileUrl){
        File cacheDir = new File("cache/" + tileUrl);
        //cacheDir.mkdirs();
        File cache = new File(cacheDir.getAbsolutePath() + ".png");
        if (cache.exists()) {
            try {
                byte[] tile = FileUtils.readFileToByteArray(cache);

                return tile;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String url = "http://tile.openstreetmap.org/" + tileUrl + ".png";
        try {
            URLConnection connection = new URL(url).openConnection();
            DataInputStream dis = new DataInputStream(connection.getInputStream());
            int count = 0;
            byte[] data = new byte[1024];
            ByteOutputStream bos = new ByteOutputStream();
            while ((count = dis.read(data)) != -1) {
                bos.write(data, 0, count);
            }
            bos.flush();
            byte[] tile = bos.getBytes();
            FileUtils.writeByteArrayToFile(cache, tile);

            return tile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
