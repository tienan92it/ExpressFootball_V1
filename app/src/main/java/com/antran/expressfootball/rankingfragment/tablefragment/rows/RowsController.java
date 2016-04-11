package com.antran.expressfootball.rankingfragment.tablefragment.rows;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.antran.expressfootball.networkrequest.Global;
import com.antran.expressfootball.networkrequest.MySingleton;
import com.antran.expressfootball.rankingfragment.tablefragment.RowInfo;
import com.antran.expressfootball.rankingfragment.tablefragment.row.RowController;
import com.antran.expressfootball.rankingfragment.tablefragment.row.RowView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AnTran on 06/03/2016.
 */
public class RowsController extends RecyclerView.Adapter<RowController> implements RowsViewListener {

    private Context context;
    private RowsView rowsView;
    private RowsControllerListener listener;
    private List<RowInfo> rows;

    public RowsController(Context context, RowsView rowsView, RowsControllerListener ltn) {
        this.context = context;
        this.rowsView = rowsView;
        this.listener = ltn;
        this.rows = new ArrayList<RowInfo>();
    }

    @Override
    public RowController onCreateViewHolder(ViewGroup parent, int viewType) {
        RowController rowController = null;

        RowView rowView = new RowView(parent);
        rowController = new RowController(rowView);
        rowView.setListener(rowController);

        return rowController;
    }

    @Override
    public void onBindViewHolder(RowController holder, int position) {
        holder.bindData(rows.get(position));
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    public void loadData(int leagueId) {
        if (leagueId != 0 && leagueId != 405) {
            String url = Global.getURLRanking(leagueId);
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject rowData = response.getJSONObject(i);
                            RowInfo rowInfo = RowInfo.parse(rowData);
                            rows.add(rowInfo);
                            notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    notifyDataSetChanged();
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
}
