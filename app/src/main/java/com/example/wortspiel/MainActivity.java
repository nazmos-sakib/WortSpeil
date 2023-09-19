package com.example.wortspiel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.wortspiel.Model.AudioDownloadTracker;
import com.example.wortspiel.Model.DataHolder;
import com.example.wortspiel.Model.Sound;
import com.example.wortspiel.Model.Utility;
import com.example.wortspiel.Model.Word;
import com.example.wortspiel.databinding.ActivityMainBinding;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity->";

    Button leftSelectedButton = null;
    Button rightSelectedButton = null;

    ActivityMainBinding binding;

    int[] leftColumns = {11,21,31,41,51,61};
    int[] rightColumns = {12,22,32,42,52,62};
    HashMap<Integer,Word> words;
    List<Map.Entry<String, Word>> entries;

    int numberOfCorrectAns = 0;

    //sound
    private SoundPool soundPool;
    private int soundB1,soundB2,soundB3,soundB4,soundB5,soundB6;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //for setting app to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //calling singleton data which was created in FirstHit
        Multimap<String, Word> wordList = DataHolder.getWordList();
        Log.d(TAG, "onCreate: " +  wordList.keys().getClass().getName());

        // Convert the Multimap to a List of Map.Entry objects
        entries = new ArrayList<>(wordList.entries());


        //view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //app drawer setup
        binding.includeToolbox.menuToolbox.setOnClickListener(View->{
            openDrawer(binding.parentDrawerLayout);
        });



        //data create
        /*ArrayList<Wort> data = GenerateSampleData.getData();
        Map<Integer,Wort> word = new  HashMap<>();
        words = new  HashMap<>();

        for (int i=0;i<data.size();i++){
            words.put(data.get(i).getGermanWord(),data.get(i));
        }*/



        //start story point1 (one button select other not select) -----------
        initButtons();
        // end story point1

        //start story point2(assigning to buttons)--------------------------------
        //step 1: shuffle button left column and right column
        //step 2: generate 6 random number list within the data size
        //step 3: create a new variable to hold six new random words
        //step 4: assign them in button
        doMagic();
        //end story point2

        //start story point3 (Mapping right and left button)-------------------------------
        //step 1: check if one button from both side is selected
        //step 2: if selected button has correct mapping
                //step 3: increase number of correct answer
                //step 4: disable this two button
        //step 5: if the answer is wrong
                //step 6: show a alert dialog
                //step 7: halt all execution for 800ms
                //step 8: set button background to default
        //step 9: deselect both button
        //step 10: if number of correct answer is more then 5
                // step 11: set all button background to default
                // step 12: get new dataset
                // step 13: set onclick listener to buttons
        //checkCorrectAnswer()
        //end story point3

        //story point 4 (play sound)-------------------------------
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION )
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new  SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC,0);  //stream_music means if headPhone is connected play in headPhone
        }

        soundB1 = soundPool.load(this, R.raw.water_pouring_a,1);
        //end story point 4

    }  //end onCreate()



    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(binding.parentDrawerLayout);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    public void initButtons(){
        //Floating action buttons
        binding.fabRefreshList.setOnClickListener(View->{
            buttonResetBackgroundColor();
            doMagic();
        });

        //details
        binding.includeToolbox.imvWordDetailsToolbar.setOnClickListener(View->{
            Log.d(TAG, "initButtons: AudioDownloadTracker-> "+AudioDownloadTracker.getValue());
            if (AudioDownloadTracker.getValue()>=6){
                Intent intent = new Intent(this, WordDetails.class);
                intent.putExtra("words",words);
                startActivity(intent);
            }
        });
        // left columns -----------------------------

        // right columns ----------------------------
        initGameButtons();
    }

    void initGameButtons(){
        //left column
        addClickListenerToButton(binding.button11,true);
        addClickListenerToButton(binding.button21,true);
        addClickListenerToButton(binding.button31,true);
        addClickListenerToButton(binding.button41,true);
        addClickListenerToButton(binding.button51,true);
        addClickListenerToButton(binding.button61,true);

        //right column
        addClickListenerToButton(binding.button12,false);
        addClickListenerToButton(binding.button22,false);
        addClickListenerToButton(binding.button32,false);
        addClickListenerToButton(binding.button42,false);
        addClickListenerToButton(binding.button52,false);
        addClickListenerToButton(binding.button62,false);
    }

    private void doMagic(){
        AudioDownloadTracker.reset();

        //step 1: shuffle columns
        Integer[] leftColumn = shuffleArray(new Integer[]{11,21,31,41,51,61});
        Integer[] rightColumn = shuffleArray(new Integer[]{12,22,32,42,52,62});

        List<Integer> randomEntries = generateUniqueRandomNumbers((entries.size()>6)?entries.size():6);

        words = new HashMap<>();
        //Object[] key = words.keySet().toArray();
        for (int i =0;i<leftColumn.length;i++){
            words.put(leftColumn[i],entries.get(randomEntries.get(i)).getValue());
            getButtonByNumber(leftColumn[i]).setText(entries.get(randomEntries.get(i)).getValue().getGermanWord() );
            getButtonByNumber(rightColumn[i]).setText(entries.get(randomEntries.get(i)).getValue().getEnglishMeaning());
        }

        //get pronunciation url
        words.forEach((key,value)->{
            Utility.getPronunciationUrl(
                    getApplicationContext(),
                    getButtonByNumber(key),
                    (url)->{
                        AudioDownloadTracker.increase();
                        if (url!=null){
                            value.setPronunciation(new Sound(url.toString()));
                        }
                    });
        });
    }

    private void checkCorrectAnswer()  {
        if (leftSelectedButton!=null  && rightSelectedButton!=null){

            if (getClickedButtonId(leftSelectedButton.getId())!=null){

                Word word = words.get(getClickedButtonId(leftSelectedButton.getId()));

                // correct word mapping
                if(word.getGermanWord().equals(leftSelectedButton.getText()) &&
                    word.getEnglishMeaning()
                        .equals(rightSelectedButton.getText())){

                    ++numberOfCorrectAns;

                    leftSelectedButton.setBackground(getDrawable(R.drawable.button_style_complete));
                    rightSelectedButton.setBackground(getDrawable(R.drawable.button_style_complete));
                    leftSelectedButton.setOnClickListener(null);
                    rightSelectedButton.setOnClickListener(null);

                } else {// wrong word mapping

                    AlertDialog wrongAnswerDialog = new AlertDialog.Builder(this)
                            .create();

                    // Inflate the view containing the EditText and Button
                    LayoutInflater inflater = LayoutInflater.from(this);
                    View view = inflater.inflate(R.layout.alartdialog_warning, null);

                    wrongAnswerDialog.setView(view);
                    // Create and show the AlertDialog
                    wrongAnswerDialog.show();
                    //step 7: halt all execution for 800ms
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // do stuff
                            wrongAnswerDialog.dismiss();
                        }
                    }, 800);

                    //step 8: set button background to default
                    leftSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));
                    rightSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));

                }
                //step 8: deselect both button
                //for both case wrong answer or right answer reset selected button
                leftSelectedButton= null;
                rightSelectedButton=null;

                //step 10:
                if (numberOfCorrectAns>5){
                    numberOfCorrectAns = 0;
                    buttonResetBackgroundColor();
                    doMagic();
                    initGameButtons();
                }
            }


        }
    }

    private Button getButtonByNumber(int n){
        switch (n){
            case 11:
                return binding.button11;
            case 12:
                return binding.button12;
            case 21:
                return binding.button21;
            case 22:
                return binding.button22;
            case 31:
                return binding.button31;
            case 32:
                return binding.button32;
            case 41:
                return binding.button41;
            case 42:
                return binding.button42;
            case 51:
                return binding.button51;
            case 52:
                return binding.button52;
            case 61:
                return binding.button61;
            case 62:
                return binding.button62;
            default:
                return null;
        }
    }

    @SuppressLint("NonConstantResourceId")
    private Integer getClickedButtonId(int n){
        switch (n){
            case R.id.button11:
                return 11;

            case R.id.button21:
                return 21;

            case R.id.button31:
                return 31;

            case R.id.button41:
                return 41;

            case R.id.button51:
                return 51;

            case R.id.button61:
                return 61;

            default:
                return null;
        }
    }

    private Integer[] shuffleArray(Integer [] intArray){
        List<Integer> intList = Arrays.asList(intArray);
        Collections.shuffle(intList);
        return intList.toArray(intArray);
    }

    private List<Integer> generateUniqueRandomNumbers(int maxValue){
        Set<Integer> uniqueNumbers = new HashSet<>();
        Random random = new Random();

        while (uniqueNumbers.size() < 6) {
            int randomValue = random.nextInt(maxValue) + 1;
            uniqueNumbers.add(randomValue);
        }
        return new ArrayList<>(uniqueNumbers);
    }

    private void generateRandomWords(List<Integer> randomEntries){
        Map<Integer,Word> words = new HashMap<>();
        for (int i=0;i<randomEntries.size();i++){
            words.put(i,entries.get(randomEntries.get(i)).getValue());
        }
        //RandomWordDataHolder.setWordList(words);
    }

    private void buttonResetBackgroundColor(){
        binding.button11.setBackground(getDrawable(R.drawable.button_style_normal));
        binding.button12.setBackground(getDrawable(R.drawable.button_style_normal));
        binding.button21.setBackground(getDrawable(R.drawable.button_style_normal));
        binding.button22.setBackground(getDrawable(R.drawable.button_style_normal));
        binding.button31.setBackground(getDrawable(R.drawable.button_style_normal));
        binding.button32.setBackground(getDrawable(R.drawable.button_style_normal));
        binding.button41.setBackground(getDrawable(R.drawable.button_style_normal));
        binding.button42.setBackground(getDrawable(R.drawable.button_style_normal));
        binding.button51.setBackground(getDrawable(R.drawable.button_style_normal));
        binding.button52.setBackground(getDrawable(R.drawable.button_style_normal));
        binding.button61.setBackground(getDrawable(R.drawable.button_style_normal));
        binding.button62.setBackground(getDrawable(R.drawable.button_style_normal));
    }

    private void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void closeDrawer(DrawerLayout drawerLayout){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    void addClickListenerToButton(Button btn, boolean isLeftBtn){
        btn.setOnClickListener(View-> {
            if ((btn == leftSelectedButton) || (btn == rightSelectedButton)) {
                // Deselect the button
                btn.setSelected(false);
                btn.setBackground(getDrawable(R.drawable.button_style_normal));

                leftSelectedButton = isLeftBtn ? null : leftSelectedButton;
                rightSelectedButton = !isLeftBtn ? null : rightSelectedButton;
            } else {
                // Deselect the previously selected button (if any)
                if (leftSelectedButton != null && isLeftBtn) {
                    leftSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));
                    leftSelectedButton.setSelected(false);
                } else if (rightSelectedButton != null && !isLeftBtn) {
                    rightSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));
                    rightSelectedButton.setSelected(false);
                }


                // Select the clicked button
                btn.setSelected(true);
                btn.setBackground(getDrawable(R.drawable.button_style_on_select));

                leftSelectedButton = isLeftBtn ? btn : leftSelectedButton;
                rightSelectedButton = !isLeftBtn ? btn : rightSelectedButton;
                //look for correct solution
                checkCorrectAnswer();

            }
            //story point 4
            playSound(btn.getId());
        });

    }

    private void playSound(int id) {
        //step 1: get row from button id
        if (getClickedButtonId(id)!=null){
            Word word = words.get(getClickedButtonId(id));
            if (word.getPronunciation()!=null){
                System.out.println(word.getPronunciation().getUrl());
                Utility.playPronunciation(word.getPronunciation().getUrl());
            }
        }

    }

























    void printButtonsId(){
        System.out.println(R.id.button11);
        System.out.println(R.id.button21);
    }
    private void toggleBackground(Button button){
        Drawable normal = getDrawable(R.drawable.button_style_normal);
        Drawable currentDrawable = button.getBackground();
        if (areDrawablesIdentical(normal,currentDrawable)){
            button.setBackground(getDrawable(R.drawable.button_style_on_select));
        } else {
            button.setBackground(getDrawable(R.drawable.button_style_normal));
        }
    }

    //compare drawable
    public static boolean areDrawablesIdentical(Drawable drawableA, Drawable drawableB) {
        Drawable.ConstantState stateA = drawableA.getConstantState();
        Drawable.ConstantState stateB = drawableB.getConstantState();
        // If the constant state is identical, they are using the same drawable resource.
        // However, the opposite is not necessarily true.
        return (stateA != null && stateB != null && stateA.equals(stateB))
                || getBitmap(drawableA).sameAs(getBitmap(drawableB));
    }

    //helper function to compare drawable
    public static Bitmap getBitmap(Drawable drawable) {
        Bitmap result;
        if (drawable instanceof BitmapDrawable) {
            result = ((BitmapDrawable) drawable).getBitmap();
        } else {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            // Some drawables have no intrinsic width - e.g. solid colours.
            if (width <= 0) {
                width = 1;
            }
            if (height <= 0) {
                height = 1;
            }

            result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}