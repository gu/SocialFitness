package wigwam.socialfitness;

import android.app.Activity;
import android.opengl.ETC1;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by freddy on 11/15/15.
 */
public class ManualInput extends Activity {

    EditText etWeight, etHeight, etHR, etSteps, etDistance, etCalories, etSleep, etMile,
        etPushups, etSquats, etSitups, etPlank, etPullups, etOneSquats;

    Button btSubmit;

    RequestQueue queue;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        id = getIntent().getIntExtra("id", 0);

        etWeight = (EditText) findViewById(R.id.etWeight);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etHR = (EditText) findViewById(R.id.etHR);
        etSteps = (EditText) findViewById(R.id.etSteps);
        etDistance = (EditText) findViewById(R.id.etDistance);
        etCalories = (EditText) findViewById(R.id.etCalories);
        etSleep = (EditText) findViewById(R.id.etSleep);
        etMile = (EditText) findViewById(R.id.etMile);
        etPushups = (EditText) findViewById(R.id.etPushups);
        etSquats = (EditText) findViewById(R.id.etSquats);
        etSitups = (EditText) findViewById(R.id.etSitups);
        etPlank = (EditText) findViewById(R.id.etPlank);
        etPullups = (EditText) findViewById(R.id.etPullups);
        etOneSquats = (EditText) findViewById(R.id.etOneSquats);

        btSubmit = (Button) findViewById(R.id.btManual);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSnapshot();
            }
        });

        queue = Volley.newRequestQueue(getApplicationContext());

    }

    public void addSnapshot() {
        String url = "http://tmoore.casavant.org/db/add_snapshot.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("ADSGASDG", response);
                    setResult(Activity.RESULT_OK);
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ASDFASDF", error.getMessage());
                    Toast.makeText(ManualInput.this, "Error Submitting Data.", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String,String> getParams() {
                    Map<String,String> params = new HashMap<>();

                    params.put("pid", String.valueOf(id));
                    if (etWeight.getText().length() > 0) {params.put("weight", etWeight.getText().toString());}
                    if (etHeight.getText().length() > 0) {params.put("height", etHeight.getText().toString());}
                    if (etHR.getText().length() > 0) {params.put("restinghr", etHR.getText().toString());}
                    if (etSteps.getText().length() > 0) {params.put("steps", etSteps.getText().toString());}
                    if (etDistance.getText().length() > 0) {params.put("distance", etDistance.getText().toString());}
                    if (etCalories.getText().length() > 0) {params.put("calories", etCalories.getText().toString());}
                    if (etSleep.getText().length() > 0) {params.put("sleep", etSleep.getText().toString());}
                    if (etMile.getText().length() > 0) {params.put("mile", etMile.getText().toString());}
                    if (etPushups.getText().length() > 0) {params.put("pushups", etPushups.getText().toString());}
                    if (etSquats.getText().length() > 0) {params.put("squats", etSquats.getText().toString());}
                    if (etSitups.getText().length() > 0) {params.put("situps", etSitups.getText().toString());}
                    if (etPlank.getText().length() > 0) {params.put("plank", etPlank.getText().toString());}
                    if (etPullups.getText().length() > 0) {params.put("pullup", etPullups.getText().toString());}
                    if (etOneSquats.getText().length() > 0) {params.put("onelegsquats", etOneSquats.getText().toString());}

                    return params;
                }
            };
        queue.add(stringRequest);

    }
}

