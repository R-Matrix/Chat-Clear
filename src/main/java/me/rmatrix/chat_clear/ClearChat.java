/*
 * This file is part of the TemplateMod project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2026  Fallen_Breath and contributors
 *
 * TemplateMod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TemplateMod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TemplateMod.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.rmatrix.chat_clear;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;


public class ClearChat implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) ->
                dispatcher.register(
                        ClientCommandManager.literal("clear-screen").executes(
                                commandContext -> {
                                    Minecraft client = Minecraft.getInstance();
                                    ChatComponent chatHud = client.gui.getChat();
                                    chatHud.clearMessages(false);
                                    if (client.player != null) {
                                        LocalPlayer player = client.player;
                                        player.playSound(SoundEvents.ARROW_HIT_PLAYER);
                                        player.displayClientMessage(Component.literal("聊天栏已清除")
                                                .withStyle(ChatFormatting.GOLD)
                                                .withStyle(ChatFormatting.BOLD),
                                                true);
                                    }
                                    return 1;
                                }
                        )
                ));

    }
}
