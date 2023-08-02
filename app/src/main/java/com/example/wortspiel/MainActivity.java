package com.example.wortspiel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.wortspiel.Model.Wort;
import com.example.wortspiel.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity->";

    Button leftSelectedButton = null;
    Button rightSelectedButton = null;

    ActivityMainBinding binding;

    int[] leftColumns = {11,21,31,41,51,61};
    int[] rightColumns = {12,22,32,42,52,62};
    Map<String,Wort> words;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //for setting app to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //data create
        ArrayList<Wort> data = GenerateSampleData.getData();
        //Map<Integer,Wort> word = new  HashMap<>();
        words = new  HashMap<>();

        for (int i=0;i<data.size();i++){
            words.put(data.get(i).getGermanWord(),data.get(i));
        }



        //start story point1 (one button select other not select) -----------
        // left columns -----------------------------
        binding.button11.setOnClickListener(View->{
            if (binding.button11 == leftSelectedButton) {
                // Deselect the button
                binding.button11.setSelected(false);
                binding.button11.setBackground(getDrawable(R.drawable.button_style_normal));
                leftSelectedButton = null;
            } else {
                // Deselect the previously selected button (if any)
                if (leftSelectedButton != null) {
                    leftSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));
                    leftSelectedButton.setSelected(false);
                }

                // Select the clicked button
                binding.button11.setSelected(true);
                binding.button11.setBackground(getDrawable(R.drawable.button_style_on_select));
                leftSelectedButton = binding.button11;
                //look for correct solution
                checkCorrectAnswer();

            }

        });
        binding.button21.setOnClickListener(View->{
            if (binding.button21 == leftSelectedButton) {
                // Deselect the button
                binding.button21.setSelected(false);
                binding.button21.setBackground(getDrawable(R.drawable.button_style_normal));

                leftSelectedButton = null;
            } else {
                // Deselect the previously selected button (if any)
                if (leftSelectedButton != null) {
                    leftSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));

                    leftSelectedButton.setSelected(false);
                }

                // Select the clicked button
                binding.button21.setSelected(true);
                binding.button21.setBackground(getDrawable(R.drawable.button_style_on_select));

                leftSelectedButton = binding.button21;
                //look for correct solution
                checkCorrectAnswer();

            }

        });
        binding.button31.setOnClickListener(View->{
            if (binding.button31 == leftSelectedButton) {
                // Deselect the button
                binding.button31.setSelected(false);
                binding.button31.setBackground(getDrawable(R.drawable.button_style_normal));

                leftSelectedButton = null;
            } else {
                // Deselect the previously selected button (if any)
                if (leftSelectedButton != null) {
                    leftSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));

                    leftSelectedButton.setSelected(false);
                }

                // Select the clicked button
                binding.button31.setSelected(true);
                binding.button31.setBackground(getDrawable(R.drawable.button_style_on_select));

                leftSelectedButton = binding.button31;
                //look for correct solution
                checkCorrectAnswer();

            }

        });
        binding.button41.setOnClickListener(View->{
            if (binding.button41 == leftSelectedButton) {
                // Deselect the button
                binding.button41.setSelected(false);
                binding.button41.setBackground(getDrawable(R.drawable.button_style_normal));

                leftSelectedButton = null;
            } else {
                // Deselect the previously selected button (if any)
                if (leftSelectedButton != null) {
                    leftSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));

                    leftSelectedButton.setSelected(false);
                }

                // Select the clicked button
                binding.button41.setSelected(true);
                binding.button41.setBackground(getDrawable(R.drawable.button_style_on_select));

                leftSelectedButton = binding.button41;
                //look for correct solution
                checkCorrectAnswer();

            }

        });
        binding.button51.setOnClickListener(View->{
            if (binding.button51 == leftSelectedButton) {
                // Deselect the button
                binding.button51.setSelected(false);
                binding.button51.setBackground(getDrawable(R.drawable.button_style_normal));

                leftSelectedButton = null;
            } else {
                // Deselect the previously selected button (if any)
                if (leftSelectedButton != null) {
                    leftSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));

                    leftSelectedButton.setSelected(false);
                }

                // Select the clicked button
                binding.button51.setSelected(true);
                binding.button51.setBackground(getDrawable(R.drawable.button_style_on_select));

                leftSelectedButton = binding.button51;
                //look for correct solution
                checkCorrectAnswer();

            }

        });
        binding.button61.setOnClickListener(View->{
            if (binding.button61 == leftSelectedButton) {
                // Deselect the button
                binding.button61.setSelected(false);
                binding.button61.setBackground(getDrawable(R.drawable.button_style_normal));

                leftSelectedButton = null;
            } else {
                // Deselect the previously selected button (if any)
                if (leftSelectedButton != null) {
                    leftSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));

                    leftSelectedButton.setSelected(false);
                }
                // Select the clicked button
                binding.button61.setSelected(true);
                binding.button61.setBackground(getDrawable(R.drawable.button_style_on_select));

                leftSelectedButton = binding.button61;
                //look for correct solution
                checkCorrectAnswer();

            }

        });

        // right columns ----------------------------
        binding.button12.setOnClickListener(View->{
            if (binding.button12 == rightSelectedButton) {
                // Deselect the button
                binding.button12.setSelected(false);
                binding.button12.setBackground(getDrawable(R.drawable.button_style_normal));
                rightSelectedButton = null;
            } else {
                // Deselect the previously selected button (if any)
                if (rightSelectedButton != null) {
                    rightSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));
                    rightSelectedButton.setSelected(false);
                }

                // Select the clicked button
                binding.button12.setSelected(true);
                binding.button12.setBackground(getDrawable(R.drawable.button_style_on_select));
                rightSelectedButton = binding.button12;
                //look for correct solution
                checkCorrectAnswer();

            }

        });
        binding.button22.setOnClickListener(View->{
            if (binding.button22 == rightSelectedButton) {
                // Deselect the button
                binding.button22.setSelected(false);
                binding.button22.setBackground(getDrawable(R.drawable.button_style_normal));

                rightSelectedButton = null;
            } else {
                // Deselect the previously selected button (if any)
                if (rightSelectedButton != null) {
                    rightSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));

                    rightSelectedButton.setSelected(false);
                }

                // Select the clicked button
                binding.button22.setSelected(true);
                binding.button22.setBackground(getDrawable(R.drawable.button_style_on_select));

                rightSelectedButton = binding.button22;
                //look for correct solution
                checkCorrectAnswer();

            }

        });
        binding.button32.setOnClickListener(View->{
            if (binding.button32 == rightSelectedButton) {
                // Deselect the button
                binding.button32.setSelected(false);
                binding.button32.setBackground(getDrawable(R.drawable.button_style_normal));

                rightSelectedButton = null;
            } else {
                // Deselect the previously selected button (if any)
                if (rightSelectedButton != null) {
                    rightSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));

                    rightSelectedButton.setSelected(false);
                }

                // Select the clicked button
                binding.button32.setSelected(true);
                binding.button32.setBackground(getDrawable(R.drawable.button_style_on_select));

                rightSelectedButton = binding.button32;
                //look for correct solution
                checkCorrectAnswer();

            }

        });
        binding.button42.setOnClickListener(View->{
            if (binding.button42 == rightSelectedButton) {
                // Deselect the button
                binding.button42.setSelected(false);
                binding.button42.setBackground(getDrawable(R.drawable.button_style_normal));

                rightSelectedButton = null;
            } else {
                // Deselect the previously selected button (if any)
                if (rightSelectedButton != null) {
                    rightSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));

                    rightSelectedButton.setSelected(false);
                }

                // Select the clicked button
                binding.button42.setSelected(true);
                binding.button42.setBackground(getDrawable(R.drawable.button_style_on_select));

                rightSelectedButton = binding.button42;
                //look for correct solution
                checkCorrectAnswer();

            }

        });
        binding.button52.setOnClickListener(View->{
            if (binding.button52 == rightSelectedButton) {
                // Deselect the button
                binding.button52.setSelected(false);
                binding.button52.setBackground(getDrawable(R.drawable.button_style_normal));

                rightSelectedButton = null;
            } else {
                // Deselect the previously selected button (if any)
                if (rightSelectedButton != null) {
                    rightSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));

                    rightSelectedButton.setSelected(false);
                }

                // Select the clicked button
                binding.button52.setSelected(true);
                binding.button52.setBackground(getDrawable(R.drawable.button_style_on_select));

                rightSelectedButton = binding.button52;
                //look for correct solution
                checkCorrectAnswer();

            }

        });
        binding.button62.setOnClickListener(View->{
            if (binding.button62 == rightSelectedButton) {
                // Deselect the button
                binding.button62.setSelected(false);
                binding.button62.setBackground(getDrawable(R.drawable.button_style_normal));

                rightSelectedButton = null;
            } else {
                // Deselect the previously selected button (if any)
                if (rightSelectedButton != null) {
                    rightSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));

                    rightSelectedButton.setSelected(false);
                }
                // Select the clicked button
                binding.button62.setSelected(true);
                binding.button62.setBackground(getDrawable(R.drawable.button_style_on_select));

                rightSelectedButton = binding.button62;
                //look for correct solution
                checkCorrectAnswer();

            }

        });
        // end story point1

        //start story point2(assigning to buttons)--------------------------------

        //shuffle columns
        Integer[] leftColumn =shuffleArray(new Integer[]{11,21,31,41,51,61});
        Integer[] rightColumn =shuffleArray(new Integer[]{12,22,32,42,52,62});

        // assigning into buttons
        /*word.forEach((i,w)->{
            getButtonByNumber(leftColumn[i]).setText(w.getGermanWord());
            getButtonByNumber(rightColumn[i]).setText(w.getEnglishMeaning());
        });*/
        Object[] key = words.keySet().toArray();
        for (int i =0;i<leftColumn.length;i++){
            getButtonByNumber(leftColumn[i]).setText( words.get(key[i]).getGermanWord());
            getButtonByNumber(rightColumn[i]).setText(words.get(key[i]).getEnglishMeaning());
        }

        //end story point2

        //start story point3 (Mapping right and wrong button)-------------------------------

        //end story point3

    }

    private void checkCorrectAnswer()  {
        if (leftSelectedButton!=null  && rightSelectedButton!=null){

            // correct word mapping
             if(words
                     .get(leftSelectedButton.getText())
                     .getEnglishMeaning()
                     .equals(rightSelectedButton.getText())){

                 leftSelectedButton.setBackground(getDrawable(R.drawable.button_style_complete));
                 rightSelectedButton.setBackground(getDrawable(R.drawable.button_style_complete));
                 leftSelectedButton.setOnClickListener(null);
                 rightSelectedButton.setOnClickListener(null);

                 leftSelectedButton= null;
                 rightSelectedButton=null;

             } else {// wrong word mapping

                 AlertDialog wrongAnswerDialog = new AlertDialog.Builder(this)
                         .create();

                 // Inflate the view containing the EditText and Button
                 LayoutInflater inflater = LayoutInflater.from(this);
                 View view = inflater.inflate(R.layout.alartdialog_warning, null);

                 wrongAnswerDialog.setView(view);
                 // Create and show the AlertDialog
                 wrongAnswerDialog.show();

                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         // do stuff
                         wrongAnswerDialog.dismiss();
                     }
                 }, 800);


                 leftSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));
                 rightSelectedButton.setBackground(getDrawable(R.drawable.button_style_normal));

                 leftSelectedButton= null;
                 rightSelectedButton=null;
             }




        }
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

    private Integer[] shuffleArray(Integer [] intArray){
        List<Integer> intList = Arrays.asList(intArray);
        Collections.shuffle(intList);
        return intList.toArray(intArray);
    }

}