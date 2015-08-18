package me.planetguy.tct;

import com.mojang.authlib.GameProfile;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.SidedProxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.UUID;

public final class Ref {

    private Ref() {}

    public static final String MOD_ID = "TurboCraftingTable";
    public static final String RES_DOMAIN = MOD_ID.toLowerCase(Locale.ENGLISH);
    public static final String RES_PREFIX = String.format(Locale.ENGLISH, "%s:", RES_DOMAIN);
    public static final String CHANNEL = RES_DOMAIN;
    public static final String CONFIG_DIR = RES_DOMAIN;
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static String MOD_NAME;
    public static String VERSION;
    public static String URL;

}
