package com.example.framgiababatundefatoyesunday.mymvpfundemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.framgiababatundefatoyesunday.mymvpfundemo.Model.Contact;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * DataBaseHelper
 * Created by ThongDang on 6/1/16.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "Mvp.sqlite";
    private static final int DATABASE_VERSION = 1;
    private Map<String, Dao<?, ?>> daoMap = new HashMap<>();

    public static final Class<?>[] DataBaseClasses = new Class[] {
            Contact.class
    };

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            for (Class obj : DataBaseClasses) {
                TableUtils.createTable(connectionSource, obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
            int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            try {
                for (Class obj : DataBaseClasses) {
                    TableUtils.dropTable(connectionSource, obj, false);
                }
                onCreate(database, connectionSource);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void clearDatabase() {
        for (Class clazz : DataBaseClasses) {
            try {
                TableUtils.clearTable(getConnectionSource(), clazz);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T, ID> Dao<T, ID> getCachedDao(Class<T> clazz) {
        Dao<?, ?> result = daoMap.get(clazz.getName());
        if (result == null) {
            try {
                result = getDao(clazz);
                daoMap.put(clazz.getName(), result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (Dao<T, ID>) result;
    }
}
