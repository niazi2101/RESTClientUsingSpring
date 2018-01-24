package com.example.hknia.restclient.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.hknia.restclient.R;
import com.example.hknia.restclient.core.Person;
import com.example.hknia.restclient.core.PersonAdaptor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class GetAllActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all);

        new HttpGetAllTask().execute();
    }



    // This is an inner class
    // It will execute in the background
    // It is called using HttpRequestTask.execute() method
    // Responsible to GET ALL Persons stored in DB as a single object from provided link
    // Also requires Authentication
    private class HttpGetAllTask extends AsyncTask<Void, Void, ResponseEntity<Person[]>> {

        @Override
        protected ResponseEntity<Person[]> doInBackground(Void... params) {
            try {
                //URL of the REST API GET method
                final String url = "http://192.168.56.1:45455/api/Person";

                //Used for converting Jackson to Http
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                //Headers are used to add additional parameters to a link
                //such as API keys, user name and password etc.
                HttpHeaders headers = new HttpHeaders();
                headers.set("APIKey", "1234ABCD5678EFGH");

                //Attaching header with link
                HttpEntity<String> entity = new HttpEntity<>(headers);

                // Response will be an array of persons
                // That is what Person[].class means
                ResponseEntity<Person[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,  Person[].class);


                return responseEntity;

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(ResponseEntity<Person[]> personReturned) {

            // Storing response into an array of persons
            Person[] person = personReturned.getBody();

            // Converting Array into arraylist becuase arraylist is used to
            // populate Listview
            ArrayList<Person> personList = new ArrayList<Person>(Arrays.asList(person)); //new ArrayList is only needed if you absolutely need an ArrayList

            // Listview object
            ListView listView = (ListView) findViewById(R.id.listView);

            // Initializing adaptor with context and arraylist of persons
            PersonAdaptor todoAdaptor = new PersonAdaptor(context, personList);
            // Attaching list with adaptor
            listView.setAdapter(todoAdaptor);

        }

    }

}
