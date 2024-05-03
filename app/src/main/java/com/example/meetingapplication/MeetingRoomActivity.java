package com.example.meetingapplication;

import android.os.Bundle;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MeetingRoomActivity extends AppCompatActivity {

    private ParticipantManager participantManager;

    private GridLayout participantContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        participantContainer = findViewById(R.id.participantContainer);
        participantManager = new ParticipantManager(this, participantContainer);

        initializeButtons();
    }

    private void initializeButtons() {
        findViewById(R.id.addButton).setOnClickListener(v -> participantManager.addParticipant());
        findViewById(R.id.removeButton).setOnClickListener(v -> participantManager.removeParticipant());
    }

}
