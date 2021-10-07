/*     */ package pw.cinque.spinnermod;
/*     */ 
/*     */ import club.sk1er.mods.spinner.Sk1erMod;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraftforge.client.ClientCommandHandler;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventHandler;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ import pw.cinque.spinnermod.render.SpinnerGui;
/*     */ import pw.cinque.spinnermod.render.SpinnerRenderer;
/*     */ import pw.cinque.spinnermod.util.SpinnerColor;
/*     */ 
/*     */ @Mod(name = "SpinnerMod v2", modid = "cpsmod", version = "1.0", acceptedMinecraftVersions = "[1.8.9]")
/*     */ public class SpinnerMod
/*     */ {
/*     */   private static SpinnerMod instance;
/*  28 */   private int x = 0; private static SpinnerRenderer renderer; private boolean showGui = false;
/*  29 */   private int y = 0;
/*  30 */   private SpinnerColor color = new SpinnerColor();
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void init(FMLInitializationEvent event) {
/*  35 */     instance = this;
/*  36 */     renderer = new SpinnerRenderer();
/*  37 */     FMLCommonHandler.instance().bus().register(this);
/*  38 */     FMLCommonHandler.instance().bus().register(renderer);
/*  39 */     ClientCommandHandler.instance.func_71560_a((ICommand)new CommandSpinnerMod());
/*  40 */     (new Thread(() -> renderer.runUpdateLoop())).start();
/*  41 */     loadSettings();
/*  42 */     (new Sk1erMod("Spinner-Mod", "1.0", "Spinner Mod")).checkStatus();
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onClientTick(TickEvent.ClientTickEvent event) {
/*  47 */     if (this.showGui) {
/*  48 */       Minecraft.func_71410_x().func_147108_a((GuiScreen)new SpinnerGui());
/*  49 */       this.showGui = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static SpinnerMod getInstance() {
/*  54 */     return instance;
/*     */   }
/*     */   
/*     */   public static SpinnerRenderer getRenderer() {
/*  58 */     return renderer;
/*     */   }
/*     */   
/*     */   public void loadSettings() {
/*  62 */     File file = new File("config/spinnermod.dat");
/*  63 */     if (!file.exists()) {
/*     */       return;
/*     */     }
/*  66 */     try (DataInputStream in = new DataInputStream(new FileInputStream(file))) {
/*  67 */       this.x = in.readInt();
/*  68 */       this.y = in.readInt();
/*  69 */       this.color = new SpinnerColor(in.readFloat(), in.readFloat(), in.readFloat());
/*  70 */       this.color.setIndex(in.readInt());
/*     */     }
/*  72 */     catch (IOException e) {
/*  73 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void saveSettings() {
/*  78 */     try (DataOutputStream out = new DataOutputStream(new FileOutputStream("config/spinnermod.dat", false))) {
/*  79 */       out.writeInt(this.x);
/*  80 */       out.writeInt(this.y);
/*  81 */       out.writeFloat(this.color.getRed());
/*  82 */       out.writeFloat(this.color.getGreen());
/*  83 */       out.writeFloat(this.color.getBlue());
/*  84 */       out.writeInt(this.color.getIndex());
/*     */     }
/*  86 */     catch (IOException e) {
/*  87 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void showGUI() {
/*  92 */     this.showGui = true;
/*     */   }
/*     */   
/*     */   public int getX() {
/*  96 */     return this.x;
/*     */   }
/*     */   
/*     */   public void setX(int x) {
/* 100 */     this.x = x;
/*     */   }
/*     */   
/*     */   public int getY() {
/* 104 */     return this.y;
/*     */   }
/*     */   
/*     */   public void setY(int y) {
/* 108 */     this.y = y;
/*     */   }
/*     */   
/*     */   public SpinnerColor getColor() {
/* 112 */     return this.color;
/*     */   }
/*     */ }


/* Location:              D:\Sk1er Chroma Spinner Mod (1.8.9)-1.0 (1).jar!\pw\cinque\spinnermod\SpinnerMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */