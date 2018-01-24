package com.example.hknia.restclient.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.hknia.restclient.core.Person;
import com.example.hknia.restclient.R;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class GetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

    }

    public void sendMessage(View view) {
        //final String uri = "http://localhost:64042/api/Person/3";
        new HttpRequestTask().execute();
    }


    // This is an inner class
    // It will execute in the background
    // It is called using HttpRequestTask.execute() method
    // Responsible to GET a single object from provided link
    // Also requires Authentication
    private class HttpRequestTask extends AsyncTask<Void, Void, ResponseEntity<Person>> {
        @Override
        protected ResponseEntity<Person> doInBackground(Void... params) {
            try {
                //URL of the REST API GET method
                final String url = "http://192.168.56.1:45455/api/Person/1";

                //Used for converting Jackson to Http
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                //Headers are used to add additional parameters to a link
                //such as API keys, user name and password etc.
                HttpHeaders headers = new HttpHeaders();
                headers.set("APIKey", "1234ABCD5678EFGH");

                //Attaching header with link
                HttpEntity<String> entity = new HttpEntity<>(headers);

                //Finally executing
                // It will take a URL, a method (GET,POST,PUT,DELETE), an entity(for parameters)
                // and a class for storing received data
                // It will return response which we can then use in onPostExecute method
                ResponseEntity<Person> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Person.class);
                return responseEntity;

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(ResponseEntity<Person> personReturned) {
            TextView greetingIdText =  findViewById(R.id.tvID);

            // getBody() function is used to convert ResponseEntity into normal object
            Person person = personReturned.getBody();

            //Display
            String data = "\nID: " + person.getID()
                        + "\nFirst Name: " + person.getFirstName()
                        + "\nLast Name: " + person.getLastName()
                        + "\nPayRate: " + person.getPayRate()
                        + "\nStart Date: " + person.getStartDate()
                        + "\nEnd Date: " + person.getEndDate();

            greetingIdText.setText(data);
        }

    }

    //region    not working code
/*
    private class RESTTask extends AsyncTask<String, Void, ResponseEntity<Person>>
    {
        @Override
        protected  ResponseEntity<Person> doInBackground(String... uri)
        {
            final String url = uri[0];
            RestTemplate restTemplate = new RestTemplate();
            try
            {   //Set up meesage converters
                //we are using jackson to http message converter
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                HttpHeaders headers = new HttpHeaders();

                //have to set API key
                headers.set("APIKey", "1234ABCD5678EFGH");

                //String auth = "Jack" + ":" + "Jones";

                //String encodedAuth = Base64.encodeToString(auth.getBytes(), Base64.DEFAULT);
                //String authHeader = "Basic " + new String(encodedAuth);
                //headers.set("Authorization", authHeader);

                HttpEntity<String> entity = new HttpEntity<>(headers);

                ResponseEntity<Person> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Person.class);
                return responseEntity;
            }
            catch (Exception ex)
            {
                Log.e("Exception", "Authentication Exception");
                return null;
            }
        }

        protected void onPostExecute(ResponseEntity<Person> result)
        {
            HttpStatus statusCode = result.getStatusCode();
            Person personReturned = result.getBody();

            //Toast.makeText(Applicatio, ""+statusCode.toString(), Toast.LENGTH_LONG).show();
            response.setText(statusCode.toString());
        }
    }
*/
//    TextView response;
//endregion not


}