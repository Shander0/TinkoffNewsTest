package shander.tinkoffnewstest.interfaces;

public interface IContract {

    interface INetwork {
        String BASE_URL = "https://api.tinkoff.ru/v1/";
        String FEED_POINT = "news";
        String DETAILS_POINT = "news_content?id=";
    }

    interface IDataBase {
        String DB_NAME = "tnewsdb";
        int DB_VERSION = 1;
        String TABLE_FEED = "feed";
        String TABLE_NEWS_DETAILS = "news";

        String FEED_ID = "_id";
        String FEED_NAME = "feedname";
        String FEED_TEXT = "feedtext";
        String FEED_DATE = "feeddate";

        String NEWS_ID = "_id";
        String NEWS_TITLE = "title";
        String NEWS_CONTENT = "content";
        String NEWS_CREATION_DATE = "creationdate";
        String NEWS_MODIFY_DATE = "modifydate";
    }
}
