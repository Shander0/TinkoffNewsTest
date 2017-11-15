package shander.tinkoffnewstest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import shander.tinkoffnewstest.entities.Date;
import shander.tinkoffnewstest.entities.feedEntities.Payload;
import shander.tinkoffnewstest.entities.newsDetailsEntities.SinglePayload;
import shander.tinkoffnewstest.entities.newsDetailsEntities.Title;
import shander.tinkoffnewstest.interfaces.IContract;
import shander.tinkoffnewstest.utils.IConverter;

public class DBHelper extends SQLiteOpenHelper implements IContract.IDataBase {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_FEED + "(" + FEED_ID +
                " integer primary key not null," + FEED_NAME + " text," +
                FEED_TEXT + " text," + FEED_DATE + " long" + ");");

        db.execSQL("create table " + TABLE_NEWS_DETAILS + "(" + NEWS_ID +
                " integer primary key not null," + NEWS_TITLE + " text," +
                NEWS_CONTENT + " text," + NEWS_CREATION_DATE + " long," +
                NEWS_MODIFY_DATE + " long" + ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void refreshDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEED);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS_DETAILS);

        onCreate(db);
    }


    public List<Payload> getFeed() {
        SQLiteDatabase db = this.getWritableDatabase();
        return CURSOR_TO_FEED_LIST.convert(db.query(TABLE_FEED, null, null, null, null, null, FEED_DATE + " DESC"));
    }

    public SinglePayload getNewsDetails(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = NEWS_ID + "=" + String.valueOf(id);
        return CURSOR_TO_SINGLE_NEWS_CONVERTER.convert(db.query(TABLE_NEWS_DETAILS, null, query, null, null, null, null));
    }

    public void writeFeed(List<Payload> feedList) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (Payload payload : feedList) {
            db.insertWithOnConflict(TABLE_FEED, null, FEED_TO_CV_CONVERTER.convert(payload), SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    public void writeNewsDetails(SinglePayload payload) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insertWithOnConflict(TABLE_NEWS_DETAILS, null, SINGLE_NEWS_TO_CV_CONVERTER.convert(payload), SQLiteDatabase.CONFLICT_REPLACE);
    }

    private IConverter<Payload, ContentValues> FEED_TO_CV_CONVERTER = new IConverter<Payload, ContentValues>() {
        @Override
        public ContentValues convert(Payload src) {
            ContentValues cv = new ContentValues();
            cv.put(FEED_ID, src.getId());
            cv.put(FEED_NAME, src.getName());
            cv.put(FEED_DATE, src.getPublicationDate().getMilliseconds());
            cv.put(FEED_TEXT, src.getText());
            return cv;
        }
    };

    private IConverter<Cursor, List<Payload>> CURSOR_TO_FEED_LIST = new IConverter<Cursor, List<Payload>>() {
        @Override
        public List<Payload> convert(Cursor src) {
            List<Payload> feedList = new ArrayList<>();
            for (int i = 0; i < src.getCount(); i++) {
                src.moveToPosition(i);
                Payload feedItem = new Payload();
                feedItem.setId(src.getInt(src.getColumnIndex(FEED_ID)));
                feedItem.setName(src.getString(src.getColumnIndex(FEED_NAME)));
                feedItem.setText(src.getString(src.getColumnIndex(FEED_TEXT)));
                Date pubDate = new Date();
                pubDate.setMilliseconds(src.getLong(src.getColumnIndex(FEED_DATE)));
                feedItem.setPublicationDate(pubDate);
                feedList.add(feedItem);
            }
            return feedList;
        }
    };

    private IConverter<SinglePayload, ContentValues> SINGLE_NEWS_TO_CV_CONVERTER = new IConverter<SinglePayload, ContentValues>() {
        @Override
        public ContentValues convert(SinglePayload src) {
            ContentValues cv = new ContentValues();
            cv.put(NEWS_ID, src.getTitle().getId());
            cv.put(NEWS_TITLE, src.getTitle().getText());
            cv.put(NEWS_CREATION_DATE, src.getCreationDate().getMilliseconds());
            cv.put(NEWS_MODIFY_DATE, src.getLastModificationDate().getMilliseconds());
            cv.put(NEWS_CONTENT, src.getContent());
            return cv;
        }
    };

    private IConverter<Cursor, SinglePayload> CURSOR_TO_SINGLE_NEWS_CONVERTER = new IConverter<Cursor, SinglePayload>() {
        @Override
        public SinglePayload convert(Cursor src) {
            src.moveToFirst();
            SinglePayload singleNews = new SinglePayload();
            Date creationDate = new Date();
            creationDate.setMilliseconds(src.getLong(src.getColumnIndex(NEWS_CREATION_DATE)));
            Date modifyDate = new Date();
            modifyDate.setMilliseconds(src.getLong(src.getColumnIndex(NEWS_MODIFY_DATE)));
            Title title = new Title();
            title.setId(src.getInt(src.getColumnIndex(NEWS_ID)));
            title.setText(src.getString(src.getColumnIndex(NEWS_TITLE)));
            singleNews.setCreationDate(creationDate);
            singleNews.setLastModificationDate(modifyDate);
            singleNews.setTitle(title);
            singleNews.setContent(src.getString(src.getColumnIndex(NEWS_CONTENT)));
            return singleNews;
        }
    };
}
