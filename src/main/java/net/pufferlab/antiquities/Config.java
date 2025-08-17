package net.pufferlab.antiquities;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static String greeting = "Hello World";
    public static float globeMaxSpeed;
    public static float globeSpeedAddition;
    public static float globeSpeedDeceleration;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        greeting = configuration.getString("greeting", Configuration.CATEGORY_GENERAL, greeting, "How shall I greet?");
        globeMaxSpeed = configuration.getFloat(
            "globeMaxSpeed",
            Configuration.CATEGORY_GENERAL,
            35F,
            0F,
            Float.MAX_VALUE,
            "The maximum speed that the globe can spin.");
        globeSpeedAddition = configuration.getFloat(
            "globeSpeedAddition",
            Configuration.CATEGORY_GENERAL,
            4F,
            0F,
            10F,
            "The speed added to the globe every time a player spins it.");
        globeSpeedDeceleration = configuration.getFloat(
            "globeSpeedDeceleration",
            Configuration.CATEGORY_GENERAL,
            0.15F,
            0F,
            10F,
            "The speed that the globe decelerate every tick.");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
