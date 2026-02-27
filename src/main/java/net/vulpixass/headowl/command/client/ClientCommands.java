package net.vulpixass.headowl.command.client;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class ClientCommands {
    public static OwlTypesEnum FoxType = OwlTypesEnum.REGULAR;
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("foxAppearance")
                .then(ClientCommandManager.argument("type", StringArgumentType.word())
                        .suggests(((context, builder) -> {
                            builder.suggest("regular");
                            return builder.buildFuture();
                        }))
                        .executes(ctx -> {
                            String type = StringArgumentType.getString(ctx, "type");
                            switch (type) {
                                case "regular": FoxType = OwlTypesEnum.REGULAR; return 1;
                            }
                            return 0;
                        })));
    }
}
