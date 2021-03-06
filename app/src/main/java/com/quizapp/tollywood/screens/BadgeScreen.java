package com.quizapp.tollywood.screens;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.GridView;

import com.quizapp.tollywood.R;
import com.quizapp.tollywood.Screen;
import com.quizapp.tollywood.adapters.BadgeListAdapter;
import com.quizapp.tollywood.appcontrollers.BadgeScreenController;
import com.quizapp.tollywood.databaseutils.Badge;

public class BadgeScreen extends Screen implements OnItemClickListener{
	
	GridView gridView;
	ArrayList<Badge> badges;
	BadgeScreenController controller;
	
	public BadgeScreen(BadgeScreenController controller){
		super(controller);
		this.controller = controller;
		badges = new ArrayList<Badge>(getApp().getDataBaseHelper().getAllBadges());
        LayoutInflater inflater = getApp().getActivity().getLayoutInflater();
		View tmp = inflater.inflate(R.layout.badges_grid, null);
        gridView = (GridView) tmp.findViewById(R.id.gridView);
        gridView.setAdapter( new BadgeListAdapter(getApp(), 0, badges));
        gridView.setOnItemClickListener(this);
        tmp.setBackgroundColor(getApp().getConfig().getAThemeColor());
//        tmp.setBackgroundColor(getApp().getConfig().getAThemeColor());
//        gridView.setBackgroundColor(getApp().getConfig().getAThemeColor());
        addView(tmp);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		controller.onBadgeClick(badges.get(position));
	}
	
	@Override
	public boolean showMenu() {
		return true;
	}

	@Override
	public ScreenType getScreenType() {
		return ScreenType.BADGES_SCREEN;
	}
}
