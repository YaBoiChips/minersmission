package yaboichips.minersmission.core;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yaboichips.minersmission.common.blocks.TeleporterBlock;

import static yaboichips.minersmission.MinersMission.MINERS_WORLD;
import static yaboichips.minersmission.MinersMission.MODID;

public class MBlocks {
    // Create a Deferred Register to hold Blocks which will all be registered under the "minersmission" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> RUBY_ORE = registerOreBlock("ruby_ore"); //auto smelt FIXME
    public static final RegistryObject<Block> SAPPHIRE_ORE = registerOreBlock("sapphire_ore"); //looting
    public static final RegistryObject<Block> TOPAZ_ORE = registerOreBlock("topaz_ore");
    public static final RegistryObject<Block> OPAL_ORE = registerOreBlock("opal_ore");
    public static final RegistryObject<Block> AMBER_ORE = registerOreBlock("amber_ore");
    public static final RegistryObject<Block> ONYX_ORE = registerOreBlock("onyx_ore"); //feeds you
    public static final RegistryObject<Block> PYRITE_ORE = registerOreBlock("pyrite_ore");
    public static final RegistryObject<Block> THALLIUM_ORE = registerOreBlock("thallium_ore");

    public static final RegistryObject<Block> BLUE_CRYSTAL = registerCrystal("blue_crystal", DyeColor.BLUE);
    public static final RegistryObject<Block> RED_CRYSTAL = registerCrystal("red_crystal", DyeColor.RED);
    public static final RegistryObject<Block> YELLOW_CRYSTAL = registerCrystal("yellow_crystal", DyeColor.YELLOW);
    public static final RegistryObject<Block> GREEN_CRYSTAL = registerCrystal("green_crystal", DyeColor.GREEN);

    public static final RegistryObject<Block> TELEPORTER_BLOCK = registerTeleporter("teleporter_block");

    public static RegistryObject<Block> registerBlock(String id, Block block) {
        return BLOCKS.register(id, () -> block);
    }

    public static RegistryObject<Block> registerCrystal(String id, DyeColor color){
        return BLOCKS.register(id, () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST, color)));
    }

    public static RegistryObject<Block> registerTeleporter(String id) {
        return BLOCKS.register(id, () -> new TeleporterBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN), MINERS_WORLD));
    }

    public static RegistryObject<Block> registerOreBlock(String id) {
        return BLOCKS.register(id, () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)));
    }
}
