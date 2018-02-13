package com.bryantchang.autodepsys.dao;

import com.bryantchang.autodepsys.bean.HadoopSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bryantchang on 2016/12/18.
 */
@Repository
public class HadoopSettingsDao extends BaseDAO {
//    private static final Logger logger = LoggerFactory.getLogger(HadoopSettingsDao.class);
    public HadoopSettings addSetting(HadoopSettings setting) {
        long pk = 0;
        pk = this.insert(setting);
        if(pk != 0) {
            setting.setId(pk);
            return setting;
        }else {
            return null;
        }
    }

    public HadoopSettings updateSetting(HadoopSettings settings) {
        boolean res = false;
        res = this.update(settings);
        if(res == true) {
            return settings;
        }else {
            return null;
        }
    }

    public boolean delAllSetting() {
        boolean res = this.deleteData(HadoopSettings.class, null);
        return res;
    }

    public boolean delSetting(Long SettingId) {
        Map conditions = new HashMap();
        conditions.put("id", SettingId);
        boolean res = this.deleteData(HadoopSettings.class, conditions);
        return res;
    }

    public ArrayList<HadoopSettings> getAllHadoopSettings() {
        return this.getList(HadoopSettings.class, null);
    }

    public ArrayList<HadoopSettings> getAllHadoopSettingsByFileName(String fileName) {
        Map conditions = new HashMap();
        conditions.put("filename", fileName);
        ArrayList list = new ArrayList<HadoopSettings>();
        list = this.getList(HadoopSettings.class, conditions);
        return list;
    }

    public ArrayList<HadoopSettings> getHadoopSettingsByName(String name) {
        Map conditions = new HashMap();
        conditions.put("name", name);
        ArrayList list = null;
        list = this.getList(HadoopSettings.class, conditions);
        if(list.isEmpty()) {
            list = null;
        }
        return list;
    }


}
