package com.zia.androidpopupprojectwithlist;

public class EntryItem implements item {

	public final String title;

	public EntryItem(String title) {
		this.title = title;

	}

	@Override
	public boolean isSection() {
		return false;
	}

	@Override
	public String getTitle() {
		return title;

	}

}