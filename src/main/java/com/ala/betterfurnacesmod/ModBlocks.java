package com.ala.betterfurnacesmod;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    private static Block register(
            String name,
            Function<AbstractBlock.Settings, Block> blockFactory,
            AbstractBlock.Settings settings,
            boolean shouldRegisterItem) {

        RegistryKey<Block> blockKey = keyOfBlock(name);

        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    public static final Block BETTER_COAL_BLOCK = register(
            "better_coal_block",
            Block::new,
            AbstractBlock.Settings.copy(Blocks.COAL_BLOCK)
                    .strength(1.5f, 6.0f)
                    .sounds(BlockSoundGroup.STONE)
                    .requiresTool(),
            true
    );

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ModItems.BETTER_FURNACES_GROUP_KEY).register((itemGroup) -> {
            itemGroup.add(ModBlocks.BETTER_COAL_BLOCK.asItem());
        });

    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(BetterFurnacesMod.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BetterFurnacesMod.MOD_ID, name));
    }

}