package cn.ksmcbrigade.badd.mixin;

import cn.ksmcbrigade.badd.Boom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.TntEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(TntEntity.class)
public abstract class TNTMixin extends Entity implements Ownable {
    public TNTMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "explode",at = @At("HEAD"), cancellable = true)
    public void explode(CallbackInfo ci) throws IOException {
        float f;
        if(!Boom.init){
            Boom.init();
        }
        f = Boom.tnt;
        this.getWorld().createExplosion(this, this.getX(), this.getBodyY(0.0625), this.getZ(), f, World.ExplosionSourceType.TNT);
        ci.cancel();
    }
}
