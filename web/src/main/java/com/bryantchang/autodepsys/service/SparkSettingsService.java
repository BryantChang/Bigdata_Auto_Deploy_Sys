package com.bryantchang.autodepsys.service;

import com.bryantchang.autodepsys.bean.SparkSettings;
import com.bryantchang.autodepsys.dao.SparkSettingsDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by bryantchang on 2016/12/9.
 */
@Service
public class SparkSettingsService {
    @Resource
    SparkSettingsDao dao;
    public boolean delSetting(String id) {
        Long settingId = Long.valueOf(id);
        boolean res = false;
        res = dao.delSetting(settingId);
        return res;
    }

    public ArrayList<SparkSettings> getAllSparkSettings() {
        ArrayList<SparkSettings> list = dao.getAllSparkSettings();
        return list;
    }

    public SparkSettings addSparkSettings(String name, String value,
                            String description, String filename) {
        if(dao.getSparkSettingsByName(name) != null){
            return null;
        }
        SparkSettings setting = new SparkSettings(0L, name, value, description, filename);
        SparkSettings res = dao.addSetting(setting);
        return res;
    }

    public SparkSettings updateSparkSetting(String id, String name, String value,
                                            String description, String filename) {
        Long id_long = Long.valueOf(id);
        SparkSettings setting = new SparkSettings(id_long, name, value, description, filename);
        SparkSettings res = dao.updateSetting(setting);
        return res;
    }
}
