package com.ver.subtitle.parser.srt;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.xddai.chardet.CharsetDetector;

import com.ver.subtitle.model.TuziSubTitleInfoTreeMap;
import com.ver.subtitle.utils.Log;

public class SRTReader {

	public SRTReader() {
	}

	/**
	 * Reads an SRT file and transforming it into SRT object.
	 * 
	 * @param srtFile
	 *            SRT file
	 * @return the SRTInfo object
	 * @throws InvalidSRTException
	 *             thrown when the SRT file is invalid
	 * @throws SRTReaderException
	 *             thrown while reading SRT file
	 */
	public static TuziSubTitleInfoTreeMap read(InputStream is) throws InvalidSRTException, SRTReaderException {
		if (is == null) {
			throw new SRTReaderException("is does not null");
		}
		TuziSubTitleInfoTreeMap mTuziSubTitleInfoTreeMap = new TuziSubTitleInfoTreeMap();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			parse(mTuziSubTitleInfoTreeMap, br);			
		} catch (EOFException e) {
			// Do nothing
		} catch (IOException e) {
			throw new SRTReaderException(e);
		}

		return mTuziSubTitleInfoTreeMap;
	}

	/**
	 * Reads an SRT file and transforming it into SRT object.
	 * 
	 * @param srtFile
	 *            SRT file
	 * @return the SRTInfo object
	 * @throws InvalidSRTException
	 *             thrown when the SRT file is invalid
	 * @throws SRTReaderException
	 *             thrown while reading SRT file
	 */
	public static TuziSubTitleInfoTreeMap read(File srtFile) throws InvalidSRTException, SRTReaderException {
		if (!srtFile.exists()) {
			throw new SRTReaderException(srtFile.getAbsolutePath() + " does not exist");
		}
		if (!srtFile.isFile()) {
			throw new SRTReaderException(srtFile.getAbsolutePath() + " is not a regular file");
		}
		TuziSubTitleInfoTreeMap mTuziSubTitleInfoTreeMap = new TuziSubTitleInfoTreeMap();
		InputStream in = null;
		InputStream inputStream = null;

		BufferedInputStream bin = null;
		try {
			in = new FileInputStream(srtFile);
			inputStream = new FileInputStream(srtFile);
			BufferedReader br = null;
			try {
				String code = codeString(in);
				br = new BufferedReader(new InputStreamReader(inputStream, code));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parse(mTuziSubTitleInfoTreeMap, br);
		} catch (EOFException e) {

		} catch (IOException e) {
			throw new SRTReaderException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (bin != null) {
				try {
					bin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

		return mTuziSubTitleInfoTreeMap;
	}

	private static String codeString(InputStream bin) throws Exception {
		CharsetDetector charDect = new CharsetDetector();
		String[] probableSet = charDect.detectChineseCharset(bin);
		for (String charset : probableSet) {
			Log.e("lyric", "p=" + charset);
		}
		if (probableSet != null && probableSet.length > 0) {
			return probableSet[0];
		}

		return "";
	}

	private static void parse(TuziSubTitleInfoTreeMap mTreeMap, BufferedReader br) throws IOException, EOFException {
		String srtResult = bufferReader2String(br);

		if (srtResult == null) {
			throw new EOFException();
		}

		String dealSrt = dealSrt(srtResult);

		if (dealSrt == null) {
			throw new EOFException();
		}

		Pattern pat = Pattern.compile("^\\d{1,}\\s*\\r?\\n", Pattern.MULTILINE);
		Matcher mat = pat.matcher(dealSrt);
		String parseResult = mat.replaceAll("<1>\n");
		Pattern pattern = Pattern.compile("([^\\n-]*)\\s*-->\\s*([^\\n-]*)[\\r\\n]?([^<]*)", Pattern.MULTILINE);
		Matcher m = pattern.matcher(parseResult);
		int count = 0;
		while (m.find()) {
			count = count + 1;
			long startTimeLong = 0;
			long endTimeLong = 0;
			ArrayList<String> subtitleLines = new ArrayList<String>();
			for (int i = 1; i <= m.groupCount(); i++) {
				if (i == 1) {
					Date startTime = null;
					try {
						startTime = SRTTimeFormat.parse("1970-01-01 " + m.group(i).replaceAll("\r", "").replaceAll("\n", "").trim().replaceAll(",", "."));
						startTimeLong = startTime.getTime();
					} catch (ParseException e) {
						throw new InvalidSRTException(m.group(i) + " has an invalid time format");
					}

				}
				if (i == 2) {
					Date endTime = null;
					try {
						endTime = SRTTimeFormat.parse("1970-01-01 " + m.group(i).replaceAll("\r", "").replaceAll("\n", "").trim().replaceAll(",", "."));
						endTimeLong = endTime.getTime();
					} catch (ParseException e) {
						throw new InvalidSRTException(m.group(i) + " has an invalid time format");
					}
				}

				if (i == 3) {
					String[] results = m.group(i).split("[\\r\\n]");
					for (int j = 0; j < results.length; j++) {
						if (!"".equals(results[j].replaceAll("\r", "").replaceAll("\n", "").trim()) && (null != results[j].replaceAll("\r", "").replaceAll("\n", "").trim())) {
							subtitleLines.add(results[j].replaceAll("\r", "").replaceAll("\n", "").trim());
						}
					}
				}
			}
			mTreeMap.insert(count, startTimeLong, endTimeLong, subtitleLines);
		}

		// 识别字幕中的，时间，文字等
		// String pat = "(\\r\\n)+";
		// Pattern pattern = Pattern.compile(pat);
		// String[] models = pattern.split(dealSrt);
		// if (models.length > 0) {
		// for (int i = 0; i < models.length; i++) {
		// String[] zimuItems = models[i].split("[\\r\\n]");
		// if (zimuItems.length >= 2) {
		// // 读取字幕中的时间
		// String timeString = zimuItems[1];
		// String[] times = timeString.split(SRTTimeFormat.TIME_DELIMITER);
		// if (times.length != 2) {
		// throw new InvalidSRTException(timeString +
		// " needs to be seperated with " + SRTTimeFormat.TIME_DELIMITER);
		// }
		// Date startTime = null;
		// long startTimeLong = 0;
		// try {
		// startTime = SRTTimeFormat.parse("1970-01-01 " +
		// times[0].replaceAll(",", "."));
		// startTimeLong = startTime.getTime();
		// } catch (ParseException e) {
		// System.out.println(e);
		// throw new InvalidSRTException(times[0] +
		// " has an invalid time format");
		// }
		//
		// Date endTime = null;
		// long endTimeLong = 0;
		//
		// try {
		// endTime = SRTTimeFormat.parse("1970-01-01 " +
		// times[1].replaceAll(",", "."));
		// endTimeLong = endTime.getTime();
		//
		// } catch (ParseException e) {
		// throw new InvalidSRTException(times[1] +
		// " has an invalid time format");
		// }
		// // 读取字幕中的内容
		// ArrayList<String> subtitleLines = new ArrayList<String>();
		//
		// if (zimuItems.length > 2) {
		// for (int j = 2; j < zimuItems.length; j++)
		// subtitleLines.add(zimuItems[j]);
		// }
		// mTreeMap.insert(i + 1, startTimeLong, endTimeLong, subtitleLines);
		//
		// }
		// }
		// }

		// String nString = br.readLine();
		// if (nString == null) {
		// throw new EOFException();
		// }
		//
		// int subtitleNumber = -1;
		// try {
		// subtitleNumber = Integer.parseInt(nString.replaceAll("\\D+",
		// "").replaceAll("\r", "").replaceAll("\n", "").trim());
		// } catch (Exception e) {
		// throw new InvalidSRTException(nString +
		// " has an invalid subtitle number");
		// }
		//
		// String tString = br.readLine();
		// if (tString == null) {
		// throw new
		// InvalidSRTException("Start time and end time information is not present");
		// }
		// String timeString = tString.replaceAll("\\D+", "").replaceAll("\r",
		// "").replaceAll("\n", "").trim();
		// String[] times = timeString.split(SRTTimeFormat.TIME_DELIMITER);
		// if (times.length != 2) {
		// throw new InvalidSRTException(timeString +
		// " needs to be seperated with " + SRTTimeFormat.TIME_DELIMITER);
		// }
		// Date startTime = null;
		// long startTimeLong = 0;
		// try {
		// startTime = SRTTimeFormat.parse("1970-01-01 " +
		// times[0].replaceAll(",", "."));
		// startTimeLong = startTime.getTime();
		// } catch (ParseException e) {
		// System.out.println(e);
		//
		// throw new InvalidSRTException(times[0] +
		// " has an invalid time format");
		// }
		//
		// Date endTime = null;
		// long endTimeLong = 0;
		//
		// try {
		// endTime = SRTTimeFormat.parse("1970-01-01 " +
		// times[1].replaceAll(",", "."));
		// endTimeLong = endTime.getTime();
		//
		// } catch (ParseException e) {
		// throw new InvalidSRTException(times[1] +
		// " has an invalid time format");
		// }
		//
		// ArrayList<String> subtitleLines = new ArrayList<String>();
		// String line;
		//
		// while ((line = br.readLine()) != null) {
		// if (line.trim().isEmpty()) {
		// break;
		// }
		// // Spanned spanned=Html.fromHtml(line);
		// subtitleLines.add(line.replaceAll("\\D+", "").replaceAll("\r",
		// "").replaceAll("\n", "").trim());
		// }
		//
		// if (subtitleLines.size() == 0) {
		// br.readLine();
		// // throw new
		// // InvalidSRTException("Missing subtitle text information");
		// }
		//
		// mTreeMap.insert(subtitleNumber, startTimeLong, endTimeLong,
		// subtitleLines);

	}

	private static String bufferReader2String(BufferedReader br) throws IOException {
		StringBuffer out = new StringBuffer();
		String line = "";
		while ((line = br.readLine()) != null) {
			out.append(line + "\n");
		}
		return out.toString();
	}

	/**
	 * 处理文件中的换行问题
	 * 
	 * @param content
	 * @return
	 */
	private static String dealSrt(String content) {
		String p = "(\\s*\\n){2,}";
		Pattern pattern = Pattern.compile(p, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(content);
		String result = matcher.replaceAll("\r\n\r\n");
		return result;
	}

}
