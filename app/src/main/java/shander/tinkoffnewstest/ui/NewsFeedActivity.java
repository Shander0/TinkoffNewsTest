package shander.tinkoffnewstest.ui;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shander.tinkoffnewstest.R;
import shander.tinkoffnewstest.adapters.FeedAdapter;
import shander.tinkoffnewstest.entities.feedEntities.Payload;
import shander.tinkoffnewstest.loaders.FeedLoader;
import shander.tinkoffnewstest.utils.ServiceManager;

public class NewsFeedActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Payload>>, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.swipe_feed)
    SwipeRefreshLayout swipeFeed;

    @BindView(R.id.recycler_feed)
    RecyclerView recyclerFeed;

    private FeedAdapter adapter;
    private Loader<List<Payload>> loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        ServiceManager sm = new ServiceManager(this);
        if (!sm.isNetworkAvailable()) {
            Intent intent = new Intent(NewsFeedActivity.this, NoInternetActivity.class);
            startActivity(intent);
            finish();
        }
        ButterKnife.bind(this);
        swipeFeed.setOnRefreshListener(this);
        swipeFeed.setRefreshing(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerFeed.setLayoutManager(llm);
        adapter = new FeedAdapter(this);
        recyclerFeed.setAdapter(adapter);
        loader = getSupportLoaderManager().initLoader(1, null, this);
    }

    @Override
    public void onRefresh() {
        loader.forceLoad();
    }

    @Override
    public Loader<List<Payload>> onCreateLoader(int id, Bundle args) {
        loader = new FeedLoader(this);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<Payload>> loader, List<Payload> data) {
        adapter.setFeedItems(data);
        swipeFeed.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<List<Payload>> loader) {

    }

}
