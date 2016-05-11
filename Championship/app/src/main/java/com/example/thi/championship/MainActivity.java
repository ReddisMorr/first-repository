package com.example.thi.championship;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.thi.championship.model.Match;
import com.example.thi.championship.model.Player;
import com.example.thi.championship.model.Team;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static Context context;
    private static DBHelper dbHelper;
    ListView matches;
    ArrayAdapter<Match> matchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        dbHelper = new DBHelper(context);
        initialization();

        Button add_match_button = (Button) findViewById(R.id.add_match_button);
        add_match_button.setOnClickListener(onClickListener);
        matches = (ListView) findViewById(R.id.listView);
        matches.setOnItemClickListener(onItemClickListener);

        ArrayList<Match> matches = dbHelper.findAllMatches();
        matchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, matches);
        this.matches.setAdapter(matchAdapter);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, MatchWork.class);
            intent.putExtra("isNew", true);
            startActivity(intent);
        }
    };

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, MatchWork.class);
            intent.putExtra("isNew", false);
            intent.putExtra("matchId", matchAdapter.getItem(position).getId());
            startActivity(intent);
        }
    };

    private static void initialization() {
        Team team1 = new Team("First"); dbHelper.createTeam(team1);
        Team team2 = new Team("Second"); dbHelper.createTeam(team2);
        Team team3 = new Team("Third"); dbHelper.createTeam(team3);
        Team team4 = new Team("Fourth"); dbHelper.createTeam(team4);
        Team team5 = new Team("Filth"); dbHelper.createTeam(team5);

        Player p11 = new Player("First1", 23,  team1.getId()); dbHelper.createPlayer(p11);
        Player p12 = new Player("First2", 25, team1.getId()); dbHelper.createPlayer(p12);
        Player p13 = new Player("First3", 24, team1.getId()); dbHelper.createPlayer(p13);
        Player p14 = new Player("First4", 23, team1.getId()); dbHelper.createPlayer(p14);
        Player p15 = new Player("First5", 26, team1.getId()); dbHelper.createPlayer(p15);

        Player p21 = new Player("Second1", 27, team2.getId()); dbHelper.createPlayer(p21);
        Player p22 = new Player("Second2", 21, team2.getId()); dbHelper.createPlayer(p22);
        Player p23 = new Player("Second3", 22, team2.getId()); dbHelper.createPlayer(p23);
        Player p24 = new Player("Second4", 23, team2.getId()); dbHelper.createPlayer(p24);
        Player p25 = new Player("Second5", 25, team2.getId()); dbHelper.createPlayer(p25);

        Player p31 = new Player("Third1", 24, team3.getId()); dbHelper.createPlayer(p31);
        Player p32 = new Player("Third2", 23, team3.getId()); dbHelper.createPlayer(p32);
        Player p33 = new Player("Third3", 22, team3.getId()); dbHelper.createPlayer(p33);
        Player p34 = new Player("Third4", 21, team3.getId()); dbHelper.createPlayer(p34);
        Player p35 = new Player("Third5", 29, team3.getId()); dbHelper.createPlayer(p35);

        Player p41 = new Player("Fourth1", 21, team4.getId()); dbHelper.createPlayer(p41);
        Player p42 = new Player("Fourth2", 22, team4.getId()); dbHelper.createPlayer(p42);
        Player p43 = new Player("Fourth3", 24, team4.getId()); dbHelper.createPlayer(p43);
        Player p44 = new Player("Fourth4", 25, team4.getId()); dbHelper.createPlayer(p44);
        Player p45 = new Player("Fourth5", 28, team4.getId()); dbHelper.createPlayer(p45);

        Player p51 = new Player("Filth1", 21, team5.getId()); dbHelper.createPlayer(p51);
        Player p52 = new Player("Filth2", 33, team5.getId()); dbHelper.createPlayer(p52);
        Player p53 = new Player("Filth3", 31, team5.getId()); dbHelper.createPlayer(p53);
        Player p54 = new Player("Filth4", 22, team5.getId()); dbHelper.createPlayer(p54);
        Player p55 = new Player("Filth5", 21, team5.getId()); dbHelper.createPlayer(p55);

        Match m1 = new Match( team3, team5, "4:20");
        Match m2 = new Match( team2, team1, "13:37");
        Match m3 = new Match( team5, team4, "14:88");
        dbHelper.createMatch(m1);
        dbHelper.createMatch(m2);
        dbHelper.createMatch(m3);
    }

    @Override
    protected void onResume() {
        ArrayList<Match> matches = dbHelper.findAllMatches();
        matchAdapter = new ArrayAdapter<Match>(this, android.R.layout.simple_list_item_single_choice, matches);
        this.matches.setAdapter(matchAdapter);
        super.onResume();
    }
}
