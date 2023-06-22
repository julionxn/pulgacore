package net.pulga22.pulgacore.Registry;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandRegistry extends AbstractRegistry{

    private static final List<CommandRegistrationCallback> REGISTRATION_CALLBACKS = new ArrayList<>();

    /**
     * @param cmd The command registration callback which is being registered.
     */
    protected static void registerCommand(CommandRegistrationCallback cmd){
        REGISTRATION_CALLBACKS.add(cmd);
    }

    /**
     * Call this method to register all the commands.
     */
    public static void register(){
        for (CommandRegistrationCallback callback : REGISTRATION_CALLBACKS){
            CommandRegistrationCallback.EVENT.register(callback);
        }
        register("commands");
    }
}
