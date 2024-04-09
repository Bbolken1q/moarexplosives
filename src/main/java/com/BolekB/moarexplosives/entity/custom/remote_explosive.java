package com.BolekB.moarexplosives.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Orientation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.NBTTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static net.minecraft.util.Rotation.CLOCKWISE_180;

public class remote_explosive extends MobEntity {
    public remote_explosive(EntityType<?> type, World world) {
        super((EntityType<? extends MobEntity>) type, world);
    }

//    public remote_explosive(EntityType<?> type, World world, PlayerEntity player)
//    {
//        super((EntityType<? extends MobEntity>) type, world);
//        this.getPersistentData().putString(player.getUUID().toString(), player.getUUID().toString());
//    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1D)
                .add(Attributes.MOVEMENT_SPEED, 0D)
                .add(Attributes.ATTACK_DAMAGE, 0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1D);
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.PAINTING_BREAK;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return null;
    }

    @Override
    public boolean canTrample(BlockState state, BlockPos pos, float fallDistance) { return false; }



    @Override
    public Vector3d collide(Vector3d vector3d4)
    {
        AxisAlignedBB axisalignedbb = this.getBoundingBox();
        ISelectionContext iselectioncontext = ISelectionContext.of(this);
        VoxelShape voxelshape = this.level.getWorldBorder().getCollisionShape();
        Stream<VoxelShape> stream = VoxelShapes.joinIsNotEmpty(voxelshape, VoxelShapes.create(axisalignedbb.deflate(1.0E-7D)), IBooleanFunction.AND) ? Stream.empty() : Stream.of(voxelshape);
        Stream<VoxelShape> stream1 = this.level.getEntityCollisions(this, axisalignedbb.expandTowards(vector3d4), (p_233561_0_) -> {
            return true;
        });
        ReuseableStream<VoxelShape> reuseablestream = new ReuseableStream<>(Stream.concat(stream1, stream));
        Vector3d vector3d = vector3d4.lengthSqr() == 0.0D ? vector3d4 : collideBoundingBoxHeuristically(this, vector3d4, axisalignedbb, this.level, iselectioncontext, reuseablestream);
        boolean flag = vector3d4.x != vector3d.x;
        boolean flag1 = vector3d4.y != vector3d.y;
        boolean flag2 = vector3d4.z != vector3d.z;
        boolean flag3 = this.onGround || flag1 && vector3d4.y < 0.0D;
        if (this.maxUpStep > 0.0F && flag3 && (flag || flag2)) {
            Vector3d vector3d1 = collideBoundingBoxHeuristically(this, new Vector3d(vector3d4.x, (double)this.maxUpStep, vector3d4.z), axisalignedbb, this.level, iselectioncontext, reuseablestream);
            Vector3d vector3d2 = collideBoundingBoxHeuristically(this, new Vector3d(0.0D, (double)this.maxUpStep, 0.0D), axisalignedbb.expandTowards(vector3d4.x, 0.0D, vector3d4.z), this.level, iselectioncontext, reuseablestream);
            if (vector3d2.y < (double)this.maxUpStep) {
                Vector3d vector3d3 = collideBoundingBoxHeuristically(this, new Vector3d(vector3d4.x, 0.0D, vector3d4.z), axisalignedbb.move(vector3d2), this.level, iselectioncontext, reuseablestream).add(vector3d2);
                if (getHorizontalDistanceSqr(vector3d3) > getHorizontalDistanceSqr(vector3d1)) {
                    vector3d1 = vector3d3;
                }
            }

            if (getHorizontalDistanceSqr(vector3d1) > getHorizontalDistanceSqr(vector3d)) {
                return vector3d1.add(collideBoundingBoxHeuristically(this, new Vector3d(0.0D, -vector3d1.y + vector3d4.y, 0.0D), axisalignedbb.move(vector3d1), this.level, iselectioncontext, reuseablestream));
            }
        }


        if (this.isOnGround())
        {
            return new Vector3d(0, 0, 0);
        }
        else if(this.horizontalCollision)
        {
            return new Vector3d(0, 0, 0);
        }
        else
        {
            return vector3d;
        }

    }

    @Override
    public boolean causeFallDamage(float p_225503_1_, float p_225503_2_) {
        return false;
    }
}
