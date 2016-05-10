package com.example.thi.championship;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.thi.championship.model.Match;
import com.example.thi.championship.model.Team;

import java.util.ArrayList;



/**
 * Created by THI on 06.05.2016.
 */
public class MatchWork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_work);

        final Button ok = (Button) findViewById(R.id.button);
        final Spinner team1 = (Spinner) findViewById(R.id.team1);
        final Spinner team2 = (Spinner) findViewById(R.id.team2);
        final EditText time = (EditText) findViewById(R.id.time);
        final Spinner winner = (Spinner) findViewById(R.id.winner);
        TextView textView = (TextView) findViewById(R.id.textView2);

        boolean isNew = getIntent().getExtras().getBoolean("isNew");
        if (isNew) {
            final Match match = new Match(this);
            final DBHelper dbHelper = new DBHelper(this);
            final ArrayList<Team> allTeams = dbHelper.findAllTeams();
            ArrayAdapter<Team> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allTeams);
            team1.setAdapter(adapter);
            team2.setAdapter(adapter);

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    match.setTeam1id(((Team) team1.getSelectedItem()).getId());
                    match.setTeam2id(((Team) team2.getSelectedItem()).getId());
                    match.setStartTime(String.valueOf(time.getText()));
                    dbHelper.createMatch(match);
                    finish();
                }
            });

            winner.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);

        } else {
            long matchId = getIntent().getExtras().getLong("matchId");
            final DBHelper dbHelper = new DBHelper(this);
            final Match match = dbHelper.findMatchById(matchId);
            final Team t1 = dbHelper.findTeamById(match.getTeam1id());
            final Team t2 = dbHelper.findTeamById(match.getTeam2id());

            ArrayAdapter<Team> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new Team[]{t1});
            team1.setAdapter(adapter1);
            ArrayAdapter<Team> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new Team[]{t2});
            team2.setAdapter(adapter2);
            time.setText(match.getStartTime());
            ArrayAdapter<Team> adapter3 = new ArrayAdapter<Team>(this, android.R.layout.simple_spinner_item, new Team[]{t1, t2});
            winner.setAdapter(adapter3);

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Team win = (Team) winner.getSelectedItem();
                    match.setStartTime(String.valueOf(time.getText()));
                    match.setWinnerId(win.getId());
                    dbHelper.updateMatch(match);
                    finish();
                }
            });
        }
    }
}

