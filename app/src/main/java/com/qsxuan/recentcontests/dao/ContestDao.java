package com.qsxuan.recentcontests.dao;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.qsxuan.recentcontests.model.Contest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.j256.ormlite.android.apptools.OpenHelperManager.getHelper;

/**
 * Contest 数据访问层
 *
 * @author qinshaoxuan
 */

public class ContestDao {
    private RuntimeExceptionDao<Contest, Integer> contestRuntimeDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(Contest.class);

    public ContestDao(Context contest) {
        this.contestRuntimeDao = getHelper(contest, ContestDataHelper.class).getContestDao();
    }


    /**
     * 获取数据库中全部数据
     *
     * @return
     */
    public List<Contest> getContestList() {
        return contestRuntimeDao.queryForAll();
    }

    /**
     * 更新数据库中的数据，删除过期数据
     *
     * @param contestList
     */
    public void updateContestList(List<Contest> contestList) {
        if (null == contestList || contestList.isEmpty()) {
            return;
        }
        // 清除过期数据
        List<Contest> oldContests = contestRuntimeDao.queryForAll();
        if (null != oldContests && !oldContests.isEmpty()) {
            contestRuntimeDao.delete(oldContests);
            LOGGER.info("clear old data");
        }
        // 更新数据
        for (Contest contest : contestList) {
            contestRuntimeDao.createOrUpdate(contest);
            LOGGER.info("update data to database");
        }
    }
}
