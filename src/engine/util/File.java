package engine.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class File {

	private static final String FILE_SEPARATOR = "/";
	
	private String path;
	private String name;
	
	public File(String path) {
		this.path = FILE_SEPARATOR + path;
		String[] dirs = path.split(FILE_SEPARATOR);
		this.name = dirs[dirs.length - 1];
	}
	
	public File(String... paths) {
		this.path = "";
		for (String part : paths) {
			this.path += (FILE_SEPARATOR + part);
		}
		String[] dirs = path.split(FILE_SEPARATOR);
		this.name = dirs[dirs.length - 1];
	}
	
	public File(File file, String subFile) {
		this.path = file.path + FILE_SEPARATOR + subFile;
		this.name = subFile;
	}
	
	public String getName() {return name;}
	public String getPath() {return path;}
	@Override
	public String toString() {return getPath();}
	
	
	public InputStream getInputStream() {
		return Class.class.getResourceAsStream(path);
	}
	
	public BufferedReader getReader() {
		try {
			InputStreamReader inStreamReader = new InputStreamReader(getInputStream());
			BufferedReader reader = new BufferedReader(inStreamReader);
			return reader;
		} catch (Exception e) {
			System.err.println("Couldn't get reader for " + path);
		}
		return null;
	}
	
}
