package com.qsxuan.recentcontests.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.qsxuan.recentcontests.R;
import com.qsxuan.recentcontests.entity.ContestEntity;
import com.qsxuan.recentcontests.model.Contest;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配器
 *
 * @author qinshaoxuan
 */

public class ContestAdapter extends BaseAdapter implements ListAdapter {

    private List<Contest> data = new ArrayList<>();

    private LayoutInflater layoutInflater;

    public void setData(List<Contest> data) {
        this.data = data;
    }

    public ContestAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ContestEntity contestEntity;
        if (null == view) {
            contestEntity = new ContestEntity();
            // 设置 view
            view = layoutInflater.inflate(R.layout.list_entry, viewGroup, false);
            contestEntity.oj = (TextView) view.findViewById(R.id.oj);
            contestEntity.name = (TextView) view.findViewById(R.id.name);
            contestEntity.access = (TextView) view.findViewById(R.id.access);
            contestEntity.startTime = (TextView) view.findViewById(R.id.startTime);
            contestEntity.week = (TextView) view.findViewById(R.id.week);
            view.setTag(contestEntity);
        } else {
            contestEntity = (ContestEntity) view.getTag();
        }
        assembleContest(contestEntity, data.get(i));
        return view;
    }

    /**
     * 拼装contestEntity
     *
     * @param contestEntity
     * @param contest
     */
    private void assembleContest(ContestEntity contestEntity, Contest contest) {
        if (null == contestEntity || null == contest) {
            return;
        }
        contestEntity.id = contest.getId();
        contestEntity.oj.setText(contest.getOj());
        contestEntity.name.setText(contest.getName());
        contestEntity.startTime.setText(contest.getStartTime());
        contestEntity.week.setText(contest.getWeek());
        contestEntity.access.setText(contest.getAccess());
        contestEntity.link = contest.getLink();
    }
}
