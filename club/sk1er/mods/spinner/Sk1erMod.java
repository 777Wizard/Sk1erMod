/*     */ package club.sk1er.mods.spinner;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.io.InputStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.network.FMLNetworkEvent;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Sk1erMod
/*     */ {
/*     */   private static Sk1erMod instance;
/*  29 */   private List<String> updateMessage = new ArrayList<>();
/*     */   private String modid;
/*     */   private String version;
/*     */   private boolean enabled = true;
/*     */   private boolean hasUpdate = false;
/*     */   private String name;
/*     */   private String apIKey;
/*     */   private String prefix;
/*     */   
/*     */   public Sk1erMod(String modid, String version, String name) {
/*  39 */     this.modid = modid;
/*  40 */     this.version = version;
/*  41 */     this.name = name;
/*  42 */     instance = this;
/*  43 */     this.prefix = EnumChatFormatting.RED + "[" + EnumChatFormatting.AQUA + "" + this.name + EnumChatFormatting.RED + "]" + EnumChatFormatting.YELLOW + ": ";
/*     */   }
/*     */   
/*     */   public static Sk1erMod getInstance() {
/*  47 */     return instance;
/*     */   }
/*     */   
/*     */   public boolean hasUpdate() {
/*  51 */     return this.hasUpdate;
/*     */   }
/*     */   
/*     */   public boolean isEnabled() {
/*  55 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public List<String> getUpdateMessage() {
/*  59 */     return this.updateMessage;
/*     */   }
/*     */   
/*     */   public String getApIKey() {
/*  63 */     return this.apIKey;
/*     */   }
/*     */   
/*     */   public void sendMessage(String message) {
/*  67 */     EntityPlayerSP thePlayer = (Minecraft.func_71410_x()).field_71439_g;
/*  68 */     if (thePlayer != null)
/*  69 */       thePlayer.func_146105_b((IChatComponent)new ChatComponentText(this.prefix + message)); 
/*     */   }
/*     */   
/*     */   public JsonObject getPlayer(String name) {
/*  73 */     return (new JsonParser()).parse(rawWithAgent("http://sk1er.club/data/" + name + "/" + getApIKey())).getAsJsonObject();
/*     */   }
/*     */   
/*     */   public void checkStatus() {
/*  77 */     Multithreading.runAsync(() -> {
/*     */           JsonObject en = (new JsonParser()).parse(rawWithAgent("http://sk1er.club/genkey?name=" + Minecraft.func_71410_x().func_110432_I().func_148256_e().getName() + "&uuid=" + Minecraft.func_71410_x().func_110432_I().func_148255_b().replace("-", "") + "&mcver=" + Minecraft.func_71410_x().func_175600_c() + "&modver=" + this.version + "&mod=" + this.modid)).getAsJsonObject();
/*     */           this.updateMessage.clear();
/*     */           this.enabled = en.get("enabled").getAsBoolean();
/*     */           this.hasUpdate = en.get("update").getAsBoolean();
/*     */           this.apIKey = en.get("key").getAsString();
/*     */           if (this.hasUpdate) {
/*     */             this.updateMessage.add(this.prefix + "----------------------------------");
/*     */             this.updateMessage.add(this.prefix + " ");
/*     */             this.updateMessage.add(this.prefix + "            " + this.name + " is out of date!");
/*     */             this.updateMessage.add(this.prefix + "Update level: " + en.get("level").getAsString());
/*     */             this.updateMessage.add(this.prefix + "Update URL: " + en.get("url").getAsString());
/*     */             this.updateMessage.add(this.prefix + "Message from Sk1er: ");
/*     */             this.updateMessage.add(this.prefix + "----------------------------------");
/*     */             this.updateMessage.add(this.prefix + "  ");
/*     */             this.updateMessage.add(this.prefix + "----------------------------------");
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onLoin(FMLNetworkEvent.ClientConnectedToServerEvent event) {
/* 104 */     if (hasUpdate()) {
/* 105 */       Multithreading.runAsync(() -> {
/*     */             try {
/*     */               Thread.sleep(3000L);
/* 108 */             } catch (InterruptedException e) {
/*     */               e.printStackTrace();
/*     */             } 
/*     */             while ((Minecraft.func_71410_x()).field_71439_g == null) {
/*     */               try {
/*     */                 Thread.sleep(100L);
/* 114 */               } catch (InterruptedException e) {
/*     */                 e.printStackTrace();
/*     */               } 
/*     */             } 
/*     */             for (String s : getUpdateMessage()) {
/*     */               (Minecraft.func_71410_x()).field_71439_g.func_146105_b((IChatComponent)new ChatComponentText(s));
/*     */             }
/*     */           });
/*     */     }
/*     */   }
/*     */   
/*     */   public String rawWithAgent(String url) {
/*     */     try {
/* 127 */       URL u = new URL(url);
/* 128 */       HttpURLConnection connection = (HttpURLConnection)u.openConnection();
/* 129 */       connection.setRequestMethod("GET");
/* 130 */       connection.setUseCaches(true);
/* 131 */       connection.addRequestProperty("User-Agent", "Mozilla/4.76 (" + this.modid + " V" + this.version + ")");
/* 132 */       connection.setReadTimeout(15000);
/* 133 */       connection.setConnectTimeout(15000);
/* 134 */       connection.setDoOutput(true);
/* 135 */       InputStream is = connection.getInputStream();
/* 136 */       Charset encoding = Charset.defaultCharset();
/* 137 */       String s = IOUtils.toString(is, encoding);
/* 138 */       if (s != null) {
/* 139 */         return s;
/*     */       }
/* 141 */     } catch (Exception e) {
/* 142 */       e.printStackTrace();
/*     */     } 
/* 144 */     JsonObject object = new JsonObject();
/* 145 */     object.addProperty("success", Boolean.valueOf(false));
/* 146 */     object.addProperty("cause", "Exception");
/* 147 */     return object.toString();
/*     */   }
/*     */ }


/* Location:              D:\Sk1er Chroma Spinner Mod (1.8.9)-1.0 (1).jar!\club\sk1er\mods\spinner\Sk1erMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */