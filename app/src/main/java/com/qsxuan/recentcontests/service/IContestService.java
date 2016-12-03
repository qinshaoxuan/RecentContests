package com.qsxuan.recentcontests.service;

import com.qsxuan.recentcontests.model.Contest;

import java.util.List;

/**
 * Contest 服务层
 *
 * @author qinshaoxuan
 */

public interface IContestService {


    /**
     * 获取数据
     *
     * @return List
     */
    List<Contest> getContestList();
}
