package com.ala.betterfurnacesmod;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static com.ala.betterfurnacesmod.ModFoodComponents.BETTER_COAL_CONSUMABLE_COMPONENT;
import static com.ala.betterfurnacesmod.ModFoodComponents.BETTER_COAL_FOOD_COMPONENT;

public class ModItems {
    public static final Item BETTER_COAL = register("better_coal", Item::new, new Item.Settings()
            .food(BETTER_COAL_FOOD_COMPONENT, BETTER_COAL_CONSUMABLE_COMPONENT));


    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BetterFurnacesMod.MOD_ID, name));

        Item item = itemFactory.apply(settings.registryKey(itemKey));

        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, BETTER_FURNACES_GROUP_KEY, BETTER_FURNACES_GROUP);

        ItemGroupEvents.modifyEntriesEvent(BETTER_FURNACES_GROUP_KEY).register((itemGroup) -> {
            itemGroup.add(ModItems.BETTER_COAL);
        });

        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.BETTER_COAL, 30 * 20);
        });
    }
    // Register the mod tab for the creative inventory
    public static final RegistryKey<ItemGroup> BETTER_FURNACES_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(BetterFurnacesMod.MOD_ID, "better_furnaces_group"));
    public static final ItemGroup BETTER_FURNACES_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.BETTER_COAL))
            .displayName(Text.of("Better Furnaces Mod"))
            .build();




}
