package net.dakuro.arkhesys.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.dakuro.arkhesys.ARKHESYS;
import net.dakuro.arkhesys.container.InfuserContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class InfuserScreen extends ContainerScreen<InfuserContainer> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(ARKHESYS.MODID, "textures/gui/container/infuser_gui_test.png");

    public InfuserScreen(InfuserContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);

        int i = 256;
        int j = 114;
        this.imageHeight = 256;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, x, y, partialTicks);
        this.renderTooltip(matrixStack, x, y);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        if(minecraft != null){
            //RenderSystem.color4f(1,1,1,1);
            minecraft.getTextureManager().bind(TEXTURE);
            int posX = (this.width - this.imageWidth) / 2;
            int posY = (this.height - this.imageHeight) / 2;
            this.blit(matrixStack, posX, posY, 0, 0, this.imageWidth, 159);
            this.blit(matrixStack, posX, posY + 159, 0, 159, this.imageWidth, 96);

            //Progress Bar
            int k = this.menu.getProgressScale();
            this.blit(matrixStack, posX + 60, posY + 61 + 55 - k, 180, 55 - k, 55, k);

            //Fluid Bar
            int j = this.menu.getStockScale();
            this.blit(matrixStack, posX + 158, posY + 32 + 112 - j, 176, 112 - j, 4, j + 1);
        }
    }
}
