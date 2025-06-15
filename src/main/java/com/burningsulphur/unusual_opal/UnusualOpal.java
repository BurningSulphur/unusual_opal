package  com.burningsulphur.unusual_opal;

import com.mojang.logging.LogUtils;
import com.peeko32213.unusualprehistory.core.registry.UPBlocks;
import net.mariu73.opalescence.block.OpalBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.ArrayList;
import com.peeko32213.unusualprehistory.core.registry.UPItems;
import com.peeko32213.unusualprehistory.core.registry.UPBlocks;

import static com.peeko32213.unusualprehistory.core.registry.UPBlocks.OPAL_BLOCK;



import static net.mariu73.opalescence.item.ModCreativeModeTabs.OPALESCENCE_TAB;
import static com.peeko32213.unusualprehistory.core.registry.UPTabs.UP_TAB;

import static net.minecraft.world.item.Items.registerBlock;
import static net.minecraft.world.level.block.Blocks.RAW_COPPER_BLOCK;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(com.burningsulphur.unusual_opal.UnusualOpal.MOD_ID)
public class UnusualOpal
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "unusual_opal";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    /*
    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));

    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(1).saturationMod(2f).build())));
*/


    public static final RegistryObject<Block> OPAL_FOSSIL_BLOCK = BLOCKS.register("opal_fossil_block",
            () -> new OpalBlock(BlockBehaviour.Properties
                    .copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_GRAY)));

    public static final RegistryObject<Item> OPAL_FOSSIL_BLOCK_ITEM = ITEMS.register("opal_fossil_block", () -> new BlockItem(OPAL_FOSSIL_BLOCK.get(), new Item.Properties()));


    public UnusualOpal(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }




    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTab() == OPALESCENCE_TAB.get()) {
            event.accept(OPAL_FOSSIL_BLOCK_ITEM.get());
        }
        if (event.getTab() == UP_TAB.get()) {
            event.accept(OPAL_FOSSIL_BLOCK_ITEM.get());
        }

        //removing items
        event.getEntries().remove(UPItems.OPAL_CHUNK.get().getDefaultInstance());
        event.getEntries().remove(UPBlocks.DEEPSLATE_OPAL_FOSSIL.get().asItem().getDefaultInstance());
        event.getEntries().remove(UPBlocks.STONE_OPAL_FOSSIL.get().asItem().getDefaultInstance());
        event.getEntries().remove(UPBlocks.OPAL_BLOCK.get().asItem().getDefaultInstance());

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}
