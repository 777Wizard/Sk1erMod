/*    */ package pw.cinque.spinnermod.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class ClickCounter
/*    */ {
/* 10 */   private List<Long> clicks = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void addClick() {
/* 14 */     this.clicks.add(Long.valueOf(System.currentTimeMillis() + 1000L));
/*    */   }
/*    */   
/*    */   public int getCPS() {
/* 18 */     Iterator<Long> iterator = this.clicks.iterator();
/* 19 */     while (iterator.hasNext()) {
/* 20 */       if (((Long)iterator.next()).longValue() < System.currentTimeMillis()) {
/* 21 */         iterator.remove();
/*    */       }
/*    */     } 
/* 24 */     return this.clicks.size();
/*    */   }
/*    */ }


/* Location:              D:\Sk1er Chroma Spinner Mod (1.8.9)-1.0 (1).jar!\pw\cinque\spinnermo\\util\ClickCounter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */