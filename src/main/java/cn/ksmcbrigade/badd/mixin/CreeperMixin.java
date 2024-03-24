package cn.ksmcbrigade.badd.mixin;

import cn.ksmcbrigade.badd.Boom;
import net.minecraft.client.render.entity.feature.SkinOverlayOwner;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(CreeperEntity.class)
public abstract class CreeperMixin extends HostileEntity implements SkinOverlayOwner {


    @Shadow private int explosionRadius;

    protected CreeperMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "<init>",at = @At("TAIL"))
    public void explode(CallbackInfo ci) throws IOException {
        if(!Boom.init){
            Boom.init();
        }
        this.explosionRadius = Boom.creeper;
    }
}
