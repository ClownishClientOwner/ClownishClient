package clownish.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ClownMenuScreen extends Screen {

    private boolean freeCam = false;
    private boolean chestEsp = false;
    private boolean chestTracers = false;

    public ClownMenuScreen() {
        super(Text.literal("ClownClient"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // Free Cam
        this.addDrawableChild(
            ButtonWidget.builder(
                Text.literal("Free Cam: OFF"),
                button -> {
                    freeCam = !freeCam;
                    button.setMessage(
                        Text.literal("Free Cam: " + (freeCam ? "ON" : "OFF"))
                    );
                }
            ).dimensions(
                centerX - 100,
                centerY - 20,
                200,
                25
            ).build()
        );

        // Chest ESP
        this.addDrawableChild(
            ButtonWidget.builder(
                Text.literal("Chest ESP: OFF"),
                button -> {
                    chestEsp = !chestEsp;
                    button.setMessage(
                        Text.literal("Chest ESP: " + (chestEsp ? "ON" : "OFF"))
                    );
                }
            ).dimensions(
                centerX - 100,
                centerY + 10,
                200,
                25
            ).build()
        );

        // Chest Tracers
        this.addDrawableChild(
            ButtonWidget.builder(
                Text.literal("Chest Tracers: OFF"),
                button -> {
                    chestTracers = !chestTracers;
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

        // Main panel
        context.fill(
            centerX - 130,
            centerY - 100,
            centerX + 130,
            centerY + 100,
            0xE8202030
        );

        // Title
        context.drawCenteredTextWithShadow(
            this.textRenderer,
            Text.literal("ClownClient"),
            centerX,
            centerY - 80,
            0xFFFF55FF
        );

        // Module heading
        context.drawCenteredTextWithShadow(
            this.textRenderer,
            Text.literal("Modules"),
            centerX,
            centerY - 55,
            0xFFFFFFFF
        );

        // Render buttons
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
