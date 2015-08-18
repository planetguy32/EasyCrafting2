package me.planetguy.tct.util;

import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import me.planetguy.tct.ConfigHandler;
import me.planetguy.tct.Ref;
import me.planetguy.tct.recipe.RecipeHelper;
import me.planetguy.tct.recipe.WrappedRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class PacketSender {

	public static void craftItem(WrappedRecipe recipe, boolean isRightClick, boolean isShiftClick) {
		
		NetHandlerPlayClient conn=Minecraft.getMinecraft().getNetHandler();
		
		EntityPlayer player=Minecraft.getMinecraft().thePlayer;
		
		if (recipe == null) {
			return;
		}

		ItemStack stack_in_hand = player.inventory.getItemStack();

		// We need this call to canCraft() to populate the output in getCraftingResult() with NBT
		if (RecipeHelper.canCraft(recipe, player.inventory, false, 1, ConfigHandler.MAX_RECURSION) == 0) {
			return;
		}

		ItemStack return_stack = recipe.handler.getCraftingResult(recipe, recipe.usedIngredients);
		int return_size = 0;

		if (stack_in_hand == null) {
			return_size = return_stack.stackSize;
		} else if (StackUtils.canStack(stack_in_hand, return_stack) == 0) {
			return_size = return_stack.stackSize + stack_in_hand.stackSize;
		}

		if (return_size > 0) {
			if (!isRightClick) {
				if (isShiftClick) {
					int maxTimes = RecipeHelper.calculateCraftingMultiplierUntilMaxStack(return_stack, null);
					int timesCrafted = RecipeHelper.canCraftWithComponents(recipe, player.inventory, false, maxTimes, ConfigHandler.MAX_RECURSION);
					if (timesCrafted > 0) {
						return_stack.stackSize *= timesCrafted;
						RecipeHelper.canCraftWithComponents(recipe, player.inventory, true, timesCrafted, ConfigHandler.MAX_RECURSION);
						InventoryUtils.addItemToInventory(player.inventory, return_stack);
					}
				} else {
					if (RecipeHelper.canCraft(recipe, player.inventory, true, 1, ConfigHandler.MAX_RECURSION) > 0) {
						return_stack.stackSize = return_size;
						player.inventory.setItemStack(return_stack);
					}
				}
			} else {
				int maxTimes = RecipeHelper.calculateCraftingMultiplierUntilMaxStack(return_stack, stack_in_hand);
				int timesCrafted = RecipeHelper.canCraft(recipe, player.inventory, true, maxTimes, ConfigHandler.MAX_RECURSION);
				if (timesCrafted > 0) {
					return_stack.stackSize = return_size + (timesCrafted - 1) * return_stack.stackSize;
					player.inventory.setItemStack(return_stack);
				}
			}
		}
	}
}
