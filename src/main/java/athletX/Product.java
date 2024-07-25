package athletX;

import java.net.URL;

/**
 * Holds information about the product name, images, and stock
 */
public class Product {

    public enum ProductCategory {
        SHOES( Shoes.class), APPAREL( Apparel.class), ACCESSORIES( Accessories.class);



        private Class<? extends Product> clazz;
        ProductCategory(Class<? extends Product> clazz)
        {
            this.clazz = clazz;
        }

        public Class<? extends Product> getClazz() {
            return clazz;
        }
    }

    private String name;
    private int prodId, stock;
    private double price;
    private final ProductCategory category;
    private final String[] supImageDir = new String[4];

    /**
     * Product constructor to make a new product object
     * @param name Product name
     * @param prodId Product ID
     * @param stock Product Stock
     * @param price Product price
     * @param category Product category
     * @param imageDir Product Image Directory
     */
    public Product(String name, int prodId, int stock, double price, ProductCategory category, String imageDir) {
        this.name = name;
        this.prodId = prodId;
        this.stock = stock;
        this.price = price;
        this.category = category;
        this.supImageDir[0] = "AppFiles\\images\\" + imageDir;
    }

    /**
     * This method return product name
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * This method return product image directory
     * @return product image dir
     */
    public String getImageDir() {
        return supImageDir[0];
    }

    /**
     * This method return the product image directory array which consists of 4 images max
     * @return array string where image dir is stored
     */
    public String[] getSupImageDir() {
        return supImageDir;
    }

    /**
     * This method set product name
     * @param name product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method return the product it
     * @return product id
     */
    public int getprodId() {
        return prodId;
    }

    /**
     * This method set new product id to the old product id
     * @param prodId product id
     */
    public void setprodId(int prodId) {
        this.prodId = prodId;
    }

    /**
     * This method return the product stock
     * @return product stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * This method set the new product stock
     * @param stock new stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * This method reduce the stock by 1
     */
    public void reduceStock() {
        stock--;
    }

    /**
     * This method return the product price
     * @return product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method set the product price
     * @param price price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * This method return the category of the product
     * @return product category
     */
    public ProductCategory getProductCategory() {
        return category;
    }

}