/*     */ package pw.cinque.spinnermod.render;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.InputEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import pw.cinque.spinnermod.SpinnerMod;
/*     */ import pw.cinque.spinnermod.util.ClickCounter;
/*     */ import pw.cinque.spinnermod.util.SpinnerColor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SpinnerRenderer
/*     */ {
/*     */   private static final float FRICTION = 0.1F;
/*  28 */   private final Minecraft mc = Minecraft.func_71410_x();
/*  29 */   private final ResourceLocation spinnerTexture = new ResourceLocation("cpsmod", "spinner.png");
/*  30 */   private final ClickCounter clickCounter = new ClickCounter();
/*  31 */   private volatile float angle = 0.0F;
/*  32 */   private volatile float speed = 0.0F;
/*  33 */   private volatile float acceleration = 0.0F;
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onClick(InputEvent.MouseInputEvent event) {
/*  38 */     if (Mouse.getEventButtonState() && Mouse.getEventButton() == this.mc.field_71474_y.field_74312_F.func_151463_i() + 100) {
/*  39 */       this.clickCounter.addClick();
/*  40 */       this.acceleration = Math.min(12.0F, this.acceleration + 2.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderTick(TickEvent.RenderTickEvent event) {
/*  46 */     if (!this.mc.field_71415_G || this.mc.field_71474_y.field_74330_P) {
/*     */       return;
/*     */     }
/*  49 */     drawSpinner();
/*     */   }
/*     */   
/*     */   public void drawSpinner() {
/*  53 */     this.mc.func_110434_K().func_110577_a(this.spinnerTexture);
/*  54 */     ScaledResolution res = new ScaledResolution(this.mc);
/*  55 */     SpinnerColor color = SpinnerMod.getInstance().getColor();
/*  56 */     color.update();
/*  57 */     int x = SpinnerMod.getInstance().getX();
/*  58 */     int y = SpinnerMod.getInstance().getY();
/*  59 */     if (x < 0) {
/*  60 */       SpinnerMod.getInstance().setX(x = 0);
/*  61 */     } else if (x > res.func_78326_a() - 128) {
/*  62 */       SpinnerMod.getInstance().setX(x = res.func_78326_a() - 128);
/*     */     } 
/*  64 */     if (y < 0) {
/*  65 */       SpinnerMod.getInstance().setY(y = 0);
/*  66 */     } else if (y > res.func_78328_b() - 128) {
/*  67 */       SpinnerMod.getInstance().setY(y = res.func_78328_b() - 128);
/*     */     } 
/*  69 */     GL11.glPushMatrix();
/*  70 */     GL11.glTranslatef(x + 64.0F, y + 64.0F, 0.0F);
/*  71 */     GL11.glRotatef(this.angle, 0.0F, 0.0F, 1.0F);
/*  72 */     GL11.glTranslatef(-64.0F, -64.0F, 0.0F);
/*  73 */     GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), 1.0F);
/*  74 */     drawTexturedModalRect(0, 0, 0, 0, 128, 128);
/*  75 */     GL11.glPopMatrix();
/*  76 */     String text = String.valueOf(this.clickCounter.getCPS());
/*  77 */     this.mc.field_71466_p.func_175063_a(text, (x + 64 - this.mc.field_71466_p.func_78256_a(text) / 2), (y + 60), -1);
/*     */   }
/*     */   
/*     */   public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
/*  81 */     float f = 1.0F / width;
/*  82 */     float f2 = 1.0F / height;
/*  83 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  84 */     WorldRenderer worldrenderer = tessellator.func_178180_c();
/*  85 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
/*  86 */     worldrenderer.func_181662_b((x + 0), (y + height), 0.0D).func_181673_a(((textureX + 0) * f), ((textureY + height) * f2)).func_181675_d();
/*  87 */     worldrenderer.func_181662_b((x + width), (y + height), 0.0D).func_181673_a(((textureX + width) * f), ((textureY + height) * f2)).func_181675_d();
/*  88 */     worldrenderer.func_181662_b((x + width), (y + 0), 0.0D).func_181673_a(((textureX + width) * f), ((textureY + 0) * f2)).func_181675_d();
/*  89 */     worldrenderer.func_181662_b((x + 0), (y + 0), 0.0D).func_181673_a(((textureX + 0) * f), ((textureY + 0) * f2)).func_181675_d();
/*  90 */     tessellator.func_78381_a();
/*     */   }
/*     */   
/*     */   public void runUpdateLoop() {
/*     */     while (true) {
/*  95 */       this.acceleration = Math.max(0.0F, this.acceleration - 0.1F);
/*  96 */       this.speed = (2.0F * this.speed + this.acceleration) / 3.0F;
/*  97 */       this.angle += this.speed;
/*     */       try {
/*  99 */         Thread.sleep(10L);
/* 100 */       } catch (InterruptedException e) {
/* 101 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Sk1er Chroma Spinner Mod (1.8.9)-1.0 (1).jar!\pw\cinque\spinnermod\render\SpinnerRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */