package com.example.wortspiel.Model;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAudio;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;


public class Pronunciation {
    //private final String url = "https://www.collinsdictionary.com/dictionary/german-english/";
    private final String url = "https://www.howtopronounce.com/german/";

    public Pronunciation(String word,Callback callback) {

/*        try {
            System.out.println(loadPage(word));

        } catch (Exception e){
            e.printStackTrace();
        }*/

        try  {

            // Fetch the webpage
            HtmlPage page = getWebPage(word);

            // Find the <div> with id "pronun"
            HtmlDivision div = (HtmlDivision) page.getElementById("pronouncedContents");

            //System.out.println(div.toString());

            if (div != null) {
                // Find the <a> element within the <div>
                HtmlAudio audio = div.getFirstByXPath(".//audio");

                if (audio != null) {
                    // Extract the src attribute from the <audio> element
                    String src = audio.getAttribute("src");
                    System.out.println("Audio src: " + src);

                    callback.onCallback(src);

                } else {
                    System.out.println("No <audio> element found within the <div id=\"pronun\">.");
                }

            } else {
                System.out.println("No <div id=\"pronun\"> found.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String loadPage(String word) throws IOException{
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url+word)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }


    }

    private HtmlPage getWebPage(String word) throws IOException {
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
