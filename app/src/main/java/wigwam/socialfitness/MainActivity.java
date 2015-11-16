package wigwam.socialfitness;

import android.app.Activity;
import android.content.Intent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {
    ListView listView;
    RequestQueue queue;

    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12, tv13, tv14;

    int pid;

    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private FloatingActionMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, 0);

        menu = (FloatingActionMenu) findViewById(R.id.menu2);

        menu.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.toggle(true);
            }
        });

        fab1 = (FloatingActionButton) findViewById(R.id.fab12);
        fab2 = (FloatingActionButton) findViewById(R.id.fab22);
        fab3 = (FloatingActionButton) findViewById(R.id.fab32);

        fab1.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);

        tv1 = (TextView) findViewById(R.id.textView3);
        tv2 = (TextView) findViewById(R.id.textView5);
        tv3 = (TextView) findViewById(R.id.textView7);
        tv4 = (TextView) findViewById(R.id.textView9);
        tv5 = (TextView) findViewById(R.id.textView10);
        tv6 = (TextView) findViewById(R.id.textView13);
        tv7 = (TextView) findViewById(R.id.textView14);
        tv8 = (TextView) findViewById(R.id.textView15);
        tv9 = (TextView) findViewById(R.id.textView16);
        tv10 = (TextView) findViewById(R.id.textView17);
        tv11 = (TextView) findViewById(R.id.textView18);
        tv12 = (TextView) findViewById(R.id.textView19);
        tv13 = (TextView) findViewById(R.id.textView20);
        tv14 = (TextView) findViewById(R.id.textView21);

        queue = Volley.newRequestQueue(getApplicationContext());

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab12:
                    Intent intent = new Intent(MainActivity.this, ManualInput.class);
                    intent.putExtra("id", pid);
                    startActivityForResult(intent, 1);
                    break;
                case R.id.fab22:
                    Intent intent2 = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent2);
                    if (menu.isOpened()) {
                        menu.toggle(true);
                    }
                    break;
                case R.id.fab32:
                    Intent intent3 = new Intent(MainActivity.this, VersusActivity.class);
                    intent3.putExtra("id", pid);
                    startActivity(intent3);
                    if (menu.isOpened()) {
                        menu.toggle(true);
                    }
                    break;
            }
        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            pid = data.getIntExtra("pid", 0);
            Log.d("KAKAKKEKFKF", "" + pid);


            String url = "http://tmoore.casavant.org/db/get_person.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("ASDFASDF", response);
                            String[] data = response.split(",");
                            tv1.setText(data[8]);
                            tv2.setText(data[9]);
                            tv3.setText(data[10]);
                            tv4.setText(data[11]);
                            tv5.setText(data[12]);
                            tv6.setText(data[13]);
                            tv7.setText(data[14]);
                            tv8.setText(data[15]);
                            tv9.setText(data[16]);
                            tv10.setText(data[17]);
                            tv11.setText(data[18]);
                            tv12.setText(data[19]);
                            tv13.setText(data[20]);
                            tv14.setText(data[21]);
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
                            params.put("pid", String.valueOf(pid));
                            return params;
                        }
                    };
            queue.add(stringRequest);
        } else if (requestCode == 1 && resultCode == Activity.RESULT_OK) {String url = "http://tmoore.casavant.org/db/get_person.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("ASDFASDF", response);
                            String[] data = response.split(",");

                            if (menu.isOpened()) {
                                menu.toggle(true);
                            }
                            tv1.setText(data[8]);
                            tv2.setText(data[9]);
                            tv3.setText(data[10]);
                            tv4.setText(data[11]);
                            tv5.setText(data[12]);
                            tv6.setText(data[13]);
                            tv7.setText(data[14]);
                            tv8.setText(data[15]);
                            tv9.setText(data[16]);
                            tv10.setText(data[17]);
                            tv11.setText(data[18]);
                            tv12.setText(data[19]);
                            tv13.setText(data[20]);
                            tv14.setText(data[21]);
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
                    params.put("pid", String.valueOf(pid));
                    return params;
                }
            };
            queue.add(stringRequest);
        }
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
}
