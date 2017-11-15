package shander.tinkoffnewstest.loaders;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;

import shander.tinkoffnewstest.App;
import shander.tinkoffnewstest.db.DBHelper;
import shander.tinkoffnewstest.entities.newsDetailsEntities.NewsDetails;
import shander.tinkoffnewstest.entities.newsDetailsEntities.SinglePayload;
import shander.tinkoffnewstest.interfaces.IContract;

public class NewsDetailsLoader extends AsyncTaskLoader<SinglePayload> implements IContract.INetwork {

    private Integer id;

    public NewsDetailsLoader(Context context, Bundle args) {
        super(context);
        if (args != null) {
            id = args.getInt("newsId");
        }
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
    public void deliverResult(SinglePayload data) {
        super.deliverResult(data);
    }

    @Override
    public SinglePayload loadInBackground() {
        NewsDetails newsDetails;
        DBHelper dbHelper = App.from(this.getContext()).getBaseHelper();
        try {
            String json = IOUtils.toString(new URL(BASE_URL + DETAILS_POINT + String.valueOf(id)));
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            newsDetails = gson.fromJson(json, NewsDetails.class);
            if (newsDetails != null) {
                dbHelper.writeNewsDetails(newsDetails.getPayload());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbHelper.getNewsDetails(id);
    }

}
