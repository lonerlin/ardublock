package com.ardublock;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import processing.app.Base;
import processing.app.Editor;
import processing.app.EditorTab;
import processing.app.SketchFile;
import processing.app.tools.Tool;

import com.ardublock.core.Context;
import com.ardublock.ui.ArduBlockToolFrame;
import com.ardublock.ui.listener.OpenblocksFrameListener;
import sun.security.ssl.Debug;

import javax.swing.*;

public class ArduBlockTool implements Tool, OpenblocksFrameListener
{
	//Base base;
	static Editor editor;

	static ArduBlockToolFrame openblocksFrame;
	public static void setSysClipboardText(String writeMe) {
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable tText = new StringSelection(writeMe);
		clip.setContents(tText, null);
	}

	//public void init(Editor editor) {
	  public void init(Editor editor){
		  //this.base = base;
		if (ArduBlockTool.editor == null )
		{
			ArduBlockTool.editor = editor;

			ArduBlockTool.openblocksFrame = new ArduBlockToolFrame();
			ArduBlockTool.openblocksFrame.addListener(this);
			Context context = Context.getContext();
			String arduinoVersion = this.getArduinoVersion();
			context.setInArduino(true);
			context.setArduinoVersionString(arduinoVersion);
			context.setEditor(editor);
			System.out.println("Arduino Version: " + arduinoVersion);
		}

	}

	public void run() {

		//ArduBlockTool.editor = base.getActiveEditor();

		try {

			ArduBlockTool.editor.toFront();
			ArduBlockTool.openblocksFrame.setVisible(true);
			ArduBlockTool.openblocksFrame.toFront();
		} catch (Exception e) {
			
		}
	}

	public String getMenuTitle() {
		return Context.APP_NAME;
	}

	public void didSave() {
		
	}
	
	public void didLoad() {
		
	}
	
	public void didSaveAs()
	{
		
	}
	
	public void didNew()
	{
		
	}
	public void didSendCode(String code)
	{
		/*
		java.lang.reflect.Method method;
		try {
			// pre Arduino 1.6.12
			Class ed = ArduBlockTool.editorTab.getClass();
			Class[] cArg = new Class[1];
			cArg[0] = String.class;
			method = ed.getMethod("setText", cArg);
			method.invoke(ArduBlockTool.editorTab, code);
		}
		catch (NoSuchMethodException e) {
			//ArduBlockTool.editor.getCurrentTab().setText(source);
		} catch (IllegalAccessException e) {
			//ArduBlockTool.editor.getCurrentTab().setText(source);
		} catch (SecurityException e) {
			//ArduBlockTool.editor.getCurrentTab().setText(source);
		} catch (InvocationTargetException e) {
			//ArduBlockTool.editor.getCurrentTab().setText(source);
		}
		*/
		EditorTab editorTab=ArduBlockTool.editor.getCurrentTab();
		editorTab.setText(code);

		//ArduBlockTool.editor.handleExport(true);
	}
	public void didGenerate(String source) {
		EditorTab editorTab=ArduBlockTool.editor.getCurrentTab();
		editorTab.setText(source);
		ArduBlockTool.editor.handleExport(false);
	}

	private String getArduinoVersion()
	{
		Context context = Context.getContext();
		File versionFile = context.getArduinoFile("lib/version.txt");
		if (versionFile.exists())
		{
			try
			{
				InputStream is = new FileInputStream(versionFile);
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String line = reader.readLine();
				reader.close();
				if (line == null)
				{
					return Context.ARDUINO_VERSION_UNKNOWN;
				}
				line = line.trim();
				if (line.length() == 0)
				{
					return Context.ARDUINO_VERSION_UNKNOWN;
				}
				return line;
				
			}
			catch (FileNotFoundException e)
			{
				return Context.ARDUINO_VERSION_UNKNOWN;
			}
			catch (UnsupportedEncodingException e)
			{
				return Context.ARDUINO_VERSION_UNKNOWN;
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return Context.ARDUINO_VERSION_UNKNOWN;
			}
		}
		else
		{
			return Context.ARDUINO_VERSION_UNKNOWN;
		}
		
	}
}
