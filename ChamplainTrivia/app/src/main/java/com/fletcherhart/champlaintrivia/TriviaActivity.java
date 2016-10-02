package com.fletcherhart.champlaintrivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class TriviaActivity extends AppCompatActivity {

    private Button mButton;
    private static final int REQUEST_CODE_QUESTION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        String category[] = { getResources().getString(R.string.cat_landmark),
                getResources().getString(R.string.cat_faculty),
                getResources().getString(R.string.cat_majors)
        };

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, category);

        ListView listView = (ListView) findViewById(R.id.cat_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                Intent i = QuestionActivity.newIntent(TriviaActivity.this, position);
                startActivity(i);
            }
        });

    }

}
