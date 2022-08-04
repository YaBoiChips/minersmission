package yaboichips.minersmission.events;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class MiningEvent {


    public static void spawnWave(int level, Player player) {
        Level world = player.getLevel();
        Vec3 wavePos = new Vec3(findRandomSpawnPos(player).getX(), findRandomSpawnPos(player).getY(), findRandomSpawnPos(player).getZ());
        for (int i = 0; i < level * 4; i++) {
            Entity entity = getRandomEntity(player.getRandom().nextInt(4)).create(player.level);
            if (entity != null) {
                entity.setPos(wavePos);
                world.addFreshEntity(entity);
            }
        }
    }

    public static EntityType<?> getRandomEntity(int i) {
        return switch (i) {
            case 1 -> EntityType.ZOMBIE;
            case 2 -> EntityType.CREEPER;
            case 3 -> EntityType.HUSK;
            case 4 -> EntityType.SKELETON;
            default -> EntityType.CAVE_SPIDER;
        };
    }

    @Nullable
    private static BlockPos findRandomSpawnPos(Player player) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        Level world = player.getLevel();
        double distance = 100.0D;
        double d3 = player.getX() + (player.getRandom().nextDouble() - 0.5D) * distance;
        double d4 = player.getY();
        double d5 = player.getZ() + (player.getRandom().nextDouble() - 0.5D) * distance;
        blockpos$mutableblockpos.set(d3, d4, d5);

        if (!world.getBlockState(blockpos$mutableblockpos).isAir()) {
            blockpos$mutableblockpos.set(blockpos$mutableblockpos.above());
        }
        else if (world.getBlockState(blockpos$mutableblockpos.below()).isAir()) {
            blockpos$mutableblockpos.set(blockpos$mutableblockpos.below());
        }
        return blockpos$mutableblockpos;
    }
}
