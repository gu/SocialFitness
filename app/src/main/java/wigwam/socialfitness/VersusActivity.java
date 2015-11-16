package wigwam.socialfitness;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by freddy on 11/15/15.
 */
public class VersusActivity extends Activity {

    ListView you, world;

    int id;

    RequestQueue queue;

    String[] values = new String[] {"Weight", "Height", "Heart Rate", "Steps", "Distance", "Calories", "Sleep", "Mile Time", "Pushups", "Squats", "Situps", "Plank", "Pullup", "One Leg Squats"};
    List<String> keys = Arrays.asList(values);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_versus);

        queue = Volley.newRequestQueue(this);

        id = getIntent().getIntExtra("id", 0);

        you = (ListView) findViewById(R.id.listView);
        world = (ListView) findViewById(R.id.listView2);

        getUserData();
        getWorldData();
    }

    public void getUserData() {
        String url = "http://tmoore.casavant.org/db/get_person.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("ASDF", response);
                        String[] attrs = response.split(",");
                        List<String> values = new ArrayList<String>(Arrays.asList(attrs));
                        values.remove(0);
                        values.remove(0);
                        values.remove(0);
                        values.remove(0);
                        values.remove(0);
                        values.remove(0);
                        values.remove(0);
                        values.remove(0);
                        values = values.subList(0, values.size()-1);
                        ArrayAdapter adapter =
                                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, values);
                        you.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ASDFASDF", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("pid", String.valueOf(id));
                return params;
            }
        };
        queue.add(stringRequest);

    }

    public void getWorldData() {
        String url = "http://tmoore.casavant.org/db/custom_command.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("ASDF", response);
                        String[] attrs = response.split(",");
                        List<String> val = new ArrayList<>(Arrays.asList(attrs));
                        for (int i = 0; i < val.size(); i++) {
                            int index = val.get(i).indexOf(".");
                            val.set(i, val.get(i).substring(0, index));
                        }
                        ArrayAdapter adapter =
                                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, val);
                        world.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ASDFASDF", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("cmd", "get_averages");
                return params;
            }
        };
        queue.add(stringRequest);

    }

}
