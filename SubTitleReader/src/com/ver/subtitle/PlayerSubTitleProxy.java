package com.ver.subtitle;

import com.ver.subtitle.loader.PlayerSubTitleLoader;
import com.ver.subtitle.loader.PlayerSubTitleLoader.LoaderSubTitleListener;
import com.ver.subtitle.model.TuziSubTitleInfoTreeMap;
import com.ver.subtitle.utils.Log;

/**
 * 字幕的代理类 处理字幕展现和加载类的之间的逻辑
 * 
 * @author Vermouth
 * 
 */
public class PlayerSubTitleProxy implements LoaderSubTitleListener {
	private PlayerSubTitleLoader mPlayerSubTitleLoader;
	private TuziSubTitleInfoTreeMap mTuziSubTitleInfoTreeMap = null;

	public PlayerSubTitleProxy() {
		mPlayerSubTitleLoader = new PlayerSubTitleLoader();
		mPlayerSubTitleLoader.setLoaderSubTitleListener(this);
	}

	public void updatCurrentSubTitle(long ms) {
		Log.d("zimu", formatTime((int) ms));
		if (mTuziSubTitleInfoTreeMap != null) {
			String subtitle = mTuziSubTitleInfoTreeMap.getByMins(ms);
			Log.d("zimu", "当前的字幕是："+subtitle);
		}
	}

	public void loaderSubTitle(String filepath) {
		Log.d("zimu", filepath);
		mPlayerSubTitleLoader.loadSubTitle(filepath);
	}

	public void clearSubTitle() {
		if (mTuziSubTitleInfoTreeMap != null) {
			mTuziSubTitleInfoTreeMap.clear();
			mTuziSubTitleInfoTreeMap = null;
		}
	}

	@Override
	public void onLoadSuccess(TuziSubTitleInfoTreeMap mTuziSubTitleInfoTreeMap) {
		this.mTuziSubTitleInfoTreeMap = mTuziSubTitleInfoTreeMap;
		//如果加载成功了，测试读取2分钟时候的字幕
		updatCurrentSubTitle(1000*120);
	}

	@Override
	public void onLoadFail() {

	}

	@SuppressWarnings("all")
	public static String formatTime(int millisecond) {
		int totalSecond = millisecond / 1000;
		int minute = totalSecond / 60;
		int hour = minute / 60;
		int second = totalSecond % 60;
		minute %= 60;
		// p_txt_totaltime.setText(String.format("%02d:%02d:%02d", hour,minute,
		// second));
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}

}
