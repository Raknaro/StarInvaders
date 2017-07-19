/*
 * Copyright (c) 20016 - 2017, NG Tech and/or its affiliates. All rights reserved.
 * GNI GPL v3 licence . Use is subject to license terms
 */

package com.nekitsgames.starinvaders.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nekitsgames.starinvaders.API.settingsApi.Settings2API;
import com.nekitsgames.starinvaders.StarInvaders;
import com.nekitsgames.starinvaders.classes.Exceptions.SettingsAccessException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Launcher for Desktops
 *
 * Launches game from core package on Windows, Linux, macOS. Generated by libGDX.
 *
 * @author Nikita Serba
 * @version 2.0
 * @since 1.0
 */
public class DesktopLauncher {

	/**
	 * Launcher for Desktops
	 *
	 * Launches game from core package on Windows, Linux, macOS. Generated by libGDX.
	 *
	 * @since 1.0
	 * @param arg - console arguments.
	 * @throws IOException if can't access properties files
	 */
    public static void main(String[] arg) throws IOException, SettingsAccessException {
        Properties prop = new Properties();
        Settings2API set = new Settings2API();
        set.load("game");
        prop.load(new FileInputStream("properties/main.properties"));
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.title = prop.getProperty("app.name") + prop.getProperty("app.version");
			config.width = Integer.parseInt(prop.getProperty("resolution.width"));
			config.height = Integer.parseInt(prop.getProperty("resolution.height"));
			config.fullscreen = true;
			config.samples = (int) set.get("msaa", 1);
			config.useGL30 = (int) set.get("gl", 0) == 1;
			config.allowSoftwareMode = (boolean) set.get("smode", false);
			config.vSyncEnabled = (boolean) set.get("vsync", false);
			prop.load(new FileInputStream("properties/defaults.properties"));
        config.foregroundFPS = (Integer) set.get("FPS.limit", Integer.parseInt(prop.getProperty("settings.FPS.limit")));
        config.backgroundFPS = (Integer) set.get("FPS.limit", Integer.parseInt(prop.getProperty("settings.FPS.limit")));
        new LwjglApplication(new StarInvaders(), config);
	}

}
