package yaboichips.minersmission.common.entites;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Gremlin extends Monster implements IAnimatable {

    public AnimationFactory factory = new AnimationFactory(this);
    protected static final EntityDataAccessor<Integer> RUN_TIME = SynchedEntityData.defineId(Gremlin.class, EntityDataSerializers.INT);


    public Gremlin(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }
    public void registerGoals(){
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, true));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMobAttributes().add(Attributes.MAX_HEALTH, 12.0).add(Attributes.ATTACK_DAMAGE, 3).add(Attributes.MOVEMENT_SPEED, 0.4D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(RUN_TIME, 0);
    }

    @Override
    public void tick() {
        super.tick();

    }

    @Override
    public boolean canAttack(LivingEntity p_21171_) {
        return true;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <E extends IAnimatable>PlayState predicate(AnimationEvent<E> event) {
        AnimationController<?> controller = event.getController();
        controller.transitionLengthTicks = 0;
        if (this.getMainHandItem().getItem() != Items.AIR){
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.gremlin.getaway", true));
            return PlayState.CONTINUE;
        }
        if (this.isOnGround() && event.isMoving()) {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.gremlin.run", true));
            return PlayState.CONTINUE;
        }
        else if (!event.isMoving() && this.isOnGround()){
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.gremlin.idle", true));
            return PlayState.CONTINUE;
        }
        else{
            return PlayState.STOP;
        }
    }
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public void doRun(int time){
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.0D, 1.2D));
        setRunTime(time);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        System.out.println(this.getMainHandItem());
        if (entity instanceof Player player) {
            if (this.getMainHandItem().getItem() == Items.AIR) {
                if (player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() != Items.AIR) {
                    this.setItemSlotAndDropWhenKilled(EquipmentSlot.MAINHAND, player.getItemBySlot(EquipmentSlot.MAINHAND).getItem().getDefaultInstance());
                    player.getItemBySlot(EquipmentSlot.MAINHAND).shrink(1);
                } else if (player.getItemBySlot(EquipmentSlot.LEGS).getCount() >= 1) {
                    this.setItemSlotAndDropWhenKilled(EquipmentSlot.MAINHAND, player.getItemBySlot(EquipmentSlot.LEGS));
                } else return super.doHurtTarget(entity);
            }
        }
        return super.doHurtTarget(entity);
    }

    public int getRunTime() {
        return entityData.get(RUN_TIME);
    }


    public void setRunTime(int i) {
        entityData.set(RUN_TIME, i);
    }
}
