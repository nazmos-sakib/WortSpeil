package com.example.wortspiel.Model;

import android.os.AsyncTask;
import android.util.Log;

import com.example.wortspiel.Interfaces.Callback;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAudio;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

public class NetworkTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = "NetworkTask";
    private final String url = "https://www.howtopronounce.com/german/";
    private String word;

    Callback callback;

    String src;

    public NetworkTask(String arg1,Callback callback) {
        word = arg1;
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try  {

            // Fetch the webpage
            HtmlPage page = getWebPage();

            // Check the HTTP status code of the response
            int statusCode = page.getWebResponse().getStatusCode();

            if (statusCode == 404) {
                // Handle the 404 error here
                System.out.println("The page returned a 404 error (Page Not Found).");
                callback.onCallback(null);
            } else if (statusCode == 200) {
                // The page was successfully retrieved, continue processing
                // Find the <div> with id "pronun"
                HtmlDivision div = (HtmlDivision) page.getElementById("pronouncedContents");

                if (div != null) {
                    // Find the <a> element within the <div>
                    HtmlAudio audio = div.getFirstByXPath(".//audio");

                    if (audio != null) {
                        // Extract the src attribute from the <audio> element
                        src = audio.getAttribute("src");
                        System.out.println("Audio src: " + src);

                        callback.onCallback(src);
                    } else {
                        System.out.println("No <audio> element found within the <div id=\"pronun\">.");
                        callback.onCallback(null);
                    }

                } else {
                    System.out.println("No <div id=\"pronun\"> found.");
                    callback.onCallback(null);
                }

            } else {
                // Handle other HTTP status codes if needed
                System.out.println("The page returned an unexpected status code: " + statusCode);
                callback.onCallback(null);
            }

        } catch (Exception e) {
            Log.d(TAG, "doInBackground: "+word);
            //e.printStackTrace();
            callback.onCallback(null);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result != null) {
            // Update your UI with the parsed HTML content here
            System.out.println("onPostExecution");
            //callback.onCallback(src);
        } else {
            // Handle errors here
        }
    }

    private HtmlPage getWebPage() throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        //webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        //webClient.waitForBackgroundJavaScript(3000);
        //webClient.waitForBackgroundJavaScriptStartingBefore(3000);
        System.out.println(url+word);
        return webClient.getPage(url+word);
    }
}
