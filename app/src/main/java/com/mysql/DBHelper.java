package com.mysql;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DBHelper {

    private static final String API_URL = "https://prakhar-doneria.in/api/api.php"; // Replace with your API URL

    private Context context;

    public DBHelper(Context context) {
        this.context = context;
    }

    public void addUser(String email, String password) {
        new AddUserTask().execute(email, password);
    }

    public void checkUser(String email, String password) {
        new CheckUserTask().execute(email, password);
    }

    private class AddUserTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String email = params[0];
            String password = params[1];

            try {
                // Prepare JSON object
                JSONObject json = new JSONObject();
                json.put("email", email);
                json.put("password", password);

                // Create connection
                URL url = new URL(API_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);

                // Write data
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = json.toString().getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                // Get response
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Successful response
                    showToast("User added");
                    return "User added successfully";
                } else {
                    // Failed response
                    showToast("Failed to add " + responseCode);
                    return "Failed to add user. Response code: " + responseCode;
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                showToast(e.getMessage());
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            showToast(result);
        }
    }

    private class CheckUserTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String email = params[0];
            String password = params[1];

            try {
                // Create connection
                URL url = new URL(API_URL + "?email=" + email + "&password=" + password);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // Get response
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Successful response
                    return "User exists: " + parseResponse(connection.getInputStream());
                } else {
                    // Failed response
                    showToast("Failed to check user " + responseCode);
                    return "Failed to check user. Response code: " + responseCode;
                }
            } catch (IOException e) {
                e.printStackTrace();
                showToast(e.getMessage());
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            showToast(result);
        }

        private String parseResponse(java.io.InputStream inputStream) throws IOException {
            // Parse the response from the server
            // This will depend on the format of the response (JSON, XML, etc.)
            // For simplicity, assuming JSON here
            // Modify accordingly based on your API response format

            StringBuilder response = new StringBuilder();
            int n;
            while ((n = inputStream.read()) != -1) {
                response.append((char) n);
            }

            try {
                JSONObject jsonObject = new JSONObject(response.toString());
                showToast("Exist");
                return String.valueOf(jsonObject.getBoolean("exists"));
            } catch (JSONException e) {
                e.printStackTrace();
                
                return "Error parsing response";
            }
        }
    }

    private void showToast(final String message) {
        // Use runOnUiThread to show the Toast on the main UI thread
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
