package com.ver.subtitle.model;

import java.util.ArrayList;
import java.util.TreeMap;

public class TuziSubTitleInfoTreeMap {
	private TreeMap<KeyEntry, SubTitleItem> mSubMap = new TreeMap<KeyEntry, SubTitleItem>();

	public TuziSubTitleInfoTreeMap() {

	}

	public void insert(int index, long start, long end, ArrayList<String> mSubTitles) {
//		System.out.println(index+" "+start+" "+end+" "+mSubTitles);
		mSubMap.put(new KeyEntry(start, end), genData(index, start, end, mSubTitles));
	}

	public void clear() {
		mSubMap.clear();
	}

	public String getByMins(long ms) {
		SubTitleItem data = mSubMap.get(new KeyEntry(ms, ms));
		if (data == null) {
			return "";
		} else {
			return data.getSubTitle();
		}

	}

	private class KeyEntry implements Comparable<KeyEntry> {

		final long start;
		final long end;

		public KeyEntry(long start, long end) {
			this.start = start;
			this.end = end;
		}

		public int compareTo(KeyEntry t) {
			long t1 = start - t.start;
			if (t1 < 0) {
				return -1;
			}
			long t2 = end - t.end;
			if (t1 >= 0 && t2 <= 0) {
				return 0;
			}
			return 1;
		}

	}

	private static SubTitleItem genData(int index, long start, long end, ArrayList<String> mSubTitles) {
		SubTitleItem data = new SubTitleItem();
		data.setCount(index);
		data.setSubLists(mSubTitles);
		data.setStart(start);
		data.setEnd(end);
		return data;
	}

}
