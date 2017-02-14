package com.bryantchang.autodepsys.service;

import com.bryantchang.autodepsys.bean.HadoopSettings;
import com.bryantchang.autodepsys.dao.HadoopSettingsDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by bryantchang on 2016/12/18.
 */
@Service
public class HadoopSettingsService {
    @Resource
    HadoopSettingsDao dao;
    public boolean delSetting(String id) {
        Long settingId = Long.valueOf(id);
        boolean res = false;
        res = dao.delSetting(settingId);
        return res;
    }

    public ArrayList<HadoopSettings> getAllHadoopSettings() {
        ArrayList<HadoopSettings> list = dao.getAllHadoopSettings();
        return list;
    }

    public ArrayList<HadoopSettings> getHadoopSettingByFileName(String fileName) {
        ArrayList<HadoopSettings> list = dao.getAllHadoopSettingsByFileName(fileName);
        return list;
    }

    public HadoopSettings addHadoopSettings(String name, String value,
                                          String description, String filename) {
        if(dao.getHadoopSettingsByName(name) != null){
            return null;
        }
        HadoopSettings setting = new HadoopSettings(0L, name, value, description, filename);
        HadoopSettings res = dao.addSetting(setting);
        return res;
    }

    public HadoopSettings updateHadoopSetting(String id, String name, String value,
                                            String description, String filename) {
        Long id_long = Long.valueOf(id);
        HadoopSettings setting = new HadoopSettings(id_long, name, value, description, filename);
        HadoopSettings res = dao.updateSetting(setting);
        return res;
    }
}
