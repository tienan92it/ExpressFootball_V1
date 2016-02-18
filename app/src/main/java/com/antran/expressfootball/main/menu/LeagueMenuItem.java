package com.antran.expressfootball.main.menu;

import com.parse.ParseObject;

/**
 * Created by AnTran on 13/12/2015.
 */
public class LeagueMenuItem {

    private String parseId;
    private int itemId;
    private String name;
    private String icon;

    public LeagueMenuItem(){
        itemId = (int) System.currentTimeMillis();
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getParseId() {
        return parseId;
    }

    public void setParseId(String parseId) {
        this.parseId = parseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static LeagueMenuItem parse(ParseObject object) {
        LeagueMenuItem menuItem = new LeagueMenuItem();
        menuItem.setParseId(object.getObjectId());
        menuItem.setName(object.getString("name"));
//        menuItem.setIcon(object.getParseFile("thumbnail").getUrl());
        return menuItem;
    }
}
