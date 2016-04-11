package com.antran.expressfootball.matchesfragment.matches;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.antran.expressfootball.matchesfragment.MatchInfo;
import com.antran.expressfootball.matchesfragment.matchitem.MatchItemController;
import com.antran.expressfootball.matchesfragment.matchitem.MatchItemView;
import com.antran.expressfootball.datahelper.MatchDataHelper;
import com.antran.expressfootball.model.Leagues;
import com.antran.expressfootball.networkrequest.Global;
import com.antran.expressfootball.networkrequest.MySingleton;
import com.bumptech.glide.GenericRequestBuilder;
import com.parse.FindCallback;
import com.parse.FunctionCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnTran on 05/12/2015.
 */
public class MatchesController extends RecyclerView.Adapter<MatchItemController> implements MatchesViewListener {

    private static final int LIMIT = 10;
    private Context context;
    private int leagueId = 0;
    private List<MatchInfo> matches;
    private MatchesView matchesView;
    private MatchesControllerListener listener;

    public MatchesController(Context context, MatchesView matchesView, MatchesControllerListener ltn) {
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

    public void firstLoadData(int leagueId) {
        this.leagueId = leagueId;
        listener.onLoading();
        matches.clear();
        if (leagueId != 0) {
            String url = Global.getURLMatchesByLeagueIdWith(leagueId, 1, LIMIT, "date_match", "desc");
            Log.e("URL", url);
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
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
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
            MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        } else {
            String url = Global.getURLMatchesWith(1, LIMIT, "date_match", "desc");
            Log.e("URL", url);
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
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
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
            MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        }

    }

//    public void firstLoadData(int leagueId) {
//        this.leagueId = leagueId;
//        listener.onLoading();
//        matches.clear();
//        if (leagueId != 0) {
//            MatchDataHelper.getLimitMatchesFromLeague(leagueId, LIMIT, new FindCallback<ParseObject>() {
//                @Override
//                public void done(List<ParseObject> objects, ParseException e) {
//                    if (e == null) {
//                        for (ParseObject match : objects) {
//                            matches.add(MatchInfo.parse(match));
//                            MatchDataHelper.getMatchFromId(match.getInt("match_id"), new FunctionCallback<JSONObject>() {
//                                @Override
//                                public void done(JSONObject object, ParseException e) {
//                                    try {
//                                        //JSONObject jsMatch = new JSONObject(object.get("league"));
//                                        MatchInfo tmpMatch = MatchInfo.parse(object);
//                                        if (matches.size() > 0) {
//                                            for (int i = 0; i < matches.size(); i++) {
//                                                if (matches.get(i).getMatchId() == tmpMatch.getMatchId())
//                                                    matches.set(i, tmpMatch);
//                                            }
//                                        } else
//                                            matches.add(tmpMatch);
//                                    } catch (JSONException e1) {
//                                        e1.printStackTrace();
//                                    }
//                                    notifyDataSetChanged();
//                                }
//                            });
//                            // updateItemInBackground(MatchInfo.parse(match));
//                        }
//                        notifyDataSetChanged();
//                    } else {
//                        e.printStackTrace();
//                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
//                    }
//                    listener.onLoaded();
//                }
//            });
//        } else {
//            MatchDataHelper.getLimitMatches(LIMIT, new FindCallback<ParseObject>() {
//                @Override
//                public void done(List<ParseObject> objects, ParseException e) {
//                    if (e == null) {
//                        for (ParseObject match : objects) {
//                            matches.add(MatchInfo.parse(match));
//                            MatchDataHelper.getMatchFromId(match.getInt("match_id"), new FunctionCallback<JSONObject>() {
//                                @Override
//                                public void done(JSONObject object, ParseException e) {
//                                    try {
//                                        //JSONObject jsMatch = new JSONObject(object.get("league"));
//                                        MatchInfo tmpMatch = MatchInfo.parse(object);
//                                        if (matches.size() > 0) {
//                                            for (int i = 0; i < matches.size(); i++) {
//                                                if (matches.get(i).getMatchId() == tmpMatch.getMatchId())
//                                                    matches.set(i, tmpMatch);
//                                            }
//                                        } else
//                                            matches.add(tmpMatch);
//                                    } catch (JSONException e1) {
//                                        e1.printStackTrace();
//                                    }
//                                    notifyDataSetChanged();
//                                }
//                            });
//                            //matches.add(MatchInfo.parse(match));
//                            //updateItemInBackground(MatchInfo.parse(match));
//                        }
//                        notifyDataSetChanged();
//                    } else {
//                        e.printStackTrace();
//                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
//                    }
//                    listener.onLoaded();
//                }
//            });
//        }
//    }

    @Override
    public void onRefreshListener() {
        if (leagueId != 0) {
            String url = Global.getURLMatchesByLeagueIdWith(leagueId, 1, LIMIT, "date_match", "desc");
            Log.e("URL", url);
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            listener.onLoaded();
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
                            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                            matchesView.setRefreshComplete();
                        }
                    });
            MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        } else {
            String url = Global.getURLMatchesWith(1, LIMIT, "date_match", "desc");
            Log.e("URL", url);
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            listener.onLoaded();
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
                            Toast.makeText(context, "Some things went wrong", Toast.LENGTH_SHORT).show();
                            matchesView.setRefreshComplete();
                        }
                    });
            MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        }
    }


    @Override
    public void loadMore(int current_page) {
        //matchesView.showProgressLoadMore();
        if (leagueId != 0) {
            String url = Global.getURLMatchesByLeagueIdWith(leagueId, current_page, LIMIT, "date_match", "desc");
            Log.e("URL", url);
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
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
                            Toast.makeText(context, "Some things went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
            MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        } else {
            String url = Global.getURLMatchesWith(current_page, LIMIT, "date_match", "desc");
            Log.e("URL", url);
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
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
                            Toast.makeText(context, "Some things went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
            MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        }
    }

}
