package org.androidtown.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    TextView textView;
    ListView listView;
    String databaseName;
    String tableName;
    SQLiteDatabase database;
    CustomerDatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        textView = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.listView);
    }

    public void onButton1Clicked(View v){
        databaseName = editText.getText().toString();
        try {
            //database = openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);

            databaseHelper = new CustomerDatabaseHelper(getApplicationContext(), databaseName, null, 3);
            database = databaseHelper.getWritableDatabase();

            println("데이터베이스를 열었습니다. : " + databaseName);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onButton2Clicked(View v){
        tableName = editText2.getText().toString();
        try {
            if(database != null){
                database.execSQL("CREATE TABLE if not exists " + tableName + "("
                        + "_id integer PRIMARY KEY autoincrement, "
                        + "name text, "
                        + "age integer, "
                        + "mobile text"
                        + ")");
                println("데이블을 만들었습니다. : " + tableName);
            } else {
                println("데이터베이스를 먼저 열어야 합니다.");
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void createTable(SQLiteDatabase db){
        tableName = editText2.getText().toString();
        try {
            if(db != null){
                db.execSQL("CREATE TABLE if not exists " + tableName + "("
                        + "_id integer PRIMARY KEY autoincrement, "
                        + "name text, "
                        + "age integer, "
                        + "mobile text"
                        + ")");
                println("데이블을 만들었습니다. : " + tableName);
            } else {
                println("데이터베이스를 먼저 열어야 합니다.");
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void chageTable(SQLiteDatabase db){
        try {
            if(db != null){
                db.execSQL("CREATE TABLE if not exists " + "PRODUCT " + "("
                        + "_id integer PRIMARY KEY autoincrement, "
                        + "name text, "
                        + "price integer"
                        + ")");
                println("데이블을 추가로 만들었습니다. : PRODUCT" );
            } else {
                println("데이터베이스를 먼저 열어야 합니다.");
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public  void onButton3Clicked(View v){
        try {
            if (tableName == null){
                tableName = editText2.getText().toString();
            }
            if(database != null){
                database.execSQL("INSERT INTO " + tableName + "(name, age, mobile) VALUES "
                        + "('소녀시대', 20, '010-1000-1000')");

                println("데이터를 추가했습니다. : ");
            } else {
                println("데이터베이스를 먼저 열어야 합니다.");
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public  void onButton4Clicked(View v){
        try {
            if (tableName == null){
                tableName = editText2.getText().toString();
            }
            if(database != null){
                Cursor cursor = database.rawQuery("SELECT _id, name, age, mobile FROM " + tableName, null);

                startManagingCursor(cursor);
                String[] columns = new String[] {"name", "age", "mobile"};
                int[] to = new int[] {R.id.textView2, R.id.textView3, R.id.textView4};

                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.customer_item, cursor, columns, to);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                int count = cursor.getCount();
                println("결과 레코드의 개수 : " + count);

                for(int i = 0; i < count; i++){
                    cursor.moveToNext();
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    int age = cursor.getInt(2);
                    String mobile = cursor.getString(3);
                    println("레코드 #" + i + " : " + name + ", " + age + ", " + mobile);
                }
                cursor.close();

                println("데이터를 조회했습니다. : ");
            } else {
                println("데이터베이스를 먼저 열어야 합니다.");
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void println(String data){
        textView.append(data + "\n");
    }

    class CustomerDatabaseHelper extends SQLiteOpenHelper {
        public CustomerDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public synchronized void close() {
            super.close();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Toast.makeText(MainActivity.this, "Helper의 onCreate() 호출됨.", Toast.LENGTH_SHORT).show();
            createTable(db);
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
            Toast.makeText(MainActivity.this, "Helper의 onOpen() 호출됨.", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Toast.makeText(MainActivity.this, "Helper의 onUpdate() 호출됨. :" + oldVersion + " -> " + newVersion, Toast.LENGTH_SHORT).show();
            chageTable(db);
        }
    }
}
