package com.ala.betterfurnacesmod;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

public class ModFoodComponents {
    public static final ConsumableComponent BETTER_COAL_CONSUMABLE_COMPONENT = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.SPEED, 15*20, 1), 1.0f))
            .build();

    public static final FoodComponent BETTER_COAL_FOOD_COMPONENT = new FoodComponent.Builder()
            .alwaysEdible()
            .nutrition(2)
            .saturationModifier(1.0f)
            .build();

}

