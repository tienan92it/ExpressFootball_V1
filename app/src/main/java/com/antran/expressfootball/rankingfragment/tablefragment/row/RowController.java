package com.antran.expressfootball.rankingfragment.tablefragment.row;

import android.support.v7.widget.RecyclerView;

import com.antran.expressfootball.rankingfragment.tablefragment.RowInfo;

/**
 * Created by AnTran on 06/03/2016.
 */
public class RowController extends RecyclerView.ViewHolder implements RowViewListener {

    private RowView rowView;
    private RowInfo rowInfo;

    public RowController(RowView itemView) {
        super(itemView.getContainer());
        rowView = itemView;
        rowInfo = new RowInfo();
    }

    public void bindData(RowInfo rowInfo) {
        this.rowInfo = rowInfo;
        rowView.bindData(rowInfo);
    }

    private boolean isSVGImage(String url) {
        return url.endsWith(".svg");
    }
}
