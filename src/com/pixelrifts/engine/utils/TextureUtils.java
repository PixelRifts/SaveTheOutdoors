package com.pixelrifts.engine.utils;

import static org.lwjgl.opengl.GL13.*;

public class TextureUtils
{
	private static int currentbank;
	
	static
	{
		glActiveTexture(GL_TEXTURE0);
	}
	
	public static void setTextureBank(int texBankNo)
	{
		currentbank = texBankNo;
		glActiveTexture(GL_TEXTURE0 + texBankNo);
	}
	
	public static int getCurrentbank()
	{
		return currentbank;
	}
}
