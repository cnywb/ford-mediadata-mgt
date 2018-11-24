package io.dabing.common.util;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceUtils {

	public static String readString(String path) {
		List<String> strings = readLines(path);
		if (strings == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (String string : strings) {
			sb.append(string);
		}
		return sb.toString();
	}

	public static List<String> readLines(String path) {
		try {
			ClassLoader classLoader = ResourceUtils.class.getClassLoader();
			@Cleanup
			InputStream is = classLoader.getResourceAsStream(path);
			return IOUtils.readLines(is);
		} catch (Exception e) {
			log.error("read resource error", e);
		}
		return null;
	}

}
