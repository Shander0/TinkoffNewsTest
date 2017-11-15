package shander.tinkoffnewstest;

import android.app.Application;
import android.content.Context;

import shander.tinkoffnewstest.db.DBHelper;
import shander.tinkoffnewstest.interfaces.IContract;

public class App extends Application implements IContract{

    private DBHelper helper;

    public static App from(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        helper = new DBHelper(this, IDataBase.DB_NAME, null, IDataBase.DB_VERSION);
        helper.refreshDB();
    }

    public DBHelper getBaseHelper() {
        return helper;
    }
}
