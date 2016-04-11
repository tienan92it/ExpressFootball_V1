package com.antran.expressfootball.matchesfragment.matches;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.antran.expressfootball.matchesfragment.MatchInfo;
import com.antran.expressfootball.matchesfragment.matchitem.MatchItemController;
import com.antran.expressfootball.matchesfragment.matchitem.MatchItemView;
import com.antran.expressfootball.model.Leagues;
import com.antran.expressfootball.model.Team;
import com.antran.expressfootball.networkrequest.Global;
import com.antran.expressfootball.networkrequest.JSONArrayRequest;
import com.antran.expressfootball.networkrequest.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AnTran on 27/03/2016.
 */
public class MatchesByTeamController extends RecyclerView.Adapter<MatchItemController> implements MatchesViewListener {

    private static final int LIMIT = 10;
    private Context context;
    private Team team;
    private List<MatchInfo> matches;
    private MatchesView matchesView;
    private MatchesControllerListener listener;

    public MatchesByTeamController(Context context, MatchesView matchesView, MatchesControllerListener ltn) {
        this.context = context;
        this.matchesView = matchesView;
        this.listener = ltn;
        this.matches = new ArrayList<MatchInfo>();
    }

    /**
     * Called when RecyclerView needs a new {@link MatchItemController} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(MatchItemController, int)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link MatchItemView} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(MatchItemController, int)
     */
    @Override
    public MatchItemController onCreateViewHolder(ViewGroup parent, int viewType) {
        MatchItemController matchItemController = null;

        MatchItemView matchItemView = new MatchItemView(parent);
        matchItemController = new MatchItemController(matchItemView);
        matchItemView.setListener(matchItemController);

        return matchItemController;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link MatchItemController#itemView} to reflect the item at the given
     * position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link MatchItemController#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p/>
     * Override {@link #onBindViewHolder(MatchItemController, int)} instead if Adapter can
     * handle effcient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(MatchItemController holder, int position) {
        holder.bindData(matches.get(position));
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return matches.size();
    }

    public void firstLoadData(Team favoriteTeam) {
        team = favoriteTeam;
        listener.onLoading();
        matches.clear();
        if (team != null) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("teamName", team.getTeamName());
            params.put("page", "1");
            params.put("limit", String.valueOf(LIMIT));
            params.put("orderBy", "date_match");
            String url = Global.getURLMatchesByTeamNameWith();
            JSONArrayRequest jsonObjectRequest = new JSONArrayRequest(Request.Method.POST, url, params,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            listener.onLoaded();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    MatchInfo match = MatchInfo.parse(jsonObject);
                                    match.getLeague().setLeagueName(Leagues.getInstance(context).getLeagueName(match.getLeague().getLeagueId()));
                                    matches.add(match);
                                    notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            listener.onLoaded();
                            Toast.makeText(context, "Unable to connect to server, try again later", Toast.LENGTH_SHORT).show();
                        }
                    });
            MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        } else {
            listener.onLoaded();
            Toast.makeText(context, "Team not found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRefreshListener() {
        if (team != null) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("teamName", team.getTeamName());
            params.put("page", "1");
            params.put("limit", String.valueOf(LIMIT));
            params.put("orderBy", "date_match");
            String url = Global.getURLMatchesByTeamNameWith();
            Log.e("URL", url);
            JSONArrayRequest jsonObjectRequest = new JSONArrayRequest(Request.Method.POST, url, params,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.e("Response: ", response.toString());
                            matches.clear();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    MatchInfo match = MatchInfo.parse(jsonObject);
                                    match.getLeague().setLeagueName(Leagues.getInstance(context).getLeagueName(match.getLeague().getLeagueId()));
                                    matches.add(match);
                                    notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            matchesView.setRefreshComplete();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            listener.onLoaded();
                            Toast.makeText(context, "Unable to connect to server, try again later", Toast.LENGTH_SHORT).show();
                            matchesView.setRefreshComplete();
                        }
                    });
            MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        } else {
            Toast.makeText(context, "Team not found!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void loadMore(final int current_page) {
        if (team != null) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("teamName", team.getTeamName());
            params.put("page", String.valueOf(current_page));
            params.put("limit", String.valueOf(LIMIT));
            params.put("orderBy", "date_match");
            String url = Global.getURLMatchesByTeamNameWith();
            Log.e("URL", url);
            JSONArrayRequest jsonObjectRequest = new JSONArrayRequest(Request.Method.POST, url, params,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            listener.onLoaded();
                            Log.e("Response: ", response.toString());
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    MatchInfo match = MatchInfo.parse(jsonObject);
                                    match.getLeague().setLeagueName(Leagues.getInstance(context).getLeagueName(match.getLeague().getLeagueId()));
                                    matches.add(match);
                                    notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            listener.onLoaded();
                            Toast.makeText(context, "Unable to connect to server, try again later", Toast.LENGTH_SHORT).show();
                        }
                    });
            MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        } else {
            Toast.makeText(context, "Team not found!", Toast.LENGTH_SHORT).show();
        }
    }

}