package me.planetguy.tct;

import cpw.mods.fml.common.FMLCommonHandler;
import me.planetguy.tct.recipe.RecipeChecker;
import net.minecraftforge.client.ClientCommandHandler;

public class ProxyClient extends Proxy {

    @Override
    public void registerHandlers() {
        FMLCommonHandler.instance().bus().register(RecipeChecker.INSTANCE);
    }

}
