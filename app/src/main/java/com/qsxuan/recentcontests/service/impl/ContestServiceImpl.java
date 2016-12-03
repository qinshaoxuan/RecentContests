package com.qsxuan.recentcontests.service.impl;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qsxuan.recentcontests.dao.ContestDao;
import com.qsxuan.recentcontests.model.Contest;
import com.qsxuan.recentcontests.service.IContestService;
import com.qsxuan.recentcontests.util.HttpClientUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinshaoxuan
 */

public class ContestServiceImpl implements IContestService {

    private static final String API = "http://contests.acmicpc.info/contests.json";
    private Gson gson = new Gson();
    private static final Logger LOGGER = LoggerFactory.getLogger(ContestServiceImpl.class);
    private ContestDao contestDao;

    public ContestServiceImpl(Context context) {
        this.contestDao = new ContestDao(context);
    }

    @Override
    public List<Contest> getContestList() {
        updateContestList();
        List<Contest> contestList = contestDao.getContestList();
        LOGGER.info("get data from database : {}", contestList);
        return contestList;
    }

    /**
     * 获取数据并更新数据库
     */
    private void updateContestList() {
        List<Contest> contestList = getContestFromApi();
        contestDao.updateContestList(contestList);
    }


    /**
     * 调用 api 获取数据
     *
     * @return
     */
    private List<Contest> getContestFromApi() {
        List<Contest> contestList = new ArrayList<>();
        // get 请求 api
        String result = HttpClientUtil.get(API);
        LOGGER.info(result);
        try {
            contestList = gson.fromJson(result,
                                        new TypeToken<ArrayList<Contest>>() {
                                        }.getType());
        } catch (Exception e) {
            LOGGER.error("call api failed");
        }
        return contestList;
    }
}
