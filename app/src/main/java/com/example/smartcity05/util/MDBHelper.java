package com.example.smartcity05.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartcity05.bean.NewsBean;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class MDBHelper extends OrmLiteSqliteOpenHelper {
    private static MDBHelper mdbHelper;
    private Dao<NewsBean, Integer> newsBeanDao;

    public MDBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, NewsBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }

    public static MDBHelper getInstance(Context context) {
        if (mdbHelper == null) {
            mdbHelper = new MDBHelper(context, "db.db", null, 1);
        }
        return mdbHelper;
    }

    public Dao<NewsBean, Integer> getNewsBeanDao() {
        if (newsBeanDao == null) {
            try {
                newsBeanDao = getDao(NewsBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return newsBeanDao;
    }
}
