package com.example.synthesizer;

import java.util.ArrayList;
import java.util.List;

public class Note {
    private int noteId;
    private int delay;

    //120BPM
    private static final int SIXTEENTH_NOTE_120 = 125; // in ms
    private static final int WHOLE_NOTE_120 = 16 * SIXTEENTH_NOTE_120;
    private static final int HALF_NOTE_120 = 8 * SIXTEENTH_NOTE_120;
    private static final int QUARTER_NOTE_120 = 4 * SIXTEENTH_NOTE_120;
    private static final int EIGHTH_NOTE_120 = 2 * SIXTEENTH_NOTE_120;

    //180BPM
    private static final int SIXTEENTH_NOTE_180 = (int) (SIXTEENTH_NOTE_120 * 1.5); // in ms
    private static final int WHOLE_NOTE_180 = 16 * SIXTEENTH_NOTE_120;
    private static final int HALF_NOTE_180 = 8 * SIXTEENTH_NOTE_180;
    private static final int QUARTER_NOTE_180 = 4 * SIXTEENTH_NOTE_180;
    private static final int EIGHTH_NOTE_180 = 2 * SIXTEENTH_NOTE_180;

    public Note(int noteId) {
        this.noteId = noteId;
        this.delay = WHOLE_NOTE_120;
    }

    public Note(int noteId, int delay) {
        this.noteId = noteId;
        this.delay = delay;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", delay=" + delay +
                '}';
    }
}
