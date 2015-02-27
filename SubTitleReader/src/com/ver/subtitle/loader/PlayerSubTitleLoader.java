package com.ver.subtitle.loader;

import java.io.File;

import com.ver.subtitle.model.TuziSubTitleInfoTreeMap;
import com.ver.subtitle.parser.SubTitleParserFactory;

/**
 * 字幕的加载类
 * 
 * @author Vermouth
 * 
 */
public class PlayerSubTitleLoader {
	private LoaderSubTitleListener mLoaderSubTitleListener;
	private SubTitleParserFactory mSubTitleParserFactory;

	public PlayerSubTitleLoader() {
		mSubTitleParserFactory = new SubTitleParserFactory();
	}

	public void setLoaderSubTitleListener(LoaderSubTitleListener mLoaderSubTitleListener) {
		this.mLoaderSubTitleListener = mLoaderSubTitleListener;
	}

	public interface LoaderSubTitleListener {
		public void onLoadSuccess(TuziSubTitleInfoTreeMap mTuziSubTitleInfoTreeMap);

		public void onLoadFail();
	}

	public void loadSubTitle(final String filePath) {
		if (!"".equals(filePath) && null != (filePath)) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						File file = new File(filePath);
						if (!file.exists()) {
							if (mLoaderSubTitleListener != null) {
								mLoaderSubTitleListener.onLoadFail();
							}
							return;
						}
						final TuziSubTitleInfoTreeMap mTuziSubTitleInfoTreeMap = mSubTitleParserFactory.parserSubTitle("srt", file);
						if (mLoaderSubTitleListener != null) {
							mLoaderSubTitleListener.onLoadSuccess(mTuziSubTitleInfoTreeMap);
						}
					}catch (Exception e) {
						e.printStackTrace();

						if (mLoaderSubTitleListener != null) {
							mLoaderSubTitleListener.onLoadFail();
						}
					}

				}
			}).start();
		}

	}

}
