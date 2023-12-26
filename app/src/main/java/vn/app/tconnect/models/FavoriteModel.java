package vn.app.tconnect.models;

public class FavoriteModel {

    int productPrice;
    String productName;
    String productImage;
    String documentId;

    public FavoriteModel(int productPrice, String productName, String productImage) {
        this.productPrice = productPrice;
        this.productName = productName;
        this.productImage = productImage;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public FavoriteModel() {
    }
}
