package yaboichips.minersmission.mixin.common;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Player.class)
public class PlayerMixin {


//    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
//    public void saveMinersData(CompoundTag tag, CallbackInfo ci) {
//        tag.putInt("MissionTimer", missionTimer);
//    }
//
//    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
//    public void readMinersData(CompoundTag tag, CallbackInfo ci) {
//        missionTimer = tag.getInt("MissionTimer");
//    }
//
//    @Inject(method = "aiStep", at = @At("TAIL"))
//    public void tick(CallbackInfo ci){
//        if (missionTimer <=6000 && !(missionTimer <= 0)){
//            missionTimer--;
//        }
//        System.out.println(missionTimer);
//    }


}
