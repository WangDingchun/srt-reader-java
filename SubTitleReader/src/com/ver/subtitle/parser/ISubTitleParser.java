package com.ver.subtitle.parser;

import java.io.File;
import java.io.InputStream;

import com.ver.subtitle.model.TuziSubTitleInfoTreeMap;

public interface ISubTitleParser {
	public TuziSubTitleInfoTreeMap parser(InputStream is);
	public TuziSubTitleInfoTreeMap parser(File file);
}
