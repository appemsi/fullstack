package ma.emsi.contact_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {


    String table_name="contacts";// City class java => cities
    String id ="id";
    String name="name";
    String email="email";
    String phone="phone";
    String note="note";
    String image ="image";
    String create="created_at";
    String update="updated_at";
    public DbHelper(@Nullable Context context, int version) {
        super(context, "contact_6_db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+table_name+" (" +
                id +" integer primary key autoincrement," +
                name+" text ,"+
                phone+" text ,"+
                email+" text ,"+
                note+" text ,"+
                image+" text ,"+
                create+" text ,"+
                update+" text "+
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("drop table if exists "+table_name);
            onCreate(db);
    }



    public long insert(String name,String phone,String email,String note,String image,String create,String update)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(this.name,name);
        cv.put(this.phone,phone);
        cv.put(this.email,email);
        cv.put(this.note,note);
        cv.put(this.image,image);
        cv.put(this.create,create);
        cv.put(this.update,update);
        long id=db.insert(table_name,null,cv);
        db.close();
        return id;
    }

    public ArrayList<ContactModel> getAllContacts() {
        //create arrayList
        ArrayList<ContactModel> arrayList = new ArrayList<>();
        //sql command query
        String selectQuery = "SELECT * FROM "+table_name;

        //get readable db
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all record and add to list
        if (cursor.moveToFirst()){
            do {
                ContactModel modelContact = new ContactModel(
                        // only id is integer type
                        cursor.getInt(cursor.getColumnIndexOrThrow(id)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(name)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(phone)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(image))
                );
                arrayList.add(modelContact);
            }while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }
}
