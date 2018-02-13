package com.bryantchang.autodepsys.dao;

import com.bryantchang.autodepsys.bean.SparkSettings;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bryantchang on 2016/12/9.
 */
@Repository
public class SparkSettingsDao extends BaseDAO{
    public SparkSettings addSetting(SparkSettings setting) {
        long pk = 0;
        pk = this.insert(setting);
        if(pk != 0) {
            setting.setId(pk);
            return setting;
        }else {
            return null;
        }
    }

    public SparkSettings updateSetting(SparkSettings settings) {
        boolean res = false;
        res = this.update(settings);
        if(res == true) {
            return settings;
        }else {
            return null;
        }
    }

    public boolean delAllSettings() {
        boolean res = this.deleteData(SparkSettings.class, null);
        return res;
    }

    public boolean delSetting(Long SettingId) {
        Map conditions = new HashMap();
        conditions.put("id", SettingId);
        boolean res = this.deleteData(SparkSettings.class, conditions);
        return res;
    }

    public ArrayList<SparkSettings> getAllSparkSettings() {
        return this.getList(SparkSettings.class, null);
    }

    public ArrayList<SparkSettings> getAllSparkSettingsByFileName(String fileName) {
        Map conditions = new HashMap();
        conditions.put("filename", fileName);
        ArrayList list = new ArrayList<SparkSettings>();
        list = this.getList(SparkSettings.class, conditions);
        return list;
    }

    public ArrayList<SparkSettings> getSparkSettingsByName(String name) {
        Map conditions = new HashMap();
        conditions.put("name", name);
        ArrayList list = null;
        list = this.getList(SparkSettings.class, conditions);
        if(list.isEmpty()) {
            list = null;
        }
        return list;
    }
}
