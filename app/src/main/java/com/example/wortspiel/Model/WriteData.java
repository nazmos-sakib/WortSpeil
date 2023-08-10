package com.example.wortspiel.Model;

import android.content.Context;

import com.example.wortspiel.MainActivityCallBack;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WriteData {
    public WriteData(Context context, MainActivityCallBack callBack) {
        //System.out.println(getAssets().open("wordlist.txt"));

        System.out.println("Hello world!-------------");
        String home = System.getProperty("user.home");
        System.out.println(home);
        System.out.println(System.getProperty("user.dir"));
        String workingDir =  home + File.separator + "AndroidStudioProjects" + File.separator + "WortSpeil" + File.separator + "app" +
                File.separator + "src" + File.separator + "main" + File.separator+ "assets" + File.separator ;

        workingDir = home+"/AndroidStudioProjects/WortSpeil/app/src/main/assets/";

        Multimap<String, Word> wordList = ArrayListMultimap.create();


        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(  context.getAssets().open("example_v1_short.txt")));
            String line;


            while ((line = bufferedReader.readLine()) !=null){
                Word word = new Word();
                //preserve the whole line
                word.setAll(line);
                //System.out.println(line+"\n----------");
                //separating each part
                String[] a = line.split("\\t");
                /*for(String s: a){
                    System.out.println(s);
                }*/

                System.out.println(a.length);

                //a[0] start for german word --------------

                //String onlyWordPattern = "\\b(?!\\[.*?\\]|\\{.*?\\})\\w+\\b";
                String onlyWordPattern = "\\[.*?\\]|\\{.*?\\}|(\\w+)";
                String identityPattern = "\\{.*?\\}";
                String tagPattern = "\\[.*?\\]";


                //german word
                // Matches words that are not inside square brackets [] or curly braces {}
                word.setGermanWord(extractWord(a[0],onlyWordPattern).trim());
                word.setGermanWordIdentity(extractStringByPatternMatcher(a[0],identityPattern).trim());
                word.setTagGermanWord(extractTag(a[0],tagPattern));

                //english meaning
                word.setEnglishMeaning(extractWord(a[1],onlyWordPattern).trim());
                word.setEnglishMeaningIdentity(extractStringByPatternMatcher(a[1],identityPattern).trim());
                word.setTagEnglishMeaning(extractTag(a[1],tagPattern));

                //parts of speech

                if (a.length>2){
                    word.setPartsOfSpeech(a[2]);
                }
                if (a.length==4){
                    word.setTag(extractTag(a[3],tagPattern));
                }


                //System.out.println(word);
                wordList.put(word.getGermanWord(),word);
                System.out.println("------------------------------------------\n");
            }

            bufferedReader.close();

            System.out.println(wordList.size());
            System.out.println(wordList.keys().size());

            //list approach
            List<Map.Entry<String, Word>> wordEntries = new ArrayList<>(wordList.entries());
            String filename = "wordList.ser";
            File file = new File(context.getFilesDir(), filename);
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(wordEntries);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in wordList.ser");

            callBack.startMainActivity(wordList);



            /*
            Gson gson = new Gson();
            String json = gson.toJson(wordList);
            System.out.println("------------"+json.length());

            FileOutputStream fos = context.openFileOutput("multimap_data.json", Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            //osw.write(json);


            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(json);

            // Close the writer
            bw.close();
            osw.close();
            fos.close();
*/


        } catch ( IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractStringByPatternMatcher(String s, String p){
        String findings = "";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            findings = findings +" "+ matcher.group();
        }
        return findings;
    }

    public static List<String> extractTag(String s, String p){
        List<String> findings = new ArrayList<>();
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            findings .add( matcher.group());
        }
        return findings;
    }


    public static String extractWord(String s, String p){
        String findings = "";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String word = matcher.group(1);
            if (word != null) {
                findings += " "+ word;
            }
        }
        return findings;
    }

}