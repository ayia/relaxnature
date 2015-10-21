package com.tyolar.inc.relax.clazz;

import java.util.ArrayList;

import android.content.Context;

import com.tyolar.inc.relax.R;

public class Menu {
	private String Titel;
	private int imageresource;
	private int backgrounfcolor;
	private int imagebackgrond;
	private ArrayList<Integer> listsongs;
	public MenuType Type;

	public Menu(MenuType type, Context x) {
		super();
		this.Type = type;
		switch (this.Type) {
		case Country:
			this.imageresource = R.drawable.ic_country;
			this.backgrounfcolor = R.color.c_medium_sea_green;
			this.Titel = x.getResources().getString(R.string.titel_country);
			this.imagebackgrond=R.drawable.country_background;
			this.listsongs=ConstantClass.getCountrysongs();
			
			break;
		case Forest:
			this.imageresource = R.drawable.ic_forest;
			this.backgrounfcolor = R.color.c_forest_green;
			this.Titel = x.getResources().getString(R.string.titel_Forest);
			this.imagebackgrond=R.drawable.forest_background;
			this.listsongs=ConstantClass.getForestsongs();
			
			break;
		case Mountain:
			this.imageresource = R.drawable.ic_mountain;
			this.backgrounfcolor = R.color.c_yankeesblue;
			this.Titel = x.getResources().getString(R.string.titel_Mountain);
			this.imagebackgrond=R.drawable.mountain_background;
			this.listsongs=ConstantClass.getMountainssongs();
			break;
		case Night:
			this.imageresource = R.drawable.ic_night;
			this.backgrounfcolor = R.color.c_eerieblack;
			this.Titel = x.getResources().getString(R.string.titel_Night);
			this.imagebackgrond=R.drawable.night_background;
			this.listsongs=ConstantClass.getNightnssongs();
			break;
		case Rain:
			this.imageresource = R.drawable.ic_rain;
			this.backgrounfcolor = R.color.blue;
			this.Titel = x.getResources().getString(R.string.titel_rain);
			this.imagebackgrond=R.drawable.rain_background;
			this.listsongs=ConstantClass.getRainsongs();
			break;
		case Sae:
			this.imageresource = R.drawable.ic_water;
			this.backgrounfcolor = R.color.c_light_sky_blue;
			this.Titel = x.getResources().getString(R.string.titel_Sea);
			this.imagebackgrond=R.drawable.water_background;
			this.listsongs=ConstantClass.getSeasongs();
			
			break;

		default:
			this.imageresource = R.drawable.ic_rain;
			this.backgrounfcolor = R.color.c_slate_blue;
			this.Titel = x.getResources().getString(R.string.titel_rain);
			this.imagebackgrond=R.drawable.rain_background;
			this.listsongs=ConstantClass.getRainsongs();
			break;
		}

	}

	public String getTitel() {
		return Titel;
	}

	public void setTitel(String titel) {
		Titel = titel;
	}

	public int getImageresource() {
		return imageresource;
	}

	public int getImagebackgrond() {
		return imagebackgrond;
	}

	public void setImagebackgrond(int imagebackgrond) {
		this.imagebackgrond = imagebackgrond;
	}

	public void setImageresource(int imageresource) {
		this.imageresource = imageresource;
	}

	public int getBackgrounfcolor() {
		return backgrounfcolor;
	}

	public void setBackgrounfcolor(int backgrounfcolor) {
		this.backgrounfcolor = backgrounfcolor;
	}

	public MenuType getType() {
		return Type;
	}

	public ArrayList<Integer> getListsongs() {
		return listsongs;
	}

	public void setListsongs(ArrayList<Integer> listsongs) {
		this.listsongs = listsongs;
	}

	public void setType(MenuType type) {
		Type = type;
	}

	public enum MenuType {
		Rain, Night, Sae, Forest, Country, Mountain;
	}
}
