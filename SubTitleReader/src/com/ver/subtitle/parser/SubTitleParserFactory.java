package com.ver.subtitle.parser;

import java.io.File;
import java.io.InputStream;

import com.ver.subtitle.model.TuziSubTitleInfoTreeMap;

public class SubTitleParserFactory {
	private ISubTitleParser mSubTitleParser;

	public TuziSubTitleInfoTreeMap parserSubTitle(String type, File file) {
		if ("srt".equals(type)) {
			mSubTitleParser = new SrtSubTitleShop();
			return mSubTitleParser.parser(file);
		} else {
			return null;
		}
	}

	public TuziSubTitleInfoTreeMap parserSubTitle(String type, InputStream is) {
		if ("srt".equals(type)) {
			mSubTitleParser = new SrtSubTitleShop();
			return mSubTitleParser.parser(is);
		} else {
			return null;
		}

	}
}
