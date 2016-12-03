package com.qsxuan.recentcontests.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.qsxuan.recentcontests.R;

/**
 * 关于页面 activity
 *
 * @author qinshaoxuan
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ((TextView) findViewById(R.id.authorView)).setText(R.string.author);

        TextView blog = (TextView) findViewById(R.id.blogView);
        blog.setText(R.string.blog);
        blog.setMovementMethod(LinkMovementMethod.getInstance());

        TextView dataSource = (TextView) findViewById(R.id.dataSource);
        dataSource.setText(R.string.data_source);
        dataSource.setMovementMethod(LinkMovementMethod.getInstance());

        TextView github = (TextView) findViewById(R.id.github);
        github.setText(R.string.GitHub);
        github.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
