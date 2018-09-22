package com.example.synthesizer;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SynthesizerActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = SynthesizerActivity.class.getSimpleName();

    private Button buttonA;
    private Button buttonBflat;
    private Button buttonB;
    private Button buttonC;
    private Button buttonCsharp;
    private Button buttonD;
    private Button buttonDsharp;
    private Button buttonE;
    private Button buttonF;
    private Button buttonFsharp;
    private Button buttonG;
    private Button buttonGsharp;
    private Button buttonA4;
    private Button buttonBflat4;
    private Button buttonB4;
    private Button buttonC5;
    private Button buttonCsharp5;
    private Button buttonD5;
    private Button buttonDsharp5;
    private Button buttonE5;
    private Button buttonF5;
    private Button buttonFsharp5;
    private Button buttonG5;
    private Button buttonGsharp5;

    private Button buttonPlayScale;
    private Button buttonPlayLife;
    private Button buttonPlaySchu;

    private SoundPool soundPool;

    private int noteA;
    private int noteBflat;
    private int noteB;
    private int noteC;
    private int noteCsharp;
    private int noteD;
    private int noteDsharp;
    private int noteE;
    private int noteF;
    private int noteFsharp;
    private int noteG;
    private int noteGsharp;
    private int noteA4;
    private int noteBflat4;
    private int noteB4;
    private int noteC5;
    private int noteCsharp5;
    private int noteD5;
    private int noteDsharp5;
    private int noteE5;
    private int noteF5;
    private int noteFsharp5;
    private int noteG5;
    private int noteGsharp5;

    private Handler handler;

    private Map<Integer, Integer> noteMap;

    public static final float DEFAULT_VOLUME = 1.0f;
    public static final float DEFAULT_RATE = 1.0f;
    public static final int DEFAULT_PRIORITY = 1;

    //120BPM
    private static final int SIXTEENTH_NOTE_120 = 125; // in ms
    private static final int WHOLE_NOTE_120 = 16 * SIXTEENTH_NOTE_120;
    private static final int HALF_NOTE_120 = 8 * SIXTEENTH_NOTE_120;
    private static final int QUARTER_NOTE_120 = 4 * SIXTEENTH_NOTE_120;
    private static final int EIGHTH_NOTE_120 = 2 * SIXTEENTH_NOTE_120;

    //180BPM
    private static final int SIXTEENTH_NOTE_180 = (int) (SIXTEENTH_NOTE_120 * 1.5); // in ms
    private static final int WHOLE_NOTE_180 = 16 * SIXTEENTH_NOTE_120;
    private static final int HALF_NOTE_180 = 8 * SIXTEENTH_NOTE_120;
    private static final int QUARTER_NOTE_180 = 4 * SIXTEENTH_NOTE_120;
    private static final int EIGHTH_NOTE_180 = 2 * SIXTEENTH_NOTE_120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthesizer);

        handler = new Handler();
        wireWidgets();
        setListeners();
        initializeSoundPool();
        initializeNoteMap();
    }

    private void initializeNoteMap() {
        noteMap = new HashMap<>();
        // in a map, you store a key:value pair
        // key is the button ID, the value is the note ID
        noteMap.put(R.id.button_synth_a, noteA);
        noteMap.put(R.id.button_synth_bflat, noteBflat);
        noteMap.put(R.id.button_synth_b, noteB);
        noteMap.put(R.id.button_synth_c, noteC);
        noteMap.put(R.id.button_synth_csharp,  noteCsharp);
        noteMap.put(R.id.button_synth_d, noteD);
        noteMap.put(R.id.button_synth_dsharp,  noteDsharp);
        noteMap.put(R.id.button_synth_e, noteE);
        noteMap.put(R.id.button_synth_f, noteF);
        noteMap.put(R.id.button_synth_fsharp, noteFsharp);
        noteMap.put(R.id.button_synth_g, noteG);
        noteMap.put(R.id.button_synth_gsharp, noteGsharp);
        noteMap.put(R.id.button_synth_a4, noteA4);
        noteMap.put(R.id.button_synth_bflat4, noteBflat4);
        noteMap.put(R.id.button_synth_b4, noteB4);
        noteMap.put(R.id.button_synth_c5, noteC5);
        noteMap.put(R.id.button_synth_csharp5,  noteCsharp5);
        noteMap.put(R.id.button_synth_d5, noteD5);
        noteMap.put(R.id.button_synth_dsharp5,  noteDsharp5);
        noteMap.put(R.id.button_synth_e5, noteE5);
        noteMap.put(R.id.button_synth_f5, noteF5);
        noteMap.put(R.id.button_synth_fsharp5, noteFsharp5);
        noteMap.put(R.id.button_synth_g5, noteG5);
        noteMap.put(R.id.button_synth_gsharp5, noteGsharp5);
    }

    private void initializeSoundPool() {
        soundPool = new SoundPool(25, AudioManager.STREAM_MUSIC, 0);

        noteA = soundPool.load(this, R.raw.a4, 1);
        noteBflat = soundPool.load(this, R.raw.bflat4, 1);
        noteB = soundPool.load(this, R.raw.b4, 1);
        noteC = soundPool.load(this, R.raw.c5, 1);
        noteCsharp = soundPool.load(this, R.raw.csharp5, 1);
        noteD = soundPool.load(this, R.raw.d5, 1);
        noteDsharp = soundPool.load(this, R.raw.dsharp5, 1);
        noteE = soundPool.load(this, R.raw.e5, 1);
        noteF = soundPool.load(this, R.raw.f5, 1);
        noteFsharp = soundPool.load(this, R.raw.fsharp5, 1);
        noteG = soundPool.load(this, R.raw.g5, 1);
        noteGsharp = soundPool.load(this, R.raw.gsharp5, 1);
        noteA4 = soundPool.load(this, R.raw.a5, 1);
        noteBflat4 = soundPool.load(this, R.raw.bflat5, 1);
        noteB4 = soundPool.load(this, R.raw.b5, 1);
        noteC5 = soundPool.load(this, R.raw.c6, 1);
        noteCsharp5 = soundPool.load(this, R.raw.csharp6, 1);
        noteD5 = soundPool.load(this, R.raw.d6, 1);
        noteDsharp5 = soundPool.load(this, R.raw.dsharp6, 1);
        noteE5 = soundPool.load(this, R.raw.e6, 1);
        noteF5 = soundPool.load(this, R.raw.f6, 1);
        noteFsharp5 = soundPool.load(this, R.raw.fsharp6, 1);
        noteG5 = soundPool.load(this, R.raw.g6, 1);
        noteGsharp5 = soundPool.load(this, R.raw.gsharp6, 1);

    }

    private void setListeners() {
        KeyboardNoteListener noteListener = new KeyboardNoteListener();
        buttonA.setOnClickListener(noteListener);
        buttonBflat.setOnClickListener(noteListener);
        buttonB.setOnClickListener(noteListener);
        buttonC.setOnClickListener(noteListener);
        buttonCsharp.setOnClickListener(noteListener);
        buttonD.setOnClickListener(noteListener);
        buttonDsharp.setOnClickListener(noteListener);
        buttonE.setOnClickListener(noteListener);
        buttonF.setOnClickListener(noteListener);
        buttonFsharp.setOnClickListener(noteListener);
        buttonG.setOnClickListener(noteListener);
        buttonGsharp.setOnClickListener(noteListener);
        buttonA4.setOnClickListener(noteListener);
        buttonBflat4.setOnClickListener(noteListener);
        buttonB4.setOnClickListener(noteListener);
        buttonC5.setOnClickListener(noteListener);
        buttonCsharp5.setOnClickListener(noteListener);
        buttonD5.setOnClickListener(noteListener);
        buttonDsharp5.setOnClickListener(noteListener);
        buttonE5.setOnClickListener(noteListener);
        buttonF5.setOnClickListener(noteListener);
        buttonFsharp5.setOnClickListener(noteListener);
        buttonG5.setOnClickListener(noteListener);
        buttonGsharp5.setOnClickListener(noteListener);

        buttonPlayScale.setOnClickListener(this);
        buttonPlayLife.setOnClickListener(this);
        buttonPlaySchu.setOnClickListener(this);
    }

    private void wireWidgets() {
        buttonA = findViewById(R.id.button_synth_a);
        buttonBflat = findViewById(R.id.button_synth_bflat);
        buttonB = findViewById(R.id.button_synth_b);
        buttonC = findViewById(R.id.button_synth_c);
        buttonCsharp = findViewById(R.id.button_synth_csharp);
        buttonD = findViewById(R.id.button_synth_d);
        buttonDsharp = findViewById(R.id.button_synth_dsharp);
        buttonE = findViewById(R.id.button_synth_e);
        buttonF = findViewById(R.id.button_synth_f);
        buttonFsharp = findViewById(R.id.button_synth_fsharp);
        buttonG = findViewById(R.id.button_synth_g);
        buttonGsharp = findViewById(R.id.button_synth_gsharp);
        buttonA4 = findViewById(R.id.button_synth_a4);
        buttonBflat4 = findViewById(R.id.button_synth_bflat4);
        buttonB4 = findViewById(R.id.button_synth_b4);
        buttonC5 = findViewById(R.id.button_synth_c5);
        buttonCsharp5 = findViewById(R.id.button_synth_csharp5);
        buttonD5 = findViewById(R.id.button_synth_d5);
        buttonDsharp5 = findViewById(R.id.button_synth_dsharp5);
        buttonE5 = findViewById(R.id.button_synth_e5);
        buttonF5 = findViewById(R.id.button_synth_f5);
        buttonFsharp5 = findViewById(R.id.button_synth_fsharp5);
        buttonG5 = findViewById(R.id.button_synth_g5);
        buttonGsharp5 = findViewById(R.id.button_synth_gsharp5);

        buttonPlayScale = findViewById(R.id.button_synth_scale);
        buttonPlayLife = findViewById(R.id.button_synth_life);
        buttonPlaySchu = findViewById(R.id.button_synth_schu);
    }

    @Override
    public void onClick(View view) {
        // one method to handle the clicks of all the buttons
        // but don't forget to tell the buttons who is doing the listening.
        switch(view.getId()) {
            case R.id.button_synth_scale:
                playScale();
                break;
            case R.id.button_synth_life:
                playLife();
                break;
            case R.id.button_synth_schu:
                scheduleSongs(500, getFl(), getOb(), getCl(), getBsn(), getHn(),
                        getTpt(), getTbn(), getTimp(), getVln1(), getVln2(), getBass());
                //playSchu();
                break;
        }
    }

    private Note[] getFl() {
        Note[] part = new Note[31];

        part[0] = new Note(noteB4, 25*(HALF_NOTE_120 + QUARTER_NOTE_120));

        part[1] = new Note(noteD5, HALF_NOTE_120 + QUARTER_NOTE_120 + HALF_NOTE_120 + EIGHTH_NOTE_120);
        part[2] = new Note(noteE5, EIGHTH_NOTE_120);

        part[3] = new Note(noteE5, HALF_NOTE_120);
        part[4] = new Note(noteFsharp5, 0); //1
        part[5] = new Note(noteE5, QUARTER_NOTE_120); //2

        part[6] = new Note(noteG5, 0); //1
        part[7] = new Note(noteE5, 3 * HALF_NOTE_120);

        part[8] = new Note(noteD5, HALF_NOTE_120 + QUARTER_NOTE_120 + HALF_NOTE_120 + EIGHTH_NOTE_120);
        part[9] = new Note(noteE5, 0); //1
        part[10] = new Note(noteD5, EIGHTH_NOTE_120); //2

        part[11] = new Note(noteE5, 0); //1
        part[12] = new Note(noteD5, QUARTER_NOTE_120); //2
        part[13] = new Note(noteCsharp5, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[14] = new Note(noteFsharp5, 0); //1
        part[15] = new Note(noteE5, EIGHTH_NOTE_120); //2

        part[16] = new Note(noteFsharp5, 0); //1
        part[17] = new Note(noteE5, QUARTER_NOTE_120); //2
        part[18] = new Note(noteD5, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[19] = new Note(noteFsharp5, 0); //1
        part[20] = new Note(noteE5, EIGHTH_NOTE_120); //2

        part[21] = new Note(noteFsharp5, 0); //1
        part[22] = new Note(noteE5, QUARTER_NOTE_120); //2
        part[23] = new Note(noteD5, QUARTER_NOTE_120); //2
        part[24] = new Note(noteFsharp5, EIGHTH_NOTE_120); //1
        part[25] = new Note(noteFsharp5, 0); //1
        part[26] = new Note(noteE5, EIGHTH_NOTE_120); //2

        part[27] = new Note(noteG5, 0); //1
        part[28] = new Note(noteFsharp5, 3 * HALF_NOTE_120); //2

        part[29] = new Note(noteD5, 0); //1
        part[30] = new Note(noteB4, QUARTER_NOTE_120); //2

        return part;
    }

    private Note[] getOb() {
        Note[] part = new Note[71];

        part[0] = new Note(noteB4, 12*(HALF_NOTE_120 + QUARTER_NOTE_120));

        part[1] = new Note(noteFsharp5, HALF_NOTE_120 + QUARTER_NOTE_120);

        part[2] = new Note(noteD5, QUARTER_NOTE_120 + EIGHTH_NOTE_120);
        part[3] = new Note(noteBflat4, EIGHTH_NOTE_120);
        part[4] = new Note(noteB4, EIGHTH_NOTE_120);
        part[5] = new Note(noteCsharp5, EIGHTH_NOTE_120);

        part[6] = new Note(noteFsharp5, HALF_NOTE_120 + QUARTER_NOTE_120);
        part[7] = new Note(noteD5, QUARTER_NOTE_120 + EIGHTH_NOTE_120);
        part[8] = new Note(noteBflat4, EIGHTH_NOTE_120);
        part[9] = new Note(noteB4, EIGHTH_NOTE_120);
        part[10] = new Note(noteCsharp5, EIGHTH_NOTE_120);

        part[11] = new Note(noteD5, HALF_NOTE_120 + QUARTER_NOTE_120);

        part[12] = new Note(noteE5, QUARTER_NOTE_120);
        part[13] = new Note(noteF5, QUARTER_NOTE_120 + EIGHTH_NOTE_120);
        part[14] = new Note(noteE5, EIGHTH_NOTE_120);

        part[15] = new Note(noteD5, QUARTER_NOTE_120);
        part[16] = new Note(noteCsharp5, HALF_NOTE_120);

        part[17] = new Note(noteD5, 3 * HALF_NOTE_120);

        part[18] = new Note(noteFsharp5, HALF_NOTE_120 + QUARTER_NOTE_120);

        part[19] = new Note(noteB4, QUARTER_NOTE_120 + EIGHTH_NOTE_120);
        part[20] = new Note(noteBflat4, EIGHTH_NOTE_120);
        part[21] = new Note(noteB4, EIGHTH_NOTE_120);
        part[22] = new Note(noteCsharp5, EIGHTH_NOTE_120);

        part[23] = new Note(noteFsharp5, HALF_NOTE_120 + QUARTER_NOTE_120);

        part[24] = new Note(noteB4, QUARTER_NOTE_120 + EIGHTH_NOTE_120);
        part[25] = new Note(noteBflat4, EIGHTH_NOTE_120);
        part[26] = new Note(noteB4, EIGHTH_NOTE_120);
        part[27] = new Note(noteCsharp5, EIGHTH_NOTE_120);

        part[28] = new Note(noteD5, 0); //1
        part[29] = new Note(noteA4, QUARTER_NOTE_120); //2
        part[30] = new Note(noteB4, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[31] = new Note(noteC5, EIGHTH_NOTE_120); //2

        part[32] = new Note(noteC5, QUARTER_NOTE_120); //2
        part[33] = new Note(noteB4, QUARTER_NOTE_120); //2
        part[34] = new Note(noteD5, EIGHTH_NOTE_120); //1
        part[35] = new Note(noteE5, 0); //1
        part[36] = new Note(noteD5, EIGHTH_NOTE_120); //2

        part[37] = new Note(noteE5, 0); //1
        part[38] = new Note(noteD5, QUARTER_NOTE_120); //2
        part[39] = new Note(noteCsharp5, QUARTER_NOTE_120); //2
        part[40] = new Note(noteFsharp5, 0); //1
        part[41] = new Note(noteE5, QUARTER_NOTE_120); //2

        part[42] = new Note(noteG5, 0); //1
        part[43] = new Note(noteE5, 3 * HALF_NOTE_120); //2

        part[44] = new Note(noteD5, 0); //1
        part[45] = new Note(noteA4, QUARTER_NOTE_120); //2
        part[46] = new Note(noteB4, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[47] = new Note(noteC5, EIGHTH_NOTE_120); //2

        part[48] = new Note(noteC5, QUARTER_NOTE_120); //2
        part[49] = new Note(noteB4, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[50] = new Note(noteE5, 0); //1
        part[51] = new Note(noteB4, EIGHTH_NOTE_120); //2

        part[52] = new Note(noteE5, 0); //1
        part[53] = new Note(noteB4, QUARTER_NOTE_120); //2
        part[54] = new Note(noteCsharp5, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[55] = new Note(noteFsharp5, 0); //1
        part[56] = new Note(noteCsharp5, EIGHTH_NOTE_120); //2

        part[57] = new Note(noteFsharp5, 0); //1
        part[58] = new Note(noteCsharp5, QUARTER_NOTE_120); //2
        part[59] = new Note(noteD5, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[60] = new Note(noteFsharp5, 0); //1
        part[61] = new Note(noteE5, EIGHTH_NOTE_120); //2

        part[62] = new Note(noteFsharp5, 0); //1
        part[63] = new Note(noteE5, QUARTER_NOTE_120); //2
        part[64] = new Note(noteD5, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[65] = new Note(noteBflat4, 0); //1
        part[66] = new Note(noteE5, EIGHTH_NOTE_120); //2

        part[67] = new Note(noteBflat4, 0); //1
        part[68] = new Note(noteE5, 3 * HALF_NOTE_120); //2

        part[69] = new Note(noteB4, 0); //1
        part[70] = new Note(noteD5, QUARTER_NOTE_120); //2

        return part;
    }

    private Note[] getCl() {
        Note[] part = new Note[88];

        part[0] = new Note(noteB4, 12*(HALF_NOTE_120 + QUARTER_NOTE_120));

        part[1] = new Note(noteFsharp5, HALF_NOTE_120 + QUARTER_NOTE_120);

        part[2] = new Note(noteD5, QUARTER_NOTE_120 + EIGHTH_NOTE_120);
        part[3] = new Note(noteBflat4, EIGHTH_NOTE_120);
        part[4] = new Note(noteB4, EIGHTH_NOTE_120);
        part[5] = new Note(noteCsharp5, EIGHTH_NOTE_120);

        part[6] = new Note(noteFsharp5, HALF_NOTE_120 + QUARTER_NOTE_120);

        part[7] = new Note(noteD5, QUARTER_NOTE_120 + EIGHTH_NOTE_120);
        part[8] = new Note(noteBflat4, EIGHTH_NOTE_120);
        part[9] = new Note(noteB4, EIGHTH_NOTE_120);
        part[10] = new Note(noteCsharp5, EIGHTH_NOTE_120);

        part[11] = new Note(noteD5, HALF_NOTE_120 + QUARTER_NOTE_120);

        part[12] = new Note(noteE5, QUARTER_NOTE_120);
        part[13] = new Note(noteF5, QUARTER_NOTE_120 + EIGHTH_NOTE_120);
        part[14] = new Note(noteE5, EIGHTH_NOTE_120);

        part[15] = new Note(noteD5, QUARTER_NOTE_120);
        part[16] = new Note(noteCsharp5, HALF_NOTE_120);

        part[17] = new Note(noteD5, 3 * HALF_NOTE_120);

        part[18] = new Note(noteFsharp5, HALF_NOTE_120 + QUARTER_NOTE_120);

        part[19] = new Note(noteB4, QUARTER_NOTE_120 + EIGHTH_NOTE_120);
        part[20] = new Note(noteBflat4, EIGHTH_NOTE_120);
        part[21] = new Note(noteB4, EIGHTH_NOTE_120);
        part[22] = new Note(noteCsharp5, EIGHTH_NOTE_120);

        part[23] = new Note(noteFsharp5, HALF_NOTE_120 + QUARTER_NOTE_120);

        part[24] = new Note(noteB4, QUARTER_NOTE_120 + EIGHTH_NOTE_120);
        part[25] = new Note(noteBflat4, EIGHTH_NOTE_120);
        part[26] = new Note(noteB4, EIGHTH_NOTE_120);
        part[27] = new Note(noteCsharp5, EIGHTH_NOTE_120);

        part[28] = new Note(noteD5, 0); //1
        part[29] = new Note(noteFsharp, QUARTER_NOTE_120); //2
        part[30] = new Note(noteB4, 0); //1
        part[31] = new Note(noteG, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[32] = new Note(noteC5, 0); //1
        part[33] = new Note(noteA4, EIGHTH_NOTE_120); //2

        part[34] = new Note(noteC5, 0); //1
        part[35] = new Note(noteA4, QUARTER_NOTE_120); //2
        part[36] = new Note(noteB4, 0); //1
        part[37] = new Note(noteG, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[38] = new Note(noteB4, 0); //1
        part[39] = new Note(noteGsharp, EIGHTH_NOTE_120); //2

        part[40] = new Note(noteB4, 0); //1
        part[41] = new Note(noteGsharp, QUARTER_NOTE_120); //2
        part[42] = new Note(noteCsharp5, 0); //1
        part[43] = new Note(noteA4, QUARTER_NOTE_120); //2
        part[44] = new Note(noteCsharp5, 0); //1
        part[45] = new Note(noteBflat4, QUARTER_NOTE_120); //2

        part[46] = new Note(noteCsharp5, 0); //1
        part[47] = new Note(noteBflat4, QUARTER_NOTE_120); //2
        part[48] = new Note(noteG5, 0); //1
        part[49] = new Note(noteE5, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[50] = new Note(noteFsharp5, EIGHTH_NOTE_120); //1

        part[51] = new Note(noteG5, 0); //1
        part[52] = new Note(noteE5, QUARTER_NOTE_120); //2
        part[53] = new Note(noteFsharp5, HALF_NOTE_120); //1

        part[54] = new Note(noteA4, 0); //1
        part[55] = new Note(noteFsharp, QUARTER_NOTE_120); //2
        part[56] = new Note(noteB4, 0); //1
        part[57] = new Note(noteG, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[58] = new Note(noteC5, 0); //1
        part[59] = new Note(noteA4, EIGHTH_NOTE_120); //2

        part[60] = new Note(noteC5, 0); //1
        part[61] = new Note(noteA4, QUARTER_NOTE_120); //2
        part[62] = new Note(noteB4, 0); //1
        part[63] = new Note(noteG, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[64] = new Note(noteB4, 0); //1
        part[65] = new Note(noteGsharp, EIGHTH_NOTE_120); //2

        part[66] = new Note(noteB4, 0); //1
        part[67] = new Note(noteGsharp, QUARTER_NOTE_120); //2
        part[68] = new Note(noteCsharp5, 0); //1
        part[69] = new Note(noteA4, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[70] = new Note(noteCsharp5, 0); //1
        part[71] = new Note(noteBflat4, EIGHTH_NOTE_120); //2

        part[72] = new Note(noteCsharp5, 0); //1
        part[73] = new Note(noteBflat4, QUARTER_NOTE_120); //2
        part[74] = new Note(noteD5, 0); //1
        part[75] = new Note(noteB4, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[76] = new Note(noteCsharp5, 0); //1
        part[77] = new Note(noteBflat4, EIGHTH_NOTE_120); //2

        part[78] = new Note(noteCsharp5, 0); //1
        part[79] = new Note(noteBflat4, QUARTER_NOTE_120); //2
        part[80] = new Note(noteD5, 0); //1
        part[81] = new Note(noteB4, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[82] = new Note(noteCsharp5, 0); //1
        part[83] = new Note(noteBflat4, EIGHTH_NOTE_120); //2

        part[84] = new Note(noteCsharp5, 0); //1
        part[85] = new Note(noteBflat4, 3 * HALF_NOTE_120); //2

        part[86] = new Note(noteD5, 0); //1
        part[87] = new Note(noteB4, QUARTER_NOTE_120); //2

        return part;
    }

    private Note[] getBsn() {
        Note[] part = new Note[59];

        part[0] = new Note(noteB4, 19 * (HALF_NOTE_120 + QUARTER_NOTE_120) + QUARTER_NOTE_120);

        part[1] = new Note(noteCsharp, 0); //1
        part[2] = new Note(noteBflat, HALF_NOTE_120 + 5 * (HALF_NOTE_120 + QUARTER_NOTE_120)); //2

        part[3] = new Note(noteA, 0); //1
        part[4] = new Note(noteFsharp, QUARTER_NOTE_120); //2
        part[5] = new Note(noteB, 0); //1
        part[6] = new Note(noteG, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[7] = new Note(noteC, 0); //1
        part[8] = new Note(noteA, QUARTER_NOTE_120); //2

        part[9] = new Note(noteC, 0); //1
        part[10] = new Note(noteA, QUARTER_NOTE_120); //2
        part[11] = new Note(noteB, 0); //1
        part[12] = new Note(noteG, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[13] = new Note(noteD, 0); //1
        part[14] = new Note(noteB, QUARTER_NOTE_120); //2

        part[15] = new Note(noteD, 0); //1
        part[16] = new Note(noteB, QUARTER_NOTE_120); //2
        part[17] = new Note(noteCsharp, 0); //1
        part[18] = new Note(noteA, QUARTER_NOTE_120); //2
        part[19] = new Note(noteCsharp, 0); //1
        part[20] = new Note(noteFsharp, QUARTER_NOTE_120); //2

        part[21] = new Note(noteCsharp, 0); //1
        part[22] = new Note(noteFsharp, QUARTER_NOTE_120); //2
        part[23] = new Note(noteCsharp, 0); //1
        part[24] = new Note(noteBflat, HALF_NOTE_120 + HALF_NOTE_120 + QUARTER_NOTE_120); //2

        part[25] = new Note(noteA, 0); //1
        part[26] = new Note(noteFsharp, QUARTER_NOTE_120); //2
        part[27] = new Note(noteB, 0); //1
        part[28] = new Note(noteG, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[29] = new Note(noteC, 0); //1
        part[30] = new Note(noteA, QUARTER_NOTE_120); //2

        part[31] = new Note(noteC, 0); //1
        part[32] = new Note(noteA, QUARTER_NOTE_120); //2
        part[33] = new Note(noteB, 0); //1
        part[34] = new Note(noteG, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[35] = new Note(noteD, 0); //1
        part[36] = new Note(noteB, QUARTER_NOTE_120); //2

        part[37] = new Note(noteD, 0); //1
        part[38] = new Note(noteB, QUARTER_NOTE_120); //2
        part[39] = new Note(noteCsharp, 0); //1
        part[40] = new Note(noteA, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[41] = new Note(noteE, 0); //1
        part[42] = new Note(noteCsharp, QUARTER_NOTE_120); //2

        part[43] = new Note(noteE, 0); //1
        part[44] = new Note(noteCsharp, QUARTER_NOTE_120); //2
        part[45] = new Note(noteD, 0); //1
        part[46] = new Note(noteB, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[47] = new Note(noteE, 0); //1
        part[48] = new Note(noteCsharp, QUARTER_NOTE_120); //2

        part[49] = new Note(noteE, 0); //1
        part[50] = new Note(noteCsharp, QUARTER_NOTE_120); //2
        part[51] = new Note(noteD, 0); //1
        part[52] = new Note(noteB, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[53] = new Note(noteCsharp, 0); //1
        part[54] = new Note(noteGsharp, QUARTER_NOTE_120); //2

        part[55] = new Note(noteCsharp, 0); //1
        part[56] = new Note(noteGsharp, 3 * HALF_NOTE_120); //2

        part[57] = new Note(noteD, 0); //1
        part[58] = new Note(noteB, QUARTER_NOTE_120); //2

        return part;
    }

    private Note[] getHn() {
        Note[] part = new Note[38];

        part[0] = new Note(noteB4, 19 * (HALF_NOTE_120 + QUARTER_NOTE_120) + QUARTER_NOTE_120);

        part[1] = new Note(noteG, 0); //1
        part[2] = new Note(noteE, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[3] = new Note(noteFsharp, EIGHTH_NOTE_120); //1

        part[4] = new Note(noteG, QUARTER_NOTE_120); //1
        part[5] = new Note(noteFsharp, HALF_NOTE_120 + HALF_NOTE_120 + QUARTER_NOTE_120); //1

        part[6] = new Note(noteFsharp, HALF_NOTE_120 + EIGHTH_NOTE_120);
        part[7] = new Note(noteG, EIGHTH_NOTE_120);

        part[8] = new Note(noteFsharp, HALF_NOTE_120 + QUARTER_NOTE_120);

        part[9] = new Note(noteFsharp, HALF_NOTE_120 + EIGHTH_NOTE_120);
        part[10] = new Note(noteG, EIGHTH_NOTE_120);

        part[11] = new Note(noteFsharp, QUARTER_NOTE_120);
        part[12] = new Note(noteD, HALF_NOTE_120 + HALF_NOTE_120 + EIGHTH_NOTE_120);
        part[13] = new Note(noteE, EIGHTH_NOTE_120);

        part[14] = new Note(noteE, HALF_NOTE_120);
        part[15] = new Note(noteFsharp, 0); //1
        part[16] = new Note(noteE, QUARTER_NOTE_120); //2

        part[17] = new Note(noteG, 0); //1
        part[18] = new Note(noteE, QUARTER_NOTE_120); //2
        part[19] = new Note(noteG, 0); //1
        part[20] = new Note(noteE, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[21] = new Note(noteFsharp, EIGHTH_NOTE_120); //1

        part[22] = new Note(noteG, 0); //1
        part[23] = new Note(noteE, QUARTER_NOTE_120); //2
        part[24] = new Note(noteFsharp, HALF_NOTE_120 + QUARTER_NOTE_120); //1

        part[25] = new Note(noteD, HALF_NOTE_120 + HALF_NOTE_120 + EIGHTH_NOTE_120);
        part[26] = new Note(noteE, EIGHTH_NOTE_120);

        part[27] = new Note(noteE, HALF_NOTE_120 + EIGHTH_NOTE_120);
        part[28] = new Note(noteFsharp, EIGHTH_NOTE_120);

        part[29] = new Note(noteFsharp, HALF_NOTE_120 + EIGHTH_NOTE_120);
        part[30] = new Note(noteFsharp, EIGHTH_NOTE_120);

        part[31] = new Note(noteFsharp, HALF_NOTE_120 + EIGHTH_NOTE_120);
        part[32] = new Note(noteFsharp, 0); //1
        part[33] = new Note(noteE, EIGHTH_NOTE_120); //2

        part[34] = new Note(noteFsharp, 0); //1
        part[35] = new Note(noteE, 3 * HALF_NOTE_120); //2

        part[36] = new Note(noteFsharp, 0); //1
        part[37] = new Note(noteD, QUARTER_NOTE_120); //2

        return part;
    }

    private Note[] getTpt() {
        Note[] part = new Note[23];

        part[0] = new Note(noteB4, 27 * (HALF_NOTE_120 + QUARTER_NOTE_120) + HALF_NOTE_120);

        part[1] = new Note(noteD5, 0); //1
        part[2] = new Note(noteD, QUARTER_NOTE_120); //2

        part[3] = new Note(noteD5, 0); //1
        part[4] = new Note(noteD, 6 * (HALF_NOTE_120 + QUARTER_NOTE_120) + HALF_NOTE_120 + EIGHTH_NOTE_120);
        part[5] = new Note(noteE5, 0); //1
        part[6] = new Note(noteD5, EIGHTH_NOTE_120); //2

        part[7] = new Note(noteE5, 0); //1
        part[8] = new Note(noteD5, QUARTER_NOTE_120 + EIGHTH_NOTE_120);
        part[9] = new Note(noteE5, 0); //1
        part[10] = new Note(noteD5, EIGHTH_NOTE_120); //2
        part[11] = new Note(noteE5, 0); //1
        part[12] = new Note(noteD5, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2

        part[13] = new Note(noteE5, 0); //1
        part[14] = new Note(noteD5, EIGHTH_NOTE_120); //2
        part[15] = new Note(noteE5, 0); //1
        part[16] = new Note(noteD5, QUARTER_NOTE_120); //2
        part[17] = new Note(noteE5, 0); //1
        part[18] = new Note(noteD5, EIGHTH_NOTE_120); //2
        part[19] = new Note(noteE5, 0); //1
        part[20] = new Note(noteD5, EIGHTH_NOTE_120); //2

        part[21] = new Note(noteE5, 0); //1
        part[22] = new Note(noteA4, QUARTER_NOTE_120); //2

        return part;
    }

    private Note[] getTbn() {
        Note[] part = new Note[23];

        part[0] = new Note(noteB4, 19 * (HALF_NOTE_120 + QUARTER_NOTE_120) + QUARTER_NOTE_120);

        part[1] = new Note(noteFsharp, HALF_NOTE_120 + 7 * (HALF_NOTE_120 + QUARTER_NOTE_120) + HALF_NOTE_120);

        part[2] = new Note(noteCsharp, 0); //1
        part[3] = new Note(noteBflat, 0); //2
        part[4] = new Note(noteFsharp, QUARTER_NOTE_120); //3

        part[5] = new Note(noteCsharp, 0); //1
        part[6] = new Note(noteBflat, 0); //2
        part[7] = new Note(noteFsharp, 7 * (HALF_NOTE_120 + QUARTER_NOTE_120)); //3

        part[8] = new Note(noteCsharp, 0); //1
        part[9] = new Note(noteBflat, 0); //2
        part[10] = new Note(noteFsharp, HALF_NOTE_120); //3

        part[11] = new Note(noteCsharp, 0); //1
        part[12] = new Note(noteBflat, 0); //2
        part[13] = new Note(noteFsharp, HALF_NOTE_120); //3

        part[14] = new Note(noteCsharp, 0); //1
        part[15] = new Note(noteBflat, 0); //2
        part[16] = new Note(noteFsharp, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //3

        part[17] = new Note(noteCsharp, 0); //1
        part[18] = new Note(noteBflat, 0); //2
        part[19] = new Note(noteFsharp, EIGHTH_NOTE_120); //3

        part[20] = new Note(noteD, 0); //1
        part[21] = new Note(noteB, 0); //2
        part[22] = new Note(noteB, QUARTER_NOTE_120); //3

        return part;
    }

    private Note[] getTimp() {
        Note[] part = new Note[12];

        part[0] = new Note(noteB4, 27 * (HALF_NOTE_120 + QUARTER_NOTE_120) + HALF_NOTE_120);

        part[1] = new Note(noteG, QUARTER_NOTE_120);

        part[2] = new Note(noteG, 6 * (HALF_NOTE_120 + QUARTER_NOTE_120) + HALF_NOTE_120 + EIGHTH_NOTE_120);

        part[3] = new Note(noteG, EIGHTH_NOTE_120);

        part[4] = new Note(noteG, QUARTER_NOTE_120 + EIGHTH_NOTE_120);
        part[5] = new Note(noteG, EIGHTH_NOTE_120);
        part[6] = new Note(noteG, QUARTER_NOTE_120 + EIGHTH_NOTE_120);

        part[7] = new Note(noteG, EIGHTH_NOTE_120);
        part[8] = new Note(noteG, QUARTER_NOTE_120);
        part[9] = new Note(noteG, EIGHTH_NOTE_120);
        part[10] = new Note(noteG, EIGHTH_NOTE_120);

        part[11] = new Note(noteCsharp, QUARTER_NOTE_120);

        return part;
    }

    private Note[] getVln1() {
        Note[] part = new Note[353];

        part[0] = new Note(noteB4, 8 * (HALF_NOTE_120 + QUARTER_NOTE_120));

        part[1] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[2] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[3] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[4] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[5] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[6] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[7] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[8] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[9] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[10] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[11] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[12] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[13] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[14] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[15] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[16] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[17] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[18] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[19] = new Note(noteBflat4, SIXTEENTH_NOTE_120);
        part[20] = new Note(noteBflat4, SIXTEENTH_NOTE_120);
        part[21] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[22] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[23] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[24] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);

        part[25] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[26] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[27] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[28] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[29] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[30] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[31] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[32] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[33] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[34] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[35] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[36] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[37] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[38] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[39] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[40] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[41] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[42] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[43] = new Note(noteBflat4, SIXTEENTH_NOTE_120);
        part[44] = new Note(noteBflat4, SIXTEENTH_NOTE_120);
        part[45] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[46] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[47] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[48] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);

        part[49] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[50] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[51] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[52] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[53] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[54] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[55] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[56] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[57] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[58] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[59] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[60] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[61] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[62] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[63] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[64] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[65] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[66] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[67] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[68] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[69] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[70] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[71] = new Note(noteBflat4, SIXTEENTH_NOTE_120);
        part[72] = new Note(noteBflat4, SIXTEENTH_NOTE_120);

        part[73] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[74] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[75] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[76] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[77] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[78] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[79] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[80] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[81] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[82] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[83] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[84] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[85] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[86] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[87] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[88] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[89] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[90] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[91] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[92] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[93] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[94] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[95] = new Note(noteBflat4, SIXTEENTH_NOTE_120);
        part[96] = new Note(noteBflat4, SIXTEENTH_NOTE_120);

        part[97] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[98] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[99] = new Note(noteGsharp, SIXTEENTH_NOTE_120);
        part[100] = new Note(noteGsharp, SIXTEENTH_NOTE_120);
        part[101] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[102] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[103] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[104] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[105] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[106] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[107] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[108] = new Note(noteA4, SIXTEENTH_NOTE_120);

        part[109] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[110] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[111] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[112] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[113] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[114] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[115] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[116] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[117] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[118] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[119] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[120] = new Note(noteB4, SIXTEENTH_NOTE_120);

        part[121] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[122] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[123] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[124] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[125] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[126] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[127] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[128] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[129] = new Note(noteBflat4, SIXTEENTH_NOTE_120);
        part[130] = new Note(noteBflat4, SIXTEENTH_NOTE_120);
        part[131] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[132] = new Note(noteA4, SIXTEENTH_NOTE_120);

        part[133] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[134] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[135] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[136] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[137] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[138] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[139] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[140] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[141] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[142] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[143] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[144] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[145] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[146] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[147] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[148] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[149] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[150] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[151] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[152] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[153] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[154] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[155] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[156] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[157] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[158] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[159] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[160] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[161] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[162] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[163] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[164] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[165] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[166] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[167] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[168] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[169] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[170] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[171] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[172] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[173] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[174] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[175] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[176] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[177] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[178] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[179] = new Note(noteBflat4, SIXTEENTH_NOTE_120);
        part[180] = new Note(noteBflat4, SIXTEENTH_NOTE_120);

        part[181] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[182] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[183] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[184] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[185] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[186] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[187] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[188] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[189] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[190] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[191] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[192] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[193] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[194] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[195] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[196] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[197] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[198] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[199] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[200] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[201] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[202] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[203] = new Note(noteBflat4, SIXTEENTH_NOTE_120);
        part[204] = new Note(noteBflat4, SIXTEENTH_NOTE_120);

        part[205] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[206] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[207] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[208] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[209] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[210] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[211] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[212] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[213] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[214] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[215] = new Note(noteC5, SIXTEENTH_NOTE_120);
        part[216] = new Note(noteC5, SIXTEENTH_NOTE_120);

        part[217] = new Note(noteC5, SIXTEENTH_NOTE_120);
        part[218] = new Note(noteC5, SIXTEENTH_NOTE_120);
        part[219] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[220] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[221] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[222] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[223] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[224] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[225] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[226] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[227] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[228] = new Note(noteD5, SIXTEENTH_NOTE_120);

        part[229] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[230] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[231] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[232] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[233] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[234] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[235] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[236] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[237] = new Note(noteFsharp5, 0); //1
        part[238] = new Note(noteE5, SIXTEENTH_NOTE_120); //2
        part[239] = new Note(noteFsharp5, 0); //1
        part[240] = new Note(noteE5, SIXTEENTH_NOTE_120); //2
        part[241] = new Note(noteFsharp5, 0); //1
        part[242] = new Note(noteE5, SIXTEENTH_NOTE_120); //2
        part[243] = new Note(noteFsharp5, 0); //1
        part[244] = new Note(noteE5, SIXTEENTH_NOTE_120); //2

        part[245] = new Note(noteG5, 0); //1
        part[246] = new Note(noteE5, EIGHTH_NOTE_120); //2
        part[247] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[248] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[249] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[250] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[251] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[252] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[253] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[254] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[255] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[256] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[257] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[258] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[259] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[260] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[261] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[262] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[263] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[264] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[265] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[266] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[267] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[268] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[269] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[270] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[271] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[272] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[273] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[274] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[275] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[276] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[277] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[278] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[279] = new Note(noteC5, SIXTEENTH_NOTE_120);
        part[280] = new Note(noteC5, SIXTEENTH_NOTE_120);

        part[281] = new Note(noteC5, SIXTEENTH_NOTE_120);
        part[282] = new Note(noteC5, SIXTEENTH_NOTE_120);
        part[283] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[284] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[285] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[286] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[287] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[288] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[289] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[290] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[291] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[292] = new Note(noteD5, SIXTEENTH_NOTE_120);

        part[293] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[294] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[295] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[296] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[297] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[298] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[299] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[300] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[301] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[302] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[303] = new Note(noteE5, 0); //1
        part[304] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2
        part[305] = new Note(noteE5, 0); //1
        part[306] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2

        part[307] = new Note(noteE5, 0); //1
        part[308] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2
        part[309] = new Note(noteE5, 0); //1
        part[310] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2
        part[311] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[312] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[313] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[314] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[315] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[316] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[317] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[318] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[319] = new Note(noteE5, 0); //1
        part[320] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2
        part[321] = new Note(noteE5, 0); //1
        part[322] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2

        part[323] = new Note(noteE5, 0); //1
        part[324] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2
        part[325] = new Note(noteE5, 0); //1
        part[326] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2
        part[327] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[328] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[329] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[330] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[331] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[332] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[333] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[334] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[335] = new Note(noteBflat4, 0); //1
        part[336] = new Note(noteCsharp5, EIGHTH_NOTE_120); //2

        part[337] = new Note(noteBflat4, 0); //1
        part[338] = new Note(noteCsharp5, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[339] = new Note(noteBflat4, 0); //1
        part[340] = new Note(noteCsharp5, EIGHTH_NOTE_120); //2
        part[341] = new Note(noteBflat4, 0); //1
        part[342] = new Note(noteCsharp5, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2

        part[343] = new Note(noteBflat4, 0); //1
        part[344] = new Note(noteCsharp5, EIGHTH_NOTE_120); //2
        part[345] = new Note(noteBflat4, 0); //1
        part[346] = new Note(noteCsharp5, QUARTER_NOTE_120); //2
        part[347] = new Note(noteBflat4, 0); //1
        part[348] = new Note(noteCsharp5, EIGHTH_NOTE_120); //2
        part[349] = new Note(noteBflat4, 0); //1
        part[350] = new Note(noteCsharp5, EIGHTH_NOTE_120); //2

        part[351] = new Note(noteB4, 0); //1
        part[352] = new Note(noteD5, QUARTER_NOTE_120); //2

        return part;
    }

    private Note[] getVln2() {
        Note[] part = new Note[200];

        //TODO: finish
        part[0] = new Note(noteB4, 8 * (HALF_NOTE_120 + QUARTER_NOTE_120));

        part[1] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[2] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[3] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[4] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[5] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[6] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[7] = new Note(noteB, SIXTEENTH_NOTE_120);
        part[8] = new Note(noteB, SIXTEENTH_NOTE_120);
        part[9] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[10] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[11] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[12] = new Note(noteD, SIXTEENTH_NOTE_120);

        part[13] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[14] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[15] = new Note(noteDsharp, SIXTEENTH_NOTE_120);
        part[16] = new Note(noteDsharp, SIXTEENTH_NOTE_120);
        part[17] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[18] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[19] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[20] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[21] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[22] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[23] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[24] = new Note(noteE, SIXTEENTH_NOTE_120);

        part[25] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[26] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[27] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[28] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[29] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[30] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[31] = new Note(noteB, SIXTEENTH_NOTE_120);
        part[32] = new Note(noteB, SIXTEENTH_NOTE_120);
        part[33] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[34] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[35] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[36] = new Note(noteD, SIXTEENTH_NOTE_120);

        part[37] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[38] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[39] = new Note(noteDsharp, SIXTEENTH_NOTE_120);
        part[40] = new Note(noteDsharp, SIXTEENTH_NOTE_120);
        part[41] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[42] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[43] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[44] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[45] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[46] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[47] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[48] = new Note(noteE, SIXTEENTH_NOTE_120);

        part[49] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[50] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[51] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[52] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[53] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[54] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[55] = new Note(noteB, SIXTEENTH_NOTE_120);
        part[56] = new Note(noteB, SIXTEENTH_NOTE_120);
        part[57] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[58] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[59] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[60] = new Note(noteD, SIXTEENTH_NOTE_120);

        part[61] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[62] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[63] = new Note(noteDsharp, SIXTEENTH_NOTE_120);
        part[64] = new Note(noteDsharp, SIXTEENTH_NOTE_120);
        part[65] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[66] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[67] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[68] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[69] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[70] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[71] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[72] = new Note(noteE, SIXTEENTH_NOTE_120);

        part[73] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[74] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[75] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[76] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[77] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[78] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[79] = new Note(noteB, SIXTEENTH_NOTE_120);
        part[80] = new Note(noteB, SIXTEENTH_NOTE_120);
        part[81] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[82] = new Note(noteCsharp, SIXTEENTH_NOTE_120);
        part[83] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[84] = new Note(noteD, SIXTEENTH_NOTE_120);

        part[85] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[86] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[87] = new Note(noteDsharp, SIXTEENTH_NOTE_120);
        part[88] = new Note(noteDsharp, SIXTEENTH_NOTE_120);
        part[89] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[90] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[91] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[92] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[93] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[94] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[95] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[96] = new Note(noteE, SIXTEENTH_NOTE_120);

        part[97] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[98] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[99] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[100] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[101] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[102] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[103] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[104] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[105] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[106] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[107] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[108] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        //start here : page 2, system 2, first measure
        part[109] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[110] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[111] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[112] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[113] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[114] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[115] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[116] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[117] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[118] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[119] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[120] = new Note(noteB4, SIXTEENTH_NOTE_120);

        part[121] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[122] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[123] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[124] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[125] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[126] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[127] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[128] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[129] = new Note(noteBflat4, SIXTEENTH_NOTE_120);
        part[130] = new Note(noteBflat4, SIXTEENTH_NOTE_120);
        part[131] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[132] = new Note(noteA4, SIXTEENTH_NOTE_120);

        part[133] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[134] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[135] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[136] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[137] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[138] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[139] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[140] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[141] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[142] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[143] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[144] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[145] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[146] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[147] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[148] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[149] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[150] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[151] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[152] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[153] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[154] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[155] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[156] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[157] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[158] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[159] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[160] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[161] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[162] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[163] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[164] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[165] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[166] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[167] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[168] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[169] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[170] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[171] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[172] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[173] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[174] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[175] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[176] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[177] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[178] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[179] = new Note(noteBflat4, SIXTEENTH_NOTE_120);
        part[180] = new Note(noteBflat4, SIXTEENTH_NOTE_120);

        part[181] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[182] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[183] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[184] = new Note(noteF, SIXTEENTH_NOTE_120);
        part[185] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[186] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[187] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[188] = new Note(noteD, SIXTEENTH_NOTE_120);
        part[189] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[190] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[191] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[192] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[193] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[194] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[195] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[196] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[197] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[198] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[199] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[200] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[201] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[202] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[203] = new Note(noteBflat4, SIXTEENTH_NOTE_120);
        part[204] = new Note(noteBflat4, SIXTEENTH_NOTE_120);

        part[205] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[206] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[207] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[208] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[209] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[210] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[211] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[212] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[213] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[214] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[215] = new Note(noteC5, SIXTEENTH_NOTE_120);
        part[216] = new Note(noteC5, SIXTEENTH_NOTE_120);

        part[217] = new Note(noteC5, SIXTEENTH_NOTE_120);
        part[218] = new Note(noteC5, SIXTEENTH_NOTE_120);
        part[219] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[220] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[221] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[222] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[223] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[224] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[225] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[226] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[227] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[228] = new Note(noteD5, SIXTEENTH_NOTE_120);

        part[229] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[230] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[231] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[232] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[233] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[234] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[235] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[236] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[237] = new Note(noteFsharp5, 0); //1
        part[238] = new Note(noteE5, SIXTEENTH_NOTE_120); //2
        part[239] = new Note(noteFsharp5, 0); //1
        part[240] = new Note(noteE5, SIXTEENTH_NOTE_120); //2
        part[241] = new Note(noteFsharp5, 0); //1
        part[242] = new Note(noteE5, SIXTEENTH_NOTE_120); //2
        part[243] = new Note(noteFsharp5, 0); //1
        part[244] = new Note(noteE5, SIXTEENTH_NOTE_120); //2

        part[245] = new Note(noteG5, 0); //1
        part[246] = new Note(noteE5, EIGHTH_NOTE_120); //2
        part[247] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[248] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[249] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[250] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[251] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[252] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[253] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[254] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[255] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[256] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[257] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[258] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[259] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[260] = new Note(noteG, SIXTEENTH_NOTE_120);
        part[261] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[262] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[263] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[264] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[265] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[266] = new Note(noteE, SIXTEENTH_NOTE_120);
        part[267] = new Note(noteFsharp, SIXTEENTH_NOTE_120);
        part[268] = new Note(noteFsharp, SIXTEENTH_NOTE_120);

        part[269] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[270] = new Note(noteA4, SIXTEENTH_NOTE_120);
        part[271] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[272] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[273] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[274] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[275] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[276] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[277] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[278] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[279] = new Note(noteC5, SIXTEENTH_NOTE_120);
        part[280] = new Note(noteC5, SIXTEENTH_NOTE_120);

        part[281] = new Note(noteC5, SIXTEENTH_NOTE_120);
        part[282] = new Note(noteC5, SIXTEENTH_NOTE_120);
        part[283] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[284] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[285] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[286] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[287] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[288] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[289] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[290] = new Note(noteB4, SIXTEENTH_NOTE_120);
        part[291] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[292] = new Note(noteD5, SIXTEENTH_NOTE_120);

        part[293] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[294] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[295] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[296] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[297] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[298] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[299] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[300] = new Note(noteE5, SIXTEENTH_NOTE_120);
        part[301] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[302] = new Note(noteCsharp5, SIXTEENTH_NOTE_120);
        part[303] = new Note(noteE5, 0); //1
        part[304] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2
        part[305] = new Note(noteE5, 0); //1
        part[306] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2

        part[307] = new Note(noteE5, 0); //1
        part[308] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2
        part[309] = new Note(noteE5, 0); //1
        part[310] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2
        part[311] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[312] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[313] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[314] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[315] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[316] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[317] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[318] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[319] = new Note(noteE5, 0); //1
        part[320] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2
        part[321] = new Note(noteE5, 0); //1
        part[322] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2

        part[323] = new Note(noteE5, 0); //1
        part[324] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2
        part[325] = new Note(noteE5, 0); //1
        part[326] = new Note(noteCsharp5, SIXTEENTH_NOTE_120); //2
        part[327] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[328] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[329] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[330] = new Note(noteD5, SIXTEENTH_NOTE_120);
        part[331] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[332] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[333] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[334] = new Note(noteFsharp5, SIXTEENTH_NOTE_120);
        part[335] = new Note(noteBflat4, 0); //1
        part[336] = new Note(noteCsharp5, EIGHTH_NOTE_120); //2

        part[337] = new Note(noteBflat4, 0); //1
        part[338] = new Note(noteCsharp5, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2
        part[339] = new Note(noteBflat4, 0); //1
        part[340] = new Note(noteCsharp5, EIGHTH_NOTE_120); //2
        part[341] = new Note(noteBflat4, 0); //1
        part[342] = new Note(noteCsharp5, QUARTER_NOTE_120 + EIGHTH_NOTE_120); //2

        part[343] = new Note(noteBflat4, 0); //1
        part[344] = new Note(noteCsharp5, EIGHTH_NOTE_120); //2
        part[345] = new Note(noteBflat4, 0); //1
        part[346] = new Note(noteCsharp5, QUARTER_NOTE_120); //2
        part[347] = new Note(noteBflat4, 0); //1
        part[348] = new Note(noteCsharp5, EIGHTH_NOTE_120); //2
        part[349] = new Note(noteBflat4, 0); //1
        part[350] = new Note(noteCsharp5, EIGHTH_NOTE_120); //2

        part[351] = new Note(noteB4, 0); //1
        part[352] = new Note(noteD5, QUARTER_NOTE_120); //2

        return part;
    }

    private Note[] getBass() {
        Note[] part = new Note[150];

        //TODO: finish

        return part;
    }


    private void playScale() {
        Song scale = new Song();

        scale.add(new Note(noteA));
        scale.add(new Note(noteBflat));
        scale.add(new Note(noteB));
        scale.add(new Note(noteC));
        scale.add(new Note(noteCsharp));
        scale.add(new Note(noteD));
        scale.add(new Note(noteDsharp));
        scale.add(new Note(noteE));
        scale.add(new Note(noteF));
        scale.add(new Note(noteFsharp));
        scale.add(new Note(noteG));
        scale.add(new Note(noteGsharp));
        scale.add(new Note(noteA4));
        scale.add(new Note(noteBflat4));
        scale.add(new Note(noteB4));
        scale.add(new Note(noteC5));
        scale.add(new Note(noteCsharp5));
        scale.add(new Note(noteD5));
        scale.add(new Note(noteDsharp5));
        scale.add(new Note(noteE5));
        scale.add(new Note(noteF5));
        scale.add(new Note(noteFsharp5));
        scale.add(new Note(noteG5));
        scale.add(new Note(noteGsharp5));

        // play all the notes one at a time with a delay in between

        playSong(scale);
    }

    private void playSong(Song song) {
        for (Note n : song.getNotes()) {
            playNote(n);
            delay(n.getDelay());
        }
    }

    private void playLife() {
        Song life = new Song();

        life.add(new Note(noteA4, WHOLE_NOTE_120 + QUARTER_NOTE_120));
        life.add(new Note(noteA4, EIGHTH_NOTE_120));
        life.add(new Note(noteB4, EIGHTH_NOTE_120));
        life.add(new Note(noteCsharp5, EIGHTH_NOTE_120));
        life.add(new Note(noteD5, EIGHTH_NOTE_120));
        life.add(new Note(noteCsharp5, EIGHTH_NOTE_120));
        life.add(new Note(noteB4, EIGHTH_NOTE_120));
        life.add(new Note(noteA4, WHOLE_NOTE_120 + WHOLE_NOTE_120));
        life.add(new Note(noteA4, WHOLE_NOTE_120 + QUARTER_NOTE_120));
        life.add(new Note(noteA4, EIGHTH_NOTE_120));
        life.add(new Note(noteB4, EIGHTH_NOTE_120));
        life.add(new Note(noteCsharp5, EIGHTH_NOTE_120));
        life.add(new Note(noteD5, EIGHTH_NOTE_120));
        life.add(new Note(noteCsharp5, EIGHTH_NOTE_120));
        life.add(new Note(noteB4, EIGHTH_NOTE_120));
        life.add(new Note(noteA4, WHOLE_NOTE_120 + WHOLE_NOTE_120));
        life.add(new Note(noteD5, WHOLE_NOTE_120 + QUARTER_NOTE_120));
        life.add(new Note(noteD5, EIGHTH_NOTE_120));
        life.add(new Note(noteE5, EIGHTH_NOTE_120));
        life.add(new Note(noteFsharp5, EIGHTH_NOTE_120));
        life.add(new Note(noteG5, EIGHTH_NOTE_120));
        life.add(new Note(noteFsharp5, EIGHTH_NOTE_120));
        life.add(new Note(noteE5, EIGHTH_NOTE_120));
        life.add(new Note(noteD5, QUARTER_NOTE_120));
        life.add(new Note(noteB4, WHOLE_NOTE_120));
        life.add(new Note(noteD5, EIGHTH_NOTE_120));
        life.add(new Note(noteE5, EIGHTH_NOTE_120));
        life.add(new Note(noteFsharp5, EIGHTH_NOTE_120));
        life.add(new Note(noteG5, EIGHTH_NOTE_120));
        life.add(new Note(noteFsharp5, EIGHTH_NOTE_120));
        life.add(new Note(noteE5, EIGHTH_NOTE_120));
        life.add(new Note(noteD5, QUARTER_NOTE_120 + EIGHTH_NOTE_120));
        life.add(new Note(noteB4, EIGHTH_NOTE_120));
        life.add(new Note(noteCsharp5, QUARTER_NOTE_120 + EIGHTH_NOTE_120));
        life.add(new Note(noteB4, EIGHTH_NOTE_120));
        life.add(new Note(noteD5, QUARTER_NOTE_120 + EIGHTH_NOTE_120));
        life.add(new Note(noteB4, EIGHTH_NOTE_120));
        life.add(new Note(noteD5, EIGHTH_NOTE_120));
        life.add(new Note(noteCsharp5, EIGHTH_NOTE_120));
        life.add(new Note(noteB4, EIGHTH_NOTE_120));
        life.add(new Note(noteBflat4, EIGHTH_NOTE_120));
        life.add(new Note(noteB4, WHOLE_NOTE_120));

        playSong(life);
    }

    // TODO: Delete once new functionality is working
    private void playSchu() {
        Song schu = new Song();

        //bar 1
        schu.add(new Note(noteB4, HALF_NOTE_120 + QUARTER_NOTE_120));
        schu.add(new Note(noteCsharp5, HALF_NOTE_120));
        schu.add(new Note(noteD5, QUARTER_NOTE_120));
        schu.add(new Note(noteB4, HALF_NOTE_120 + QUARTER_NOTE_120));
        schu.add(new Note(noteA4, QUARTER_NOTE_120));
        schu.add(new Note(noteFsharp, QUARTER_NOTE_120));
        schu.add(new Note(noteG, QUARTER_NOTE_120));
        schu.add(new Note(noteD, HALF_NOTE_120));
        schu.add(new Note(noteCsharp, QUARTER_NOTE_120));
        schu.add(new Note(noteFsharp, 3*(HALF_NOTE_120 + QUARTER_NOTE_120)));

        //bar 2
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteF, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteF, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //vln2 and bass
        schu.add(new Note(noteD, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteB, 0)); //vln2
        schu.add(new Note(noteD, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1

        //bar 3
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteDsharp, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteDsharp, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteBflat4, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteBflat4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln1

        //bar 4
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteF, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteF, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //vln2 and bass
        schu.add(new Note(noteD, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteB, 0)); //vln2
        schu.add(new Note(noteD, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1

        //bar 5
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteDsharp, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteDsharp, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteBflat4, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteBflat4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln1

        //bar 6
        schu.add(new Note(noteFsharp5, 0)); //ob
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteF, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteF, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //vln2 and bass
        schu.add(new Note(noteD, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteB, 0)); //vln2
        schu.add(new Note(noteD, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1

        //bar 7
        schu.add(new Note(noteB4, 0)); //ob
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteDsharp, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteDsharp, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteBflat4, 0)); //ob
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB4, 0)); //ob
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp5, 0)); //ob
        schu.add(new Note(noteG, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteBflat4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteBflat4, SIXTEENTH_NOTE_120)); //vln1

        //bar 8
        schu.add(new Note(noteFsharp5, 0)); //ob
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteF, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteF, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //vln2 and bass
        schu.add(new Note(noteD, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteB, 0)); //vln2
        schu.add(new Note(noteD, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1

        //bar 9
        schu.add(new Note(noteB4, 0)); //ob
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteDsharp, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteDsharp, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteBflat4, 0)); //ob
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB4, 0)); //ob
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp5, 0)); //ob
        schu.add(new Note(noteG, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteBflat4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteBflat4, SIXTEENTH_NOTE_120)); //vln1

        //bar 10
        schu.add(new Note(noteD5, 0)); //ob
        schu.add(new Note(noteD, 0)); //bass
        schu.add(new Note(noteFsharp, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteF, 0)); //vln2
        schu.add(new Note(noteGsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteF, 0)); //vln2
        schu.add(new Note(noteGsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //bass
        schu.add(new Note(noteFsharp, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1

        //bar 11
        schu.add(new Note(noteE5, 0)); //ob
        schu.add(new Note(noteG, 0)); //bass
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteBflat4, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteBflat4, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteF5, 0)); //ob
        schu.add(new Note(noteB4, 0)); //vln2
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB4, 0)); //vln2
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //bass
        schu.add(new Note(noteBflat4, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteBflat4, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //bass
        schu.add(new Note(noteB4, 0)); //vln2
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB4, 0)); //vln2
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE5, 0)); //ob
        schu.add(new Note(noteGsharp, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1

        //bar 12
        schu.add(new Note(noteD5, 0)); //ob
        schu.add(new Note(noteA, 0)); //bass
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp5, 0)); //ob
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteA, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteA, 0)); //bass
        schu.add(new Note(noteFsharp, 0)); //vln2
        schu.add(new Note(noteBflat4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp, 0)); //vln2
        schu.add(new Note(noteBflat4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteA, 0)); //bass
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1

        //bar 13
        schu.add(new Note(noteD5, 0)); //ob
        schu.add(new Note(noteD, 0)); //bass
        schu.add(new Note(noteFsharp, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //bsn1
        schu.add(new Note(noteBflat, 0)); //bsn2
        schu.add(new Note(noteG, 0)); //horn1
        schu.add(new Note(noteE, 0)); //horn2
        schu.add(new Note(noteFsharp, 0)); //bass
        schu.add(new Note(noteC, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteC, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp, 0)); //horn1
        schu.add(new Note(noteC, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteC, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1

        //bar 14
        schu.add(new Note(noteG, 0)); //horn1
        schu.add(new Note(noteC, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteC, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp, 0)); //horn1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp, 0)); //bass
        schu.add(new Note(noteC, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteC, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp, 0)); //bass
        schu.add(new Note(noteC, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteC, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1

        //bar 15
        schu.add(new Note(noteFsharp5, 0)); //ob
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteF, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteF, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //vln2 and bass
        schu.add(new Note(noteD, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteB, 0)); //vln2
        schu.add(new Note(noteD, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1

        //bar 16
        schu.add(new Note(noteB4, 0)); //ob
        schu.add(new Note(noteFsharp, 0)); //horn
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteDsharp, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteDsharp, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteBflat4, 0)); //ob
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB4, 0)); //ob
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp5, 0)); //ob
        schu.add(new Note(noteG, 0)); //horn
        schu.add(new Note(noteG, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteBflat4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteBflat4, SIXTEENTH_NOTE_120)); //vln1

        //bar 17
        schu.add(new Note(noteFsharp5, 0)); //ob
        schu.add(new Note(noteFsharp, 0)); //horn
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteF, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteF, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //vln2 and bass
        schu.add(new Note(noteD, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteB, 0)); //vln2
        schu.add(new Note(noteD, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1

        //bar 18
        schu.add(new Note(noteB4, 0)); //ob
        schu.add(new Note(noteFsharp, 0)); //horn
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteDsharp, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteDsharp, 0)); //vln2
        schu.add(new Note(noteFsharp, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteBflat4, 0)); //ob
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB4, 0)); //ob
        schu.add(new Note(noteB, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp5, 0)); //ob
        schu.add(new Note(noteG, 0)); //horn
        schu.add(new Note(noteG, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteBflat4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteBflat4, SIXTEENTH_NOTE_120)); //vln1

        //bar 19
        schu.add(new Note(noteD5, 0)); //fl and ob1 and cl1
        schu.add(new Note(noteFsharp, 0)); //cl2
        schu.add(new Note(noteA4, 0)); //ob2
        schu.add(new Note(noteA, 0)); //bsn1
        schu.add(new Note(noteFsharp, 0)); //bsn2
        schu.add(new Note(noteFsharp, 0)); //horn
        schu.add(new Note(noteD, 0)); //bass
        schu.add(new Note(noteFsharp, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp, 0)); //vln2
        schu.add(new Note(noteA4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB4, 0)); //ob2 and cl1
        schu.add(new Note(noteG, 0)); //cl2
        schu.add(new Note(noteB, 0)); //bsn1
        schu.add(new Note(noteG, 0)); //bsn2
        schu.add(new Note(noteD, 0)); //horn
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //bass
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteC5, 0)); //ob2 and cl1
        schu.add(new Note(noteA4, 0)); //cl2
        schu.add(new Note(noteC, 0)); //bsn1
        schu.add(new Note(noteA, 0)); //bsn2
        schu.add(new Note(noteFsharp, 0)); //bass
        schu.add(new Note(noteA4, 0)); //vln2
        schu.add(new Note(noteC5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteA4, 0)); //vln2
        schu.add(new Note(noteC5, SIXTEENTH_NOTE_120)); //vln1

        //bar 20
        schu.add(new Note(noteD5, 0)); //fl and ob1
        schu.add(new Note(noteA4, 0)); //cl2
        schu.add(new Note(noteC5, 0)); //ob2 and cl1
        schu.add(new Note(noteC, 0)); //bsn1
        schu.add(new Note(noteA, 0)); //bsn2
        schu.add(new Note(noteFsharp, 0)); //horn
        schu.add(new Note(noteG, 0)); //bass
        schu.add(new Note(noteA4, 0)); //vln2
        schu.add(new Note(noteC5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteA4, 0)); //vln2
        schu.add(new Note(noteC5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB4, 0)); //ob2 and cl1
        schu.add(new Note(noteG, 0)); //cl2
        schu.add(new Note(noteB, 0)); //bsn1
        schu.add(new Note(noteG, 0)); //bsn2
        schu.add(new Note(noteD, 0)); //horn
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //bass
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteD, 0)); //vln2
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //bass
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteB4, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE5, 0)); //fl
        schu.add(new Note(noteD5, 0)); //ob2 and cl1
        schu.add(new Note(noteGsharp, 0)); //cl2
        schu.add(new Note(noteD, 0)); //bsn1
        schu.add(new Note(noteB, 0)); //bsn2
        schu.add(new Note(noteE, 0)); //horn
        schu.add(new Note(noteE, 0)); //bass
        schu.add(new Note(noteB4, 0)); //vln2a
        schu.add(new Note(noteGsharp, 0)); //vln2b
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteB4, 0)); //vln2a
        schu.add(new Note(noteGsharp, 0)); //vln2b
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln1

        //bar 21
        schu.add(new Note(noteE5, 0)); //fl and ob1
        schu.add(new Note(noteB4, 0)); //cl1
        schu.add(new Note(noteG, 0)); //cl2
        schu.add(new Note(noteD5, 0)); //ob2
        schu.add(new Note(noteD, 0)); //bsn1
        schu.add(new Note(noteB, 0)); //bsn2
        schu.add(new Note(noteE, 0)); //horn
        schu.add(new Note(noteE, 0)); //bass
        schu.add(new Note(noteB4, 0)); //vln2a
        schu.add(new Note(noteGsharp, 0)); //vln2b
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteA4, 0)); //vln2
        schu.add(new Note(noteD5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteE5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteE5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteCsharp5, 0)); //ob2
        schu.add(new Note(noteCsharp5, 0)); //cl1
        schu.add(new Note(noteA4, 0)); //cl2
        schu.add(new Note(noteCsharp, 0)); //bsn1
        schu.add(new Note(noteA, 0)); //bsn2
        schu.add(new Note(noteA, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteA, 0)); //vln2
        schu.add(new Note(noteCsharp5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteE5, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteE5, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp5, 0)); //fl1 and ob1
        schu.add(new Note(noteE5, 0)); //fl2 and ob2
        schu.add(new Note(noteCsharp5, 0)); //cl1
        schu.add(new Note(noteBflat4, 0)); //cl2
        schu.add(new Note(noteCsharp, 0)); //bsn1
        schu.add(new Note(noteFsharp, 0)); //bsn2
        schu.add(new Note(noteFsharp, 0)); //horn1
        schu.add(new Note(noteE, 0)); //horn2
        schu.add(new Note(noteD, 0)); //tpt
        schu.add(new Note(noteCsharp, 0)); //tbn1
        schu.add(new Note(noteBflat, 0)); //tb2
        schu.add(new Note(noteFsharp, 0)); //tbn3 and timp
        schu.add(new Note(noteA, 0)); //bass
        schu.add(new Note(noteG, 0)); //vln2
        schu.add(new Note(noteFsharp5, 0)); //vln1a
        schu.add(new Note(noteE5, SIXTEENTH_NOTE_120)); //vln1b
        schu.add(new Note(noteC5, 0)); //vln2a
        schu.add(new Note(noteBflat4, 0)); //vln2b
        schu.add(new Note(noteFsharp5, 0)); //vln1a
        schu.add(new Note(noteE5, SIXTEENTH_NOTE_120)); //vln1b
        schu.add(new Note(noteFsharp, 0)); //bass
        schu.add(new Note(noteC5, 0)); //vln2a
        schu.add(new Note(noteBflat4, 0)); //vln2b
        schu.add(new Note(noteFsharp5, 0)); //vln1a
        schu.add(new Note(noteE5, SIXTEENTH_NOTE_120)); //vln1b
        schu.add(new Note(noteFsharp, 0)); //bass
        schu.add(new Note(noteC5, 0)); //vln2a
        schu.add(new Note(noteBflat4, 0)); //vln2b
        schu.add(new Note(noteFsharp5, 0)); //vln1a
        schu.add(new Note(noteE5, SIXTEENTH_NOTE_120)); //vln1b

        //bar 22
        schu.add(new Note(noteG5, 0)); //fl1 and ob1
        schu.add(new Note(noteE5, 0)); //fl2 and ob2
        schu.add(new Note(noteCsharp5, 0)); //cl1
        schu.add(new Note(noteBflat4, 0)); //cl2
        schu.add(new Note(noteCsharp, 0)); //bsn1 and tbn1
        schu.add(new Note(noteFsharp, 0)); //bsn2 and tbn3
        schu.add(new Note(noteG, 0)); //horn1
        schu.add(new Note(noteE, 0)); //horn2
        schu.add(new Note(noteD5, 0)); //tpt1
        schu.add(new Note(noteD, 0)); //tpt2
        schu.add(new Note(noteBflat, 0)); //tbn2
        schu.add(new Note(noteFsharp, 0)); //bass
        schu.add(new Note(noteC5, 0)); //vln2a
        schu.add(new Note(noteBflat4, 0)); //vln2b
        schu.add(new Note(noteG5, 0)); //vln1a
        schu.add(new Note(noteE5, EIGHTH_NOTE_120)); //vln1b
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG5, 0)); //cl1
        schu.add(new Note(noteE5, 0)); //cl2
        schu.add(new Note(noteCsharp, 0)); //bsn1
        schu.add(new Note(noteBflat, 0)); //bsn2
        schu.add(new Note(noteG, 0)); //horn1
        schu.add(new Note(noteE, 0)); //horn2
        schu.add(new Note(noteC, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteC, 0)); //vln2
        schu.add(new Note(noteE, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteG, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln 1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp5, 0)); //fl1 and ob1
        schu.add(new Note(noteE5, 0)); //fl2 and ob2
        schu.add(new Note(noteCsharp5, 0)); //cl1
        schu.add(new Note(noteBflat4, 0)); //cl2
        schu.add(new Note(noteCsharp, 0)); //bsn1
        schu.add(new Note(noteFsharp, 0)); //bsn2
        schu.add(new Note(noteFsharp, 0)); //horn1
        schu.add(new Note(noteE, 0)); //horn2
        schu.add(new Note(noteD, 0)); //tpt
        schu.add(new Note(noteCsharp, 0)); //tbn1
        schu.add(new Note(noteBflat, 0)); //tb2
        schu.add(new Note(noteFsharp, 0)); //tbn3 and timp
        schu.add(new Note(noteFsharp, 0)); //bass
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteE, 0)); //vln2
        schu.add(new Note(noteG, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp5, 0)); //cl1
        schu.add(new Note(noteFsharp, 0)); //horn1
        schu.add(new Note(noteFsharp, 0)); //bass
        schu.add(new Note(noteC, 0)); //vln2
        schu.add(new Note(noteC, SIXTEENTH_NOTE_120)); //vln1
        schu.add(new Note(noteFsharp, 0)); //bass
        schu.add(new Note(noteC, 0)); //vln2a
        schu.add(new Note(noteC, SIXTEENTH_NOTE_120)); //vln1

        playSong(schu);
    }

    private void delay(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void playNote(Note note) {
        playNote(note.getNoteId());
    }

    private void playNote(int note, int loop) {
        soundPool.play(note, DEFAULT_VOLUME, DEFAULT_VOLUME, DEFAULT_PRIORITY, loop, DEFAULT_RATE);
    }

    private void playNote(int note) {
        playNote(note, 0);
    }

    private void playNote(List<Note> chord) {
        for(Note note : chord) {
            playNote(note);
        }
    }

    // make an inner class to handle the button clicks for the individual notes
    private class KeyboardNoteListener implements View.OnClickListener
    {

        @Override
        public void onClick(View view) {
            // get id of the button that was clicked
            int id = view.getId();
            // use map to figure out what note to play
            int note = noteMap.get(id);
            // play the note
            playNote(note);
        }
    }

    private void scheduleSongs(long startDelay, Note[]... songs) {
        //the start delay makes sure all tasks are scheduled before songs are played
        long base = SystemClock.uptimeMillis() + startDelay;
        for (Note[] song: songs) {
            //delay accumulates as the song plays
            long delay = 0;
            for (final Note note: song) {
                handler.postAtTime(new Runnable() {
                    @Override
                    public void run() {
                        soundPool.play(note.getNoteId(), 1f, 1f, 1, 0, 1f);
                    }
                }, base + delay); //schedule the note to play at the sum of the uptime and the delay
                delay+=note.getDelay();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //cancel all Handler callbacks to avoid lifecycle errors
        handler.removeCallbacksAndMessages(null);
    }
}
