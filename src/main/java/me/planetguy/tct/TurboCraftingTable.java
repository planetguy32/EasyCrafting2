package me.planetguy.tct;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.planetguy.tct.recipe.RecipeManager;
import me.planetguy.tct.util.ItemMap;

@Mod(modid = Ref.MOD_ID, useMetadata = true)
public class TurboCraftingTable {

    @Instance(Ref.MOD_ID)
    public static TurboCraftingTable INSTANCE;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.initialize(event.getSuggestedConfigurationFile());
        
        MinecraftForge.EVENT_BUS.register(new Object() {
        	@SubscribeEvent
        	public void onRightClick(PlayerInteractEvent e) {
        		if(e.world.getBlock(e.x, e.y, e.z) == Blocks.crafting_table) {
        			e.setCanceled(true);
        			e.entityPlayer.openGui(this, 1, e.world, e.x, e.y, e.z);
        		}
        	}
        });
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @EventHandler
    public void available(FMLLoadCompleteEvent event) {
        ItemMap.build();

        // This fires after the recipes are sorted by forge; Mods should not add/remove recipes after this point!!
        RecipeManager.scanRecipes();
    }
}
