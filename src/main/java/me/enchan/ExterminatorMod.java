package me.enchan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;

public class ExterminatorMod implements ModInitializer {
    public static final Logger Logger = LoggerFactory.getLogger("exterminator");

    @Override
    public void onInitialize() {
        Logger.info("Hello Fabric world!");
    }
}
