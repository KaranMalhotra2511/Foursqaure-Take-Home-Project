package com.foursquare.takehome;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvRecyclerView;
    private PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvRecyclerView = (RecyclerView) findViewById(R.id.rvRecyclerView);
        personAdapter = new PersonAdapter();

        //TODO hook up your adapter and any additional logic here

        parseVenueFromResponse();
    }


    /**
     * Parsing a fake json response from assets/people.json
     */
    private void parseVenueFromResponse() {
        new AsyncTask<Void, Void, Venue>() {
            @Override
            protected Venue doInBackground(Void... params) {
                try {
                    InputStream is = getAssets().open("people.json");
                    InputStreamReader inputStreamReader = new InputStreamReader(is);

                    PeopleHereJsonResponse response = new Gson().fromJson(inputStreamReader, PeopleHereJsonResponse.class);
                    return response.getVenue();
                } catch (Exception e) {}

                return null;
            }

            @Override
            protected void onPostExecute(Venue venue) {
                //TODO use the venue object to populate your recyclerview
            }
        }.execute();
    }
}
