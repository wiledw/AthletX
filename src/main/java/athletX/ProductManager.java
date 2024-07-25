package athletX;


import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.regex.Pattern;

/**
 * Product Manager handles product creation, deletion, sorting, and serialization.
 */
public class ProductManager {

    public static final String FILE_DIR = "AppFiles/products";
    private static final Pattern validFiles = Pattern.compile("(SHOES|ACCESSORIES|APPAREL)-(\\d){8}.json");
    File directory;
    private static final Gson serializer = new GsonBuilder().setPrettyPrinting().create();
    public enum Filters {PRICE, NAME, CATEGORY}
    private static final Map<Integer, Product> products = new HashMap<>();
    private static  int productIdIndex = 19000000;

    /**
     * creates a file for images to be stored
     * if not already present
     * @throws IOException
     * if file DNE
     */
    public ProductManager() throws IOException {
        directory = new File(FILE_DIR);
        if(!directory.exists())
        {
            directory.mkdirs();
        }


        registerFromFiles();



    }

    /**
     * Uses product category to select which product to create,
     * adds to list of all products in store
     * @param c
     * Category of product
     * @param name
     * Product name
     * @param stock
     * Product stock
     * @param price
     * Product price
     * @param size
     * Product size, null if N/A
     * @param colour
     * Product colour, null if N/A
     * @param gender
     * Product gender, null if N/A
     * @param subCategory
     * Product subCategory, null if N/A
     * @param imageDir
     * Product image directory for saving thumbnails
     * @return
     * Returns a new product with these specifications
     */
    public static Product createProduct(Product.ProductCategory c, String name, int stock, double price, String size, String colour, String gender, String subCategory, String imageDir) {
        Product p;
        switch(c) {
            case APPAREL:
                p = new Apparel(name, productIdIndex, stock, price, size, colour, gender, Apparel.ApparelCategory.valueOf(subCategory), imageDir);
                break;
            case SHOES:
                p = new Shoes(name, productIdIndex, stock, price, gender, size, colour, imageDir);
                break;
            default:
                p = new Accessories(name, productIdIndex, stock, price, Accessories.AccessoryCategory.valueOf(subCategory), imageDir);
                break;
        }




        products.put(productIdIndex++, p);



        return p;
    }

    /**
     * creates a json to store newly created product
     * @param p
     * Product to be serialized
     * @throws IOException
     * If file could not be created
     */
    public static void serialize(Product p) throws IOException {
        File f = new File("AppFiles\\products\\" + p.getProductCategory() + "-" + p.getprodId() + ".json");

        f.createNewFile();


        FileWriter fw = new FileWriter(f);
        String ss = serializer.toJson(p);

        fw.write(ss);
        fw.flush();
        fw.close();

    }

    /**
     * Loads in some default items into the store
     * @throws IOException
     * If AppFiles have been tampered with/are missing
     */
     private void registerFromFiles() throws IOException {
        int maxID = 0;

         for (File f : directory.listFiles(((dir, name) -> validFiles.matcher(name).matches()))) {
             String fileName = f.getName();
             FileReader file = new FileReader(f);


             Product p = serializer.fromJson(file, Product.ProductCategory.valueOf(fileName.substring(0, fileName.indexOf('-'))).getClazz());


             file.close();

             productIdIndex = Integer.parseInt(fileName.substring(fileName.indexOf('-') + 1, fileName.indexOf('.')));
             maxID = Math.max(productIdIndex, maxID);
             products.put(productIdIndex, p);
         }

         productIdIndex = ++maxID;
     }

    /**
     *
     * @param productId
     * productID of a product in the system
     * @return
     * specified product
     */
    public static Product getProduct(int productId)
    {
        return products.get(productId);
    }

    /**
     *
     * @param category
     * category to filter products by
     * @param params
     * parameters to filter by
     * @return
     * a list of filtered products
     */
    public static List<Product> getFilteredProduct(Filters category, Object[] params)
    {
        return switch (category)
        {
            case PRICE -> products.values().stream().filter(p -> p.getPrice() >= (int)params[0] && p.getPrice() <= (int)params[1]).toList();
            case NAME -> products.values().stream().filter(product -> product.getName().contains((String)params[0])).toList();
            case CATEGORY -> products.values().stream().filter(product -> product.getProductCategory().equals(params[0])).toList();
        };
    }

    /**
     *
     * @param productId
     * productId of an item to be deleted from the store
     * @return
     * whether file successfully deleted
     * @throws IOException
     * if file DNE
     */
    public static boolean deleteProduct(int productId) throws IOException {
        if(products.containsKey(productId)) {
            Product.ProductCategory c = products.remove(productId).getProductCategory();
            String fileName = FILE_DIR + "/" + c + "-" + productId + ".json";

            return Files.deleteIfExists(Path.of(fileName));
        }

        return false;

    }

    /**
     * post-increments productIdIndex
     * @return
     * current value of productIdIndex
     */
    public static int genProductID()
    {
        return productIdIndex++;
    }


}
