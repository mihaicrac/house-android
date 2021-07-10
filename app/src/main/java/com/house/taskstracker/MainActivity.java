package com.house.taskstracker;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((view) ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()

        );

//        FirebaseInstanceId.getInstance().getInstanceId()
//                .addOnCompleteListener(task -> {
//                    if (!task.isSuccessful()) {
//                        Log.w("MyActivity", "getInstanceId failed", task.getException());
//                        return;
//                    }
//
//                    // Get new Instance ID token
//                    String token = task.getResult().getToken();
//
//                    // Log and toast
//                    Log.d("MyActivity", token);
//                    Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
//
//                    sendFirebaseToken(token);
//
//                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void sendFirebaseToken(String token) {

        JSONObject tokenReq = new JSONObject();
        try {
            tokenReq.put("token", token);
            tokenReq.put("userId", "123e4567-e89b-12d3-a456-426655440001");
        } catch (JSONException e) {
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://172.20.0.1:8083/notifications/tokens", tokenReq,
                (response -> {
                    Log.d("MyActivity", response.toString());
                }), (error -> {
            Log.d("MyActivity", error.toString());
        }));

        Log.d("MyActivity", tokenReq.toString());
        queue.add(jsonObjectRequest);

    }

}
