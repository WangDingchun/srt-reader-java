package com.ver.subtitle;

/**
 * 
 * @title 测试字幕加载类
 * @company 北京奔流网络信息技术有线公司
 * @email mail@wangdingchun.net
 * @author Vermouth
 * @version 1.0
 * @created 2015-2-27 下午3:16:22
 * @changeRecord [修改记录]<br />
 */
public class Main {

	public static void main(String[] args) {
		PlayerSubTitleProxy mPlayerSubTitleProxy = new PlayerSubTitleProxy();
		mPlayerSubTitleProxy.loaderSubTitle("/Users/Vermouth/Downloads/test.srt");
	}
}
