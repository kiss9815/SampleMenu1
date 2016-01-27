package com.example.tacademy.samplemenu1;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.ShareActionProvider;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView messageView;
    ImageView iconView;
    ActionMode mActionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageView = (TextView)findViewById(R.id.text_message);
        iconView = (ImageView)findViewById(R.id.image_icon);

        Button btn = (Button)findViewById(R.id.btn_action_mode);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionMode =startSupportActionMode(new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                        getMenuInflater().inflate(R.menu.action_mode_menu, menu);
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_am_1:
                            case R.id.menu_am_2:
                            case R.id.menu_am_3:
                                Toast.makeText(MainActivity.this, "ActionMode Menu Click", Toast.LENGTH_SHORT).show();
                                mode.finish();
                                return true;
                        }
                        return false;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {

                    }
                });

            }
        });
        btn = (Button)findViewById(R.id.btn_popup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_pm_1:
                            case R.id.menu_pm_2:
                            case R.id.menu_pm_3:
                                Toast.makeText(MainActivity.this, "Popup Menu Click", Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {
                        Toast.makeText(MainActivity.this, "Popup Menu Dismiss", Toast.LENGTH_SHORT).show();
                    }
                });
                popupMenu.show();
            }
        });
        registerForContextMenu(messageView);
        registerForContextMenu(iconView);

    }
    ShareActionProvider mActionProvider;

    EditText keywordView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.menu_action, menu);
        MenuItem item = menu.findItem(R.id.menu_action1);
        mActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("image/*");
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "test....");
        mActionProvider.setShareIntent(intent);
/*
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
*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_menu1 :
            case R.id.menu_menu2 :
            case R.id.menu_menu3 :
                item.setChecked(!item.isChecked());
            case R.id.sub_menu_1 :
            case R.id.sub_menu_2 :
                Toast.makeText(this, "menu selected " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_action1 :
//            case R.id.menu_action2 :
                Toast.makeText(this, "action menu selected " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { ///context menu
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.text_message) {
            getMenuInflater().inflate(R.menu.context_text, menu);
        } else if (v.getId() == R.id.image_icon) {
            getMenuInflater().inflate(R.menu.context_icon, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_tm_1 :
            case R.id.menu_im_1 :
                Toast.makeText(this, "context menu click", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }


}
