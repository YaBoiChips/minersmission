package yaboichips.minersmission.core;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static yaboichips.minersmission.MinersMission.MODID;

public class MBlocks {
    // Create a Deferred Register to hold Blocks which will all be registered under the "minersmission" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);


}
