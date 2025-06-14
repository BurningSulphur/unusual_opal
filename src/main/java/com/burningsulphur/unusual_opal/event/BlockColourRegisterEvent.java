package com.burningsulphur.unusual_opal.event;

import com.burningsulphur.unusual_opal.UnusualOpal;
import com.peeko32213.unusualprehistory.core.registry.UPItems;
import net.mariu73.opalescence.block.OpalBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockColourRegisterEvent {
    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, blockAndTintGetter, pos, i) -> pos != null ? OpalBlock.getBlockColor(state, blockAndTintGetter, pos, i) : -1,
               // ModBlocks.OPAL.get(),
                UnusualOpal.OPAL_FOSSIL_BLOCK.get()
        );
    }

    @SubscribeEvent
    public static void itemColorHandlerEvent(final RegisterColorHandlersEvent.Item event) {
        event.register(OpalBlock::getItemColor,
                //ModItems.OPAL_CRYSTAL.get(),
                //ModBlocks.OPAL.get().asItem()
                UnusualOpal.OPAL_FOSSIL_BLOCK.get().asItem(),
                UPItems.OPAL_FOSSIL.get(),
                UPItems.OPALESCENT_PEARL.get(),
                UPItems.OPALESCENT_SHURIKEN.get(),
                UPItems.OPAL_CHUNK.get()
        );
    }
}
