package yaboichips.minersmission.common.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import yaboichips.minersmission.core.MItems;

import static yaboichips.minersmission.MinersMission.MINERS_WORLD;

public class TeleporterBlock extends Block {
    private final ResourceKey<Level> worldKey;
    private final ResourceKey<Level> overworldKey;


    public TeleporterBlock(Properties properties, ResourceKey<Level> worldKey) {
        super(properties);
        this.worldKey = MINERS_WORLD;
        this.overworldKey = Level.OVERWORLD;
    }



    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide) {
            ServerLevel world = worldIn.getServer().getLevel(MINERS_WORLD);
            ServerLevel overworld = worldIn.getServer().getLevel(overworldKey);
            if (world != null && player.getItemInHand(handIn).is(MItems.MINERS_KEY.get())) {
                if (player.level != world) {
                    sendPlayerToDimension((ServerPlayer) player, world, new Vec3(player.getX(), player.getY(), player.getZ()));
                    player.displayClientMessage(Component.translatable("mine.teleport.success"), true);
                    world.setBlock(player.blockPosition(), Blocks.AIR.defaultBlockState(), 2);
                    world.setBlock(player.blockPosition().above(), Blocks.AIR.defaultBlockState(), 2);
                    return InteractionResult.SUCCESS;
                }else{
                    if (overworld != null) {
                        sendPlayerToDimension((ServerPlayer) player, overworld, new Vec3(player.getX(), player.getY(), player.getZ()));
                        world.setBlock(player.blockPosition(), Blocks.AIR.defaultBlockState(), 2);
                        world.setBlock(player.blockPosition().above(), Blocks.AIR.defaultBlockState(), 2);
                    }
                }
            }
            else {
                player.displayClientMessage(Component.translatable("mine.teleport.failed"), true);
                return InteractionResult.FAIL;
            }
        }

        return InteractionResult.PASS;
    }


    public static void sendPlayerToDimension(ServerPlayer serverPlayer, ServerLevel targetWorld, Vec3 targetVec) {
        // ensure destination chunk is loaded before we put the player in it
        targetWorld.getChunk(new BlockPos(targetVec));
            serverPlayer.teleportTo(targetWorld, targetVec.x(), getYLevel(serverPlayer, targetWorld), targetVec.z(), serverPlayer.getYRot(), serverPlayer.getXRot());
    }

    public static double getYLevel(ServerPlayer serverPlayer, ServerLevel targetWorld){
        BlockPos pos = serverPlayer.blockPosition();
        while (!targetWorld.getBlockState(pos).isAir()) {
            pos = pos.below();
            if (targetWorld.getBlockState(pos).isAir() && !targetWorld.getBlockState(pos.below()).isAir()) {
                return pos.getY();
            }
        }
        return pos.getY();
    }
}