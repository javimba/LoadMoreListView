package com.osblog.loadmoreview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.osblog.library.LoadMoreListView;
import com.osblog.loadmoreview.adapter.DataAdapter;
import com.osblog.loadmoreview.factory.DataFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LoadMoreListView loadMoreListView;

    private boolean isLoad = false;
    private boolean hasMore = true;
    private List<String> dataList;

    private DataAdapter dataAdapter;

    //每次获取数据的量
    private int limit = 10;

    private int refreshTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化数据工厂
        DataFactory.init();

        loadMoreListView = (LoadMoreListView) findViewById(R.id.loadmore_listview);
        loadMoreListView.setOnLastItemVisibleListener(new LoadMoreListView.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                if (isLoad || !hasMore) {
                    loadMoreListView.setFooter(LoadMoreListView.Mode.NOMORE);
                    return;
                }
                isLoad = true;
                getData();
                loadMoreListView.setFooter(LoadMoreListView.Mode.LOAD);
            }
        });

        dataAdapter = new DataAdapter(this);
        loadMoreListView.setAdapter(dataAdapter);

        getData();
    }

    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> dataList = DataFactory.getData(0 + limit * refreshTime, limit);
                dataAdapter.addData(dataList);
                refreshTime = refreshTime + 1;
                isLoad = false;
                //取数据3次不再有时数据。
                if (refreshTime == 3) {
                    hasMore = false;
                }
            }
        }, 3000);
    }
}
