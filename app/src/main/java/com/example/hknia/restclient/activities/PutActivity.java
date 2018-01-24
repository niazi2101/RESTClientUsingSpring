package com.example.hknia.restclient.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hknia.restclient.R;
import com.example.hknia.restclient.core.Person;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class PutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put);

        Person person = new Person();

        person.setID(6);
        person.setFirstName("Name");
        person.setLastName("Changed");
        person.setPayRate(4000);
        //person.setStartDate("0001-01-01T01:00:00");
        //person.setEndDate("0001-01-01T01:00:00");

        new HttpPutTask().execute(person);


    }


    // This is an inner class
    // It will execute in the background
    // It is called using HttpPostTask.execute() method
    // Responsible to POST a single object to the provided link
    // @Person --> using in doInBackground method
    // @String --> doInBackground will return a String which will be used in PostExecute
    private class HttpPutTask extends AsyncTask<Person, Void, String> {


        @Override
        protected String doInBackground(Person... params) {

            //URL of the REST API GET method
            final String url = "http://192.168.56.1:45455/api/Person";

            //This is the main 'object' which will be used for
            //communication with the RESTApi
            RestTemplate restTemplate = new RestTemplate();

            //We need Jackson converter when dealing with Objects
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            //Headers are used to add additional parameters to a link
            //such as API keys, user name and password etc.
            HttpHeaders headers = new HttpHeaders();
            headers.set("APIKey", "1234ABCD5678EFGH");

            Person person = params[0];

            //Attaching header with given object which we want to POST
            HttpEntity<Person> requestEntity = new HttpEntity<Person>(person, headers);


            //Finally executing
            // It will take a URL, a method (GET,POST,PUT,DELETE), an entity(for parameters)
            // and a class for storing received data
            // It will return response which we can then use in onPostExecute method

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);

            //RestTemplate will return a response with status code hidden in it
            // We will take that status code and return it
            String status =  response.getStatusCode().getReasonPhrase();
            return status;


        }

        @Override
        protected void onPostExecute(String personReturned) {
            TextView greetingIdText =  findViewById(R.id.tvPostResponse);

            //Display Response
            greetingIdText.setText(personReturned);
        }

    }
}
