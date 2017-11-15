package shander.tinkoffnewstest.ui;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shander.tinkoffnewstest.R;
import shander.tinkoffnewstest.entities.newsDetailsEntities.SinglePayload;
import shander.tinkoffnewstest.loaders.NewsDetailsLoader;

public class NewsDetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<SinglePayload>{

    @BindView(R.id.tv_news_title)
    TextView newsTitle;

    @BindView(R.id.tv_news_content)
    TextView newsContent;

    @BindView(R.id.progress)
    ProgressBar progress;

    private Loader<SinglePayload> loader;
    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this);
        newsContent.setMovementMethod(new ScrollingMovementMethod());
        Integer id = getIntent().getIntExtra("newsId", 0);
        b = new Bundle();
        b.putInt("newsId", id);
        loader = getSupportLoaderManager().initLoader(2, b, this);
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public Loader<SinglePayload> onCreateLoader(int id, Bundle args) {
        return new NewsDetailsLoader(this, b);
    }

    @Override
    public void onLoadFinished(Loader<SinglePayload> loader, SinglePayload data) {
        newsTitle.setText(data.getTitle().getText());
        newsContent.setText(Html.fromHtml(data.getContent()));
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<SinglePayload> loader) {

    }

}
