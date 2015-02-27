package com.ver.subtitle.parser;

import java.io.File;
import java.io.InputStream;

import com.ver.subtitle.model.TuziSubTitleInfoTreeMap;
import com.ver.subtitle.parser.srt.SRTReader;

/**
 * 加载srt字幕的车间
 * 
 * @author Vermouth
 * 
 */
public class SrtSubTitleShop implements ISubTitleParser {
	@Override
	public TuziSubTitleInfoTreeMap parser(InputStream is) {
		TuziSubTitleInfoTreeMap mTuziSubTitleInfoTreeMap = SRTReader.read(is);
		return mTuziSubTitleInfoTreeMap;
	}

	@Override
	public TuziSubTitleInfoTreeMap parser(File file) {
		TuziSubTitleInfoTreeMap mTuziSubTitleInfoTreeMap = SRTReader.read(file);
		return mTuziSubTitleInfoTreeMap;
	}

}
