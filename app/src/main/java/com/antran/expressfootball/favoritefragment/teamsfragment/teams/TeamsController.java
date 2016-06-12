package com.antran.expressfootball.favoritefragment.teamsfragment.teams;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.antran.expressfootball.caching.CachingContant;
import com.antran.expressfootball.caching.CachingManager;
import com.antran.expressfootball.favoritefragment.teamsfragment.TeamsFragment;
import com.antran.expressfootball.favoritefragment.teamsfragment.team.TeamController;
import com.antran.expressfootball.favoritefragment.teamsfragment.team.TeamControllerListener;
import com.antran.expressfootball.favoritefragment.teamsfragment.team.TeamView;
import com.antran.expressfootball.model.Team;
import com.antran.expressfootball.networkrequest.Global;
import com.antran.expressfootball.networkrequest.MySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnTran on 12/03/2016.
 */
public class TeamsController extends RecyclerView.Adapter<TeamController> implements TeamsViewListener, TeamControllerListener {

    private Context context;
    private TeamsView teamsView;
    private TeamsControllerListener listener;
    private List<Team> teams;
    private Team currentFavorite;
    private int favoriteTeamIndex = -1;

    public TeamsController(Context context, TeamsView teamsView, TeamsControllerListener listener) {
        this.context = context;
        this.teamsView = teamsView;
        this.listener = listener;
        this.teams = new ArrayList<Team>();
        currentFavorite = new Team();
        String teamJson = CachingManager.getInstance(context).getStringCached(CachingContant.FAVORITE_TEAM, "");
        if (teamJson != null && teamJson.compareTo("") != 0) {
            Gson gson = new Gson();
            currentFavorite = gson.fromJson(teamJson, Team.class);
        }
    }

    @Override
    public TeamController onCreateViewHolder(ViewGroup parent, int viewType) {
        TeamController teamController = null;

        TeamView teamView = new TeamView(parent);
        teamController = new TeamController(teamView, this);
        teamView.setListener(teamController);

        return teamController;
    }

    @Override
    public void onBindViewHolder(TeamController holder, int position) {
        if (hasFavoriteTeam()) {
            if (position == favoriteTeamIndex) {
                holder.bindData(teams.get(position), true);
            } else holder.bindData(teams.get(position), false);
        } else holder.bindData(teams.get(position), false);
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public void loadData(int leagueId) {
        if (leagueId != 0 && leagueId != 405) {
            String url = Global.getURLTeamsByLeagueId(leagueId);
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject teamData = response.getJSONObject(i);
                            Team team = Team.parse(teamData);
                            teams.add(team);
                            if (hasFavoriteTeam()) {
                                if (team.getTeamId() == currentFavorite.getTeamId())
                                    favoriteTeamIndex = i;
                            }
                            notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    notifyDataSetChanged();
                    if (favoriteTeamIndex != -1)
                        teamsView.scrollToItem(favoriteTeamIndex);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
            MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        }
    }

    @Override
    public void teamSelected(Team team) {
        Gson gson = new Gson();
        Log.d("team info", gson.toJson(team));
        CachingManager.getInstance(context).cachesString(CachingContant.FAVORITE_TEAM, gson.toJson(team));
        listener.teamSelected();
    }

    private boolean hasFavoriteTeam() {
        return currentFavorite != null && currentFavorite.getTeamId() != -1;
    }
}
