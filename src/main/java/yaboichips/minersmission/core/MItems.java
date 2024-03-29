package yaboichips.minersmission.core;

import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yaboichips.minersmission.common.items.weapons.BattleAxeItem;
import yaboichips.minersmission.common.items.weapons.TestItem;
import yaboichips.minersmission.common.items.weapons.WarHammer;

import java.util.function.Supplier;

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

    public static final RegistryObject<Item> RUBY_ORE = createBlockItem(MBlocks.RUBY_ORE);
    public static final RegistryObject<Item> SAPPHIRE_ORE = createBlockItem(MBlocks.SAPPHIRE_ORE);
    public static final RegistryObject<Item> TOPAZ_ORE = createBlockItem(MBlocks.TOPAZ_ORE);
    public static final RegistryObject<Item> OPAL_ORE = createBlockItem(MBlocks.OPAL_ORE);
    public static final RegistryObject<Item> AMBER_ORE = createBlockItem(MBlocks.AMBER_ORE);
    public static final RegistryObject<Item> ONYX_ORE = createBlockItem(MBlocks.ONYX_ORE);
    public static final RegistryObject<Item> PYRITE_ORE = createBlockItem(MBlocks.PYRITE_ORE);
    public static final RegistryObject<Item> THALLIUM_ORE = createBlockItem(MBlocks.THALLIUM_ORE);

    public static final RegistryObject<Item> TELEPORTER_BLOCK = createBlockItem(MBlocks.TELEPORTER_BLOCK);

    public static final RegistryObject<Item> RUBY = createSimpleItem("ruby");
    public static final RegistryObject<Item> SAPPHIRE = createSimpleItem("sapphire");
    public static final RegistryObject<Item> TOPAZ = createSimpleItem("topaz");
    public static final RegistryObject<Item> OPAL = createSimpleItem("opal");
    public static final RegistryObject<Item> AMBER = createSimpleItem("amber");
    public static final RegistryObject<Item> ONYX = createSimpleItem("onyx");
    public static final RegistryObject<Item> PYRITE = createSimpleItem("pyrite");
    public static final RegistryObject<Item> THALLIUM_INGOT = createSimpleItem("thallium_ingot");

    //weapons
    public static final RegistryObject<Item> BATTLE_AXE = ITEMS.register("battle_axe", BattleAxeItem::new);
    public static final RegistryObject<Item> WAR_HAMMER = ITEMS.register("war_hammer", WarHammer::new);


    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", TestItem::new);

    public static final RegistryObject<Item> MINERS_KEY = createSimpleItem("miners_key");

    public static RegistryObject<Item> createSimpleItem(String id){
        return createItem(() -> new Item(new Item.Properties().tab(TAB)), id);
    }

    public static <T extends Item> RegistryObject<Item> createBlockItem(Supplier<? extends T> item, RegistryObject<? extends Block> block) {
        return createItem(item, block.getId().getPath());
    }

    public static <T extends Item> RegistryObject<Item> createItem(Supplier<? extends T> item, String id) {
        return ITEMS.register(id, item);
    }

    public static RegistryObject<Item> createBlockItem(RegistryObject<?extends Block> block){
        return block==null ? null: createBlockItem(()->new BlockItem(block.get(),new Item.Properties().tab(TAB)), block);
    }
}
