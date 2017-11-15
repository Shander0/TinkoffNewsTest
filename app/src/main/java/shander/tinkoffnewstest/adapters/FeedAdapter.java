package shander.tinkoffnewstest.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import shander.tinkoffnewstest.R;
import shander.tinkoffnewstest.entities.feedEntities.Payload;
import shander.tinkoffnewstest.ui.NewsDetailsActivity;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ItemHolder>{

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Payload> feedItems;

    public FeedAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        feedItems = new ArrayList<>();
    }

    public void setFeedItems(List<Payload> items) {
        this.feedItems = items;
        notifyDataSetChanged();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(mLayoutInflater.inflate(R.layout.feed_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        final Payload currentItem = feedItems.get(position);
        holder.setItem(currentItem);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NewsDetailsActivity.class);
                intent.putExtra("newsId", currentItem.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_date)
        TextView itemDate;

        @BindView(R.id.item_text)
        TextView itemText;

        ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setItem(Payload item) {
            itemText.setText(item.getText());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String dateString = formatter.format(new Date(item.getPublicationDate().getMilliseconds()));
            itemDate.setText(dateString);
        }
    }
}
