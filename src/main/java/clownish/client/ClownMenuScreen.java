package clownish.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class ClownMenuScreen extends Screen {

    private static boolean freeCam = false;
    private static boolean chestEsp = false;
    private static boolean chestTracers = false;

    private static boolean eventsRegistered = false;

    public ClownMenuScreen() {
        super(Text.literal("ClownClient"));

        registerModuleEvents();
    }

    private static void registerModuleEvents() {

        if (eventsRegistered) {
            return;
        }

        eventsRegistered = true;

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (!freeCam) {
                return;
            }

            // Free Cam will be added next.
        });

        WorldRenderEvents.AFTER_ENTITIES.register(context -> {

            if (!chestEsp && !chestTracers) {
                return;
            }

            MinecraftClient client = MinecraftClient.getInstance();

            if (client.world == null || client.player == null) {
                return;
            }

            MatrixStack matrices = context.matrixStack();

            if (matrices == null || context.consumers() == null) {
                return;
            }

            Vec3d camera = context.camera().getPos();

            matrices.push();

            matrices.translate(
                -camera.x,
                -camera.y,
                -camera.z
            );

            VertexConsumer lines =
                context.consumers()
                    .getBuffer(RenderLayer.getLines());

            for (BlockEntity entity :
                    client.world.blockEntities.values()) {

                if (!isChest(entity)) {
                    continue;
                }

                BlockPos pos = entity.getPos();

                double distance =
                    pos.getSquaredDistance(client.player.getPos());

                if (distance > 64 * 64) {
                    continue;
                }

                Box box = new Box(pos);

                if (chestEsp) {

                    WorldRenderer.drawBox(
                        matrices,
                        lines,
                        box,
                        1.0F,
                        0.2F,
                        1.0F,
                        1.0F
                    );
                }

                if (chestTracers) {

                    Vec3d target =
                        Vec3d.ofCenter(pos);

                    WorldRenderer.drawBox(
                        matrices,
                        lines,
                        new Box(
                            camera.x,
                            camera.y,
                            camera.z,
                            target.x,
                            target.y,
                            target.z
                        ),
                        1.0F,
                        0.2F,
                        1.0F,
                        1.0F
                    );
                }
            }

            matrices.pop();
        });
    }

    private static boolean isChest(BlockEntity entity) {

        return entity.getCachedState().isOf(Blocks.CHEST)
            || entity.getCachedState().isOf(Blocks.TRAPPED_CHEST);
    }

    private static void toggleFreeCam() {
        freeCam = !freeCam;
    }

    private static void toggleChestEsp() {
        chestEsp = !chestEsp;
    }

    private static void toggleChestTracers() {
        chestTracers = !chestTracers;
    }

    @Override
    protected void init() {

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addDrawableChild(
            ButtonWidget.builder(
                Text.literal(
                    "Free Cam: " +
                    (freeCam ? "ON" : "OFF")
                ),
                button -> {

                    toggleFreeCam();

                    button.setMessage(
                        Text.literal(
                            "Free Cam: " +
                            (freeCam ? "ON" : "OFF")
                        )
                    );
                }
            ).dimensions(
                centerX - 100,
                centerY - 20,
                200,
                25
            ).build()
        );

        this.addDrawableChild(
            ButtonWidget.builder(
                Text.literal(
                    "Chest ESP: " +
                    (chestEsp ? "ON" : "OFF")
                ),
                button -> {

                    toggleChestEsp();

                    button.setMessage(
                        Text.literal(
                            "Chest ESP: " +
                            (chestEsp ? "ON" : "OFF")
                        )
                    );
                }
            ).dimensions(
                centerX - 100,
                centerY + 10,
                200,
                25
            ).build()
        );

        this.addDrawableChild(
            ButtonWidget.builder(
                Text.literal(
                    "Chest Tracers: " +
                    (chestTracers ? "ON" : "OFF")
                ),
                button -> {

                    toggleChestTracers();

                    button.setMessage(
                        Text.literal(
                            "Chest Tracers: " +
                            (chestTracers ? "ON" : "OFF")
                        )
                    );
                }
            ).dimensions(
                centerX - 100,
                centerY + 40,
                200,
                25
            ).build()
        );
    }

    @Override
    public void render(
        DrawContext context,
        int mouseX,
        int mouseY,
        float delta
    ) {

        context.fill(
            0,
            0,
            this.width,
            this.height,
            0xFF101018
        );

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        context.fill(
            centerX - 130,
            centerY - 100,
            centerX + 130,
            centerY + 100,
            0xE8202030
        );

        context.drawCenteredTextWithShadow(
            this.textRenderer,
            Text.literal("ClownClient"),
            centerX,
            centerY - 80,
            0xFFFF55FF
        );

        context.drawCenteredTextWithShadow(
            this.textRenderer,
            Text.literal("Modules"),
            centerX,
            centerY - 55,
            0xFFFFFFFF
        );

        super.render(
            context,
            mouseX,
            mouseY,
            delta
        );
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
                        }
