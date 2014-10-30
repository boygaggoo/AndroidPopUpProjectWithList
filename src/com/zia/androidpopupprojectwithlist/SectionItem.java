package com.zia.androidpopupprojectwithlist;

public class SectionItem implements item{
	 
	 private final String title;
	  
	 public SectionItem(String title) {
	  this.title = title;
	 }
	  
	 public String getTitle(){
	  return title;
	 }
	  
	 @Override
	 public boolean isSection() {
	  return true;
	 }
	 
	}