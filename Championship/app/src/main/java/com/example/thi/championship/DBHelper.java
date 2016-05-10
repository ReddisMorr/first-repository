package com.example.thi.championship;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.thi.championship.model.Match;
import com.example.thi.championship.model.Player;
import com.example.thi.championship.model.Team;

import java.util.ArrayList;

/**
 * Created by THI on 05.05.2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private Context context;

    private static final String DB_NAME = "footballChampionship";
    private static final int DB_VERSION = 1;
    private static final String KEY_ID = "id";


    private static final String TABLE_PLAYERS = "Players";
    private static final String KEY_PLAYERS_NAME = "name";
    private static final String KEY_PLAYERS_AGE = "age";
    private static final String KEY_PLAYERS_TEAMID = "teamId";
    private static final String CREATE_TABLE_PLAYERS = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s INTEGER, %s INTEGER)",
            TABLE_PLAYERS, KEY_ID, KEY_PLAYERS_NAME, KEY_PLAYERS_AGE, KEY_PLAYERS_TEAMID);


    private static final String TABLE_TEAMS = "Teams";
    private static final String KEY_TEAMS_NAME = "name";
    private static final String CREATE_TABLE_TEAMS = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT)",
            TABLE_TEAMS, KEY_ID, KEY_TEAMS_NAME);


    private static final String TABLE_MATCHES = "Matches";
    private static final String KEY_TEAM1_ID = "team1id";
    private static final String KEY_TEAM2_ID = "team2id";
    private static final String KEY_START_TIME = "startTime";
    private static final String KEY_WINNER_ID = "winnerId";
    private static final String CREATE_TABLE_MATCHES = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER, %s TEXT, %s INTEGER)",
            TABLE_MATCHES, KEY_ID, KEY_TEAM1_ID, KEY_TEAM2_ID, KEY_START_TIME, KEY_WINNER_ID);


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PLAYERS);
        db.execSQL(CREATE_TABLE_TEAMS);
        db.execSQL(CREATE_TABLE_MATCHES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteDB(Context context) {
        context.deleteDatabase(DB_NAME);
    }


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }



    public Player cursorToPlayer (Cursor cursor) {
        Player player = new Player();
        player.setId(cursor.getLong(0));
        player.setName(cursor.getString(1));
        player.setAge(cursor.getInt(2));
        player.setTeamId(cursor.getLong(3));
        return player;
    }
    public Player createPlayer (Player player) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PLAYERS_NAME, player.getName());
        contentValues.put(KEY_PLAYERS_AGE, player.getAge());
        contentValues.put(KEY_PLAYERS_TEAMID, player.getTeamId());
        long id = database.insert(TABLE_PLAYERS, null, contentValues);
        player.setId(id);
        return player;
    }



    public Team cursorToTeam (Cursor cursor) {
        Team team = new Team();
        team.setId(cursor.getLong(0));
        team.setName(cursor.getString(1));
        return team;
    }
    public Team createTeam (Team team) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TEAMS_NAME, team.getName());
        long id = database.insert(TABLE_TEAMS, null, contentValues);
        team.setId(id);
        return team;
    }
    public Team findTeamById(long id) {
        String query = "SELECT * FROM " + TABLE_TEAMS + " WHERE " + KEY_ID + " = " + id;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        return cursorToTeam(cursor);
    }
    public ArrayList<Team> findAllTeams() {
        ArrayList<Team> teams = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_TEAMS;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                teams.add(cursorToTeam(cursor));
            } while (cursor.moveToNext());
        }
        return teams;
    }



    public Match cursorToMatch (Cursor cursor) {
        Match match = new Match(context);
        match.setId(cursor.getLong(0));
        match.setTeam1id(cursor.getLong(1));
        match.setTeam2id(cursor.getLong(2));
        match.setStartTime(cursor.getString(3));
        match.setWinnerId(cursor.getLong(4));
        return match;
    }
    public Match createMatch (Match match) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TEAM1_ID, match.getTeam1id());
        contentValues.put(KEY_TEAM2_ID, match.getTeam2id());
        contentValues.put(KEY_START_TIME, match.getStartTime());
        contentValues.put(KEY_WINNER_ID, match.getWinnerId());
        long id = database.insert(TABLE_MATCHES, null, contentValues);
        match.setId(id);
        return match;
    }
    public Match findMatchById(long id) {
        String query = "SELECT * FROM " + TABLE_MATCHES + " WHERE " + KEY_ID + " = " + id;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        return cursorToMatch(cursor);
    }
    public Match updateMatch(Match match) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, match.getId());
        contentValues.put(KEY_TEAM1_ID, match.getTeam1id());
        contentValues.put(KEY_TEAM2_ID, match.getTeam2id());
        contentValues.put(KEY_START_TIME, match.getStartTime());
        contentValues.put(KEY_WINNER_ID, match.getWinnerId());
        database.update(TABLE_MATCHES, contentValues, KEY_ID + " = ?", new String[]{String.valueOf(match.getId())});
        return match;
    }
    public ArrayList<Match> findAllMatches() {
        ArrayList<Match> matches = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_MATCHES;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                matches.add(cursorToMatch(cursor));
            } while (cursor.moveToNext());
        }
        return matches;
    }

}