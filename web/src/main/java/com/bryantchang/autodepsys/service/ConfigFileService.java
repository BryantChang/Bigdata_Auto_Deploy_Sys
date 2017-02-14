package com.bryantchang.autodepsys.service;

import com.bryantchang.autodepsys.bean.HadoopSettings;
import com.bryantchang.autodepsys.constant.Constants;
import com.bryantchang.autodepsys.dao.ConfigFileDao;
import com.bryantchang.autodepsys.dao.HadoopSettingsDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by bryantchang on 2016/12/8.
 */
@Service
public class ConfigFileService {
    @Resource
    ConfigFileDao dao;
    @Resource
    HadoopSettingsDao hadoopSettingsDao;

    public Long generateHadoopSlaveFile() {
        String filePath = Constants.ROOT_DIR +
                Constants.SYS_DIR + "/" +
                Constants.CONFIG_TPL + "/" +
                "hadoop/slaves";

        Long res = Constants.SUCC;
        res = dao.generateHadoopSlaveFile(filePath);
        return res;
    }

    public Long generateSparkSlaveFile() {
        String filePath = Constants.ROOT_DIR +
                Constants.SYS_DIR + "/" +
                Constants.CONFIG_TPL + "/" +
                "spark/slaves";

        Long res = Constants.SUCC;
        res = dao.generateSparkSlaveFile(filePath);
        return res;
    }

    public Long addHadoopSettingFromXML(String fileType) {
        String filePath = Constants.ROOT_DIR +
                Constants.SYS_DIR + "/" +
                Constants.CURR_CONFIG + "/" +
                fileType;
        System.out.println(filePath);
        Long res = dao.addHadoopSettingFromXML(filePath, fileType);
        return res;
    }

    public Long addSparkSettingFromCurFile() {
        String filePath = Constants.ROOT_DIR +
                Constants.SYS_DIR + "/" +
                Constants.CURR_CONFIG + "/" +
                "spark-defaults.conf";
        Long res = dao.addSparkDefaultFromCurFile(filePath);
        return res;
    }

    public Long generateSparkDefaultFile() {
        String filePath = Constants.ROOT_DIR +
                Constants.SYS_DIR + "/" +
                Constants.CONFIG_TPL + "/" +
                "spark/spark-defaults.conf";
        Long res = Constants.SUCC;
        res = dao.generateSparkDefaultFile(filePath);
        return res;
    }

    public Long generateHadoopXmlConfigFile(String fileIndex) {
        String fileBase = Constants.ROOT_DIR +
                Constants.SYS_DIR + "/" +
                Constants.CONFIG_TPL + "/hadoop";
        String fileName = (String)Constants.hadoopFiles.get(fileIndex);
        String filePath = fileBase + "/" + fileName;
        Long res = Constants.SUCC;
        ArrayList<HadoopSettings> settings = hadoopSettingsDao.getAllHadoopSettingsByFileName(fileName);
        res = dao.generateHadoopConfFile(filePath, settings);
        return res;

    }

}














