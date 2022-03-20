package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainPage extends AppCompatActivity {

    ImageView menu,wallet;
    EditText searchbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        wallet=toolbar.findViewById(R.id.toolbar_wallet);
        menu=toolbar.findViewById(R.id.toolbar_menu);
        searchbar=findViewById(R.id.searcbar);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainPage.this,"MENU",Toast.LENGTH_SHORT).show();
            }
        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainPage.this,"Wallet",Toast.LENGTH_SHORT).show();
            }
        });

        searchbar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Toast.makeText(MainPage.this,   searchbar.getText().toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
}