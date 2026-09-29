package clownish.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ClownMenuScreen extends Screen {

    public ClownMenuScreen() {
        super(Text.literal("ClownClient"));
    }

    @Override
    public void render(
        DrawContext context,
        int mouseX,
        int mouseY,
        float delta
    ) {
        this.renderBackground(context, mouseX, mouseY, delta);

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        context.fill(
            centerX - 110,
            centerY - 70,
            centerX + 110,
            centerY + 70,
            0xE8202030
        );

        context.drawCenteredTextWithShadow(
            this.textRenderer,
            Text.literal("ClownClient"),
            centerX,
            centerY - 45,
            0xFFFF55FF
        );

        context.drawCenteredTextWithShadow(
            this.textRenderer,
            Text.literal("Welcome to ClownClient!"),
            centerX,
            centerY - 10,
            0xFFFFFFFF
        );

        context.drawCenteredTextWithShadow(
            this.textRenderer,
            Text.literal("Press ESC or Right Shift to close"),
            centerX,
            centerY + 35,
            0xFFAAAAAA
        );

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
