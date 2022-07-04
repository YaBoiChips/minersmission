package yaboichips.minersmission.core;

import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static yaboichips.minersmission.MinersMission.MODID;

public class MItems {
    public static CreativeModeTab TAB = new CreativeModeTab("miner"){
        @Override
        public ItemStack makeIcon() {
            return Items.IRON_PICKAXE.getDefaultInstance();
        }
    };
    // Create a Deferred Register to hold Items which will all be registered under the "minersmission" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> RUBY_ORE = createBlockItem(MBlocks.RUBY_ORE.get());
    public static final RegistryObject<Item> SAPPHIRE_ORE = createBlockItem(MBlocks.SAPPHIRE_ORE.get());
    public static final RegistryObject<Item> TOPAZ_ORE = createBlockItem(MBlocks.TOPAZ_ORE.get());
    public static final RegistryObject<Item> OPAL_ORE = createBlockItem(MBlocks.OPAL_ORE.get());
    public static final RegistryObject<Item> AMBER_ORE = createBlockItem(MBlocks.AMBER_ORE.get());
    public static final RegistryObject<Item> ONYX_ORE = createBlockItem(MBlocks.ONYX_ORE.get());
    public static final RegistryObject<Item> PYRITE_ORE = createBlockItem(MBlocks.PYRITE_ORE.get());
    public static final RegistryObject<Item> THALLIUM_ORE = createBlockItem(MBlocks.THALLIUM_ORE.get());
    public static final RegistryObject<Item> BAUXITE_ORE = createBlockItem(MBlocks.BAUXITE_ORE.get());
    public static final RegistryObject<Item> TIN_ORE = createBlockItem(MBlocks.TIN_ORE.get());
    public static RegistryObject<Item> createBlockItem(Block block){
        return ITEMS.register(block.getDescriptionId(), ()-> new BlockItem(block, new Item.Properties().tab(TAB)));
    }
}
