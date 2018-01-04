package product.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Currency;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String type;

    private String description;

    private String imageFileName;

    private float defaultPrice;

    private Currency defaultCurrency;

    private boolean reserved;

    private Integer ownerId;

    public Product() {
    }

    public Product(String name,
                   String type,
                   String description,
                   String imageFileName,
                   float defaultPrice,
                   Currency defaultCurrency,
                   int ownerId) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.imageFileName = imageFileName;
        this.defaultPrice = defaultPrice;
        this.defaultCurrency = defaultCurrency;
        this.reserved = false;
        this.ownerId = ownerId;
    }


    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public boolean isReserved() {
        return reserved;
    }

}
