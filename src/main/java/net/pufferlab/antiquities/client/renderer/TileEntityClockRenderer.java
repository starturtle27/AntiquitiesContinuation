package net.pufferlab.antiquities.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.pufferlab.antiquities.client.models.ModelClock;
import net.pufferlab.antiquities.tileentities.TileEntityClock;

import org.lwjgl.opengl.GL11;

public class TileEntityClockRenderer extends TileEntitySpecialRenderer {

    ModelClock model = new ModelClock();
    public float rotation;
    public float rotation2;

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        TileEntityClock clock = (TileEntityClock) tileEntity;

        long time = (clock.getWorldObj()
            .getWorldTime()) + (long) partialTicks;

        this.rotation = (float) getMinuteHandAngle(time);
        this.rotation2 = (float) getHourHandAngle(time);

        model.setFacing(clock.facingMeta);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        float axisX = 1.0F;
        float axisZ = 0.0F;
        float modifier = 1.0F;
        if (clock.facingMeta == 1 || clock.facingMeta == 2) {
            modifier = -1.0F;
        }
        if (clock.facingMeta == 3 || clock.facingMeta == 1) {
            axisX = 0.0F;
            axisZ = 1.0F;
        }
        float angleMinute = rotation;
        GL11.glRotatef(-angleMinute * modifier, axisX, 0.0F, axisZ);
        model.renderClockHand();

        float angleHour = rotation2;
        GL11.glRotatef(angleMinute * modifier, axisX, 0.0F, axisZ);
        GL11.glRotatef(-angleHour * modifier, axisX, 0.0F, axisZ);
        model.renderClockHandHour();
        setFacing(clock.facingMeta);

        GL11.glPopMatrix();
    }

    /**
     * Calculates the angle of the hour hand for a 20-minute day.
     * 
     * @param secondsElapsed Seconds elapsed in the 20-minute day (0-1200)
     * @return hour hand angle in degrees (0° = 12 o'clock)
     */
    public static double getHourHandAngle(double secondsElapsed) {
        double totalSecondsInDay = 20 * 60; // 20 minutes = 1200 seconds
        double totalHoursOnClock = 12.0;

        // Map seconds elapsed to hours on the 12-hour clock
        double hoursElapsed = (secondsElapsed / totalSecondsInDay) * totalHoursOnClock;

        // Convert hours to degrees
        double hourAngle = (hoursElapsed / totalHoursOnClock) * 360.0;

        return hourAngle;
    }

    /**
     * Calculates the angle of the minute hand for a 20-minute day.
     * 
     * @param secondsElapsed Seconds elapsed in the 20-minute day (0-1200)
     * @return minute hand angle in degrees (0° = 12 o'clock)
     */
    public static double getMinuteHandAngle(double secondsElapsed) {
        // 20 minutes = 12 hours on clock → 1 “clock hour” = 100 seconds
        double secondsPerClockHour = 20 * 60 / 12.0; // 1200 / 12 = 100 seconds

        // Minutes within the current hour (0-59)
        double minutesInHour = (secondsElapsed % secondsPerClockHour) / secondsPerClockHour * 60;

        // Convert minutes to degrees
        double minuteAngle = (minutesInHour / 60.0) * 360.0;

        return minuteAngle;
    }

    public void setFacing(int meta) {
        int meta2 = meta;
        GL11.glRotatef(90 * meta2, 0, 1.0F, 0.0F);
    }
}
