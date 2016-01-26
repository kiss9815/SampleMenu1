package com.example.tacademy.samplemenu1;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    EditText keywordView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action,menu);
        MenuItem item = menu.findItem(R.id.menu_action3);
        View view = MenuItemCompat.getActionView(item);
        keywordView = (EditText)view.findViewById(R.id.edit_keyword);
        Button btn = (Button)view.findViewById(R.id.btn_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "keyword : " + keywordView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        MenuItemCompat.expandActionView(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_menu1 :
            case R.id.menu_menu2 :
            case R.id.menu_menu3 :
                item.setChecked(!item.isChecked());
            case R.id.sub_menu_1 :
            case R.id.sub_menu_2 :
                Toast.makeText(this, "menu selected " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_action1 :
            case R.id.menu_action2 :
                Toast.makeText(this, "action menu selected " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
        }



        return super.onOptionsItemSelected(item);
    }
}
