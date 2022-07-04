package yaboichips.minersmission.core;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
    public static RegistryObject<Block> registerBlock(String id, Block block) {
        return BLOCKS.register(id, () -> block);
    }

    public static RegistryObject<Block> registerOreBlock(String id) {
        return BLOCKS.register(id, () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)));
    }
}
