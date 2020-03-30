package com.pixelrifts.engine.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MyFile {
	private String path;
	private String name;

	public MyFile(final String path) {
		this.path = "/" + path;
		final String[] dirs = path.split("/");
		this.name = dirs[dirs.length - 1];
	}

	public MyFile(final String... paths) {
		this.path = "";
		for (final String part : paths) {
			this.path = String.valueOf(this.path) + "/" + part;
		}
		final String[] dirs = this.path.split("/");
		this.name = dirs[dirs.length - 1];
	}

	public MyFile(final MyFile file, final String subFile) {
		this.path = String.valueOf(file.path) + "/" + subFile;
		this.name = subFile;
	}

	public MyFile(final MyFile file, final String... subFiles) {
		this.path = file.path;
		for (final String part : subFiles) {
			this.path = String.valueOf(this.path) + "/" + part;
		}
		final String[] dirs = this.path.split("/");
		this.name = dirs[dirs.length - 1];
	}

	public String getPath() {
		return this.path;
	}

	@Override
	public String toString() {
		return this.getPath();
	}

	public InputStream getInputStream() {
		return this.getClass().getResourceAsStream(this.path);
	}

	public URL getUrl() {
		return this.getClass().getResource(this.path);
	}

	public CSVReader openCsvReader() throws Exception {
		return new CSVReader(this);
	}

	public FileReader getFileReader() {
		try {
			return new FileReader(name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public BufferedReader getReader() {
		try {
			final InputStreamReader isr = new InputStreamReader(this.getInputStream());
			final BufferedReader reader = new BufferedReader(isr);
			return reader;
		} catch (Exception e) {
			System.err.println("Couldn't get reader for " + this.path);
			throw e;
		}
	}

	public String getName() {
		return this.name;
	}
}
