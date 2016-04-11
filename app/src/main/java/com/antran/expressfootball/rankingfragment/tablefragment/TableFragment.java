package com.antran.expressfootball.rankingfragment.tablefragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antran.expressfootball.R;
import com.antran.expressfootball.rankingfragment.tablefragment.rows.RowsController;
import com.antran.expressfootball.rankingfragment.tablefragment.rows.RowsControllerListener;
import com.antran.expressfootball.rankingfragment.tablefragment.rows.RowsView;
import com.antran.expressfootball.util.Key;

/**
 * Created by AnTran on 06/03/2016.
 */
public class TableFragment extends Fragment implements RowsControllerListener {

    private Context context;

    private RowsController rowsController;
    private int leagueId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getContext();
        Bundle bundle = getArguments();
        if (bundle != null){
            leagueId = bundle.getInt(Key.LEAGUE_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ranking_table_fragment, container, false);

        RowsView rowsView = new RowsView((RecyclerView) rootView.findViewById(R.id.ranking_table));
        rowsController = new RowsController(context, rowsView, this);
        rowsView.setListener(rowsController);
        rowsView.setAdapter(rowsController);

        rowsController.loadData(leagueId);
        return rootView;
    }
}
