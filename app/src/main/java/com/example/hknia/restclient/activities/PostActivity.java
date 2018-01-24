package com.example.hknia.restclient.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hknia.restclient.R;
import com.example.hknia.restclient.core.Person;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class PostActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etPayRate, etStartDate, etEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPayRate = findViewById(R.id.etPayRate);
        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);


    }

    public void sendPOST(View view) {
      //We will store input in person object
      Person person = new Person();

      //Getting input from user
        person.setFirstName(etFirstName.getText().toString());
        person.setLastName(etLastName.getText().toString());

        //Calling Async Task to perform POST request in the background
        new HttpPostTask().execute(person);

    }

    // This is an inner class
    // It will execute in the background
    // It is called using HttpPostTask.execute() method
    // Responsible to POST a single object to the provided link
    // @Person --> using in doInBackground method
    // @String --> doInBackground will return a String which will be used in PostExecute
    private class HttpPostTask extends AsyncTask<Person, Void, String> {


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

                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

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
