/*    */ package club.sk1er.mods.spinner;
/*    */ 
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.Executors;
/*    */ import java.util.concurrent.ScheduledExecutorService;
/*    */ import java.util.concurrent.ThreadFactory;
/*    */ import java.util.concurrent.ThreadPoolExecutor;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import java.util.concurrent.atomic.AtomicInteger;
/*    */ 
/*    */ 
/*    */ public class Multithreading
/*    */ {
/* 14 */   public static ExecutorService POOL = Executors.newFixedThreadPool(100, new ThreadFactory() {
/* 15 */         AtomicInteger counter = new AtomicInteger(0);
/*    */         
/*    */         public Thread newThread(Runnable r) {
/* 18 */           return new Thread(r, String.format("Thread %s", new Object[] { Integer.valueOf(this.counter.incrementAndGet()) }));
/*    */         }
/*    */       });
/*    */   
/* 22 */   private static ScheduledExecutorService RUNNABLE_POOL = Executors.newScheduledThreadPool(3, new ThreadFactory() {
/* 23 */         private AtomicInteger counter = new AtomicInteger(0);
/*    */         
/*    */         public Thread newThread(Runnable r) {
/* 26 */           return new Thread(r, String.format("Thread " + this.counter.incrementAndGet(), new Object[0]));
/*    */         }
/*    */       });
/*    */   
/*    */   public static void schedule(Runnable r, long initialDelay, long delay, TimeUnit unit) {
/* 31 */     RUNNABLE_POOL.scheduleAtFixedRate(r, initialDelay, delay, unit);
/*    */   }
/*    */   
/*    */   public static void runAsync(Runnable runnable) {
/* 35 */     POOL.execute(runnable);
/*    */   }
/*    */   
/*    */   public static int getTotal() {
/* 39 */     ThreadPoolExecutor tpe = (ThreadPoolExecutor)POOL;
/* 40 */     return tpe.getActiveCount();
/*    */   }
/*    */ }


/* Location:              D:\Sk1er Chroma Spinner Mod (1.8.9)-1.0 (1).jar!\club\sk1er\mods\spinner\Multithreading.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */