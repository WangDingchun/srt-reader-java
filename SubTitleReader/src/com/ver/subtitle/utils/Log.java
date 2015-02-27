package com.ver.subtitle.utils;

public class Log {
	public static final boolean DEBUG = true;

	public static void v(String msg) {
		if (DEBUG) {
			if (msg == null)
				msg = "null";
			System.out.println(msg);
		}
	}

	public static void v(String TAG, String msg) {
		if (DEBUG) {
			if (msg == null)
				msg = "null";
			System.out.println(msg);
		}
	}

	public static void e(String msg) {
		if (DEBUG) {
			if (msg == null)
				msg = "null";
			System.out.println(msg);
		}
	}

	public static void e(String TAG, String msg) {
		if (DEBUG) {
			if (msg == null)
				msg = "null";
			System.out.println(msg);
		}
	}

	public static void e(String TAG, String msg, Throwable ex) {
		if (DEBUG) {
			if (msg == null)
				msg = "null";
			System.out.println(msg);
		}
	}

	public static void i(String msg) {
		if (DEBUG) {
			if (msg == null)
				msg = "null";
			System.out.println(msg);
		}
	}

	public static void i(String TAG, String msg) {
		if (DEBUG) {
			if (msg == null)
				msg = "null";
			System.out.println(msg);
		}
	}

	public static void i(String TAG, String msg, Throwable ex) {
		if (DEBUG) {
			if (msg == null)
				msg = "null";
			System.out.println(msg);
		}
	}

	public static void d(String msg) {
		if (DEBUG) {
			if (msg == null)
				msg = "null";
			System.out.println(msg);
		}
	}

	public static void d(String TAG, String msg) {
		if (DEBUG) {
			if (msg == null)
				msg = "null";
			System.out.println(msg);
		}
	}

	public static void d(String TAG, String msg, Throwable ex) {
		if (DEBUG) {
			if (msg == null)
				msg = "null";
			System.out.println(msg);
		}
	}

	public static void w(String msg) {
		if (DEBUG) {
			if (msg == null)
				msg = "null";
			System.out.println(msg);
		}
	}

	public static void w(String TAG, String msg) {
		if (DEBUG) {
			if (msg == null)
				msg = "null";
			System.out.println(msg);
		}
	}

	public static void w(String TAG, Throwable ex) {
		if (DEBUG) {
			System.out.println(ex.toString());
		}
	}

	public static void w(String TAG, String msg, Throwable ex) {
		if (DEBUG) {
			if (msg == null)
				msg = "null";
			System.out.println(msg);
		}
	}

	public static int VERBOSE = 2;

	public static boolean isLoggable(String str, int VERBOSE) {
		return DEBUG;
	}
}
