package cn.ksmcbrigade.badd;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static net.minecraft.server.command.CommandManager.literal;

public class Boom implements ModInitializer {

    public static boolean init = false;

    public static int tnt = 8;
    public static int tnt_minecraft = 12;
    public static int creeper = 10;
    public static int end_crystal = 40;
    public static int fire_ball = 14;

    @Override
    public void onInitialize() {
        try {
            init();

            CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("b-reload").executes(context -> {
                try {
                    init();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return 1;
            })));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void init() throws IOException{
        new File("config").mkdirs();
        File file = new File("config/b-config.json");
        if(!file.exists()){
            JsonObject object = new JsonObject();
            object.addProperty("tnt",8);
            object.addProperty("tnt_minecart",12);
            object.addProperty("creeper",10);
            object.addProperty("end_crystal",40);
            object.addProperty("fire_ball",14);
            Files.writeString(file.toPath(),object.toString());
        }
        JsonObject json = JsonParser.parseString(Files.readString(file.toPath())).getAsJsonObject();

        tnt = json.get("tnt").getAsInt();
        tnt_minecraft = json.get("tnt_minecart").getAsInt();
        creeper = json.get("creeper").getAsInt();
        end_crystal = json.get("end_crystal").getAsInt();
        fire_ball = json.get("fire_ball").getAsInt();

        init = true;
    }
}
