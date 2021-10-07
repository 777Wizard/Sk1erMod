/*    */ package pw.cinque.spinnermod.render;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import pw.cinque.spinnermod.SpinnerMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpinnerGui
/*    */   extends GuiScreen
/*    */ {
/* 18 */   private final Minecraft mc = Minecraft.func_71410_x();
/*    */   private boolean dragging = false;
/* 20 */   private long lastClick = 0L;
/*    */   private int lastMouseX;
/*    */   
/*    */   public void func_73863_a(int x, int y, float partialTicks) {
/* 24 */     func_73732_a(this.mc.field_71466_p, "SpinnerMod by Fyu", this.field_146294_l / 2, 2, -1);
/* 25 */     func_73732_a(this.mc.field_71466_p, "§eClick + drag §fto move.", this.field_146294_l / 2, 22, -1);
/* 26 */     func_73732_a(this.mc.field_71466_p, "§eDouble-click §fto select a random color.", this.field_146294_l / 2, 32, -1);
/*    */     try {
/* 28 */       func_146269_k();
/*    */     }
/* 30 */     catch (IOException e) {
/* 31 */       e.printStackTrace();
/*    */     } 
/* 33 */     SpinnerMod.getRenderer().drawSpinner();
/*    */   }
/*    */   private int lastMouseY;
/*    */   protected void func_73864_a(int mouseX, int mouseY, int button) {
/* 37 */     if (button != 0) {
/*    */       return;
/*    */     }
/* 40 */     int startX = SpinnerMod.getInstance().getX();
/* 41 */     int startY = SpinnerMod.getInstance().getY();
/* 42 */     int endX = startX + 128;
/* 43 */     int endY = startY + 128;
/* 44 */     if (mouseX >= startX && mouseX <= endX && mouseY >= startY && mouseY <= endY) {
/* 45 */       if (System.currentTimeMillis() < this.lastClick + 250L) {
/* 46 */         SpinnerMod.getInstance().getColor().next();
/* 47 */         this.lastClick = 0L;
/*    */         return;
/*    */       } 
/* 50 */       this.dragging = true;
/* 51 */       this.lastMouseX = mouseX;
/* 52 */       this.lastMouseY = mouseY;
/* 53 */       this.lastClick = System.currentTimeMillis();
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void func_146286_b(int mouseX, int mouseY, int action) {
/* 58 */     this.dragging = false;
/*    */   }
/*    */   
/*    */   protected void func_146273_a(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseClick) {
/* 62 */     if (!this.dragging) {
/*    */       return;
/*    */     }
/* 65 */     SpinnerMod.getInstance().setX(SpinnerMod.getInstance().getX() + mouseX - this.lastMouseX);
/* 66 */     SpinnerMod.getInstance().setY(SpinnerMod.getInstance().getY() + mouseY - this.lastMouseY);
/* 67 */     this.lastMouseX = mouseX;
/* 68 */     this.lastMouseY = mouseY;
/*    */   }
/*    */   
/*    */   public boolean func_73868_f() {
/* 72 */     return false;
/*    */   }
/*    */   
/*    */   public void func_146281_b() {
/* 76 */     SpinnerMod.getInstance().saveSettings();
/*    */   }
/*    */ }


/* Location:              D:\Sk1er Chroma Spinner Mod (1.8.9)-1.0 (1).jar!\pw\cinque\spinnermod\render\SpinnerGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */