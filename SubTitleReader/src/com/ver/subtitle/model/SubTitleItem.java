package com.ver.subtitle.model;

import java.util.ArrayList;

public class SubTitleItem {
	private int count;
	private long start;
	private long end;
	private ArrayList<String> mSubLists = new ArrayList<String>();

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public ArrayList<String> getSubLists() {
		return mSubLists;
	}

	public void setSubLists(ArrayList<String> mSubLists) {
		this.mSubLists = mSubLists;
	}
	
	public String getSubTitle(){
		StringBuffer sb=new StringBuffer("");
		for(int i=0;i<mSubLists.size();i++){
			sb.append(mSubLists.get(i)).append("\n");
		}
		return sb.toString();
	}

}
