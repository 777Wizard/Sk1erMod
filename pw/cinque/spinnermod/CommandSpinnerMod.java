/*    */ package pw.cinque.spinnermod;
/*    */ 
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ 
/*    */ public class CommandSpinnerMod extends CommandBase {
/*    */   public String func_71517_b() {
/*  8 */     return "spinnermod";
/*    */   }
/*    */   
/*    */   public String func_71518_a(ICommandSender p_71518_1_) {
/* 12 */     return "/spinnermod";
/*    */   }
/*    */   
/*    */   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) {
/* 16 */     SpinnerMod.getInstance().showGUI();
/*    */   }
/*    */   
/*    */   public int func_82362_a() {
/* 20 */     return 0;
/*    */   }
/*    */   
/*    */   public boolean func_71519_b(ICommandSender p_71519_1_) {
/* 24 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\Sk1er Chroma Spinner Mod (1.8.9)-1.0 (1).jar!\pw\cinque\spinnermod\CommandSpinnerMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */