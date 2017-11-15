package shander.tinkoffnewstest.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import shander.tinkoffnewstest.App;
import shander.tinkoffnewstest.db.DBHelper;
import shander.tinkoffnewstest.entities.feedEntities.Feed;
import shander.tinkoffnewstest.entities.feedEntities.Payload;
import shander.tinkoffnewstest.interfaces.IContract;

public class FeedLoader extends AsyncTaskLoader<List<Payload>> implements IContract.INetwork {

    public FeedLoader(Context context) {
        super(context);
    }

    @Override
    public void forceLoad() {
        super.forceLoad();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
    }

    @Override
    public void deliverResult(List<Payload> data) {
        super.deliverResult(data);
    }

    @Override
    public List<Payload> loadInBackground() {
        Feed feed;
        DBHelper dbHelper = App.from(this.getContext()).getBaseHelper();
        try {
            String json = IOUtils.toString(new URL(BASE_URL + FEED_POINT));
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            feed = gson.fromJson(json, Feed.class);
            if (feed != null) {
                dbHelper.writeFeed(feed.getPayload());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbHelper.getFeed();
    }
}
