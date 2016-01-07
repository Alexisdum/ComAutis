package com.lpiem_lyon1.comautis.Database;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lpiem_lyon1.comautis.Database.Table.ChildTable;
import com.lpiem_lyon1.comautis.Database.Table.FolderTable;
import com.lpiem_lyon1.comautis.Database.Table.PageTable;
import com.lpiem_lyon1.comautis.Database.Table.PictureTable;
import com.lpiem_lyon1.comautis.Models.Child;
import com.lpiem_lyon1.comautis.Models.Folder;
import com.lpiem_lyon1.comautis.Models.Page;
import com.lpiem_lyon1.comautis.Models.Picture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexislp on 07/01/16.
 */
public class LocalDataBase implements ILocalDataBase {

    private static final String TAG = LocalDataBase.class.getSimpleName();

    private SQLiteDatabase mSQLiteDatabase;
    private SharedPreferences mPreferences;

    public LocalDataBase(SQLiteDatabase SQLiteDatabase, SharedPreferences preferences) {
        mSQLiteDatabase = SQLiteDatabase;
        mPreferences = preferences;
    }

    //region Request
    @Override
    public void requestChild(RequestCallback callback) {
        Cursor cursor = mSQLiteDatabase.query(ChildTable.TABLE_NAME, null, null, null, null, null, null, null);
        List<Child> childList = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Child child = new ChildTable().fromCursor(cursor);
                childList.add(child);
                cursor.close();
                return;
            }
        }
        cursor.close();
        if (callback != null)
            callback.onResult(childList);
    }

    @Override
    public void requestChildById(String id, RequestCallback callback) {
        if(id != "" && id != null) {
            Cursor cursor = mSQLiteDatabase.query(ChildTable.TABLE_NAME, null, ChildTable.KEY_ID + "=?", new String[]{id}, null, null, null, null);
            List<Child> childList = new ArrayList<>();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    Child child = new ChildTable().fromCursor(cursor);
                    childList.add(child);
                    cursor.close();
                    return;
                }
            }
            cursor.close();
            if (callback != null)
                callback.onResult(childList);
        }
        if (callback != null) {
            callback.onError(new IllegalArgumentException("id is null"));
        }
    }

    @Override
    public void requestChildByName(String name, RequestCallback callback) {
        if(name != "" && name != null) {
            Cursor cursor = mSQLiteDatabase.query(ChildTable.TABLE_NAME, null, ChildTable.KEY_NAME + "=?", new String[]{name}, null, null, null, null);
            List<Child> childList = new ArrayList<>();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    Child child = new ChildTable().fromCursor(cursor);
                    childList.add(child);
                    cursor.close();
                    return;
                }
            }
            cursor.close();
            if (callback != null)
                callback.onResult(childList);
        }
        if (callback != null) {
            callback.onError(new IllegalArgumentException("name is null"));
        }
    }

    @Override
    public void requestPicture(RequestCallback callback) {
        Cursor cursor = mSQLiteDatabase.query(PictureTable.TABLE_NAME, null, null, null, null, null, null, null);
        List<Picture> PictureList = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Picture picture = new PictureTable().fromCursor(cursor);
                PictureList.add(picture);
                cursor.close();
                return;
            }
        }
        cursor.close();
        if (callback != null)
            callback.onResult(PictureList);
    }

    @Override
    public void requestPictureById(String id, RequestCallback callback) {
        if(id != "" && id != null) {
            Cursor cursor = mSQLiteDatabase.query(PictureTable.TABLE_NAME, null, PictureTable.KEY_ID + "=?", new String[]{id}, null, null, null, null);
            List<Picture> pictureList = new ArrayList<>();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    Picture picture = new PictureTable().fromCursor(cursor);
                    pictureList.add(picture);
                    cursor.close();
                    return;
                }
            }
            cursor.close();
            if (callback != null)
                callback.onResult(pictureList);
        }
        if (callback != null) {
            callback.onError(new IllegalArgumentException("id is null"));
        }
    }

    @Override
    public void requestPictureByName(String name, RequestCallback callback) {
        if(name != "" && name != null) {
            Cursor cursor = mSQLiteDatabase.query(PictureTable.TABLE_NAME, null, PictureTable.KEY_NAME + "=?", new String[]{name}, null, null, null, null);
            List<Picture> pictureList = new ArrayList<>();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    Picture picture = new PictureTable().fromCursor(cursor);
                    pictureList.add(picture);
                    cursor.close();
                    return;
                }
            }
            cursor.close();
            if (callback != null)
                callback.onResult(pictureList);
        }
        if (callback != null) {
            callback.onError(new IllegalArgumentException("name is null"));
        }
    }

    @Override
    public void requestPictureByFolder(String folderId, RequestCallback callback) {
        if(folderId != "" && folderId != null) {
            Cursor cursor = mSQLiteDatabase.query(PictureTable.TABLE_NAME, null, PictureTable.KEY_FOLDER_ID + "=?", new String[]{folderId}, null, null, null, null);
            List<Picture> pictureList = new ArrayList<>();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    Picture picture = new PictureTable().fromCursor(cursor);
                    pictureList.add(picture);
                    cursor.close();
                    return;
                }
            }
            cursor.close();
            if (callback != null)
                callback.onResult(pictureList);
        }
        if (callback != null) {
            callback.onError(new IllegalArgumentException("folder is null"));
        }
    }

    @Override
    public void requestFolder(RequestCallback callback) {
        Cursor cursor = mSQLiteDatabase.query(FolderTable.TABLE_NAME, null, null, null, null, null, null, null);
        List<Folder> FolderList = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Folder folder = new FolderTable().fromCursor(cursor);
                FolderList.add(folder);
                cursor.close();
                return;
            }
        }
        cursor.close();
        if (callback != null)
            callback.onResult(FolderList);
    }

    @Override
    public void requestFolderById(String id, RequestCallback callback) {
        if(id != "" && id != null) {
            Cursor cursor = mSQLiteDatabase.query(FolderTable.TABLE_NAME, null, FolderTable.KEY_ID + "=?", new String[]{id}, null, null, null, null);
            List<Folder> folderList = new ArrayList<>();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    Folder folder = new FolderTable().fromCursor(cursor);
                    folderList.add(folder);
                    cursor.close();
                    return;
                }
            }
            cursor.close();
            if (callback != null)
                callback.onResult(folderList);
        }
        if (callback != null) {
            callback.onError(new IllegalArgumentException("id is null"));
        }
    }

    @Override
    public void requestFolderByName(String name, RequestCallback callback) {
        if(name != "" && name != null) {
            Cursor cursor = mSQLiteDatabase.query(FolderTable.TABLE_NAME, null, FolderTable.KEY_NAME + "=?", new String[]{name}, null, null, null, null);
            List<Folder> folderList = new ArrayList<>();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    Folder folder = new FolderTable().fromCursor(cursor);
                    folderList.add(folder);
                    cursor.close();
                    return;
                }
            }
            cursor.close();
            if (callback != null)
                callback.onResult(folderList);
        }
        if (callback != null) {
            callback.onError(new IllegalArgumentException("name is null"));
        }
    }

    @Override
    public void requestFolderByFolder(String folderId, RequestCallback callback) {
        if(folderId != "" && folderId != null) {
            Cursor cursor = mSQLiteDatabase.query(FolderTable.TABLE_NAME, null, FolderTable.KEY_ID + "=?", new String[]{folderId}, null, null, null, null);
            List<Folder> folderList = new ArrayList<>();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    Folder folder = new FolderTable().fromCursor(cursor);
                    folderList.add(folder);
                    cursor.close();
                    return;
                }
            }
            cursor.close();
            if (callback != null)
                callback.onResult(folderList);
        }
        if (callback != null) {
            callback.onError(new IllegalArgumentException("name is null"));
        }
    }

    @Override
    public void requestPage(RequestCallback callback) {
        Cursor cursor = mSQLiteDatabase.query(PageTable.TABLE_NAME, null, null, null, null, null, null, null);
        List<Page> PageList = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Page page = new PageTable().fromCursor(cursor);
                PageList.add(page);
                cursor.close();
                return;
            }
        }
        cursor.close();
        if (callback != null)
            callback.onResult(PageList);
    }

    @Override
    public void requestPageById(String id, RequestCallback callback) {
        if(id != "" && id != null) {
            Cursor cursor = mSQLiteDatabase.query(PageTable.TABLE_NAME, null, PageTable.KEY_ID + "=?", new String[]{id}, null, null, null, null);
            List<Page> pageList = new ArrayList<>();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    Page page = new PageTable().fromCursor(cursor);
                    pageList.add(page);
                    cursor.close();
                    return;
                }
            }
            cursor.close();
            if (callback != null)
                callback.onResult(pageList);
        }
        if (callback != null) {
            callback.onError(new IllegalArgumentException("id is null"));
        }
    }

    @Override
    public void requestPageByName(String name, RequestCallback callback) {
        if(name != "" && name != null) {
            Cursor cursor = mSQLiteDatabase.query(PageTable.TABLE_NAME, null, PageTable.KEY_NAME + "=?", new String[]{name}, null, null, null, null);
            List<Page> pageList = new ArrayList<>();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    Page page = new PageTable().fromCursor(cursor);
                    pageList.add(page);
                    cursor.close();
                    return;
                }
            }
            cursor.close();
            if (callback != null)
                callback.onResult(pageList);
        }
        if (callback != null) {
            callback.onError(new IllegalArgumentException("name is null"));
        }
    }

    @Override
    public void requestPageByChild(String childId, RequestCallback callback) {
        if(childId != "" && childId != null) {
            Cursor cursor = mSQLiteDatabase.query(PageTable.TABLE_NAME, null, PageTable.KEY_CHILD_ID + "=?", new String[]{childId}, null, null, null, null);
            List<Page> pageList = new ArrayList<>();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    Page page = new PageTable().fromCursor(cursor);
                    pageList.add(page);
                    cursor.close();
                    return;
                }
            }
            cursor.close();
            if (callback != null)
                callback.onResult(pageList);
        }
        if (callback != null) {
            callback.onError(new IllegalArgumentException("childId is null"));
        }
    }

    //endregion
}
