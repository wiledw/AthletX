package athletX;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenData {
    private static final String jsonDir = "AppFiles\\products\\";
    public static void main(String[] args) throws IOException {
        Shoes s = new Shoes("black",  ProductManager.genProductID(), 69, 42.0, "male", "6", "black", "black.jfif");
        Gson g = new GsonBuilder().setPrettyPrinting().create();

        List<Product> products = new ArrayList<>();
        /*
        products.add(s);
        products.add( new Apparel("apparell1", ProductManager.genProductID(), 40,68.0, "4", "blue", "male", Apparel.ApparelCategory.JACKETS, "shirt.jpg"));
        products.add( new Accessories("wowo", ProductManager.genProductID(), 30, 420.0, Accessories.AccessoryCategory.BAGS, "xbox.jpg"));
        products.add( new Accessories("Santa's Sock", ProductManager.genProductID(), 12, 21.56, Accessories.AccessoryCategory.SOCKS, "Santa's Sock.jpeg" ));
        products.add( new Shoes("Elf shoes",  ProductManager.genProductID(), 69, 42.0, "male", "6", "black", "elf.jpg"));
        products.add( new Accessories("Herbal Remedy", ProductManager.genProductID(), 14, 420.69, Accessories.AccessoryCategory.SOCKS, "herbal.png"));
        products.add( new Accessories("Muscle Supplements", ProductManager.genProductID(), 2,420.69, Accessories.AccessoryCategory.SOCKS, "supplement.jpg"));
        products.add( new Accessories("Herbal Remedy", ProductManager.genProductID(), 9,420.69, Accessories.AccessoryCategory.SOCKS, "herbal.png"));
        products.add( new Accessories("Herbal Remedy", ProductManager.genProductID(), 14,420.69, Accessories.AccessoryCategory.SOCKS, "herbal.png"));
        products.add( new Accessories("Herbal Remedy", ProductManager.genProductID(), 1,420.69, Accessories.AccessoryCategory.SOCKS, "herbal.png"));
        products.add( new Accessories("Herbal Remedy", ProductManager.genProductID(), 19, 420.69, Accessories.AccessoryCategory.SOCKS, "herbal.png"));
        products.add( new Accessories("Herbal Remedy", ProductManager.genProductID(), 522, 420.69, Accessories.AccessoryCategory.SOCKS, "herbal.png"));
        products.add( new Accessories("Herbal Remedy", ProductManager.genProductID(), 10000, 420.69, Accessories.AccessoryCategory.SOCKS, "herbal.png"));
        products.add( new Accessories("Herbal Remedy", ProductManager.genProductID(), 4, 420.69, Accessories.AccessoryCategory.SOCKS, "herbal.png"));
        products.add( new Accessories("Herbal Remedy", ProductManager.genProductID(), 15, 420.69, Accessories.AccessoryCategory.SOCKS, "herbal.png"));
        products.add( new Shoes("Running Shoes",  ProductManager.genProductID(), 59, 48.0, "male", "8", "blue", "menshoe.png"));
        products.add( new Shoes("Walking Shoes",  ProductManager.genProductID(), 79, 68.0, "male", "8", "white", "mens.png"));
        products.add( new Shoes("Walking Shoes",  ProductManager.genProductID(), 35, 55.0, "female", "8", "pink", "wshoe.png"));
        products.add( new Shoes("Running Shoes",  ProductManager.genProductID(), 45, 65.0, "female", "8", "grey", "shoe7.jpg"));
        products.add( new Apparel("Athletic shirt", ProductManager.genProductID(), 52, 58.0, "4", "black", "male", Apparel.ApparelCategory.JACKETS, "mshirt.png"));
        products.add( new Apparel("Sport jacket", ProductManager.genProductID(), 12, 48.0, "4", "blue", "male", Apparel.ApparelCategory.JACKETS, "jacket.png"));
        products.add( new Apparel("Sport jacket", ProductManager.genProductID(), 13, 38.0, "4", "red", "male", Apparel.ApparelCategory.JACKETS, "rjacket.png"));
        products.add( new Apparel("T-shirt", ProductManager.genProductID(), 89, 28.0, "4", "yellow", "male", Apparel.ApparelCategory.JACKETS, "ytshirt.png"));
        products.add( new Apparel("T-shirt", ProductManager.genProductID(), 24, 58.0, "4", "black", "male", Apparel.ApparelCategory.JACKETS, "btshirt.png")); */
        
        products.add(new Apparel("T-Shirt", ProductManager.genProductID(), 35, 67.0, "5", "black", "male", Apparel.ApparelCategory.SHIRTS, "shirt.jpg"));

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            File f = new File(jsonDir + p.getProductCategory() + "-" + p.getprodId() + ".json");

            f.createNewFile();

            FileWriter fw = new FileWriter(f);
            String ss = g.toJson(p);

            fw.write(ss);
            fw.flush();
            fw.close();

        }

    }
}
