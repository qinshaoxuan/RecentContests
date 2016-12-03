package com.qsxuan.recentcontests.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qsxuan.recentcontests.R;
import com.qsxuan.recentcontests.adpter.ContestAdapter;
import com.qsxuan.recentcontests.entity.ContestEntity;
import com.qsxuan.recentcontests.model.Contest;
import com.qsxuan.recentcontests.service.IContestService;
import com.qsxuan.recentcontests.service.impl.ContestServiceImpl;

import java.util.List;

/**
 * @author qinshaoxuan
 */
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ContestAdapter contestAdapter;
    private IContestService contestService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contestService = new ContestServiceImpl(this);
        setListView(R.id.list_entry);
    }

    /**
     * 创建线程获取数据，并设置点击事件
     * @param id
     */
    private void setListView(int id) {
        Thread thread = new Thread(new NetworkTask());
        thread.start();

        listView = (ListView) findViewById(id);
        contestAdapter = new ContestAdapter(this);
        listView.setAdapter(contestAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ContestEntity contestEntity = (ContestEntity) view.getTag();
                Uri uri = Uri.parse(contestEntity.link);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    /**
     * 设置适配器数据
     */
    private void setContestAdapterData() {
        List<Contest> data = contestService.getContestList();
        contestAdapter.setData(data);
    }

    class NetworkTask implements Runnable {
        @Override
        public void run() {
            setContestAdapterData();
            handler.sendEmptyMessage(1);
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            contestAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        intent.setClass(this, AboutActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
