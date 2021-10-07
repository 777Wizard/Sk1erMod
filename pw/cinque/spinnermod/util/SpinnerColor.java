/*    */ package pw.cinque.spinnermod.util;
/*    */ 
/*    */ import java.awt.Color;
/*    */ 
/*    */ public class SpinnerColor {
/*  6 */   private static int[] COLORS = new int[] { 16777215, 16711680, 65280, 255, 16776960, 11141290 };
/*    */   private float red;
/*    */   private float green;
/*    */   private float blue;
/* 10 */   private int index = 6;
/*    */   
/*    */   public SpinnerColor() {
/* 13 */     this.red = 1.0F;
/* 14 */     this.green = 1.0F;
/* 15 */     this.blue = 1.0F;
/*    */   }
/*    */   
/*    */   public SpinnerColor(float red, float green, float blue) {
/* 19 */     this.red = 1.0F;
/* 20 */     this.green = 1.0F;
/* 21 */     this.blue = 1.0F;
/* 22 */     this.red = red;
/* 23 */     this.green = green;
/* 24 */     this.blue = blue;
/*    */   }
/*    */   
/*    */   public void update() {
/*    */     Color color;
/* 29 */     if (this.index == 6) {
/* 30 */       color = Color.getHSBColor((float)(System.currentTimeMillis() % 1000L) / 1000.0F, 0.8F, 0.8F);
/*    */     } else {
/* 32 */       String colorStr = String.format("#%06X", new Object[] { Integer.valueOf(0xFFFFFF & COLORS[this.index]) });
/*    */ 
/*    */ 
/*    */       
/* 36 */       color = new Color(Integer.valueOf(colorStr.substring(1, 3), 16).intValue(), Integer.valueOf(colorStr.substring(3, 5), 16).intValue(), Integer.valueOf(colorStr.substring(5, 7), 16).intValue());
/*    */     } 
/* 38 */     this.red = color.getRed() / 255.0F;
/* 39 */     this.green = color.getGreen() / 255.0F;
/* 40 */     this.blue = color.getBlue() / 255.0F;
/*    */   }
/*    */   
/*    */   public int getIndex() {
/* 44 */     return this.index;
/*    */   }
/*    */   
/*    */   public void setIndex(int index) {
/* 48 */     this.index = index;
/*    */   }
/*    */   
/*    */   public void next() {
/* 52 */     this.index++;
/* 53 */     if (this.index == 7)
/* 54 */       this.index = 0; 
/*    */   }
/*    */   
/*    */   public float getRed() {
/* 58 */     return this.red;
/*    */   }
/*    */   
/*    */   public void setRed(float red) {
/* 62 */     this.red = red;
/*    */   }
/*    */   
/*    */   public float getGreen() {
/* 66 */     return this.green;
/*    */   }
/*    */   
/*    */   public void setGreen(float green) {
/* 70 */     this.green = green;
/*    */   }
/*    */   
/*    */   public float getBlue() {
/* 74 */     return this.blue;
/*    */   }
/*    */   
/*    */   public void setBlue(float blue) {
/* 78 */     this.blue = blue;
/*    */   }
/*    */ }


/* Location:              D:\Sk1er Chroma Spinner Mod (1.8.9)-1.0 (1).jar!\pw\cinque\spinnermo\\util\SpinnerColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */