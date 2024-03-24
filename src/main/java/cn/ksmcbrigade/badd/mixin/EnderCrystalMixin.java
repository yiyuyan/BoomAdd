package cn.ksmcbrigade.badd.mixin;

import cn.ksmcbrigade.badd.Boom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.IOException;

@Mixin(EndCrystalEntity.class)
public abstract class EnderCrystalMixin extends Entity {

    @Shadow public abstract void tick();

    protected EnderCrystalMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "damage",at = @At(value = "INVOKE",target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;DDDFZLnet/minecraft/world/World$ExplosionSourceType;)Lnet/minecraft/world/explosion/Explosion;"), locals = LocalCapture.CAPTURE_FAILSOFT,cancellable = true)
    public void explode(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir, DamageSource damageSource) throws IOException {
        if(!Boom.init){
            Boom.init();
        }

        this.getWorld().createExplosion(this, damageSource, null, this.getX(), this.getY(), this.getZ(), Boom.end_crystal, false, World.ExplosionSourceType.BLOCK);

        cir.setReturnValue(true);
        cir.cancel();
    }
}
