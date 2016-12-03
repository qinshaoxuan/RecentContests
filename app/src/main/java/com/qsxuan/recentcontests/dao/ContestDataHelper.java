package com.qsxuan.recentcontests.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.qsxuan.recentcontests.model.Contest;

import java.sql.SQLException;

/**
 * @author qinshaoxuan
 */

public class ContestDataHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "RecentContest.db";
    private static final int DATABASE_VERSION = 1;

    private RuntimeExceptionDao<Contest, Integer> ContestRuntimeDao = null;

    public ContestDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.e(ContestDataHelper.class.getName(), "start create database");
            TableUtils.createTable(connectionSource, Contest.class);
            Log.e(ContestDataHelper.class.getName(), "create database succ");
        } catch (SQLException e) {
            Log.e(ContestDataHelper.class.getName(), "create database fail", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource,
                          int oldVersion,
                          int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Contest.class, true);
            onCreate(database, connectionSource);
            Log.e(ContestDataHelper.class.getName(), "update database succ");
        } catch (SQLException e) {
            Log.e(ContestDataHelper.class.getName(), "update database fail", e);

        }
    }

    /**
     * 获取 ContestDao
     *
     * @return
     */
    public RuntimeExceptionDao<Contest, Integer> getContestDao() {
        if (ContestRuntimeDao == null) {
            ContestRuntimeDao = getRuntimeExceptionDao(Contest.class);
        }
        return ContestRuntimeDao;
    }
}
